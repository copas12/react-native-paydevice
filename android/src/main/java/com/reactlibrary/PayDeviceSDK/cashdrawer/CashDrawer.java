package com.reactlibrary.PayDeviceSDK.cashdrawer;

import android.os.Bundle;
import android.util.Log;
import com.reactlibrary.PayDeviceSDK.gpio.Gpio;
import com.reactlibrary.PayDeviceSDK.serialport.SerialPort;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.security.InvalidParameterException;
import java.util.Timer;
import java.util.TimerTask;

public class CashDrawer {
  private static final String TAG = "CashDrawer";
  
  public static void open() {
    final int cashIO = Integer.parseInt(System.getProperty("hw.io.cashbox", "0"));
    if (cashIO > 0) {
      Gpio.init(cashIO);
      Gpio.setMode(cashIO, 1);
      Gpio.setValue(cashIO, 1);
      TimerTask task = new TimerTask() {
          public void run() {
            Gpio.setValue(cashIO, 0);
          }
        };
      Timer timer = new Timer();
      timer.schedule(task, 1000L);
      Log.d("CashDrawer", "popup cashdrawer");
    } 
  }
  
  public static void openEx() {
    try {
      byte[] cmd = { 27, 112, 0 };
      boolean found = false;
      int i;
      for (i = 0; i < 10; i++) {
        File cb = new File("/dev/ttyCB" + i);
        if (cb.exists()) {
          found = true;
          break;
        } 
      } 
      if (found) {
        SerialPort sp = new SerialPort(new File("/dev/ttyCB" + i), 9600);
        OutputStream mOutputStream = sp.getOutputStream();
        mOutputStream.write((new String(cmd)).getBytes());
        Log.d("CashDrawer", "open Cash Drawer via USB-RJ11 converter");
        sp.close();
      } else {
        Log.d("CashDrawer", "USB-RJ11 converter no found");
      } 
    } catch (NullPointerException e) {
      Log.d("CashDrawer", "USB-RJ11 converter fail:NullPointerException");
    } catch (SecurityException e) {
      Log.d("CashDrawer", "USB-RJ11 converter fail:SecurityException");
    } catch (IOException e) {
      Log.d("CashDrawer", "USB-RJ11 converter fail:IOException");
    } catch (InvalidParameterException e) {
      Log.d("CashDrawer", "USB-RJ11 converter fail:InvalidParameterException");
    } 
  }
}
