package com.instaton.util.filter;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.social.twitter.api.HashTagEntity;
import org.springframework.social.twitter.api.Tweet;

import com.instaton.entity.black.BlackHashTagEntity;
import com.instaton.entity.black.BlackNameEntity;
import com.instaton.entity.black.BlackUserIdEntity;
import com.instaton.entity.black.BlackWordEntity;
import com.instaton.entity.twitter.TwitterProfileEntity;
import com.instaton.util.LanguageUtil;
import com.instaton.util.TurkishUtils;

public class TweetFilter {

  public static boolean checkIfBlackKeywordListed(
      final List<BlackHashTagEntity> blackKeywordList, final Tweet tweet) {
    for (final HashTagEntity hashTagEntity : tweet.getEntities().getHashTags()) {
      final String text = hashTagEntity.getText();

      for (final BlackHashTagEntity blackKeywordItem : blackKeywordList) {
        final String keyword = blackKeywordItem.getKeyword();

        if (TurkishUtils.equalsIgnoreCase(keyword, text)) {
          return true;
        }
      }
    }
    return false;
  }

  public static boolean checkIfBlacklistedLanguage(final Tweet tweet) {
    final List<String> blackLanguageList = Arrays.asList("ar", "tt", "pt", "es");
    final String userLanguage = tweet.getUser().getLanguage();

    for (final String languageCode : blackLanguageList) {

      if (TurkishUtils.equalsIgnoreCase(userLanguage, languageCode)) {
        return true;
      }
    }
    return false;
  }

  public static boolean checkIfBlackName(
      final Tweet tweet, final List<BlackNameEntity> blackNameEntityList) {
    final String firstname = tweet.getUser().getName().split(" ")[0];

    for (final BlackNameEntity blackNameEntity : blackNameEntityList) {
      final String name = blackNameEntity.getName();

      if (TurkishUtils.equalsIgnoreCase(firstname, name)) {
        return true;
      }
    }
    return false;
  }

  public static boolean checkIfBlackWorded(
      final Tweet tweet, final List<BlackWordEntity> blackWordEntityList) {

    final String text = tweet.getText();
    final String[] words = text.split("\\s+");

    for (final String word : words) {

      if (StringUtils.isNotBlank(word)) {
        for (final BlackWordEntity blackWordEntity : blackWordEntityList) {
          final String blackWord = blackWordEntity.getWord();

          if (TurkishUtils.equalsIgnoreCase(blackWord, word)) {
            return true;
          }
        }
      }
    }

    return false;
  }

  public static boolean checkIfBlocked(
      final List<BlackUserIdEntity> blackUserIdList, final Tweet tweet) {
    for (final BlackUserIdEntity blackUserIdEntity : blackUserIdList) {
      final String screenName = tweet.getUser().getScreenName();

      if (blackUserIdEntity.getUserId() == tweet.getUser().getId()) {
        return true;
      }
      if (TurkishUtils.equalsIgnoreCase(blackUserIdEntity.getScreenName(), screenName)) {
        return true;
      }
    }
    return false;
  }

  public static boolean checkIfLocationBlacklisted(final Tweet tweet) {
    final List<String> blackLocationList =
        Arrays.asList(
            "italy",
            "bursa",
            "kocaeli",
            "izmit",
            "mersin",
            "lüleburgaz",
            "yalova",
            "bodrum",
            "edirne",
            "gaziantep",
            "antalya",
            "ankara",
            "denizli",
            "kayseri",
            "adana",
            "izmir",
            "çorlu",
            "aydın",
            "sakarya",
            "nazilli",
            "giresun",
            "trabzon",
            "eskişehir",
            "hatay",
            "ordu",
            "düzce",
            "kıbrıs",
            "cyprus",
            "gebze",
            "muğla",
            "kırklareli",
            "burdur",
            "zonguldak",
            "rize",
            "tekirdağ",
            "artvin",
            "konya",
            "samsun",
            "bolu",
            "nevşehir",
            "ısparta",
            "alanya");
    final String userLocation = tweet.getUser().getLocation();

    for (final String location : blackLocationList) {
      if (TurkishUtils.containsIgnoreCase(userLocation, location)) {
        return true;
      }
    }

    return false;
  }

  public static boolean checkIfNotVisitedBefore(
      final List<TwitterProfileEntity> twitterUserList, final Tweet tweet) {

    final String screenName = tweet.getUser().getScreenName();
    for (final TwitterProfileEntity twitterUser : twitterUserList) {
      if (tweet.getUser().getId() == twitterUser.getUserId()) {
        return true;
      }
      if (TurkishUtils.equalsIgnoreCase(twitterUser.getScreenName(), screenName)) {
        return true;
      }
    }
    return false;
  }

  public static boolean isBlacklistedLanguage(final Tweet tweet) {
    final String name = tweet.getUser().getName();
    final String description = tweet.getUser().getDescription();

    if (LanguageUtil.isProbablyArabic(name)) {
      return true;
    }

    if (LanguageUtil.isProbablyArabic(description)) {
      return true;
    }

    if (LanguageUtil.containsHanScript(name)) {
      return true;
    }

    if (LanguageUtil.containsHanScript(description)) {
      return true;
    }

    if (LanguageUtil.containsFrench(name)) {
      return true;
    }

    if (LanguageUtil.containsFrench(description)) {
      return true;
    }

    return false;
  }

  private TweetFilter() {
    throw new IllegalAccessError();
  }
}
