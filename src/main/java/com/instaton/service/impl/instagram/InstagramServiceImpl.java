package com.instaton.service.impl.instagram;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramTagFeedRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramFeedItem;
import org.brunocvcunha.instagram4j.requests.payload.InstagramFeedResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instaton.constant.WebConstants;
import com.instaton.entity.social.BlackNameEntity;
import com.instaton.entity.social.instagram.InstagramUserEntity;
import com.instaton.service.instagram.InstagramUserService;
import com.instaton.service.twitter.BlackNameEntityService;
import com.instaton.util.filter.InstaFilter;

@Service
public class InstagramServiceImpl {

  @Autowired private InstagramUserService instagramUserService;
  //  @Autowired private InstagramBlackFullnameEntityService instagramBlackUsernameEntityService;
  @Autowired private BlackNameEntityService blackNameEntityService;

  @Autowired private WebConstants webConstants;

  private Instagram4j instagram = null;

  public List<InstagramFeedItem> filter(final List<InstagramFeedItem> items) {
    final List<InstagramFeedItem> itemList = new ArrayList<>();
    //    final List<InstagramBlackUsernameEntity> instagramBlackUsernameEntityList =
    //        this.instagramBlackUsernameEntityService.findAll();
    final List<BlackNameEntity> blackNameEntityList = this.blackNameEntityService.findAll();

    for (final InstagramFeedItem item : items) {

      //      if (InstaFilter.checkIfHasUnexpectedFollowersAndFriendsCount(instagramFeedItem)) {
      //        continue;
      //      }

      if (InstaFilter.checkIfBlackFullname(item, blackNameEntityList)) {
        continue;
      }

      itemList.add(item);
    }

    return itemList;
  }

  public List<InstagramFeedItem> filterUsers(final List<InstagramFeedItem> items) {
    final List<InstagramUserEntity> allInstagramUserList = this.instagramUserService.findAll();

    final List<InstagramFeedItem> filteredTweets = new ArrayList<>();
    final List<String> userIdList = new ArrayList<>();
    for (final InstagramFeedItem item : items) {

      if (InstaFilter.checkIfNotVisitedBefore(allInstagramUserList, item)) {
        continue;
      }

      final String valueOf = String.valueOf(item.getUser().getPk());
      if (!userIdList.contains(valueOf)) {
        filteredTweets.add(item);
        userIdList.add(valueOf);
      }
    }

    return filteredTweets;
  }

  public InstagramFeedResult getInstagramFeedResult(final String tag)
      throws ClientProtocolException, IOException {
    final InstagramFeedResult tagFeed =
        this.getInstance().sendRequest(new InstagramTagFeedRequest(tag));

    return tagFeed;
  }

  private Instagram4j getInstance() throws ClientProtocolException, IOException {
    if (this.instagram == null) {
      this.instagram =
          Instagram4j.builder()
              .username(this.webConstants.getInstagramUsername())
              .password(this.webConstants.getInstagramPassword())
              .build();

      this.instagram.setup();
      this.instagram.login();
    }

    return this.instagram;
  }
}
