package com.instaton.util;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import com.google.common.base.Optional;
import com.optimaize.langdetect.LanguageDetector;
import com.optimaize.langdetect.LanguageDetectorBuilder;
import com.optimaize.langdetect.i18n.LdLocale;
import com.optimaize.langdetect.ngram.NgramExtractors;
import com.optimaize.langdetect.profiles.LanguageProfile;
import com.optimaize.langdetect.profiles.LanguageProfileReader;
import com.optimaize.langdetect.text.CommonTextObjectFactories;
import com.optimaize.langdetect.text.TextObject;
import com.optimaize.langdetect.text.TextObjectFactory;

public class LanguageUtil {

  public static boolean containsFrench(final String s) {
    final Pattern frenchPattern = Pattern.compile("(?i)[èéêîôœû]");
    final boolean find = frenchPattern.matcher(s).find();
    if (find) {
      System.out.println("containsFrench = " + s);
      return true;
    }
    return false;
  }

  public static boolean containsHanScript(final String s) {
    for (int i = 0; i < s.length(); ) {
      final int codepoint = s.codePointAt(i);
      i += Character.charCount(codepoint);
      if (Character.UnicodeScript.of(codepoint) == Character.UnicodeScript.HAN) {
        System.out.println("containsHanScript = " + s);
        return true;
      }
    }
    return false;
  }

  public static String detechText(final String s) throws IOException {
    System.out.println("detechText = " + s);
    // load all languages:
    final List<LanguageProfile> languageProfiles = new LanguageProfileReader().readAllBuiltIn();

    // build language detector:
    final LanguageDetector languageDetector =
        LanguageDetectorBuilder.create(NgramExtractors.standard())
            .withProfiles(languageProfiles)
            .build();

    // create a text object factory
    final TextObjectFactory textObjectFactory =
        CommonTextObjectFactories.forDetectingShortCleanText();

    // query:
    final TextObject textObject = textObjectFactory.forText(s);
    final Optional<LdLocale> lang = languageDetector.detect(textObject);

    if (lang.isPresent()) {
      final LdLocale ldLocale = lang.get();
      final String language = ldLocale.getLanguage();
      System.out.println("lang = " + lang);
    }
    return s;
  }

  public static boolean isProbablyArabic(final String s) {
    for (int i = 0; i < s.length(); ) {
      final int c = s.codePointAt(i);
      if (c >= 0x0600 && c <= 0x06E0) {
        System.out.println("isProbablyArabic = " + s);
        return true;
      }
      i += Character.charCount(c);
    }
    return false;
  }

  private LanguageUtil() {
    throw new IllegalAccessError();
  }
}
