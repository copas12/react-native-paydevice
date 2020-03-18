package com.reactlibrary.PayDeviceSDK.printer;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import com.reactlibrary.PayDeviceSDK.DeviceNative;
import com.reactlibrary.PayDeviceSDK.SmartPosException;
import com.reactlibrary.PayDeviceSDK.gpio.Gpio;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;

public class PrinterManager {
  private static final String TAG = "PrinterManager";
  
  private static final int PRINTER_BUFFER_LEN = 1024;
  
  public static final int PRINTER_ERR_OPEN = 1;
  
  public static final int PRINTER_ERR_CLOSE = 2;
  
  public static final int PRINTER_ERR_READ = 3;
  
  public static final int PRINTER_ERR_WRITE = 4;
  
  public static final int PRINTER_ERR_PARAM = 5;
  
  public static final int PRINTER_ERR_NO_PAPER = 6;
  
  public static final int PRINTER_ERR_UPDATE = 7;
  
  public static final int PRINTER_TYPE_USB = 1;
  
  public static final int PRINTER_TYPE_SERIAL = 2;
  
  public static final int PRINTER_TYPE_NETWORK = 3;
  
  public static final int PRINTER_TYPE_BLUETOOTH = 4;
  
  public static final int PRINTER_MODEL_UNKNOWN = 0;
  
  public static final int PRINTER_MODEL_PRN2103 = 1;
  
  public static final int FULL_CUT = 0;
  
  public static final int HALF_CUT = 1;
  
  public static final int ALIGN_LEFT = 0;
  
  public static final int ALIGN_MIDDLE = 1;
  
  public static final int ALIGN_RIGHT = 2;
  
  public static final int FONT_DEFAULT = 0;
  
  public static final int FONT_SMALL = 1;
  
  public static final int FONT_INVERSE = 2;
  
  public static final int FONT_UPSIDE_DOWN = 4;
  
  public static final int FONT_EMPHASIZED = 8;
  
  public static final int FONT_DOUBLE_HEIGHT = 16;
  
  public static final int FONT_DOUBLE_WIDTH = 32;
  
  public static final int FONT_ROTATE = 64;
  
  public static final int FONT_UNDERLINE = 128;
  
  public static final int UNDERLINE_ZERO = 0;
  
  public static final int UNDERLINE_HIGH_1 = 1;
  
  public static final int UNDERLINE_HIGH_2 = 2;
  
  public static final int CODEBAR_STRING_MODE_NONE = 0;
  
  public static final int CODEBAR_STRING_MODE_ABOVE = 1;
  
  public static final int CODEBAR_STRING_MODE_BELOW = 2;
  
  public static final int CODEBAR_STRING_MODE_BOTH = 3;
  
  public static final int CODEBAR_STRING_FONT_A = 0;
  
  public static final int CODEBAR_STRING_FONT_B = 1;
  
  public static final int UPC_A = 65;
  
  public static final int UPC_E = 66;
  
  public static final int EAN13 = 67;
  
  public static final int EAN8 = 68;
  
  public static final int CODE39 = 69;
  
  public static final int I25 = 70;
  
  public static final int CODEBAR = 71;
  
  public static final int CODE93 = 72;
  
  public static final int CODE128 = 73;
  
  public static final int CODE11 = 74;
  
  public static final int MSI = 75;
  
  public static final int QR_ECC_LEVEL_L = 1;
  
  public static final int QR_ECC_LEVEL_M = 2;
  
  public static final int QR_ECC_LEVEL_Q = 3;
  
  public static final int QR_ECC_LEVEL_H = 4;
  
  public static final int CHAR_SET_USA = 0;
  
  public static final int CHAR_SET_FRANCE = 1;
  
  public static final int CHAR_SET_GERMANY = 2;
  
  public static final int CHAR_SET_ENGLAND = 3;
  
  public static final int CHAR_SET_DENMARK_I = 4;
  
  public static final int CHAR_SET_SWEDEN = 5;
  
  public static final int CHAR_SET_ITALY = 6;
  
  public static final int CHAR_SET_SPAIN_I = 7;
  
  public static final int CHAR_SET_JAPAN = 8;
  
  public static final int CHAR_SET_NORWAY = 9;
  
  public static final int CHAR_SET_DENMARK_II = 10;
  
  public static final int CHAR_SET_SPAIN_II = 11;
  
  public static final int CHAR_SET_LATIN_AMERICA = 12;
  
  public static final int CHAR_SET_KOREA = 13;
  
  public static final int CHAR_SET_CROATIA = 14;
  
  public static final int CHAR_SET_CHINA = 15;
  
  public static final int CODE_PAGE_CP437 = 0;
  
  public static final int CODE_PAGE_KATAKANA = 1;
  
  public static final int CODE_PAGE_CP850 = 2;
  
  public static final int CODE_PAGE_CP860 = 3;
  
  public static final int CODE_PAGE_CP863 = 4;
  
  public static final int CODE_PAGE_CP865 = 5;
  
  public static final int CODE_PAGE_CP1251 = 6;
  
  public static final int CODE_PAGE_CP866 = 7;
  
  public static final int CODE_PAGE_MIK = 8;
  
  public static final int CODE_PAGE_CP755 = 9;
  
  public static final int CODE_PAGE_IRAN = 10;
  
  public static final int CODE_PAGE_CP862 = 15;
  
  public static final int CODE_PAGE_CP1252 = 16;
  
  public static final int CODE_PAGE_CP1253 = 17;
  
  public static final int CODE_PAGE_CP852 = 18;
  
  public static final int CODE_PAGE_CP858 = 19;
  
  public static final int CODE_PAGE_IRAN2 = 20;
  
  public static final int CODE_PAGE_LATVIAN = 21;
  
  public static final int CODE_PAGE_CP864 = 22;
  
  public static final int CODE_PAGE_ISO_8859_1 = 23;
  
  public static final int CODE_PAGE_CP737 = 24;
  
  public static final int CODE_PAGE_CP1257 = 25;
  
  public static final int CODE_PAGE_THAI = 26;
  
  public static final int CODE_PAGE_CP720 = 27;
  
  public static final int CODE_PAGE_CP855 = 28;
  
  public static final int CODE_PAGE_CP857 = 29;
  
  public static final int CODE_PAGE_CP1250 = 30;
  
  public static final int CODE_PAGE_CP775 = 31;
  
  public static final int CODE_PAGE_CP1254 = 32;
  
  public static final int CODE_PAGE_CP1255 = 33;
  
  public static final int CODE_PAGE_CP1256 = 34;
  
  public static final int CODE_PAGE_CP1258 = 35;
  
  public static final int CODE_PAGE_ISO_8859_2 = 36;
  
  public static final int CODE_PAGE_ISO_8859_3 = 37;
  
  public static final int CODE_PAGE_ISO_8859_4 = 38;
  
  public static final int CODE_PAGE_ISO_8859_5 = 39;
  
  public static final int CODE_PAGE_ISO_8859_6 = 40;
  
  public static final int CODE_PAGE_ISO_8859_7 = 41;
  
  public static final int CODE_PAGE_ISO_8859_8 = 42;
  
  public static final int CODE_PAGE_ISO_8859_9 = 43;
  
  public static final int CODE_PAGE_ISO_8859_15 = 44;
  
  public static final int CODE_PAGE_THAI2 = 45;
  
  public static final int CODE_PAGE_CP856 = 46;
  
  public static final int CODE_PAGE_CP874 = 47;
  
  public static final int CODE_PAGE_UTF8 = 95;
  
  public static final int CODE_PAGE_SHIFT_JIS = 96;
  
  public static final int CODE_PAGE_EUC_KR = 97;
  
  public static final int CODE_PAGE_BIG5 = 98;
  
  public static final int CODE_PAGE_GB18030 = 99;
  
  public static final int BITMAP_ZOOM_NONE = 0;
  
  public static final int BITMAP_ZOOM_WIDTH = 1;
  
  public static final int BITMAP_ZOOM_HEIGHT = 2;
  
  public static final int BITMAP_ZOOM_BOTH = 3;
  
  public static final int TYPE_PAPER_WIDTH_58MM = 0;
  
  public static final int TYPE_PAPER_WIDTH_80MM = 1;
  
  private final int[] bmp_byte_width = new int[] { 48, 72 };
  
  private final int[] dots_per_line = new int[] { 384, 576 };
  
  private final Printer mPrinter;
  
  private final int mPaperWidthType;
  
  private String mEncoding = "UTF-8";
  
  public PrinterManager(Printer printer, int paperWidthType) {
    this.mPrinter = printer;
    this.mPaperWidthType = paperWidthType;
  }
  
  public int getPrinterType() {
    return this.mPrinter.getType();
  }
  
  public void connect() throws SmartPosException {
    this.mPrinter.open();
    sendCmd(PrinterCommand.cmdESCat());
  }
  
  public void disconnect() throws SmartPosException {
    this.mPrinter.close();
  }
  
  public void checkPaper() throws SmartPosException {
    int ret = 0;
    byte[] status = new byte[1];
    if (this.mPrinter.getType() == 2 && Boolean.parseBoolean(System.getProperty("hw.printer_slow", "false"))) {
      int adc = DeviceNative.nativeAdcGetValue(2);
      ret = (adc < 790) ? 1 : 0;
    } else {
      try {
        sendCmd(PrinterCommand.cmdGSrn(1));
        ret = this.mPrinter.read(status, 1);
      } catch (SmartPosException e) {
        if (this.mPrinter.getType() == 1) {
          ret = 1;
          status[0] = 0;
        } else {
          ret = 0;
        } 
      } 
      status[0] = (byte)(status[0] & 0xF);
    } 
    if (ret == 0 || status[0] != 0)
      throw new SmartPosException(6); 
  }
  
  public void setStringEncoding(String encoding) throws SmartPosException {
    if (encoding == null)
      throw new SmartPosException(5); 
    this.mEncoding = encoding;
  }
  
  public void sendData(String data) throws SmartPosException {
    sendData(data, this.mEncoding);
  }
  
  public void sendData(String data, String encoding) throws SmartPosException {
    if (data == null || encoding == null)
      throw new SmartPosException(5); 
    try {
      byte[] info = data.getBytes(Charset.forName(encoding));
      if (info.length > 1024) {
        int i = 0;
        int count = info.length >> 10;
        int end = info.length - (count << 10);
        byte[] tmp = new byte[1024];
        byte[] last = new byte[end];
        while (count-- > 0) {
          System.arraycopy(info, i, tmp, 0, 1024);
          this.mPrinter.write(tmp, tmp.length);
          i += 1024;
        } 
        System.arraycopy(info, i, last, 0, end);
        this.mPrinter.write(last, last.length);
      } else {
        this.mPrinter.write(info, info.length);
      } 
    } catch (IllegalCharsetNameException e) {
      e.printStackTrace();
    } 
  }
  
  public void sendCmd(byte[] cmd) throws SmartPosException {
    if (cmd.length > 1024) {
      int i = 0;
      int count = cmd.length >> 10;
      int end = cmd.length - (count << 10);
      byte[] tmp = new byte[1024];
      byte[] last = new byte[end];
      while (count-- > 0) {
        System.arraycopy(cmd, i, tmp, 0, 1024);
        this.mPrinter.write(tmp, tmp.length);
        i += 1024;
      } 
      System.arraycopy(cmd, i, last, 0, end);
      this.mPrinter.write(last, last.length);
    } else {
      this.mPrinter.write(cmd, cmd.length);
    } 
  }
  
  public void cmdLineFeed() throws SmartPosException {
    sendCmd(PrinterCommand.cmdLF());
  }
  
  public void cmdLineFeed(int n) throws SmartPosException {
    sendCmd(PrinterCommand.cmdESCdn(n));
  }
  
  public void cmdJumpTab() throws SmartPosException {
    sendCmd(PrinterCommand.cmdHT());
  }
  
  public void cmdSetDefaultLineSpacing() throws SmartPosException {
    sendCmd(PrinterCommand.cmdESC2());
  }
  
  public void cmdSetLineSpacing(int dots) throws SmartPosException {
    sendCmd(PrinterCommand.cmdESC3n(dots));
  }
  
  public void cmdSetAlignMode(int mode) throws SmartPosException {
    sendCmd(PrinterCommand.cmdESCan(mode));
  }
  
  public void cmdSetPrintOffset(int offset) throws SmartPosException {
    sendCmd(PrinterCommand.cmdESC_nLnH(offset & 0xFF, offset >> 8 & 0xFF));
  }
  
  public void cmdSetPrintMode(int mode) throws SmartPosException {
    if ((mode & 0x2) == 2) {
      sendCmd(PrinterCommand.cmdGSBn(1));
    } else {
      sendCmd(PrinterCommand.cmdGSBn(0));
    } 
    if ((mode & 0x4) == 4) {
      sendCmd(PrinterCommand.cmdESCbracketN(1));
    } else {
      sendCmd(PrinterCommand.cmdESCbracketN(0));
    } 
    if ((mode & 0x40) == 64) {
      sendCmd(PrinterCommand.cmdESCVN(1));
    } else {
      sendCmd(PrinterCommand.cmdESCVN(0));
    } 
    sendCmd(PrinterCommand.cmdESCexclamationN(mode));
  }
  
  public void cmdSetUnderlineHeight(int n) throws SmartPosException {
    sendCmd(PrinterCommand.cmdESCminusN(n));
  }
  
  public void cmdSetFontScaleSize(int scaleWidth, int scaleHeight) throws SmartPosException {
    sendCmd(PrinterCommand.cmdGSexclamationN((scaleWidth & 0xF) << 4 | scaleHeight & 0xF));
  }
  
  public void cmdSetBarCodeStringPosition(int mode) throws SmartPosException {
    sendCmd(PrinterCommand.cmdGSHn(mode));
  }
  
  public void cmdSetBarCodeStringSize(int size) throws SmartPosException {
    sendCmd(PrinterCommand.cmdGSfn(size));
  }
  
  public void cmdSetBarCodeHeight(int n) throws SmartPosException {
    sendCmd(PrinterCommand.cmdGShn(n));
  }
  
  public void cmdSetBarCodeWidth(int n) throws SmartPosException {
    sendCmd(PrinterCommand.cmdGSwn(n));
  }
  
  public void cmdSetBarCodeLeftSpacing(int n) throws SmartPosException {
    sendCmd(PrinterCommand.cmdGSxn(n));
  }
  
  public void cmdBarCodePrint(int type, String string) throws SmartPosException {
    if (string == null)
      throw new SmartPosException(5); 
    byte[] barCode = string.getBytes();
    sendCmd(PrinterCommand.cmdGSkmnd(type, barCode.length, barCode));
  }
  
  public void cmdBarCodePrint(int type, byte[] stringBytes) throws SmartPosException {
    if (stringBytes == null)
      throw new SmartPosException(5); 
    sendCmd(PrinterCommand.cmdGSkmnd(type, stringBytes.length, stringBytes));
  }
  
  public void cmdBitmapPrint(Bitmap bitmap, int zoom, int left, int top) throws SmartPosException {
    byte[] result = null;
    if (bitmap == null)
      throw new SmartPosException(5); 
    if (bitmap.getWidth() + left > this.dots_per_line[this.mPaperWidthType] || bitmap.getHeight() + top > this.dots_per_line[this.mPaperWidthType])
      throw new SmartPosException(5); 
    int lines = bitmap.getHeight() + top;
    int w = bitmap.getWidth();
    int h = bitmap.getHeight();
    result = new byte[lines * this.bmp_byte_width[this.mPaperWidthType]];
    for (int y = 0; y < h; y++) {
      for (int x = 0; x < w; x++) {
        int color = bitmap.getPixel(x, y);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        if (red < 128 || green < 128 || blue < 128) {
          int bitX = x + left;
          int byteX = bitX >> 3;
          int byteY = y + top;
          result[byteY * this.bmp_byte_width[this.mPaperWidthType] + byteX] = (byte)(result[byteY * this.bmp_byte_width[this.mPaperWidthType] + byteX] | 128 >> bitX - (byteX << 3));
        } 
      } 
    } 
    sendCmd(PrinterCommand.cmdGSv0pwLwHhLhHd(zoom, this.bmp_byte_width[this.mPaperWidthType], 0, lines & 0xFF, lines >> 8 & 0xFF, result));
  }
  
  public void cmdBitmapPrintEx(Bitmap bitmap, int left, int top) throws SmartPosException {
    if (bitmap == null)
      throw new SmartPosException(5); 
    if (bitmap.getWidth() + left > this.dots_per_line[this.mPaperWidthType])
      throw new SmartPosException(5); 
    int lines = 0;
    int blockHeight = 24;
    int w = bitmap.getWidth();
    int h = bitmap.getHeight();
    for (int i = 0; i < top; i++)
      sendCmd(PrinterCommand.cmdESCjn(1)); 
    while (lines < h) {
      byte[] block = new byte[3 * this.dots_per_line[this.mPaperWidthType]];
      for (int y = 0; y + lines < h && y < blockHeight; y++) {
        for (int x = 0; x < w; x++) {
          int posBit = (x + left) * blockHeight + y;
          int posByte = posBit >> 3;
          int posBitInByteLeft = posBit % 8;
          int color = bitmap.getPixel(x, y + lines);
          int red = Color.red(color);
          int green = Color.green(color);
          int blue = Color.blue(color);
          if (red < 128 || green < 128 || blue < 128)
            block[posByte] = (byte)(block[posByte] | 128 >> posBitInByteLeft); 
        } 
      } 
      sendCmd(PrinterCommand.cmdESCasteriskmhLhHd(33, this.dots_per_line[this.mPaperWidthType] & 0xFF, this.dots_per_line[this.mPaperWidthType] >> 8 & 0xFF, block));
      sendCmd(PrinterCommand.cmdESCjn(0));
      lines += blockHeight;
    } 
  }
  
  public void cmdSetHeatingParam(int dots, int time, int interval) throws SmartPosException {
    sendCmd(PrinterCommand.cmdESC7n1n2n3(dots, time, interval));
  }
  
  public void cmdSetPrintDensity(int density, int delay) throws SmartPosException {
    int arg = delay << 5 & 0x7 | density & 0x1F;
    sendCmd(PrinterCommand.cmdDC2_n(arg));
  }
  
  public void cmdSetCharacterSet(int set) throws SmartPosException {
    sendCmd(PrinterCommand.cmdESCRn(set));
  }
  
  public void cmdSetPrinterLanguage(int code) throws SmartPosException {
    switch (code) {
      case 99:
        sendCmd(new byte[] { 28, 38 });
        sendCmd(new byte[] { 27, 57, 0 });
        return;
      case 95:
        sendCmd(new byte[] { 28, 38 });
        sendCmd(new byte[] { 27, 57, 1 });
        return;
      case 98:
        sendCmd(new byte[] { 28, 38 });
        sendCmd(new byte[] { 27, 57, 3 });
        return;
      case 96:
        sendCmd(new byte[] { 28, 38 });
        sendCmd(new byte[] { 27, 57, 4 });
        return;
      case 97:
        sendCmd(new byte[] { 28, 38 });
        sendCmd(new byte[] { 27, 57, 5 });
        return;
    } 
    sendCmd(new byte[] { 28, 46 });
    sendCmd(new byte[] { 27, 116, (byte)code });
  }
  
  public void cmdQrCodePrint(int version, int ecc, String data) throws SmartPosException {
    byte[] cmd1 = { 29, 40, 107, 3, 0, 49, 67, (byte)version };
    byte[] cmd2 = { 29, 40, 107, 3, 0, 49, 69, (byte)(ecc + 47) };
    byte[] cmd3 = { 29, 40, 107, 3, 0, 49, 82, 48 };
    byte[] cmd4 = { 29, 40, 107, 3, 0, 49, 81, 48 };
    byte[] str = data.getBytes();
    byte[] cmd = new byte[str.length + 8];
    cmd[0] = 29;
    cmd[1] = 40;
    cmd[2] = 107;
    cmd[3] = (byte)(str.length + 3 & 0xFF);
    cmd[4] = (byte)(str.length + 3 >> 8 & 0xFF);
    cmd[5] = 49;
    cmd[6] = 80;
    cmd[7] = 48;
    System.arraycopy(str, 0, cmd, 8, str.length);
    sendCmd(cmd1);
    sendCmd(cmd2);
    sendCmd(cmd);
    sendCmd(cmd3);
    sendCmd(cmd4);
  }
  
  public void cmdPrintMultipleLines(int lineCount, int[] lineStartPos, int[] lineEndPos) throws SmartPosException {
    if (lineStartPos == null || lineEndPos == null)
      throw new SmartPosException(5); 
    byte[] cmd = new byte[(lineCount << 2) + 3];
    cmd[0] = 29;
    cmd[1] = 39;
    cmd[2] = (byte)lineCount;
    int j = 3;
    for (int i = 0; i < lineCount; i++, j += 4) {
      cmd[j] = (byte)(lineStartPos[i] & 0xFF);
      cmd[j + 1] = (byte)(lineStartPos[i] >> 8 & 0xFF);
      cmd[j + 2] = (byte)(lineEndPos[i] & 0xFF);
      cmd[j + 3] = (byte)(lineEndPos[i] >> 8 & 0xFF);
    } 
    sendCmd(cmd);
  }
  
  public void cmdSaveBitmapToNVRAM(Bitmap[] bmpArray) throws SmartPosException {
    if (bmpArray == null)
      throw new SmartPosException(5); 
    int bmpCount = bmpArray.length;
    int totalBytes = 3;
    for (int i = 0; i < bmpCount; i++) {
      int w = bmpArray[i].getWidth();
      int h = bmpArray[i].getHeight();
      totalBytes += (w * h >> 3) + 4;
    } 
    int blockCount = totalBytes >> 8;
    int blockLastBytes = (blockCount > 1) ? (totalBytes - (blockCount << 8)) : 0;
    if (totalBytes > 65535)
      throw new SmartPosException(5); 
    byte[] cmdNvMode = { 
        3, -1, 47, 0, 1, 0, 0, 0, 0, 0, 
        -46, 0 };
    byte[] cmdNvData = { 
        3, -1, 45, 0, 0, 0, 26, 0, 0, 1, 
        -54, 89 };
    byte[] data = new byte[totalBytes];
    data[0] = -86;
    data[1] = -35;
    data[2] = (byte)bmpCount;
    int offset = 3;
    for (int j = 0; j < bmpCount; j++) {
      int w = bmpArray[j].getWidth();
      int h = bmpArray[j].getHeight();
      byte[] bmp = new byte[(w * h >> 3) + 4];
      bmp[0] = (byte)(w >> 3 & 0xFF);
      bmp[1] = (byte)(w >> 3 >> 8 & 0xFF);
      bmp[2] = (byte)(h >> 3 & 0xFF);
      bmp[3] = (byte)(h >> 3 >> 8 & 0xFF);
      for (int y = 0; y < h; y++) {
        for (int x = 0; x < w; x++) {
          int color = bmpArray[j].getPixel(x, y);
          int red = Color.red(color);
          int green = Color.green(color);
          int blue = Color.blue(color);
          if (red < 128 || green < 128 || blue < 128) {
            int bitX = y * w + x;
            int bitPos = bitX % 8;
            int byteX = bitX >> 3;
            bmp[byteX + 4] = (byte)(bmp[byteX + 4] | 128 >> bitPos);
          } 
        } 
      } 
      System.arraycopy(bmp, 0, data, offset, bmp.length);
      offset += bmp.length;
    } 
    sendCmd(cmdNvMode);
    if (blockLastBytes < 1) {
      byte[] pack = new byte[totalBytes + cmdNvData.length];
      System.arraycopy(cmdNvData, 0, pack, 0, cmdNvData.length);
      System.arraycopy(data, 0, pack, cmdNvData.length, totalBytes);
      pack[5] = 0;
      pack[8] = (byte)(totalBytes & 0xFF);
      pack[9] = (byte)(totalBytes >> 8 & 0xFF);
      pack[10] = (byte)calcXorSum(pack, 0, 10);
      pack[11] = (byte)calcXorSum(pack, 12, totalBytes);
      sendCmd(pack);
    } else {
      byte[] pack = new byte[268];
      for (int k = 0; k < blockCount; k++) {
        System.arraycopy(cmdNvData, 0, pack, 0, cmdNvData.length);
        System.arraycopy(data, k << 8, pack, cmdNvData.length, 256);
        pack[5] = (byte)k;
        pack[8] = 0;
        pack[9] = 1;
        pack[10] = (byte)calcXorSum(pack, 0, 10);
        pack[11] = (byte)calcXorSum(pack, 12, 256);
        sendCmd(pack);
      } 
      byte[] lastPack = new byte[blockLastBytes + cmdNvData.length];
      System.arraycopy(cmdNvData, 0, lastPack, 0, cmdNvData.length);
      System.arraycopy(data, blockCount << 8, lastPack, cmdNvData.length, blockLastBytes);
      lastPack[5] = (byte)blockCount;
      lastPack[8] = (byte)(blockLastBytes & 0xFF);
      lastPack[9] = (byte)(blockLastBytes >> 8 & 0xFF);
      lastPack[10] = (byte)calcXorSum(lastPack, 0, 10);
      lastPack[11] = (byte)calcXorSum(lastPack, 12, blockLastBytes);
      sendCmd(lastPack);
    } 
  }
  
  public void cmdDeleteBitmapFromNVRAM() throws SmartPosException {
    sendCmd(new byte[] { 28, 113, 0 });
  }
  
  public void cmdPrintBitmapFromNVRAM(int index, int zoom) throws SmartPosException {
    if (index < 1 || index > 255 || zoom < 0 || zoom > 3)
      throw new SmartPosException(5); 
    sendCmd(new byte[] { 28, 112, (byte)index, (byte)zoom });
  }
  
  public void cmdPrintTest() throws SmartPosException {
    sendCmd(PrinterCommand.cmdDC2T());
  }
  
  public void cmdCutPaper(int mode) throws SmartPosException {
    sendCmd(PrinterCommand.cmdGSvm(mode));
  }
  
  public void fwUpdate(AssetManager am, String name) throws SmartPosException {
    int mPowerIO = Integer.parseInt(System.getProperty("hw.io.printer", "68"));
    Gpio.init(mPowerIO);
    Gpio.setMode(mPowerIO, 1);
    Gpio.setValue(mPowerIO, 1);
    try {
      Thread.sleep(1500L);
    } catch (InterruptedException ie) {}
    if (DeviceNative.nativePrinterFwUpdate(am, name) < 0) {
      Gpio.setValue(mPowerIO, 0);
      Gpio.deinit(mPowerIO);
      throw new SmartPosException(7);
    } 
    try {
      Thread.sleep(500L);
    } catch (InterruptedException ie) {}
    Gpio.setValue(mPowerIO, 0);
    Gpio.deinit(mPowerIO);
  }
  
  public int cmdGetPrinterModel() {
    return DeviceNative.nativePrinterGetModel();
  }
  
  public int getDotsPerLine() {
    return this.dots_per_line[this.mPaperWidthType];
  }
  
  public boolean isBuiltInSlow() {
    return Boolean.parseBoolean(System.getProperty("hw.printer_slow", "false"));
  }
  
  private int calcXorSum(byte[] array, int start, int count) {
    int sum = 0;
    for (int i = start; i < start + count; i++)
      sum ^= array[i] & 0xFF; 
    return sum;
  }
}
