package com.instaton.entity.social.instagram;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.instaton.entity.AbstractEntity;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class InstagramUserSummaryEntity extends AbstractEntity {

  private boolean is_verified;
  private String profile_pic_id;
  private boolean is_favorite;
  private boolean is_private;
  private String username;
  private long pk;
  private String profile_pic_url;
  private boolean has_anonymous_profile_picture;
  private String full_name;

  public String getFull_name() {
    return this.full_name;
  }

  public long getPk() {
    return this.pk;
  }

  public String getProfile_pic_id() {
    return this.profile_pic_id;
  }

  public String getProfile_pic_url() {
    return this.profile_pic_url;
  }

  public String getUsername() {
    return this.username;
  }

  public boolean isHas_anonymous_profile_picture() {
    return this.has_anonymous_profile_picture;
  }

  public boolean isIs_favorite() {
    return this.is_favorite;
  }

  public boolean isIs_private() {
    return this.is_private;
  }

  public boolean isIs_verified() {
    return this.is_verified;
  }

  public void setFull_name(final String full_name) {
    this.full_name = full_name;
  }

  public void setHas_anonymous_profile_picture(final boolean has_anonymous_profile_picture) {
    this.has_anonymous_profile_picture = has_anonymous_profile_picture;
  }

  public void setIs_favorite(final boolean is_favorite) {
    this.is_favorite = is_favorite;
  }

  public void setIs_private(final boolean is_private) {
    this.is_private = is_private;
  }

  public void setIs_verified(final boolean is_verified) {
    this.is_verified = is_verified;
  }

  public void setPk(final long pk) {
    this.pk = pk;
  }

  public void setProfile_pic_id(final String profile_pic_id) {
    this.profile_pic_id = profile_pic_id;
  }

  public void setProfile_pic_url(final String profile_pic_url) {
    this.profile_pic_url = profile_pic_url;
  }

  public void setUsername(final String username) {
    this.username = username;
  }
}
