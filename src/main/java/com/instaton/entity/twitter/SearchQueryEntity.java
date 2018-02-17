package com.instaton.entity.twitter;

import java.sql.Date;

import com.instaton.entity.AbstractEntity;

public class SearchQueryEntity extends AbstractEntity {

  private String query;
  private int interval;
  private Date lastVisit;

  public int getInterval() {
    return this.interval;
  }

  public Date getLastVisit() {
    return this.lastVisit;
  }

  public String getQuery() {
    return this.query;
  }

  public void setInterval(final int interval) {
    this.interval = interval;
  }

  public void setLastVisit(final Date lastVisit) {
    this.lastVisit = lastVisit;
  }

  public void setQuery(final String query) {
    this.query = query;
  }
}
