package com.instaton.entity.twitter;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.instaton.entity.AbstractEntity;
import com.instaton.entity.GenderEnum;

@Entity
@Table(name = "twitteruser", uniqueConstraints = { @UniqueConstraint(columnNames = { "userId" }) })
public class TwitterUser extends AbstractEntity {

	@Enumerated(EnumType.STRING)
	private GenderEnum gender;

	private long userId;

	private String screenName;

	private String name;

	private String url;

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

	private String sidebarBorderColor;

	private String sidebarFillColor;

	private String backgroundColor;

	private Boolean useBackgroundImage;

	private String backgroundImageUrl;

	private Boolean backgroundImageTiled;

	private String textColor;

	private String linkColor;

	private Boolean showAllInlineMedia;

	private String profileBannerUrl;

	public String getBackgroundColor() {
		return this.backgroundColor;
	}

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

	public String getLinkColor() {
		return this.linkColor;
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

	public String getScreenName() {
		return this.screenName;
	}

	public String getSidebarBorderColor() {
		return this.sidebarBorderColor;
	}

	public String getSidebarFillColor() {
		return this.sidebarFillColor;
	}

	public Integer getStatusesCount() {
		return this.statusesCount;
	}

	public String getTextColor() {
		return this.textColor;
	}

	public String getTimeZone() {
		return this.timeZone;
	}

	public String getUrl() {
		return this.url;
	}

	public long getUserId() {
		return this.userId;
	}

	public Integer getUtcOffset() {
		return this.utcOffset;
	}

	public Boolean isBackgroundImageTiled() {
		return this.backgroundImageTiled;
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

	public Boolean isShowAllInlineMedia() {
		return this.showAllInlineMedia;
	}

	public Boolean isTranslator() {
		return this.translator;
	}

	public Boolean isUseBackgroundImage() {
		return this.useBackgroundImage;
	}

	public Boolean isVerified() {
		return this.verified;
	}

	public void setBackgroundColor(final String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public void setBackgroundImageTiled(final Boolean backgroundImageTiled) {
		this.backgroundImageTiled = backgroundImageTiled;
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

	public void setLinkColor(final String linkColor) {
		this.linkColor = linkColor;
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

	public void setShowAllInlineMedia(final Boolean showAllInlineMedia) {
		this.showAllInlineMedia = showAllInlineMedia;
	}

	public void setSidebarBorderColor(final String sidebarBorderColor) {
		this.sidebarBorderColor = sidebarBorderColor;
	}

	public void setSidebarFillColor(final String sidebarFillColor) {
		this.sidebarFillColor = sidebarFillColor;
	}

	public void setStatusesCount(final Integer statusesCount) {
		this.statusesCount = statusesCount;
	}

	public void setTextColor(final String textColor) {
		this.textColor = textColor;
	}

	public void setTimeZone(final String timeZone) {
		this.timeZone = timeZone;
	}

	public void setTranslator(final Boolean translator) {
		this.translator = translator;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

	public void setUseBackgroundImage(final Boolean useBackgroundImage) {
		this.useBackgroundImage = useBackgroundImage;
	}

	public void setUserId(final long userId) {
		this.userId = userId;
	}

	public void setUtcOffset(final Integer utcOffset) {
		this.utcOffset = utcOffset;
	}

	public void setVerified(final Boolean verified) {
		this.verified = verified;
	}

	public Date getUserCreatedDate() {
		return userCreatedDate;
	}

	public void setUserCreatedDate(Date userCreatedDate) {
		this.userCreatedDate = userCreatedDate;
	}

}
