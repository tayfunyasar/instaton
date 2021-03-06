package com.instaton.component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.SearchParameters;
import org.springframework.social.twitter.api.SearchParameters.ResultType;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.stereotype.Component;

import com.instaton.constant.WebConstants;
import com.instaton.entity.social.SearchQueryEntity;
import com.instaton.entity.social.twitter.TwitterUserEntity;
import com.instaton.service.database.SearchQueryService;
import com.instaton.service.impl.twitter.TwitterServiceImpl;
import com.instaton.service.twitter.TwitterUserService;
import com.instaton.util.convert.TwitterUserConverter;

@Component
public class TwitterQueryScheduler {
  public class QueryRunnable implements Runnable {

    private SearchQueryEntity searchQueryEntity;

    public QueryRunnable(final SearchQueryEntity searchQueryEntity) {
      this.searchQueryEntity = searchQueryEntity;
    }

    @Override
    public void run() {
      final LocalDateTime lastVisit = this.searchQueryEntity.getLastVisit();
      System.out.println(lastVisit);
      final LocalDateTime plusMinutes = lastVisit.plusMinutes(this.searchQueryEntity.getInterval());
      System.out.println(this.searchQueryEntity.getInterval());
      System.out.println(plusMinutes);
      final boolean before = plusMinutes.isBefore(LocalDateTime.now());
      System.out.println(before);

      if (before) {
        this.start();
      }
    }

    private void start() {
      System.out.println("query = " + this.searchQueryEntity.getQuery() + " is starting...");

      final LocalDateTime now = LocalDateTime.now();
      this.searchQueryEntity.setLastVisit(now);

      final String query =
          this.searchQueryEntity.getQuery()
              + " "
              + TwitterQueryScheduler.this.webConstants.getExcludedQuery();

      final SearchParameters searchParameters =
          new SearchParameters(query).count(100).resultType(ResultType.RECENT);
      final SearchResults search = TwitterQueryScheduler.this.service.getSearch(searchParameters);
      final List<Tweet> filterTweets1 =
          TwitterQueryScheduler.this.service.filterTweets(search.getTweets());

      final List<Tweet> filterTweets =
          TwitterQueryScheduler.this.service.filterUsers(filterTweets1);

      final double tweetSize = search.getTweets().size();
      final double filteredSize = filterTweets.size();
      final double percentage = filteredSize / tweetSize;
      final double failurePercentage = 1.0 - percentage - 0.9;
      final int interval = this.searchQueryEntity.getInterval();
      final int d = (int) (interval + Math.round(interval * failurePercentage));
      this.searchQueryEntity.setInterval(d);
      System.out.println(
          "query = "
              + this.searchQueryEntity.getQuery()
              + ", tweetSize = "
              + tweetSize
              + ", filteredSize = "
              + filteredSize
              + ", failurePercentage = "
              + failurePercentage
              + ", interval = "
              + interval
              + ", d = "
              + d);

      for (final Tweet tweet : filterTweets) {
        final TwitterUserEntity user = TwitterUserConverter.convert(tweet.getUser());
        user.setSearchQuery(this.searchQueryEntity);

        TwitterQueryScheduler.this.userService.save(user);
      }
      TwitterQueryScheduler.this.userService.evictfindAll();

      TwitterQueryScheduler.this.searchQueryService.save(this.searchQueryEntity);
    }
  }

  @Autowired private TwitterServiceImpl service;

  @Autowired private TwitterUserService userService;

  @Autowired private WebConstants webConstants;

  @Autowired private SearchQueryService searchQueryService;

  public void schedule() {

    final List<SearchQueryEntity> findAll =
        this.searchQueryService.findAllByPlatformTwitterOrderByLastVisitAsc();

    final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    for (final SearchQueryEntity searchQueryEntity : findAll) {

      final QueryRunnable queryRunnable = new QueryRunnable(searchQueryEntity);

      executor.scheduleAtFixedRate(
          queryRunnable, 0L, searchQueryEntity.getInterval(), TimeUnit.MINUTES);

      System.out.println(searchQueryEntity.getQuery() + " -> " + searchQueryEntity.getInterval());
    }
  }
}
