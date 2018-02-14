package com.instaton.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.SearchMetadata;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

  private List<Tweet> filterTweets(final SearchResults search) {
    final List<BlackHashTagEntity> blackKeywordList = this.blackKeywordService.findAll();
    final List<BlackUserIdEntity> blackUserIdList = this.blackUserIdENtityService.findAll();
    final List<TwitterProfileEntity> allTwitterUserList = this.twitterUserService.findAll();
    final List<BlackNameEntity> blackNameEntityList = this.blackNameEntityService.findAll();
    final List<BlackWordEntity> blackWordEntityList = this.blackWordEntityService.findAll();

    final List<Tweet> filteredTweets = new ArrayList<>();
    for (final Tweet tweet : search.getTweets()) {
      boolean isContains = false;

      if (!isContains) {
        isContains = TweetFilter.checkIfBlacklistedLanguage(tweet);
      }

      if (!isContains) {
        isContains = TweetFilter.checkIfLocationBlacklisted(tweet);
      }

      if (!isContains) {
        isContains = TweetFilter.checkIfBlackWorded(tweet, blackWordEntityList);
      }

      if (!isContains) {
        isContains = TweetFilter.checkIfBlackKeywordListed(blackKeywordList, tweet);
      }

      if (!isContains) {
        isContains = TweetFilter.checkIfBlocked(blackUserIdList, tweet);
      }

      if (!isContains) {
        isContains = TweetFilter.checkIfBlackName(tweet, blackNameEntityList);
      }

      if (!isContains) {
        isContains = TweetFilter.checkIfNotVisitedBefore(allTwitterUserList, tweet);
      }

      if (!isContains) {
        isContains = TweetFilter.isBlacklistedLanguage(tweet);
      }

      if (!isContains) {
        filteredTweets.add(tweet);
      }
    }
    return filteredTweets;
  }

  @GetMapping("/current")
  public CustomSearchResults getCurrent() throws IOException {

    final ArrayList<Tweet> tweets = this.twitterProfileService.getTweetList();

    final SearchResults search = new SearchResults(tweets, new SearchMetadata(1, 1));
    final CustomSearchResults customSearchResults = new CustomSearchResults(search);

    final List<Tweet> filteredTweets = this.filterTweets(search);
    customSearchResults.setFilteredTweets(filteredTweets);

    return customSearchResults;
  }
}
