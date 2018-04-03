package com.instaton.entity.social;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.instaton.entity.AbstractEntity;
import com.instaton.entity.enums.SocialAccountTypeEnum;

@Entity
@Table(
  name = "social_account",
  uniqueConstraints = {@UniqueConstraint(columnNames = {"username", "type"})}
)
public class SocialAccountEntity extends AbstractEntity {

  private String username;
  private String password;

  @Enumerated(EnumType.STRING)
  private SocialAccountTypeEnum type;

  public String getPassword() {
    return this.password;
  }

  public SocialAccountTypeEnum getType() {
    return this.type;
  }

  public String getUsername() {
    return this.username;
  }

  public void setPassword(final String password) {
    this.password = password;
  }

  public void setType(final SocialAccountTypeEnum type) {
    this.type = type;
  }

  public void setUsername(final String username) {
    this.username = username;
  }
}
