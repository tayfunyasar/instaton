package com.instaton.service.impl.twitter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.social.twitter.api.SearchParameters;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Service;

import com.instaton.config.cache.KeyGeneratorConstants;
import com.instaton.constant.CacheConstants;
import com.instaton.entity.social.BlackHashTagEntity;
import com.instaton.entity.social.BlackNameEntity;
import com.instaton.entity.social.BlackWordEntity;
import com.instaton.entity.social.twitter.TwitterUserEntity;
import com.instaton.service.twitter.BlackHashTagEntityService;
import com.instaton.service.twitter.BlackNameEntityService;
import com.instaton.service.twitter.BlackWordEntityService;
import com.instaton.service.twitter.TwitterUserService;
import com.instaton.util.filter.TweetFilter;

@Service
public class TwitterServiceImpl {

  @Autowired private Twitter twitter;

  @Autowired private BlackHashTagEntityService blackKeywordService;

  @Autowired private TwitterUserService twitterUserService;

  @Autowired private BlackNameEntityService blackNameEntityService;

  @Autowired private BlackWordEntityService blackWordEntityService;

  public List<Tweet> filterTweets(final List<Tweet> tweets) {
    final List<BlackHashTagEntity> blackKeywordList = this.blackKeywordService.findAll();
    final List<BlackNameEntity> blackNameEntityList = this.blackNameEntityService.findAll();
    final List<BlackWordEntity> blackWordEntityList = this.blackWordEntityService.findAll();

    final List<Tweet> filteredTweets = new ArrayList<>();
    final List<String> uniqueUserIdList = new ArrayList<>();

    for (final Tweet tweet : tweets) {

      if (TweetFilter.checkIfHasUnexpectedFollowersAndFriendsCount(tweet)) {
        continue;
      }

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

      if (TweetFilter.checkIfBlackName(tweet, blackNameEntityList)) {
        continue;
      }

      if (TweetFilter.isBlacklistedLanguage(tweet)) {
        continue;
      }

      final String valueOf = String.valueOf(tweet.getUser().getId());
      if (!uniqueUserIdList.contains(valueOf)) {
        filteredTweets.add(tweet);
        uniqueUserIdList.add(valueOf);
      }
    }
    return filteredTweets;
  }

  public List<Tweet> filterUsers(final List<Tweet> tweets) {
    final List<TwitterUserEntity> allTwitterUserList = this.twitterUserService.findAll();

    final List<Tweet> filteredTweets = new ArrayList<>();
    final List<String> userIdList = new ArrayList<>();
    for (final Tweet tweet : tweets) {

      if (TweetFilter.checkIfNotVisitedBefore(allTwitterUserList, tweet)) {
        continue;
      }

      final String valueOf = String.valueOf(tweet.getUser().getId());
      if (!userIdList.contains(valueOf)) {
        filteredTweets.add(tweet);
        userIdList.add(valueOf);
      }
    }

    return filteredTweets;
  }

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
}
