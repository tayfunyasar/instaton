package com.instaton.service.twitter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.social.twitter.api.SearchParameters;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Service;

import com.instaton.cache.KeyGeneratorConstants;
import com.instaton.constant.CacheConstants;

@Service
public class TwitterServiceImpl {

	@Autowired
	private Twitter twitter;

	@Cacheable(cacheNames = CacheConstants.TWITTER_PROFILE_SERVICE_CURRENT, keyGenerator = KeyGeneratorConstants.USER_BASED_CACHE_KEY_GENERATOR)
	public TwitterProfile getCurrent() {
		return this.twitter.userOperations().getUserProfile();
	}

	// @Cacheable(cacheNames = CacheConstants.TWITTER_SEARCH_CURRENT, keyGenerator = KeyGeneratorConstants.USER_BASED_CACHE_KEY_GENERATOR)
	public SearchResults getSearch(final SearchParameters searchParameters) {

		return this.twitter.searchOperations().search(searchParameters);
	}
}
