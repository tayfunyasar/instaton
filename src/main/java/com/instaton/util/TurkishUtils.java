package com.instaton.util;

import java.text.Normalizer;

import org.apache.commons.lang3.StringUtils;

public class TurkishUtils {

  public static boolean containsIgnoreCase(final String s1, final String s2) {
    final String ws1 = TurkishUtils.replaceAllTurkishCharacters(s1);
    final String ws2 = TurkishUtils.replaceAllTurkishCharacters(s2);

    return StringUtils.containsIgnoreCase(ws1, ws2);
  }

  public static boolean equalsIgnoreCase(final String s1, final String s2) {
    final String ws1 = TurkishUtils.replaceAllTurkishCharacters(s1);
    final String ws2 = TurkishUtils.replaceAllTurkishCharacters(s2);

    return StringUtils.equalsIgnoreCase(ws1, ws2);
  }

  //  public static void main(final String[] args) {
  //    System.out.println(TurkishUtils.replaceAllTurkishCharacters("TASARİM"));
  //  }

  public static String replaceAllTurkishCharacters(String string) {
    string = Normalizer.normalize(string, Normalizer.Form.NFD);
    string = string.replaceAll("[^\\p{ASCII}]", "");
    string = string.toLowerCase();
    return string.toLowerCase();
  }

  public static boolean startsWithIgnoreCase(final String s1, final String s2) {
    final String ws1 = TurkishUtils.replaceAllTurkishCharacters(s1);
    final String ws2 = TurkishUtils.replaceAllTurkishCharacters(s2);

    return StringUtils.startsWith(ws1, ws2);
  }

  private TurkishUtils() {
    throw new IllegalArgumentException();
  }
}
