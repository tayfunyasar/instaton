package com.instaton.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.GeoCode;
import org.springframework.social.twitter.api.SearchMetadata;
import org.springframework.social.twitter.api.SearchParameters;
import org.springframework.social.twitter.api.SearchParameters.ResultType;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.instaton.constant.WebConstants;
import com.instaton.entity.black.BlackHashTagEntity;
import com.instaton.entity.black.BlackNameEntity;
import com.instaton.entity.black.BlackUserIdEntity;
import com.instaton.entity.black.BlackWordEntity;
import com.instaton.entity.twitter.CustomSearchResults;
import com.instaton.entity.twitter.TwitterProfileEntity;
import com.instaton.service.twitter.BlackHashTagEntityService;
import com.instaton.service.twitter.BlackNameEntityService;
import com.instaton.service.twitter.BlackUserIdEntityService;
import com.instaton.service.twitter.BlackWordEntityService;
import com.instaton.service.twitter.TwitterUserService;
import com.instaton.service.twitter.impl.TwitterServiceImpl;
import com.instaton.util.filter.TweetFilter;

@RestController
@RequestMapping("/api/search")
public class TwitterSearchController {

  @Autowired private TwitterServiceImpl twitterProfileService;

  @Autowired private BlackHashTagEntityService blackKeywordService;

  @Autowired private BlackUserIdEntityService blackUserIdENtityService;

  @Autowired private TwitterUserService twitterUserService;

  @Autowired private BlackNameEntityService blackNameEntityService;

  @Autowired private BlackWordEntityService blackWordEntityService;

  @Autowired private WebConstants webConstants;

  private List<Tweet> filterTweets(final List<Tweet> tweets) {
    final List<BlackHashTagEntity> blackKeywordList = this.blackKeywordService.findAll();
    final List<BlackUserIdEntity> blackUserIdList = this.blackUserIdENtityService.findAll();
    final List<TwitterProfileEntity> allTwitterUserList = this.twitterUserService.findAll();
    final List<BlackNameEntity> blackNameEntityList = this.blackNameEntityService.findAll();
    final List<BlackWordEntity> blackWordEntityList = this.blackWordEntityService.findAll();

    final List<Tweet> filteredTweets = new ArrayList<>();
    for (final Tweet tweet : tweets) {

      if (TweetFilter.checkIfBlacklistedLanguage(tweet)) {
        continue;
      }

      if (TweetFilter.checkIfLocationBlacklisted(tweet)) {
        continue;
      }

      if (TweetFilter.checkIfBlackWorded(tweet, blackWordEntityList)) {
        continue;
      }

      if (TweetFilter.checkIfBlackKeywordListed(blackKeywordList, tweet)) {
        continue;
      }

      if (TweetFilter.checkIfBlocked(blackUserIdList, tweet)) {
        continue;
      }

      if (TweetFilter.checkIfBlackName(tweet, blackNameEntityList)) {
        continue;
      }

      if (TweetFilter.checkIfNotVisitedBefore(allTwitterUserList, tweet)) {
        continue;
      }

      if (TweetFilter.isBlacklistedLanguage(tweet)) {
        continue;
      }

      filteredTweets.add(tweet);
    }
    return filteredTweets;
  }

  @GetMapping("/current")
  public CustomSearchResults getCurrent() throws IOException {

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

    for (final String term : terms) {
      final SearchParameters paramTerm =
          new SearchParameters(term + excludedKeywords)
              .count(100)
              .includeEntities(true)
              .resultType(ResultType.RECENT);

      params.add(paramTerm);
    }

    final List<Tweet> tweets = new ArrayList<>();

    params
        .parallelStream()
        .forEach(
            param -> {
              final SearchResults result = this.twitterProfileService.getSearch(param);

              final List<Tweet> filteredTweets = this.filterTweets(result.getTweets());
              tweets.addAll(filteredTweets);
              System.out.println(
                  param.getQuery() + " ==================== " + filteredTweets.size());
            });

    final SearchResults search = new SearchResults(tweets, new SearchMetadata(1, 1));
    final CustomSearchResults customSearchResults = new CustomSearchResults(search);

    customSearchResults.setFilteredTweets(tweets);
    return customSearchResults;
  }
}
