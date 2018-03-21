package com.instaton.entity.social.instagram.black;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.instaton.entity.AbstractEntity;

@Entity
@Table(
  name = "instagram_black_username",
  uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})}
)
public class InstagramBlackUsernameEntity extends AbstractEntity {

  @Column(nullable = false)
  private String username;

  public String getUsername() {
    return this.username;
  }

  public void setUsername(final String username) {
    this.username = username;
  }
}
