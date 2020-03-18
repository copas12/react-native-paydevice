package com.reactlibrary.PayDeviceSDK.fingerprint;

import android.util.Log;
import com.reactlibrary.PayDeviceSDK.DeviceNative;
import com.reactlibrary.PayDeviceSDK.SmartPosException;

public class FingerPrintManager {
  private static final String TAG = "FingerPrintManager";
  
  private static boolean initFlag = false;
  
  public static final int FINGERPRINT_ERR_NO_INIT = 1;
  
  public static final int FINGERPRINT_ERR_INIT = 2;
  
  public static final int FINGERPRINT_ERR_DEINIT = 3;
  
  public static final int FINGERPRINT_ERR_ALREADY_INIT = 4;
  
  public static final int FINGERPRINT_ERR_NO_FINGER = 7;
  
  public static final int FINGERPRINT_ERR_IMAGE_UNCLEAR = 8;
  
  public static final int FINGERPRINT_ERR_IO = 99;
  
  public static final int FINGERPRINT_FINGER_PRESENT = 183;
  
  static {
    try {
      System.loadLibrary("paydevice-smartpos");
    } catch (NoClassDefFoundError localNoClassDefFoundError) {}
  }
  
  public void init() throws SmartPosException {
    if (!initFlag) {
      int ret = DeviceNative.nativeFingerPrintInit();
      if (ret < 0)
        throw new SmartPosException(2); 
    } else {
      throw new SmartPosException(4);
    } 
    initFlag = true;
  }
  
  public void deinit() {
    DeviceNative.nativeFingerPrintDeinit();
    initFlag = false;
  }
  
  public int getBitmapWidth() {
    return DeviceNative.nativeFingerPrintGetImageWidth();
  }
  
  public int getBitmapHeight() {
    return DeviceNative.nativeFingerPrintGetImageHeight();
  }
  
  public byte[] getBitmapBytes() throws SmartPosException {
    if (!initFlag)
      throw new SmartPosException(1); 
    Integer err = new Integer(0);
    byte[] bmpBytes = DeviceNative.nativeFingerPrintGetImage(err);
    switch (err.intValue()) {
      case 1:
        throw new SmartPosException(7);
      case 2:
        throw new SmartPosException(8);
      case 9:
        throw new SmartPosException(99);
    } 
    return bmpBytes;
  }
  
  public byte[] getMinutiae() throws SmartPosException {
    if (!initFlag)
      throw new SmartPosException(1); 
    return null;
  }
  
  public int compare(byte[] probe, byte[] candidate) throws SmartPosException {
    if (!initFlag)
      throw new SmartPosException(1); 
    Log.d("FingerPrintManager", "function compare does not implemented");
    return 0;
  }
}
