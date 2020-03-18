package com.reactlibrary.PayDeviceSDK.printer;

public class PrinterCommand {
  public static byte[] cmdLF() {
    return new byte[] { 10 };
  }
  
  public static byte[] cmdHT() {
    return new byte[] { 9 };
  }
  
  public static byte[] cmdFF() {
    return new byte[] { 12 };
  }
  
  public static byte[] cmdESCFF() {
    return new byte[] { 27, 12 };
  }
  
  public static byte[] cmdESCjn(int n) {
    return new byte[] { 27, 74, (byte)n };
  }
  
  public static byte[] cmdESCdn(int n) {
    return new byte[] { 27, 100, (byte)n };
  }
  
  public static byte[] cmdESCequalN(int n) {
    return new byte[] { 27, 61, (byte)n };
  }
  
  public static byte[] cmdESC2() {
    return new byte[] { 27, 50 };
  }
  
  public static byte[] cmdESC3n(int n) {
    return new byte[] { 27, 51, (byte)n };
  }
  
  public static byte[] cmdESCan(int n) {
    return new byte[] { 27, 97, (byte)n };
  }
  
  public static byte[] cmdESC_nLnH(int nL, int nH) {
    return new byte[] { 27, 36, (byte)nL, (byte)nH };
  }
  
  public static byte[] cmdGSLnLnH(int nL, int nH) {
    return new byte[] { 29, 76, (byte)nL, (byte)nH };
  }
  
  public static byte[] cmdESCexclamationN(int n) {
    return new byte[] { 27, 33, (byte)n };
  }
  
  public static byte[] cmdGSexclamationN(int n) {
    return new byte[] { 29, 33, (byte)n };
  }
  
  public static byte[] cmdESCVN(int n) {
    return new byte[] { 27, 86, (byte)n };
  }
  
  public static byte[] cmdESCEn(int n) {
    return new byte[] { 27, 69, (byte)n };
  }
  
  public static byte[] cmdESCSPn(int n) {
    return new byte[] { 27, 32, (byte)n };
  }
  
  public static byte[] cmdESCS0() {
    return new byte[] { 27, 14 };
  }
  
  public static byte[] cmdESCDC4() {
    return new byte[] { 27, 20 };
  }
  
  public static byte[] cmdESCbracketN(int n) {
    return new byte[] { 27, 123, (byte)n };
  }
  
  public static byte[] cmdGSBn(int n) {
    return new byte[] { 29, 66, (byte)n };
  }
  
  public static byte[] cmdESCminusN(int n) {
    return new byte[] { 27, 45, (byte)n };
  }
  
  public static byte[] cmdESCpercentN(int n) {
    return new byte[] { 27, 37, (byte)n };
  }
  
  public static byte[] cmdESC_SNMW(int s, int n, int w, int m, byte[] d) {
    byte[] cmd = new byte[d.length + 7];
    cmd[0] = 27;
    cmd[1] = 38;
    cmd[2] = (byte)s;
    cmd[3] = (byte)n;
    cmd[5] = (byte)w;
    cmd[6] = (byte)m;
    System.arraycopy(d, 0, cmd, 7, d.length);
    return cmd;
  }
  
  public static byte[] cmdESCquestionN(int n) {
    return new byte[] { 27, 37, (byte)n };
  }
  
  public static byte[] cmdESCRn(int n) {
    return new byte[] { 27, 82, (byte)n };
  }
  
  public static byte[] cmdESCtn(int n) {
    return new byte[] { 27, 116, (byte)n };
  }
  
  public static byte[] cmdESC_mn1n2d(int m, int n1, int n2, byte[] d) {
    byte[] cmd = new byte[d.length + 5];
    cmd[0] = 27;
    cmd[1] = 42;
    cmd[2] = (byte)m;
    cmd[3] = (byte)n1;
    cmd[4] = (byte)n2;
    System.arraycopy(d, 0, cmd, 5, d.length);
    return cmd;
  }
  
  public static byte[] cmdGSslashn(int n) {
    return new byte[] { 29, 47, (byte)n };
  }
  
  public static byte[] cmdGS_n1n2d(int n1, int n2, byte[] d) {
    byte[] cmd = new byte[d.length + 4];
    cmd[0] = 29;
    cmd[1] = 42;
    cmd[2] = (byte)n1;
    cmd[3] = (byte)n2;
    System.arraycopy(d, 0, cmd, 4, d.length);
    return cmd;
  }
  
  public static byte[] cmdGSv0pwLwHhLhHd(int p, int wL, int wH, int hL, int hH, byte[] d) {
    byte[] cmd = new byte[d.length + 8];
    cmd[0] = 29;
    cmd[1] = 118;
    cmd[2] = 48;
    cmd[3] = (byte)p;
    cmd[4] = (byte)wL;
    cmd[5] = (byte)wH;
    cmd[6] = (byte)hL;
    cmd[7] = (byte)hH;
    System.arraycopy(d, 0, cmd, 8, d.length);
    return cmd;
  }
  
  public static byte[] cmdESCasteriskmhLhHd(int m, int hL, int hH, byte[] d) {
    byte[] cmd = new byte[d.length + 5];
    cmd[0] = 27;
    cmd[1] = 42;
    cmd[2] = (byte)m;
    cmd[3] = (byte)hL;
    cmd[4] = (byte)hH;
    System.arraycopy(d, 0, cmd, 5, d.length);
    return cmd;
  }
  
  public static byte[] cmdDC2_rnd(int r, int n, byte[] d) {
    byte[] cmd = new byte[d.length + 4];
    cmd[0] = 18;
    cmd[1] = 42;
    cmd[2] = (byte)r;
    cmd[3] = (byte)n;
    System.arraycopy(d, 0, cmd, 4, d.length);
    return cmd;
  }
  
  public static byte[] cmdDC2VnLnHd(int nL, int nH, byte[] d) {
    byte[] cmd = new byte[d.length + 4];
    cmd[0] = 18;
    cmd[1] = 86;
    cmd[2] = (byte)nL;
    cmd[3] = (byte)nH;
    System.arraycopy(d, 0, cmd, 4, d.length);
    return cmd;
  }
  
  public static byte[] cmdDC2vnLnHd(int nL, int nH, byte[] d) {
    byte[] cmd = new byte[d.length + 4];
    cmd[0] = 18;
    cmd[1] = 118;
    cmd[2] = (byte)nL;
    cmd[3] = (byte)nH;
    System.arraycopy(d, 0, cmd, 4, d.length);
    return cmd;
  }
  
  public static byte[] cmdESCc5n(int n) {
    return new byte[] { 27, 99, 53, (byte)n };
  }
  
  public static byte[] cmdESCat() {
    return new byte[] { 27, 64 };
  }
  
  public static byte[] cmdGSrn(int n) {
    return new byte[] { 29, 114, (byte)n };
  }
  
  public static byte[] cmdGSan(int n) {
    return new byte[] { 29, 97, (byte)n };
  }
  
  public static byte[] cmdESCun(int n) {
    return new byte[] { 27, 117, (byte)n };
  }
  
  public static byte[] cmdGSHn(int n) {
    return new byte[] { 29, 72, (byte)n };
  }
  
  public static byte[] cmdGSfn(int n) {
    return new byte[] { 29, 102, (byte)n };
  }
  
  public static byte[] cmdGShn(int n) {
    return new byte[] { 29, 104, (byte)n };
  }
  
  public static byte[] cmdGSxn(int n) {
    return new byte[] { 29, 120, (byte)n };
  }
  
  public static byte[] cmdGSwn(int n) {
    return new byte[] { 29, 119, (byte)n };
  }
  
  public static byte[] cmdGSkmnd(int m, int n, byte[] d) {
    byte[] cmd = new byte[d.length + 4];
    cmd[0] = 29;
    cmd[1] = 107;
    cmd[2] = (byte)m;
    cmd[3] = (byte)n;
    System.arraycopy(d, 0, cmd, 4, d.length);
    return cmd;
  }
  
  public static byte[] cmdESC7n1n2n3(int n1, int n2, int n3) {
    return new byte[] { 27, 55, (byte)n1, (byte)n2, (byte)n3 };
  }
  
  public static byte[] cmdESC8n1n2(int n1, int n2) {
    return new byte[] { 27, 56, (byte)n1, (byte)n2 };
  }
  
  public static byte[] cmdDC2_n(int n) {
    return new byte[] { 18, 35, (byte)n };
  }
  
  public static byte[] cmdDC2T() {
    return new byte[] { 18, 84 };
  }
  
  public static byte[] cmdGSvm(int m) {
    return new byte[] { 29, 86, (byte)m };
  }
  
  public static byte[] cmdDELEOTn(int n) {
    return new byte[] { 16, 4, (byte)n };
  }
  
  public static byte[] cmdDELENQn(int n) {
    return new byte[] { 16, 5, (byte)n };
  }
}
