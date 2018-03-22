package com.instaton.entity.social;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.instaton.entity.AbstractEntity;
import com.instaton.entity.enums.BlackNameTypeEnum;

@Entity
@Table(
  name = "black_name",
  uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})}
)
public class BlackNameEntity extends AbstractEntity {

  @Column(nullable = false)
  private String name;

  @Enumerated(EnumType.STRING)
  private BlackNameTypeEnum type;

  public String getName() {
    return this.name;
  }

  public BlackNameTypeEnum getType() {
    return this.type;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public void setType(final BlackNameTypeEnum type) {
    this.type = type;
  }
}
