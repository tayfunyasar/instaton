package com.instaton.util;

import java.security.MessageDigest;
import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseInputUtils {

  private static Logger logger = LoggerFactory.getLogger(BaseInputUtils.class);

  public static java.sql.Timestamp getCurrTst() {
    return new Timestamp(System.currentTimeMillis());
  }

  public static String hashWithMD5(String clearValue) {
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      md.update(clearValue.getBytes());

      byte byteData[] = md.digest();

      // convert the byte to hex format method 1
      StringBuilder sb = new StringBuilder();
      for (byte element : byteData) {
        sb.append(Integer.toString((element & 0xff) + 0x100, 16).substring(1));
      }
      return sb.toString();
    } catch (Exception e) {
      logger.error("/hashWithMD5", e);

      return "";
    }
  }
}
