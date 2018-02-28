package com.instaton.entity.social;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.instaton.entity.AbstractEntity;

@Entity
@Table(
  name = "blackhashtagentity",
  uniqueConstraints = {@UniqueConstraint(columnNames = {"keyword"})}
)
public class BlackHashTagEntity extends AbstractEntity {

  @Column(nullable = false)
  private String keyword;

  public String getKeyword() {
    return this.keyword;
  }

  public void setKeyword(final String keyword) {
    this.keyword = keyword;
  }
}
