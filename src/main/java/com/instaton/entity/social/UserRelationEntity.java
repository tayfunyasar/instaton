package com.instaton.entity.social;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.instaton.entity.AbstractEntity;
import com.instaton.entity.social.instagram.InstagramUserEntity;
import com.instaton.entity.social.twitter.TwitterUserEntity;

@Entity
@Table(name = "user_relation")
public class UserRelationEntity extends AbstractEntity {

  @OneToOne
  @JoinColumn(name = "twitter_user__screenname", referencedColumnName = "screenName")
  private TwitterUserEntity twitterUser;

  @OneToOne
  @JoinColumn(name = "instagram_user__username", referencedColumnName = "username")
  private InstagramUserEntity instagramUser;

  public InstagramUserEntity getInstagramUser() {
    return this.instagramUser;
  }

  public TwitterUserEntity getTwitterUser() {
    return this.twitterUser;
  }

  public void setInstagramUser(final InstagramUserEntity instagramUser) {
    this.instagramUser = instagramUser;
  }

  public void setTwitterUser(final TwitterUserEntity twitterUser) {
    this.twitterUser = twitterUser;
  }
}
