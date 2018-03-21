package com.instaton.util.convert;

import org.springframework.social.twitter.api.TwitterProfile;

import com.instaton.entity.social.twitter.TwitterUserEntity;

public class TwitterUserConverter {

  public static TwitterUserEntity convert(final TwitterProfile p) {
    final TwitterUserEntity user = new TwitterUserEntity();

    user.setUserId(String.valueOf(p.getId()));
    user.setScreenName(p.getScreenName());
    user.setName(p.getName());
    user.setProfileImageUrl(p.getProfileImageUrl());
    user.setDescription(p.getDescription());
    user.setLocation(p.getLocation());
    user.setLanguage(p.getLanguage());
    user.setFavoritesCount(p.getFavoritesCount());
    user.setFollowersCount(p.getFollowersCount());
    user.setFollowRequestSent(p.isFollowRequestSent());
    user.setFriendsCount(p.getFriendsCount());
    user.setProtected(p.isProtected());
    user.setStatusesCount(p.getStatusesCount());
    user.setTimeZone(p.getTimeZone());
    user.setVerified(p.isVerified());
    user.setUtcOffset(p.getUtcOffset());
    user.setBackgroundImageUrl(p.getBackgroundImageUrl());
    user.setContributorsEnabled(p.isContributorsEnabled());
    user.setGeoEnabled(p.isGeoEnabled());
    user.setListedCount(p.getListedCount());
    user.setProfileBannerUrl(p.getProfileBannerUrl());

    final long time = p.getCreatedDate().getTime();
    final java.sql.Date date = new java.sql.Date(time);
    user.setUserCreatedDate(date);
    return user;
  }

  private TwitterUserConverter() {
    throw new IllegalAccessError();
  }
}
