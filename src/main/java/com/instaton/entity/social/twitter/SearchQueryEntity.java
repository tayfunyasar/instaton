package com.instaton.entity.social.twitter;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.instaton.entity.AbstractEntity;
import com.instaton.entity.enums.PlatformEnum;

@Entity
@Table(
  name = "search_query",
  uniqueConstraints = {@UniqueConstraint(columnNames = {"query", "platform"})}
)
public class SearchQueryEntity extends AbstractEntity {

  //  @OneToMany(mappedBy = "searchQuery")
  //  private List<TwitterUserEntity> twitterUsers;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private PlatformEnum platform;

  @Column(nullable = false)
  private String query;

  private int interval;
  private LocalDateTime lastVisit;
  private Integer priority = 0;

  public int getInterval() {
    return this.interval;
  }

  public LocalDateTime getLastVisit() {
    return this.lastVisit;
  }

  public PlatformEnum getPlatform() {
    return this.platform;
  }

  public Integer getPriority() {
    return this.priority;
  }

  public String getQuery() {
    return this.query;
  }

  public void setInterval(final int interval) {
    this.interval = interval;
  }

  public void setLastVisit(final LocalDateTime lastVisit) {
    this.lastVisit = lastVisit;
  }

  public void setPlatform(final PlatformEnum platform) {
    this.platform = platform;
  }

  public void setPriority(final Integer priority) {
    this.priority = priority;
  }

  public void setQuery(final String query) {
    this.query = query;
  }
}
