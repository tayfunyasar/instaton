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

  private static String replaceAllTurkishCharacters(final String s) {
    final String ns = Normalizer.normalize(s, Normalizer.Form.NFD);
    return ns.replaceAll("[^\\x00-\\x7F]", "");
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
