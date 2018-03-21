package com.instaton.entity.social.twitter;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.instaton.entity.AbstractEntity;
import com.instaton.entity.enums.GenderEnum;
import com.instaton.entity.social.SearchQueryEntity;

@Entity
@Table(
  name = "twitter_user",
  uniqueConstraints = {@UniqueConstraint(columnNames = {"screenName"})}
)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TwitterUserEntity extends AbstractEntity {

  //  @OneToOne(mappedBy = "twitterUser")
  //  private UserRelationEntity userRelation;

  @ManyToOne
  @JoinColumn(name = "search_query_id")
  private SearchQueryEntity searchQuery;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private GenderEnum gender;

  @Column(nullable = false)
  private String userId;

  @Column(nullable = false)
  private String screenName;

  private String name;

  private String profileImageUrl;

  private String description;

  private String location;

  private Date userCreatedDate;

  private String language;

  private Integer statusesCount;

  private Integer friendsCount;

  private Integer followersCount;

  private Integer favoritesCount;

  private Integer listedCount;

  private Boolean following;

  private Boolean followRequestSent;

  private Boolean isProtected;

  private Boolean notificationsEnabled;

  private Boolean verified;

  private Boolean geoEnabled;

  private Boolean contributorsEnabled;

  private Boolean translator;

  private String timeZone;

  private Integer utcOffset;

  private String backgroundImageUrl;

  private String profileBannerUrl;

  public String getBackgroundImageUrl() {
    return this.backgroundImageUrl;
  }

  public String getDescription() {
    return this.description;
  }

  public Integer getFavoritesCount() {
    return this.favoritesCount;
  }

  public Integer getFollowersCount() {
    return this.followersCount;
  }

  public Integer getFriendsCount() {
    return this.friendsCount;
  }

  public GenderEnum getGender() {
    return this.gender;
  }

  public String getLanguage() {
    return this.language;
  }

  public Integer getListedCount() {
    return this.listedCount;
  }

  public String getLocation() {
    return this.location;
  }

  public String getName() {
    return this.name;
  }

  public String getProfileBannerUrl() {
    return this.profileBannerUrl;
  }

  public String getProfileImageUrl() {
    return this.profileImageUrl;
  }

  public String getProfileUrl() {
    return "http://twitter.com/" + this.screenName;
  }

  public String getScreenName() {
    return this.screenName;
  }

  public SearchQueryEntity getSearchQuery() {
    return this.searchQuery;
  }

  public Integer getStatusesCount() {
    return this.statusesCount;
  }

  public String getTimeZone() {
    return this.timeZone;
  }

  public Date getUserCreatedDate() {
    return this.userCreatedDate;
  }

  public String getUserId() {
    return this.userId;
  }

  public Integer getUtcOffset() {
    return this.utcOffset;
  }

  public Boolean isContributorsEnabled() {
    return this.contributorsEnabled;
  }

  public Boolean isFollowing() {
    return this.following;
  }

  public Boolean isFollowRequestSent() {
    return this.followRequestSent;
  }

  public Boolean isGeoEnabled() {
    return this.geoEnabled;
  }

  public Boolean isNotificationsEnabled() {
    return this.notificationsEnabled;
  }

  public Boolean isProtected() {
    return this.isProtected;
  }

  public Boolean isTranslator() {
    return this.translator;
  }

  public Boolean isVerified() {
    return this.verified;
  }

  public void setBackgroundImageUrl(final String backgroundImageUrl) {
    this.backgroundImageUrl = backgroundImageUrl;
  }

  public void setContributorsEnabled(final Boolean contributorsEnabled) {
    this.contributorsEnabled = contributorsEnabled;
  }

  public void setDescription(final String description) {
    this.description = description;
  }

  public void setFavoritesCount(final Integer favoritesCount) {
    this.favoritesCount = favoritesCount;
  }

  public void setFollowersCount(final Integer followersCount) {
    this.followersCount = followersCount;
  }

  public void setFollowing(final Boolean following) {
    this.following = following;
  }

  public void setFollowRequestSent(final Boolean followRequestSent) {
    this.followRequestSent = followRequestSent;
  }

  public void setFriendsCount(final Integer friendsCount) {
    this.friendsCount = friendsCount;
  }

  public void setGender(final GenderEnum gender) {
    this.gender = gender;
  }

  public void setGeoEnabled(final Boolean geoEnabled) {
    this.geoEnabled = geoEnabled;
  }

  public void setLanguage(final String language) {
    this.language = language;
  }

  public void setListedCount(final Integer listedCount) {
    this.listedCount = listedCount;
  }

  public void setLocation(final String location) {
    this.location = location;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public void setNotificationsEnabled(final Boolean notificationsEnabled) {
    this.notificationsEnabled = notificationsEnabled;
  }

  public void setProfileBannerUrl(final String profileBannerUrl) {
    this.profileBannerUrl = profileBannerUrl;
  }

  public void setProfileImageUrl(final String profileImageUrl) {
    this.profileImageUrl = profileImageUrl;
  }

  public void setProtected(final Boolean isProtected) {
    this.isProtected = isProtected;
  }

  public void setScreenName(final String screenName) {
    this.screenName = screenName;
  }

  public void setSearchQuery(final SearchQueryEntity searchQuery) {
    this.searchQuery = searchQuery;
  }

  public void setStatusesCount(final Integer statusesCount) {
    this.statusesCount = statusesCount;
  }

  public void setTimeZone(final String timeZone) {
    this.timeZone = timeZone;
  }

  public void setTranslator(final Boolean translator) {
    this.translator = translator;
  }

  public void setUserCreatedDate(final Date userCreatedDate) {
    this.userCreatedDate = userCreatedDate;
  }

  public void setUserId(final String userId) {
    this.userId = userId;
  }

  public void setUtcOffset(final Integer utcOffset) {
    this.utcOffset = utcOffset;
  }

  public void setVerified(final Boolean verified) {
    this.verified = verified;
  }

  @Override
  public String toString() {
    return "TwitterUserEntity [searchQuery="
        + this.searchQuery
        + ", screenName="
        + this.screenName
        + ", name="
        + this.name
        + ", description="
        + this.description
        + ", location="
        + this.location
        + "]";
  }
}
