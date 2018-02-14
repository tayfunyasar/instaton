package com.instaton.entity.twitter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.instaton.entity.AbstractEntity;

@Entity
@Table(name = "tweet")
public class TweetEntity extends AbstractEntity {

  private String text;
  private String profileImageUrl;
  private String languageCode;

  @OneToOne(optional = false)
  @JoinColumn(name = "userId")
  private TwitterProfileEntity user;

  public String getLanguageCode() {
    return this.languageCode;
  }

  public String getProfileImageUrl() {
    return this.profileImageUrl;
  }

  public String getText() {
    return this.text;
  }

  public TwitterProfileEntity getUser() {
    return this.user;
  }

  public void setLanguageCode(final String languageCode) {
    this.languageCode = languageCode;
  }

  public void setProfileImageUrl(final String profileImageUrl) {
    this.profileImageUrl = profileImageUrl;
  }

  public void setText(final String text) {
    this.text = text;
  }

  public void setUser(final TwitterProfileEntity user) {
    this.user = user;
  }
}
