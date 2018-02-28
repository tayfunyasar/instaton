package com.instaton.entity.social.twitter;
//package com.instaton.entity.twitter;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Entity;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//import com.instaton.entity.AbstractEntity;
//
//@Entity
//@Table(name = "tweet")
//public class TweetEntity extends AbstractEntity {
//
//  private String text;
//  private String tweetId;
//
//  @ManyToOne(optional = false, cascade = CascadeType.REMOVE)
//  @JoinColumn(referencedColumnName = "userId")
//  private TwitterUserEntity user;
//
//  public String getText() {
//    return this.text;
//  }
//
//  public String getTweetId() {
//    return this.tweetId;
//  }
//
//  public TwitterUserEntity getUser() {
//    return this.user;
//  }
//
//  public void setText(final String text) {
//    this.text = text;
//  }
//
//  public void setTweetId(final String tweetId) {
//    this.tweetId = tweetId;
//  }
//
//  public void setUser(final TwitterUserEntity user) {
//    this.user = user;
//  }
//}
