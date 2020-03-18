package com.reactlibrary.PayDeviceSDK.printer;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.util.Log;
import com.reactlibrary.PayDeviceSDK.SmartPosException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class UsbPrinter implements Printer {
  private static final String TAG = "UsbPrinter";
  
  private static final String ACTION_USB_PERMISSION = "com.reactlibrary.PayDeviceSDK.printer.USB_PERMISSION";
  
  private static final int BUILTIN_VID = 19267;
  
  private static final int BUILTIN_PID = 14384;
  
  private final Context mContext;
  
  private UsbDevice mUsbDevice;
  
  private UsbManager mUsbManager;
  
  private UsbEndpoint mEpWrite;
  
  private UsbEndpoint mEpRead;
  
  private UsbDeviceConnection mUsbConnection;
  
  static {
    try {
      System.loadLibrary("paydevice-smartpos");
    } catch (NoClassDefFoundError localNoClassDefFoundError) {}
  }
  
  public UsbPrinter(Context context) {
    this.mContext = context;
    this.mUsbManager = (UsbManager)context.getSystemService("usb");
  }
  
  public void selectPrinter(UsbDevice device) {
    this.mUsbDevice = device;
  }
  
  public void selectBuiltInPrinter() {
    HashMap<String, UsbDevice> usbList = this.mUsbManager.getDeviceList();
    if (usbList != null) {
      Iterator<UsbDevice> iterator = usbList.values().iterator();
      while (iterator.hasNext()) {
        UsbDevice device = iterator.next();
        UsbInterface usbInterface = device.getInterface(0);
        if (usbInterface != null)
          if (usbInterface.getInterfaceClass() == 7 && device.getVendorId() == 19267 && device.getProductId() == 14384)
            this.mUsbDevice = device;  
      } 
    } 
  }
  
  public void open() throws SmartPosException {
    if (this.mUsbDevice == null) {
      Log.e("UsbPrinter", "UsbDevice is null");
      throw new SmartPosException(1);
    } 
    if (!this.mUsbManager.hasPermission(this.mUsbDevice)) {
      PendingIntent pi = PendingIntent.getBroadcast(this.mContext, 0, new Intent("com.reactlibrary.PayDeviceSDK.printer.USB_PERMISSION"), 0);
      this.mUsbManager.requestPermission(this.mUsbDevice, pi);
    } 
    UsbInterface usbIf = this.mUsbDevice.getInterface(0);
    this.mEpWrite = usbIf.getEndpoint(1);
    this.mEpRead = usbIf.getEndpoint(0);
    this.mUsbConnection = this.mUsbManager.openDevice(this.mUsbDevice);
    if (this.mUsbConnection != null) {
      if (!this.mUsbConnection.claimInterface(usbIf, true)) {
        Log.e("UsbPrinter", "claimInterface fail");
        throw new SmartPosException(1);
      } 
    } else {
      Log.e("UsbPrinter", "open fail");
      throw new SmartPosException(1);
    } 
  }
  
  public void close() throws SmartPosException {
    if (this.mUsbConnection != null) {
      this.mUsbConnection.close();
      this.mUsbConnection = null;
    } else {
      throw new SmartPosException(2);
    } 
  }
  
  public int read(byte[] buf, int len) throws SmartPosException {
    return read(buf, len, 1000);
  }
  
  public void write(byte[] buf, int len) throws SmartPosException {
    write(buf, buf.length, 1000);
  }
  
  public int getType() {
    return 1;
  }
  
  public int read(byte[] buf, int len, int timeout) throws SmartPosException {
    int ret = 0;
    if (buf != null && this.mUsbConnection != null && this.mEpRead != null) {
      if ((ret = this.mUsbConnection.bulkTransfer(this.mEpRead, buf, len, timeout)) < 0)
        throw new SmartPosException(3); 
    } else {
      throw new SmartPosException(3);
    } 
    return ret;
  }
  
  public void write(byte[] buf, int len, int timeout) throws SmartPosException {
    if (this.mUsbConnection != null && this.mEpWrite != null) {
      if (this.mUsbConnection.bulkTransfer(this.mEpWrite, buf, len, timeout) < 0)
        throw new SmartPosException(4); 
    } else {
      throw new SmartPosException(4);
    } 
  }
  
  public List<UsbDevice> getPrinterList() {
    HashMap<String, UsbDevice> usbList = this.mUsbManager.getDeviceList();
    List<UsbDevice> result = new ArrayList<UsbDevice>();
    if (usbList != null) {
      Iterator<UsbDevice> iterator = usbList.values().iterator();
      while (iterator.hasNext()) {
        UsbDevice device = iterator.next();
        UsbInterface usbInterface = device.getInterface(0);
        if (usbInterface != null)
          if (usbInterface.getInterfaceClass() == 7)
            result.add(device);  
      } 
    } 
    return result;
  }
}
