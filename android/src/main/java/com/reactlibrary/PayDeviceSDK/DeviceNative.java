package com.reactlibrary.PayDeviceSDK;

import android.content.res.AssetManager;
import java.io.FileDescriptor;

public class DeviceNative {
  public static native int nativePrinterFwUpdate(AssetManager paramAssetManager, String paramString);
  
  public static native int nativePrinterProbe();
  
  public static native int nativePrinterGetModel();
  
  public static native int nativeMcrInit();
  
  public static native int nativeMcrDeInit();
  
  public static native int nativeMcrQuery();
  
  public static native byte[] nativeMcrGetTrack(int paramInt);
  
  public static native int nativeFingerPrintInit();
  
  public static native int nativeFingerPrintDeinit();
  
  public static native byte[] nativeFingerPrintGetImage(Integer paramInteger);
  
  public static native int nativeFingerPrintGetImageWidth();
  
  public static native int nativeFingerPrintGetImageHeight();
  
  public static native int nativeSamInit();
  
  public static native int nativeSamDeinit();
  
  public static native byte[] nativeSamTransmit(byte[] paramArrayOfbyte, int paramInt);
  
  public static native FileDescriptor nativeSerialPortOpen(String paramString, int paramInt);
  
  public static native void nativeSerialPortClose(FileDescriptor paramFileDescriptor);
  
  public static native int nativeSerialPortRead(FileDescriptor paramFileDescriptor, byte[] paramArrayOfbyte, int paramInt);
  
  public static native int nativeSerialPortWrite(FileDescriptor paramFileDescriptor, byte[] paramArrayOfbyte, int paramInt);
  
  public static native int nativeGpioInit(int paramInt);
  
  public static native void nativeGpioDeInit(int paramInt);
  
  public static native void nativeGpioSetMode(int paramInt1, int paramInt2);
  
  public static native void nativeGpioSetValue(int paramInt1, int paramInt2);
  
  public static native int nativeGpioGetValue(int paramInt);
  
  public static native int nativeAdcGetValue(int paramInt);
}
