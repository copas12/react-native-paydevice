package com.reactlibrary.PayDeviceSDK.magneticcard;

import com.reactlibrary.PayDeviceSDK.DeviceNative;
import com.reactlibrary.PayDeviceSDK.SmartPosException;

public class MagneticCardManager {
  private static final String TAG = "MagneticCardManager";
  
  private static boolean initFlag = false;
  
  public static final int MCR_ERR_INIT = 1;
  
  public static final int MCR_ERR_DEINIT = 2;
  
  public static final int MCR_ERR_NO_INIT = 3;
  
  public static final int MCR_ERR_ALREADY_INIT = 4;
  
  public static final int MCR_ERR_PARAM = 5;
  
  public static final int MCR_TRACK_ID1 = 1;
  
  public static final int MCR_TRACK_ID2 = 2;
  
  public static final int MCR_TRACK_ID3 = 3;
  
  public static final int MCR_ERR_CODE_PREAMBLE = 81;
  
  public static final int MCR_ERR_CODE_POSTAMBLE = 82;
  
  public static final int MCR_ERR_CODE_LRC = 83;
  
  public static final int MCR_ERR_CODE_PARITY = 84;
  
  public static final int MCR_ERR_CODE_BLANK_TRACK = 85;
  
  public static final int MCR_ERR_CODE_STX_ETX = 97;
  
  public static final int MCR_ERR_CODE_CLASS_FUNCTION = 98;
  
  public static final int MCR_ERR_CODE_BBC = 99;
  
  public static final int MCR_ERR_CODE_LENGTH = 100;
  
  public static final int MCR_ERR_CODE_NO_DATA = 101;
  
  public static final int MCR_ERR_CODE_OTP_WRITE_FULL = 113;
  
  public static final int MCR_ERR_CODE_OTP_WRITE = 114;
  
  public static final int MCR_ERR_CODE_OTP_CRC = 115;
  
  public static final int MCR_ERR_CODE_OTP_EMPTY = 116;
  
  public static final int MCR_SWIPE_CARD_PRESENT = 184;
  
  static {
    try {
      System.loadLibrary("paydevice-smartpos");
    } catch (NoClassDefFoundError localNoClassDefFoundError) {}
  }
  
  public void init() throws SmartPosException {
    if (!initFlag) {
      if (DeviceNative.nativeMcrInit() < 0)
        throw new SmartPosException(1); 
    } else {
      throw new SmartPosException(4);
    } 
    initFlag = true;
  }
  
  public void deinit() {
    DeviceNative.nativeMcrDeInit();
    initFlag = false;
  }
  
  public int query() throws SmartPosException {
    if (initFlag)
      return DeviceNative.nativeMcrQuery(); 
    throw new SmartPosException(3);
  }
  
  public byte[] getTrack(int trackId) throws SmartPosException {
    if (initFlag) {
      if (trackId > 0 && trackId < 4)
        return DeviceNative.nativeMcrGetTrack(trackId); 
      throw new SmartPosException(5);
    } 
    throw new SmartPosException(3);
  }
}
