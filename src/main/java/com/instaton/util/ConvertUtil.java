package com.instaton.util;

import org.springframework.social.twitter.api.TwitterProfile;

import com.instaton.entity.twitter.TwitterUserEntity;

public class ConvertUtil {

  public static TwitterUserEntity convert(final TwitterProfile input) {
    final TwitterUserEntity output = new TwitterUserEntity();

    output.setUserId(String.valueOf(input.getId()));
    output.setScreenName(input.getScreenName());
    output.setName(input.getName());
    output.setUrl(input.getUrl());
    output.setProfileImageUrl(input.getProfileImageUrl());
    output.setDescription(input.getDescription());
    output.setLocation(input.getLocation());

    final java.sql.Date sqlDate = new java.sql.Date(input.getCreatedDate().getTime());
    output.setUserCreatedDate(sqlDate);

    output.setLanguage(input.getLanguage());
    output.setStatusesCount(input.getStatusesCount());
    output.setFriendsCount(input.getFriendsCount());
    output.setFollowersCount(input.getFollowersCount());
    output.setListedCount(input.getListedCount());
    output.setFollowing(input.isFollowing());
    output.setFollowRequestSent(input.isFollowRequestSent());
    output.setProtected(input.isProtected());
    output.setNotificationsEnabled(input.isNotificationsEnabled());
    output.setVerified(input.isVerified());
    output.setGeoEnabled(input.isGeoEnabled());
    output.setContributorsEnabled(input.isContributorsEnabled());
    output.setTranslator(input.isTranslator());
    output.setTimeZone(input.getTimeZone());
    output.setUtcOffset(input.getUtcOffset());
    output.setSidebarBorderColor(input.getSidebarBorderColor());
    output.setSidebarFillColor(input.getSidebarFillColor());
    output.setBackgroundColor(input.getBackgroundColor());
    output.setUseBackgroundImage(input.useBackgroundImage());
    output.setBackgroundImageUrl(input.getBackgroundImageUrl());
    output.setBackgroundImageTiled(input.isBackgroundImageTiled());
    output.setTextColor(input.getTextColor());
    output.setLinkColor(input.getLinkColor());
    output.setShowAllInlineMedia(input.showAllInlineMedia());
    output.setProfileBannerUrl(input.getProfileBannerUrl());

    return output;
  }

  private ConvertUtil() {
    // NO-OP
  }
}
