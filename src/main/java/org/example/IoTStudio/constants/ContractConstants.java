package org.example.IoTStudio.constants;

import java.lang.Exception;
import java.lang.RuntimeException;
import java.lang.String;

public class ContractConstants {
  public static String Uint2strAbi;

  public static String Uint2strBinary;

  public static String Uint2strGmBinary;

  public static String DateTimeAbi;

  public static String DateTimeBinary;

  public static String DateTimeGmBinary;

  public static String MainAbi;

  public static String MainBinary;

  public static String MainGmBinary;

  static {
    try {
      Uint2strAbi = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("abi/Uint2str.abi"));
      Uint2strBinary = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("bin/ecc/Uint2str.bin"));
      Uint2strGmBinary = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("bin/sm/Uint2str.bin"));
      DateTimeAbi = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("abi/DateTime.abi"));
      DateTimeBinary = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("bin/ecc/DateTime.bin"));
      DateTimeGmBinary = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("bin/sm/DateTime.bin"));
      MainAbi = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("abi/Main.abi"));
      MainBinary = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("bin/ecc/Main.bin"));
      MainGmBinary = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("bin/sm/Main.bin"));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
