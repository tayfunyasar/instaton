package com.instaton.entity.black.blacknameentity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.instaton.entity.AbstractEntity;

@Entity
@Table(
  name = "blacknameentity",
  uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})}
)
public class BlackNameEntity extends AbstractEntity {

  private String name;

  public String getName() {
    return this.name;
  }

  public void setName(final String name) {
    this.name = name;
  }
}
