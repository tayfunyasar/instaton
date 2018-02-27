package com.instaton.entity.twitter;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.instaton.entity.AbstractEntity;

@Entity
@Table(name = "searchquery")
public class SearchQueryEntity extends AbstractEntity {

  private String query;
  private int interval;
  private LocalDateTime lastVisit;

  public int getInterval() {
    return this.interval;
  }

  public LocalDateTime getLastVisit() {
    return this.lastVisit;
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

  public void setQuery(final String query) {
    this.query = query;
  }
}
