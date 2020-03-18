package com.reactlibrary.PayDeviceSDK.printer;

import com.reactlibrary.PayDeviceSDK.SmartPosException;

public interface Printer {
  void open() throws SmartPosException;
  
  void close() throws SmartPosException;
  
  int read(byte[] paramArrayOfbyte, int paramInt) throws SmartPosException;
  
  void write(byte[] paramArrayOfbyte, int paramInt) throws SmartPosException;
  
  void selectBuiltInPrinter();
  
  int getType();
}
