package com.reactlibrary.PayDeviceSDK.samcard;

import com.reactlibrary.PayDeviceSDK.DeviceNative;
import com.reactlibrary.PayDeviceSDK.SmartPosException;

public class SamCardManager {
  private static final String TAG = "SamCardManager";
  
  private static boolean initFlag = false;
  
  private static final int SAM_CMD_RESET = 55;
  
  private static final int SAM_CMD_SEND = 56;
  
  public static final int SAM_ERR_INIT = 1;
  
  public static final int SAM_ERR_DEINIT = 2;
  
  public static final int SAM_ERR_NO_INIT = 3;
  
  public static final int SAM_ERR_ALREADY_INIT = 4;
  
  public static final int SAM_ERR_PARAM = 5;
  
  public static final int SAM_ERR_CODE_TRANSMIT_ERROR = 16;
  
  public static final int SAM_ERR_CODE_NO_CARD = 17;
  
  public static final int SAMCARD_SLOT_1 = 0;
  
  public static final int SAMCARD_SLOT_2 = 1;
  
  public static final int SAMCARD_SLOT_3 = 2;
  
  public static final int SAMCARD_SLOT_4 = 3;
  
  public static final int SAMCARD_SLOT_5 = 4;
  
  public static final int SAMCARD_9600BPS = 0;
  
  public static final int SAMCARD_38400BPS = 1;
  
  static {
    try {
      System.loadLibrary("paydevice-smartpos");
    } catch (NoClassDefFoundError localNoClassDefFoundError) {}
  }
  
  public void init() throws SmartPosException {
    if (!initFlag) {
      if (DeviceNative.nativeSamInit() < 0)
        throw new SmartPosException(1); 
    } else {
      throw new SmartPosException(4);
    } 
    initFlag = true;
  }
  
  public void deinit() {
    DeviceNative.nativeSamDeinit();
    initFlag = false;
  }
  
  public void selectSlot(int slot, int baudrate, boolean reset) throws SmartPosException {
    if (!initFlag)
      throw new SmartPosException(3); 
    if (slot < 0 || slot > 4)
      throw new SmartPosException(5); 
    if (baudrate != 0 && baudrate != 1)
      throw new SmartPosException(5); 
    byte[] ack = null;
    byte[] cmd = new byte[4];
    cmd[0] = 3;
    cmd[1] = 55;
    cmd[2] = (byte)(cmd[2] | slot << 4 | baudrate);
    cmd[3] = bcc(cmd, 0, 3);
    if (!reset)
      cmd[2] = (byte)(cmd[2] | 0x4); 
    ack = DeviceNative.nativeSamTransmit(cmd, cmd.length);
    if (ack == null)
      throw new SmartPosException(16); 
    if (ack[1] != 55)
      throw new SmartPosException(17); 
  }
  
  public byte[] transmit(int slot, byte[] cmd) throws SmartPosException {
    if (!initFlag)
      throw new SmartPosException(3); 
    if (slot < 0 || slot > 4)
      throw new SmartPosException(5); 
    if (cmd == null)
      throw new SmartPosException(5); 
    byte[] ack = null;
    byte[] response = null;
    byte[] data = new byte[cmd.length + 5];
    data[0] = (byte)(cmd.length + 4);
    data[1] = 56;
    data[2] = (byte)slot;
    System.arraycopy(cmd, 0, data, 3, cmd.length);
    data[cmd.length + 4] = bcc(data, 0, cmd.length + 4);
    ack = DeviceNative.nativeSamTransmit(data, data.length);
    if (ack == null)
      throw new SmartPosException(16); 
    if (ack[1] != 56)
      throw new SmartPosException(17); 
    if (ack[0] - 2 != 0) {
      response = new byte[ack[0] - 2];
      System.arraycopy(ack, 2, response, 0, ack[0] - 2);
    } 
    return response;
  }
  
  private byte bcc(byte[] data, int offset, int count) {
    byte sum = 0;
    for (int i = 0; i < count; i++)
      sum = (byte)(sum ^ data[offset + i]); 
    return sum;
  }
}
