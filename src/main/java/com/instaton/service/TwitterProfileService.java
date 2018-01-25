package com.instaton.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.social.twitter.api.SearchParameters;
import org.springframework.social.twitter.api.SearchParameters.ResultType;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Service;

import com.instaton.cache.KeyGeneratorConstants;
import com.instaton.constant.CacheConstants;
import com.instaton.entity.twitter.CustomSearchResults;

@Service
public class TwitterProfileService {

	@Autowired
	private Twitter twitter;

	@Cacheable(cacheNames = CacheConstants.TWITTER_PROFILE_SERVICE_CURRENT, keyGenerator = KeyGeneratorConstants.USER_BASED_CACHE_KEY_GENERATOR)
	public TwitterProfile getCurrent() {
		return twitter.userOperations().getUserProfile();
	}

	@Cacheable(cacheNames = CacheConstants.TWITTER_SEARCH_CURRENT, keyGenerator = KeyGeneratorConstants.USER_BASED_CACHE_KEY_GENERATOR)
	public CustomSearchResults getSearch() {
		SearchParameters searchParameters = new SearchParameters("yazilim").count(100).includeEntities(true).resultType(ResultType.RECENT);

		 SearchResults search = twitter.searchOperations().search(searchParameters);
		 return new CustomSearchResults(search);
	}
}
