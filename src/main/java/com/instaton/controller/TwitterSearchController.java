package com.instaton.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.GeoCode;
import org.springframework.social.twitter.api.HashTagEntity;
import org.springframework.social.twitter.api.SearchMetadata;
import org.springframework.social.twitter.api.SearchParameters;
import org.springframework.social.twitter.api.SearchParameters.ResultType;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Optional;
import com.instaton.constant.WebConstants;
import com.instaton.entity.black.blackhashtagentity.BlackHashTagEntity;
import com.instaton.entity.black.blacknameentity.BlackNameEntity;
import com.instaton.entity.black.blackuserid.BlackUserIdEntity;
import com.instaton.entity.black.blackwordentity.BlackWordEntity;
import com.instaton.entity.twitter.CustomSearchResults;
import com.instaton.entity.twitter.TwitterUser;
import com.instaton.service.twitter.BlackHashTagEntityService;
import com.instaton.service.twitter.BlackNameEntityService;
import com.instaton.service.twitter.BlackUserIdEntityService;
import com.instaton.service.twitter.BlackWordEntityService;
import com.instaton.service.twitter.TwitterUserService;
import com.instaton.service.twitter.impl.TwitterServiceImpl;
import com.instaton.util.TurkishUtils;
import com.optimaize.langdetect.LanguageDetector;
import com.optimaize.langdetect.LanguageDetectorBuilder;
import com.optimaize.langdetect.i18n.LdLocale;
import com.optimaize.langdetect.ngram.NgramExtractors;
import com.optimaize.langdetect.profiles.LanguageProfile;
import com.optimaize.langdetect.profiles.LanguageProfileReader;
import com.optimaize.langdetect.text.CommonTextObjectFactories;
import com.optimaize.langdetect.text.TextObject;
import com.optimaize.langdetect.text.TextObjectFactory;

@RestController
@RequestMapping("/api/search")
public class TwitterSearchController {

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

  // public static void main(final String[] args) throws IOException {
  // final String s = "E eu q enrosquei um cotonete no piercing do tragus";
  // TwitterSearchController.detechText(s);
  // }

  @Autowired private TwitterServiceImpl twitterProfileService;

  @Autowired private BlackHashTagEntityService blackKeywordService;

  @Autowired private BlackUserIdEntityService blackUserIdENtityService;

  @Autowired private TwitterUserService twitterUserService;

  @Autowired private BlackNameEntityService blackNameEntityService;

  @Autowired private BlackWordEntityService blackWordEntityService;

  @Autowired private WebConstants webConstants;

  private boolean checkIfBlackKeywordListed(
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

  private boolean checkIfBlacklistedLanguage(final Tweet tweet) {
    final List<String> blackLanguageList = Arrays.asList("ar", "tt", "pt", "es");
    final String userLanguage = tweet.getUser().getLanguage();

    for (final String languageCode : blackLanguageList) {

      if (TurkishUtils.equalsIgnoreCase(userLanguage, languageCode)) {
        return true;
      }
    }
    return false;
  }

  private boolean checkIfBlackName(
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

  private boolean checkIfBlackWorded(
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

  private boolean checkIfBlocked(final List<BlackUserIdEntity> blackUserIdList, final Tweet tweet) {
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

  private boolean checkIfLocationBlacklisted(final Tweet tweet) {
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
            "bolu");
    final String userLocation = tweet.getUser().getLocation();

    for (final String location : blackLocationList) {
      if (TurkishUtils.containsIgnoreCase(userLocation, location)) {
        return true;
      }
    }

    return false;
  }

  private boolean checkIfNotVisitedBefore(
      final List<TwitterUser> twitterUserList, final Tweet tweet) {

    final String screenName = tweet.getUser().getScreenName();
    for (final TwitterUser twitterUser : twitterUserList) {
      if (tweet.getUser().getId() == twitterUser.getUserId()) {
        return true;
      }
      if (TurkishUtils.equalsIgnoreCase(twitterUser.getScreenName(), screenName)) {
        return true;
      }
    }
    return false;
  }

  @GetMapping("/current")
  public CustomSearchResults getCurrent() throws IOException {

    final ArrayList<Tweet> tweets = this.getTweetList();
    final SearchResults search = new SearchResults(tweets, new SearchMetadata(1, 1));
    final CustomSearchResults customSearchResults = new CustomSearchResults(search);

    final List<BlackHashTagEntity> blackKeywordList = this.blackKeywordService.findAll();
    final List<BlackUserIdEntity> blackUserIdList = this.blackUserIdENtityService.findAll();
    final List<TwitterUser> allTwitterUserList = this.twitterUserService.findAll();
    final List<BlackNameEntity> blackNameEntityList = this.blackNameEntityService.findAll();
    final List<BlackWordEntity> blackWordEntityList = this.blackWordEntityService.findAll();

    final List<Tweet> filteredTweets = new ArrayList<>();
    for (final Tweet tweet : search.getTweets()) {
      boolean isContains = false;

      if (!isContains) {
        isContains = this.checkIfBlacklistedLanguage(tweet);
      }

      if (!isContains) {
        isContains = this.checkIfLocationBlacklisted(tweet);
      }

      if (!isContains) {
        isContains = this.checkIfBlackWorded(tweet, blackWordEntityList);
      }

      if (!isContains) {
        isContains = this.checkIfBlackKeywordListed(blackKeywordList, tweet);
      }

      if (!isContains) {
        isContains = this.checkIfBlocked(blackUserIdList, tweet);
      }

      if (!isContains) {
        isContains = this.checkIfBlackName(tweet, blackNameEntityList);
      }

      if (!isContains) {
        isContains = this.checkIfNotVisitedBefore(allTwitterUserList, tweet);
      }

      if (!isContains) {
        isContains = this.isBlacklistedLanguage(tweet);
      }

      if (!isContains) {
        filteredTweets.add(tweet);
      }
    }
    customSearchResults.setFilteredTweets(filteredTweets);

    return customSearchResults;
  }

  private ArrayList<Tweet> getTweetList() {

    final List<String> terms = this.webConstants.getKeywordList();

    final List<SearchParameters> params = new ArrayList<>();

    final GeoCode istanbul100 = new GeoCode(41.020455, 29.000475, 100);
    final String excludedKeywords =
        " -izmir -kocaeli -izmit -adana -ankara -mersin -trabzon -haber -spor -radyo -galatasaray -fenerbahçe -gazete AND -filter:retweets AND -filter:replies";

    final List<String> keywordsWithLocation = Arrays.asList("#myfirsttweet");
    for (final String keyword : keywordsWithLocation) {
      final SearchParameters param =
          new SearchParameters(keyword + excludedKeywords)
              .geoCode(istanbul100)
              .count(100)
              .resultType(ResultType.RECENT);
      params.add(param);
    }

    final SearchParameters param1 =
        new SearchParameters(excludedKeywords)
            .geoCode(istanbul100)
            .lang("tr")
            .count(100)
            .resultType(ResultType.RECENT);
    params.add(param1);

    final List<String> languageCodes =
        Arrays.asList(
            "en", "pl", "ru", "be", "az", "nl", "el", "lt", "lv", "mk", "ru", "sk", "sl", "sv",
            "uk");

    for (final String language : languageCodes) {
      final SearchParameters param =
          new SearchParameters(excludedKeywords)
              .geoCode(istanbul100)
              .lang(language)
              .count(100)
              .resultType(ResultType.RECENT);
      params.add(param);
    }

    final ArrayList<Tweet> tweets = new ArrayList<>();
    for (final String term : terms) {
      final SearchParameters paramTerm =
          new SearchParameters(term + excludedKeywords)
              .count(100)
              .includeEntities(true)
              .resultType(ResultType.RECENT);

      final SearchResults result = this.twitterProfileService.getSearch(paramTerm);
      tweets.addAll(result.getTweets());
    }

    for (final SearchParameters param : params) {
      try {
        final SearchResults result = this.twitterProfileService.getSearch(param);
        tweets.addAll(result.getTweets());
      } catch (final Exception e) {
        System.out.println(e);
      }
    }
    return tweets;
  }

  private boolean isBlacklistedLanguage(final Tweet tweet) {
    final String name = tweet.getUser().getName();
    final String description = tweet.getUser().getDescription();

    if (TwitterSearchController.isProbablyArabic(name)) {
      return true;
    }

    if (TwitterSearchController.isProbablyArabic(description)) {
      return true;
    }

    if (TwitterSearchController.containsHanScript(name)) {
      return true;
    }

    if (TwitterSearchController.containsHanScript(description)) {
      return true;
    }

    if (TwitterSearchController.containsFrench(name)) {
      return true;
    }

    if (TwitterSearchController.containsFrench(description)) {
      return true;
    }

    return false;
  }
}
