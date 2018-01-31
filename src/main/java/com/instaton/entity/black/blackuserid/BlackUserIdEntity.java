package com.instaton.entity.black.blackuserid;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.instaton.entity.AbstractEntity;

@Entity
@Table(name = "blackuseridentity")
public class BlackUserIdEntity extends AbstractEntity {

  private long userId;
  private String screenName;

  public String getScreenName() {
    return this.screenName;
  }

  public long getUserId() {
    return this.userId;
  }

  public void setScreenName(final String screenName) {
    this.screenName = screenName;
  }

  public void setUserId(final long userId) {
    this.userId = userId;
  }
}
