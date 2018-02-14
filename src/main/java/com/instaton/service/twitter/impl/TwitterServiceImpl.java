package com.instaton.service.twitter.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.social.twitter.api.GeoCode;
import org.springframework.social.twitter.api.SearchParameters;
import org.springframework.social.twitter.api.SearchParameters.ResultType;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Service;

import com.instaton.cache.KeyGeneratorConstants;
import com.instaton.constant.CacheConstants;
import com.instaton.constant.WebConstants;

@Service
public class TwitterServiceImpl {

  @Autowired private Twitter twitter;

  @Autowired private WebConstants webConstants;

  @Cacheable(
    cacheNames = CacheConstants.TWITTER_PROFILE_SERVICE_CURRENT,
    keyGenerator = KeyGeneratorConstants.USER_BASED_CACHE_KEY_GENERATOR
  )
  public TwitterProfile getCurrent() {
    return this.twitter.userOperations().getUserProfile();
  }

  // @Cacheable(cacheNames = CacheConstants.TWITTER_SEARCH_CURRENT, keyGenerator = KeyGeneratorConstants.USER_BASED_CACHE_KEY_GENERATOR)
  public SearchResults getSearch(final SearchParameters searchParameters) {

    return this.twitter.searchOperations().search(searchParameters);
  }

  public ArrayList<Tweet> getTweetList() {

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

      final SearchResults result = this.getSearch(paramTerm);
      tweets.addAll(result.getTweets());
    }

    for (final SearchParameters param : params) {
      try {
        final SearchResults result = this.getSearch(param);
        tweets.addAll(result.getTweets());
      } catch (final Exception e) {
        System.out.println(e);
      }
    }
    return tweets;
  }
}
