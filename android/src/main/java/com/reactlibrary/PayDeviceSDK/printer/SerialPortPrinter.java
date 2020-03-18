package com.reactlibrary.PayDeviceSDK.printer;

import android.os.Bundle;
import android.util.Log;
import com.reactlibrary.PayDeviceSDK.DeviceNative;
import com.reactlibrary.PayDeviceSDK.SmartPosException;
import com.reactlibrary.PayDeviceSDK.gpio.Gpio;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Iterator;
import java.util.Vector;

public class SerialPortPrinter implements Printer {
  private static final String TAG = "SerialPortPrinter";
  
  private static final String BUILTIN_NODE = "/dev/printer";
  
  private static final int BUILTIN_BAUDRATE = 115200;
  
  private String mPath;
  
  private int mSpeed;
  
  private int mPowerIO;
  
  private FileDescriptor mFd;
  
  static {
    try {
      System.loadLibrary("paydevice-smartpos");
    } catch (NoClassDefFoundError e) {
      e.printStackTrace();
    } 
  }
  
  public void selectPrinter(String path, int speed) {
    this.mPath = path;
    this.mSpeed = speed;
  }
  
  public void selectBuiltInPrinter() {
    this.mPath = "/dev/printer";
    this.mSpeed = 115200;
    this.mPowerIO = Integer.parseInt(System.getProperty("hw.io.printer", "68"));
  }
  
  public void open() throws SmartPosException {
    if (this.mPath == null) {
      Log.d("SerialPortPrinter", "path is null");
      throw new SmartPosException(1);
    } 
    if (this.mPath.equals("/dev/printer")) {
      Gpio.init(this.mPowerIO);
      Gpio.setMode(this.mPowerIO, 1);
      Gpio.setValue(this.mPowerIO, 1);
      try {
        Thread.sleep(100L);
      } catch (InterruptedException ie) {}
    } 
    try {
      if (this.mPath.equals("/dev/printer") && 
        DeviceNative.nativePrinterProbe() < 0) {
        Log.e("SerialPortPrinter", "probe fail");
        throw new SmartPosException(1);
      } 
      this.mFd = DeviceNative.nativeSerialPortOpen(this.mPath, this.mSpeed);
    } catch (Exception e) {
      Log.e("SerialPortPrinter", "open fail");
      throw new SmartPosException(1);
    } 
    if (this.mFd == null) {
      Log.e("SerialPortPrinter", "fd is null");
      throw new SmartPosException(1);
    } 
  }
  
  public void close() throws SmartPosException {
    if (this.mPath.equals("/dev/printer")) {
      Gpio.setValue(this.mPowerIO, 0);
      Gpio.deinit(this.mPowerIO);
    } 
    if (this.mFd == null)
      throw new SmartPosException(3); 
    DeviceNative.nativeSerialPortClose(this.mFd);
  }
  
  public int read(byte[] buf, int len) throws SmartPosException {
    if (this.mFd == null)
      throw new SmartPosException(3); 
    if (buf == null)
      throw new SmartPosException(5); 
    return DeviceNative.nativeSerialPortRead(this.mFd, buf, len);
  }
  
  public void write(byte[] buf, int len) throws SmartPosException {
    if (this.mFd == null)
      throw new SmartPosException(4); 
    if (buf == null)
      throw new SmartPosException(5); 
    if (DeviceNative.nativeSerialPortWrite(this.mFd, buf, len) != len)
      throw new SmartPosException(4); 
  }
  
  public int getType() {
    return 2;
  }
  
  public String[] getPrinterList() {
    Vector<String> devices = new Vector<String>();
    try {
      Iterator<Driver> itdriv = getDrivers().iterator();
      while (itdriv.hasNext()) {
        Driver driver = itdriv.next();
        Iterator<File> itdev = driver.getDevices().iterator();
        while (itdev.hasNext()) {
          String device = ((File)itdev.next()).getAbsolutePath();
          devices.add(device);
        } 
      } 
    } catch (IOException e) {
      e.printStackTrace();
    } 
    return devices.<String>toArray(new String[devices.size()]);
  }
  
  private class Driver {
    private String mDriverName;
    
    private String mDeviceRoot;
    
    Vector<File> mDevices;
    
    public Driver(String name, String root) {
      this.mDevices = null;
      this.mDriverName = name;
      this.mDeviceRoot = root;
    }
    
    public Vector<File> getDevices() {
      if (this.mDevices == null) {
        this.mDevices = new Vector<File>();
        File dev = new File("/dev");
        File[] files = dev.listFiles();
        for (int i = 0; i < files.length; i++) {
          if (files[i].getAbsolutePath().startsWith(this.mDeviceRoot)) {
            Log.d("SerialPortPrinter", "Found new device: " + files[i]);
            if (!files[i].getName().contains("ttyGS") && !files[i].getName().contains("ttyCB") && !files[i].getName().contains("ttyFIQ"))
              this.mDevices.add(files[i]); 
          } 
        } 
      } 
      return this.mDevices;
    }
    
    public String getName() {
      return this.mDriverName;
    }
  }
  
  private Vector<Driver> getDrivers() throws IOException {
    Vector<Driver> mDrivers = new Vector<Driver>();
    LineNumberReader r = new LineNumberReader(new FileReader("/proc/tty/drivers"));
    String l;
    while ((l = r.readLine()) != null) {
      String drivername = l.substring(0, 21).trim();
      String[] w = l.split(" +");
      if (w.length >= 5 && w[w.length - 1].equals("serial")) {
        Log.d("SerialPortPrinter", "Found new driver " + drivername + " on " + w[w.length - 4]);
        mDrivers.add(new Driver(drivername, w[w.length - 4]));
      } 
    } 
    r.close();
    return mDrivers;
  }
}
