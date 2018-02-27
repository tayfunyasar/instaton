package com.instaton.dto.twitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.social.twitter.api.Entities;
import org.springframework.social.twitter.api.HashTagEntity;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Tweet;

import com.instaton.util.SortUtil;

public class CustomSearchResults extends SearchResults {

  private final SearchResults wrapped;

  private List<Tweet> filteredTweets = new ArrayList<>();

  public CustomSearchResults(final SearchResults original) {
    super(original.getTweets(), original.getSearchMetadata());
    this.wrapped = original;
  }

  public List<Tweet> getFilteredTweets() {
    return this.filteredTweets;
  }

  public Set<String> getMostUsedHashTags() {
    final Map<String, Integer> map = new HashMap<>();

    for (final Tweet tweet : this.getFilteredTweets()) {
      final Entities entities = tweet.getEntities();
      final List<HashTagEntity> hashTags = entities.getHashTags();

      for (final HashTagEntity hashTagEntity : hashTags) {
        final String tag = hashTagEntity.getText();
        final Integer count = map.get(tag);
        map.put(tag, count == null ? 1 : count + 1);
      }
    }

    final Map<String, Integer> treeMap = new TreeMap<>(map);

    return SortUtil.sortByValue(treeMap);
  }

  public void setFilteredTweets(final List<Tweet> filteredTweets) {
    this.filteredTweets = filteredTweets;
  }
}
