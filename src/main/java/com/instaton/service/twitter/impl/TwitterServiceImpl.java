package com.instaton.service.twitter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.social.twitter.api.GeoCode;
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

	@Cacheable(cacheNames = CacheConstants.TWITTER_SEARCH_CURRENT, keyGenerator = KeyGeneratorConstants.USER_BASED_CACHE_KEY_GENERATOR)
	public SearchResults getSearch() {
		// yeniprofilresmi
		// yeniprofilfotografi
		// sariyer
		// jjvadistanbul
		// taksim
		// levent
		// kagithane
		// tarabya
		// maslak
		// @sariyerbel
		// mecidiyekoy
		// istanbul
		// @atasehirbld
		// @CookshopTr
		// @tavairports
		// @toruncentercom
		// @Cinemaximum
		// @CapacityAvm
		// @ZorluCenter
		// @Eataly_ist
		// @thehousecafethc
		final GeoCode geoCode = new GeoCode(41.1191937694787, 29.041500091552734, 50);
		final SearchParameters searchParameters = new SearchParameters(" ").geoCode(geoCode).lang("tr").count(1000);
		// final SearchParameters searchParameters = new SearchParameters("haciosman").count(100).includeEntities(true).resultType(ResultType.RECENT);

		return this.twitter.searchOperations().search(searchParameters);
	}
}
