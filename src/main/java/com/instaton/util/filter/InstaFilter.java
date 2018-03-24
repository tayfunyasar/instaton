package com.instaton.util.filter;

import java.util.List;

import org.brunocvcunha.instagram4j.requests.payload.InstagramFeedItem;

import com.instaton.entity.social.instagram.InstagramUserEntity;

public class InstaFilter {

  public static boolean checkIfNotVisitedBefore(
      final List<InstagramUserEntity> userList, final InstagramFeedItem item) {

    for (final InstagramUserEntity user : userList) {
      if (item.getUser().getPk() == user.getPk()) {
        return true;
      }
    }
    return false;
  }

  private InstaFilter() {
    throw new IllegalAccessError();
  }
}
