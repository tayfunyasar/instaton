package com.instaton.entity.twitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.springframework.social.twitter.api.Entities;
import org.springframework.social.twitter.api.HashTagEntity;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Tweet;

import com.instaton.util.SortUtil;

public class CustomSearchResults extends SearchResults {

	private SearchResults wrapped;

	private List<HashTagEntity> allHashTags = new ArrayList<>();

	public CustomSearchResults(SearchResults original) {
		super(original.getTweets(), original.getSearchMetadata());
		this.wrapped = original;
	}

	public Set<String> getMostUsedHashTags() {
		Map<String, Integer> map = new HashMap<>();

		for (Tweet tweet : wrapped.getTweets()) {
			Entities entities = tweet.getEntities();
			List<HashTagEntity> hashTags = entities.getHashTags();

			for (HashTagEntity hashTagEntity : hashTags) {
				String tag = hashTagEntity.getText();
				Integer count = map.get(tag);
				map.put(tag, (count == null) ? 1 : count + 1);
			}
		}

		Map<String, Integer> treeMap = new TreeMap<>(map);

		return SortUtil.sortByValue(treeMap);
	}

	public List<HashTagEntity> getAllHashTags() {
		return allHashTags;
	}

	public void setAllHashTags(List<HashTagEntity> allHashTags) {
		this.allHashTags = allHashTags;
	}

}
