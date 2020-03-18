package com.reactlibrary.PayDeviceSDK;

public class SmartPosException extends Exception {
  private static final long serialVersionUID = 1L;
  
  private int mErrorCode = 255;
  
  public SmartPosException() {}
  
  public SmartPosException(String detailMessage) {
    super(detailMessage);
  }
  
  public SmartPosException(Throwable throwable) {
    super(throwable);
  }
  
  public SmartPosException(String detailMessage, Throwable throwable) {
    super(detailMessage, throwable);
  }
  
  public SmartPosException(int errCode) {
    this.mErrorCode = errCode;
  }
  
  public int getErrorCode() {
    return this.mErrorCode;
  }
}
