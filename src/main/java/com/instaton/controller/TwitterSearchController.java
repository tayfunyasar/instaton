package com.instaton.controller;

import java.io.IOException;
import java.text.Normalizer;
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
import com.instaton.entity.twitter.CustomSearchResults;
import com.instaton.entity.twitter.TwitterUser;
import com.instaton.service.twitter.BlackHashTagEntityService;
import com.instaton.service.twitter.BlackNameEntityService;
import com.instaton.service.twitter.BlackUserIdEntityService;
import com.instaton.service.twitter.TwitterUserService;
import com.instaton.service.twitter.impl.TwitterServiceImpl;
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
    System.out.println(s);
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
      System.out.println(lang);
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

  public static String replaceAllTurkishCharacters(String s) {
    s = Normalizer.normalize(s, Normalizer.Form.NFD);
    return s.replaceAll("[^\\x00-\\x7F]", "");
  }

  @Autowired private TwitterServiceImpl twitterProfileService;

  @Autowired private BlackHashTagEntityService blackKeywordService;

  @Autowired private BlackUserIdEntityService blackUserIdENtityService;

  @Autowired private TwitterUserService twitterUserService;

  @Autowired private BlackNameEntityService blackNameEntityService;

  @Autowired private WebConstants webConstants;

  @GetMapping("/current")
  public CustomSearchResults getCurrent() throws IOException {

    final List<String> terms = this.webConstants.getKeywordList();

    final List<SearchParameters> params = new ArrayList<>();

    final GeoCode geoCode = new GeoCode(41.1191937694787, 29.041500091552734, 100);
    final String excludedKeywords =
        " -izmir -adana -mersin -trabzon -haber -spor -radyo -galatasaray -fenerbahçe -besiktas -gazete AND -filter:retweets AND -filter:replies";
    final SearchParameters param1 =
        new SearchParameters(excludedKeywords).geoCode(geoCode).lang("tr").count(100);
    params.add(param1);
    final SearchParameters param2 =
        new SearchParameters(excludedKeywords)
            .lang("tr")
            .count(100)
            .includeEntities(true)
            .resultType(ResultType.RECENT);
    params.add(param2);

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
      final SearchResults result = this.twitterProfileService.getSearch(param);
      tweets.addAll(result.getTweets());
    }

    final SearchResults search = new SearchResults(tweets, new SearchMetadata(1, 1));

    final List<BlackHashTagEntity> blackKeywordList = this.blackKeywordService.findAll();
    final List<BlackUserIdEntity> blackUserIdList = this.blackUserIdENtityService.findAll();
    final List<TwitterUser> findAllByNotFemale = this.twitterUserService.findAll();
    final List<BlackNameEntity> blackNameEntityList = this.blackNameEntityService.findAll();

    final List<String> blackLanguageList = Arrays.asList("ar", "tt", "pt");

    final CustomSearchResults customSearchResults = new CustomSearchResults(search);

    final List<Tweet> filteredTweets = new ArrayList<>();
    for (final Tweet tweet : search.getTweets()) {
      final String screenName = tweet.getUser().getScreenName();
      final String name = tweet.getUser().getName();

      boolean isContains = false;

      for (final HashTagEntity hashTagEntity : tweet.getEntities().getHashTags()) {
        for (final BlackHashTagEntity blackKeywordItem : blackKeywordList) {
          if (StringUtils.equalsIgnoreCase(
              blackKeywordItem.getKeyword(), hashTagEntity.getText())) {
            isContains = true;
          }
        }
      }

      for (final BlackUserIdEntity blackUserIdEntity : blackUserIdList) {
        if (blackUserIdEntity.getUserId() == tweet.getUser().getId()) {
          isContains = true;
        }
        if (StringUtils.equalsIgnoreCase(blackUserIdEntity.getScreenName(), screenName)) {
          isContains = true;
        }
      }

      for (final BlackNameEntity blackNameEntity : blackNameEntityList) {
        if (StringUtils.startsWithIgnoreCase(
            TwitterSearchController.replaceAllTurkishCharacters(name),
            TwitterSearchController.replaceAllTurkishCharacters(blackNameEntity.getName()))) {
          isContains = true;
          System.out.println(
              "blackNameEntity startsWithIgnoreCase = tweet.getUser().getName() "
                  + name
                  + " == blackNameEntity.getName()) = "
                  + blackNameEntity.getName());
        }
        if (!isContains
            && StringUtils.containsIgnoreCase(
                TwitterSearchController.replaceAllTurkishCharacters(name),
                TwitterSearchController.replaceAllTurkishCharacters(blackNameEntity.getName()))) {
          isContains = true;
          System.out.println(
              "blackNameEntity containsIgnoreCase = tweet.getUser().getName() "
                  + name
                  + " == blackNameEntity.getName()) = "
                  + blackNameEntity.getName());
        }
      }

      for (final TwitterUser twitterUser : findAllByNotFemale) {
        if (tweet.getUser().getId() == twitterUser.getUserId()) {
          isContains = true;
        }
        if (StringUtils.equalsIgnoreCase(twitterUser.getScreenName(), screenName)) {
          isContains = true;
        }
      }
      for (final String languageCode : blackLanguageList) {
        if (StringUtils.equals(tweet.getUser().getLanguage(), languageCode)) {
          isContains = true;
        }
      }

      isContains = this.isBlacklistedLanguage(tweet, isContains);

      if (!isContains) {
        filteredTweets.add(tweet);
      }
    }
    customSearchResults.setFilteredTweets(filteredTweets);

    final List<String> filteredMostUsedHashTags = new ArrayList<>();
    for (final String tag : customSearchResults.getMostUsedHashTags()) {
      boolean isContains = false;
      for (final BlackHashTagEntity blackKeywordItem : blackKeywordList) {
        if (StringUtils.equalsIgnoreCase(blackKeywordItem.getKeyword(), tag)) {
          isContains = true;
        }
      }
      if (!isContains) {
        filteredMostUsedHashTags.add(tag);
      }
    }
    customSearchResults.setFilteredMostUsedHashTags(filteredMostUsedHashTags);

    return customSearchResults;
  }

  private boolean isBlacklistedLanguage(final Tweet tweet, boolean isContains) {
    final String name = tweet.getUser().getName();
    final String description = tweet.getUser().getDescription();

    if (!isContains && TwitterSearchController.isProbablyArabic(name)) {
      isContains = true;
    }
    if (!isContains && TwitterSearchController.isProbablyArabic(description)) {
      isContains = true;
    }

    if (!isContains && TwitterSearchController.containsHanScript(name)) {
      isContains = true;
    }
    if (!isContains && TwitterSearchController.containsHanScript(description)) {
      isContains = true;
    }

    if (!isContains && TwitterSearchController.containsFrench(name)) {
      isContains = true;
    }
    if (!isContains && TwitterSearchController.containsFrench(description)) {
      isContains = true;
    }

    return isContains;
  }
}
