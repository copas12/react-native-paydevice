package com.reactlibrary.PayDeviceSDK.serialport;

import android.util.Log;
import com.reactlibrary.PayDeviceSDK.DeviceNative;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SerialPort {
  private static final String TAG = "SerialPort";
  
  private final FileDescriptor mFd;
  
  private final FileInputStream mFileInputStream;
  
  private final FileOutputStream mFileOutputStream;
  
  static {
    try {
      System.loadLibrary("paydevice-smartpos");
    } catch (NoClassDefFoundError localNoClassDefFoundError) {}
  }
  
  public SerialPort(File device, int baudrate) throws SecurityException, IOException {
    this.mFd = DeviceNative.nativeSerialPortOpen(device.getAbsolutePath(), baudrate);
    if (this.mFd == null) {
      Log.e("SerialPort", "native open returns null");
      throw new IOException();
    } 
    if (!device.canRead() || !device.canWrite())
      throw new SecurityException(); 
    this.mFileInputStream = new FileInputStream(this.mFd);
    this.mFileOutputStream = new FileOutputStream(this.mFd);
  }
  
  public InputStream getInputStream() {
    return this.mFileInputStream;
  }
  
  public OutputStream getOutputStream() {
    return this.mFileOutputStream;
  }
  
  public void close() {
    DeviceNative.nativeSerialPortClose(this.mFd);
  }
}
