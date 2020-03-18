package com.reactlibrary.PayDeviceSDK.gpio;

import android.os.Build;
import com.reactlibrary.PayDeviceSDK.DeviceNative;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Gpio {
  public static final int LEVEL_LOW = 0;
  
  public static final int LEVEL_HIGH = 1;
  
  public static final int MODE_INPUT = 0;
  
  public static final int MODE_OUTPUT = 1;
  
  private static Method init = null;
  
  private static Method deInit = null;
  
  private static Method setValue = null;
  
  private static Method setDirection = null;
  
  private static Method getValue = null;
  
  private static Object obj = null;
  
  public static void init(int gpio) {
    if (Build.VERSION.SDK_INT > 25) {
      DeviceNative.nativeGpioInit(gpio);
    } else {
      try {
        Class<?> gpioClass;
        if (Build.VERSION.SDK_INT > 22) {
          gpioClass = Class.forName("android.hardware.Gpio");
        } else {
          gpioClass = Class.forName("com.android.Gpio");
        } 
        init = gpioClass.getMethod("init", new Class[] { int.class });
        deInit = gpioClass.getMethod("deInit", new Class[] { int.class });
        setValue = gpioClass.getMethod("setValue", new Class[] { int.class, int.class });
        getValue = gpioClass.getMethod("getValue", new Class[] { int.class });
        setDirection = gpioClass.getMethod("setDirection", new Class[] { int.class, int.class });
        init.invoke(obj, new Object[] { Integer.valueOf(gpio) });
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      } catch (Exception e) {
        e.printStackTrace();
      } 
    } 
  }
  
  public static void setMode(int gpio, int mode) {
    if (Build.VERSION.SDK_INT > 25) {
      DeviceNative.nativeGpioSetMode(gpio, mode);
    } else if (setDirection != null) {
      try {
        setDirection.invoke(obj, new Object[] { Integer.valueOf(gpio), Integer.valueOf(mode) });
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      } catch (IllegalArgumentException e) {
        e.printStackTrace();
      } catch (InvocationTargetException e) {
        e.printStackTrace();
      } 
    } 
  }
  
  public static int getValue(int gpio) {
    if (Build.VERSION.SDK_INT > 25)
      return DeviceNative.nativeGpioGetValue(gpio); 
    if (getValue != null)
      try {
        return ((Integer)getValue.invoke(obj, new Object[] { Integer.valueOf(gpio) })).intValue();
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      } catch (IllegalArgumentException e) {
        e.printStackTrace();
      } catch (InvocationTargetException e) {
        e.printStackTrace();
      }  
    return 0;
  }
  
  public static void setValue(int gpio, int value) {
    if (Build.VERSION.SDK_INT > 25) {
      DeviceNative.nativeGpioSetValue(gpio, value);
    } else if (setValue != null) {
      try {
        setValue.invoke(obj, new Object[] { Integer.valueOf(gpio), Integer.valueOf(value) });
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      } catch (IllegalArgumentException e) {
        e.printStackTrace();
      } catch (InvocationTargetException e) {
        e.printStackTrace();
      } 
    } 
  }
  
  public static void deinit(int gpio) {
    if (Build.VERSION.SDK_INT > 25) {
      DeviceNative.nativeGpioDeInit(gpio);
    } else if (deInit != null) {
      try {
        deInit.invoke(obj, new Object[] { Integer.valueOf(gpio) });
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      } catch (IllegalArgumentException e) {
        e.printStackTrace();
      } catch (InvocationTargetException e) {
        e.printStackTrace();
      } 
    } 
  }
}
