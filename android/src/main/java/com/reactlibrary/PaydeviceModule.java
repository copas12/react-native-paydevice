package com.reactlibrary;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
// SDK PayDevice
import com.reactlibrary.PayDeviceSDK.SmartPosException;
import com.reactlibrary.PayDeviceSDK.printer.PrinterManager;
import com.reactlibrary.PayDeviceSDK.printer.Printer;
import com.reactlibrary.PayDeviceSDK.printer.UsbPrinter;
import com.reactlibrary.PayDeviceSDK.printer.SerialPortPrinter;
import com.reactlibrary.PayDeviceSDK.cashdrawer.CashDrawer;

import com.reactlibrary.printer.PosSalesSlip;

public class PaydeviceModule extends ReactContextBaseJavaModule {

    private static String TAG = "PayDevice";

    private final ReactApplicationContext reactContext;

    
    public PaydeviceModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "Paydevice";
    }

    @ReactMethod
    public void sampleMethod(String stringArgument, int numberArgument, Callback callback) {
        // TODO: Implement some actually useful functionality
        callback.invoke("Received numberArgument: " + numberArgument + " stringArgument: " + stringArgument);
    }

   
}
