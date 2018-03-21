package com.instaton.entity.social.instagram;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.instaton.entity.AbstractEntity;
import com.instaton.entity.enums.GenderEnum;
import com.instaton.entity.social.SearchQueryEntity;

@Entity
@Table(
  name = "instagram_user",
  uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})}
)
public class InstagramUserEntity extends AbstractEntity {

  //  @OneToOne(mappedBy = "instagramUser")
  //  private UserRelationEntity userRelation;

  @ManyToOne
  @JoinColumn(name = "search_query_id")
  private SearchQueryEntity searchQuery;

  @Enumerated(EnumType.STRING)
  private GenderEnum gender;

  @Column(nullable = false)
  private String username;

  private long pk;

  private boolean isPrivate;
  private boolean isVerified;
  private boolean hasChaining;
  private boolean isBusiness;
  private int mediaCount;
  private String profilePicId;
  private String externalUrl;
  private String fullName;
  private boolean hasBiographyTranslation;
  private boolean hasAnonymousProfilePicture;
  private boolean isFavorite;
  private String privatePhoneCountryCode;
  private String privatePhoneNumber;
  private String privateEmail;
  private int geoMediaCount;
  private int useTagsCount;
  private String profilePicUrl;
  private String addressStreet;
  private String cityName;
  private String zip;
  private String directMessaging;
  private String businessContactMethod;
  private String biography;
  private int followerCount;
  //  private List<InstagramProfilePic> hd_profile_pic_versions;
  //  private InstagramProfilePic hd_profile_pic_url_info;
  private String externalLynxUrl;
  private int followingCount;
  private float latitude;
  private float longitude;
  private String hdProfilePicUrl;

  public String getAddressStreet() {
    return this.addressStreet;
  }

  public String getBiography() {
    return this.biography;
  }

  public String getBusinessContactMethod() {
    return this.businessContactMethod;
  }

  public String getCityName() {
    return this.cityName;
  }

  public String getDirectMessaging() {
    return this.directMessaging;
  }

  public String getExternalLynxUrl() {
    return this.externalLynxUrl;
  }

  public String getExternalUrl() {
    return this.externalUrl;
  }

  public int getFollowerCount() {
    return this.followerCount;
  }

  public int getFollowingCount() {
    return this.followingCount;
  }

  public String getFullName() {
    return this.fullName;
  }

  public GenderEnum getGender() {
    return this.gender;
  }

  public int getGeoMediaCount() {
    return this.geoMediaCount;
  }

  public String getHdProfilePicUrl() {
    return this.hdProfilePicUrl;
  }

  public float getLatitude() {
    return this.latitude;
  }

  public float getLongitude() {
    return this.longitude;
  }

  public int getMediaCount() {
    return this.mediaCount;
  }

  public long getPk() {
    return this.pk;
  }

  public String getPrivateEmail() {
    return this.privateEmail;
  }

  public String getPrivatePhoneCountryCode() {
    return this.privatePhoneCountryCode;
  }

  public String getPrivatePhoneNumber() {
    return this.privatePhoneNumber;
  }

  public String getProfilePicId() {
    return this.profilePicId;
  }

  public String getProfilePicUrl() {
    return this.profilePicUrl;
  }

  public String getUsername() {
    return this.username;
  }

  public int getUseTagsCount() {
    return this.useTagsCount;
  }

  public String getZip() {
    return this.zip;
  }

  public boolean isBusiness() {
    return this.isBusiness;
  }

  public boolean isFavorite() {
    return this.isFavorite;
  }

  public boolean isHasAnonymousProfilePicture() {
    return this.hasAnonymousProfilePicture;
  }

  public boolean isHasBiographyTranslation() {
    return this.hasBiographyTranslation;
  }

  public boolean isHasChaining() {
    return this.hasChaining;
  }

  public boolean isPrivate() {
    return this.isPrivate;
  }

  public boolean isVerified() {
    return this.isVerified;
  }

  public void setAddressStreet(final String addressStreet) {
    this.addressStreet = addressStreet;
  }

  public void setBiography(final String biography) {
    this.biography = biography;
  }

  public void setBusiness(final boolean isBusiness) {
    this.isBusiness = isBusiness;
  }

  public void setBusinessContactMethod(final String businessContactMethod) {
    this.businessContactMethod = businessContactMethod;
  }

  public void setCityName(final String cityName) {
    this.cityName = cityName;
  }

  public void setDirectMessaging(final String directMessaging) {
    this.directMessaging = directMessaging;
  }

  public void setExternalLynxUrl(final String externalLynxUrl) {
    this.externalLynxUrl = externalLynxUrl;
  }

  public void setExternalUrl(final String externalUrl) {
    this.externalUrl = externalUrl;
  }

  public void setFavorite(final boolean isFavorite) {
    this.isFavorite = isFavorite;
  }

  public void setFollowerCount(final int followerCount) {
    this.followerCount = followerCount;
  }

  public void setFollowingCount(final int followingCount) {
    this.followingCount = followingCount;
  }

  public void setFullName(final String fullName) {
    this.fullName = fullName;
  }

  public void setGender(final GenderEnum gender) {
    this.gender = gender;
  }

  public void setGeoMediaCount(final int geoMediaCount) {
    this.geoMediaCount = geoMediaCount;
  }

  public void setHasAnonymousProfilePicture(final boolean hasAnonymousProfilePicture) {
    this.hasAnonymousProfilePicture = hasAnonymousProfilePicture;
  }

  public void setHasBiographyTranslation(final boolean hasBiographyTranslation) {
    this.hasBiographyTranslation = hasBiographyTranslation;
  }

  public void setHasChaining(final boolean hasChaining) {
    this.hasChaining = hasChaining;
  }

  public void setHdProfilePicUrl(final String hdProfilePicUrl) {
    this.hdProfilePicUrl = hdProfilePicUrl;
  }

  public void setLatitude(final float latitude) {
    this.latitude = latitude;
  }

  public void setLongitude(final float longitude) {
    this.longitude = longitude;
  }

  public void setMediaCount(final int mediaCount) {
    this.mediaCount = mediaCount;
  }

  public void setPk(final long pk) {
    this.pk = pk;
  }

  public void setPrivate(final boolean isPrivate) {
    this.isPrivate = isPrivate;
  }

  public void setPrivateEmail(final String privateEmail) {
    this.privateEmail = privateEmail;
  }

  public void setPrivatePhoneCountryCode(final String privatePhoneCountryCode) {
    this.privatePhoneCountryCode = privatePhoneCountryCode;
  }

  public void setPrivatePhoneNumber(final String privatePhoneNumber) {
    this.privatePhoneNumber = privatePhoneNumber;
  }

  public void setProfilePicId(final String profilePicId) {
    this.profilePicId = profilePicId;
  }

  public void setProfilePicUrl(final String profilePicUrl) {
    this.profilePicUrl = profilePicUrl;
  }

  public void setUsername(final String username) {
    this.username = username;
  }

  public void setUseTagsCount(final int useTagsCount) {
    this.useTagsCount = useTagsCount;
  }

  public void setVerified(final boolean isVerified) {
    this.isVerified = isVerified;
  }

  public void setZip(final String zip) {
    this.zip = zip;
  }

public SearchQueryEntity getSearchQuery(){return searchQuery;}

public void setSearchQuery(SearchQueryEntity searchQuery){this.searchQuery = searchQuery;}
}
