package com.instaton.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.HashTagEntity;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.instaton.entity.black.blackhashtagentity.BlackHashTagEntity;
import com.instaton.entity.black.blackuserid.BlackUserIdEntity;
import com.instaton.entity.twitter.CustomSearchResults;
import com.instaton.service.database.BlackHashTagEntityService;
import com.instaton.service.database.BlackUserIdEntityService;
import com.instaton.service.twitter.TwitterProfileService;

@RestController
@RequestMapping("/api/search")
public class TwitterSearchController {

	@Autowired
	private TwitterProfileService twitterProfileService;

	@Autowired
	private BlackHashTagEntityService blackKeywordService;

	@Autowired
	private BlackUserIdEntityService blackUserIdENtityService;

	@GetMapping("/current")
	public CustomSearchResults getCurrent() {
		final SearchResults search = this.twitterProfileService.getSearch();
		final List<BlackHashTagEntity> blackKeywordList = this.blackKeywordService.findAll();
		final List<BlackUserIdEntity> blackUserIdList = this.blackUserIdENtityService.findAll();

		final CustomSearchResults customSearchResults = new CustomSearchResults(search);

		final List<Tweet> filteredTweets = new ArrayList<>();
		for (final Tweet tweet : search.getTweets()) {
			boolean isContains = false;

			for (final HashTagEntity hashTagEntity : tweet.getEntities().getHashTags()) {
				for (final BlackHashTagEntity blackKeywordItem : blackKeywordList) {
					if (StringUtils.equalsIgnoreCase(blackKeywordItem.getKeyword(), hashTagEntity.getText())) {
						isContains = true;
					}
				}
			}

			for (final BlackUserIdEntity blackUserIdEntity : blackUserIdList) {
				if (blackUserIdEntity.getUserId() == tweet.getUser().getId()) {
					isContains = true;
				}
			}
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

}
