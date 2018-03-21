package com.instaton.component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.http.client.ClientProtocolException;
import org.brunocvcunha.instagram4j.requests.payload.InstagramFeedItem;
import org.brunocvcunha.instagram4j.requests.payload.InstagramFeedResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.instaton.entity.social.SearchQueryEntity;
import com.instaton.entity.social.instagram.InstagramUserEntity;
import com.instaton.service.database.SearchQueryService;
import com.instaton.service.impl.instagram.InstagramServiceImpl;
import com.instaton.service.instagram.InstagramUserService;
import com.instaton.util.convert.InstagramUserConverter;

@Component
public class InstagramQueryScheduler {
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
        try {
          this.start();
        } catch (final IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }

    private void start() throws ClientProtocolException, IOException {
      System.out.println("query = " + this.searchQueryEntity.getQuery() + " is starting...");

      final LocalDateTime now = LocalDateTime.now();
      this.searchQueryEntity.setLastVisit(now);

      final String query = this.searchQueryEntity.getQuery();

      final InstagramFeedResult feed =
          InstagramQueryScheduler.this.service.getInstagramFeedResult(query);

      final List<InstagramFeedItem> filterItems =
          InstagramQueryScheduler.this.service.filterUsers(feed.getItems());

      for (final InstagramFeedItem instagramFeedItem : filterItems) {

        final InstagramUserEntity user =
            InstagramUserConverter.convert(instagramFeedItem.getUser());
        user.setSearchQuery(this.searchQueryEntity);

        InstagramQueryScheduler.this.userService.save(user);
      }
      InstagramQueryScheduler.this.userService.evictfindAll();

      InstagramQueryScheduler.this.searchQueryService.save(this.searchQueryEntity);
    }
  }

  @Autowired private InstagramServiceImpl service;

  @Autowired private InstagramUserService userService;

  @Autowired private SearchQueryService searchQueryService;

  public void schedule() {

    final List<SearchQueryEntity> findAll =
        this.searchQueryService.findAllByPlatformInstagramOrderByLastVisitAsc();

    final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    for (final SearchQueryEntity searchQueryEntity : findAll) {

      final QueryRunnable queryRunnable = new QueryRunnable(searchQueryEntity);

      executor.scheduleAtFixedRate(
          queryRunnable, 0L, searchQueryEntity.getInterval(), TimeUnit.MINUTES);

      System.out.println(searchQueryEntity.getQuery() + " -> " + searchQueryEntity.getInterval());
    }
  }
}
