package com.instaton.entity.social.instagram;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.instaton.entity.AbstractEntity;
import com.instaton.entity.enums.InstagramActionTypeEnum;

@Entity
@Table(name = "instagram_action")
public class InstagramActionEntity extends AbstractEntity {

  @Enumerated(EnumType.STRING)
  private InstagramActionTypeEnum type;

  public InstagramActionTypeEnum getType() {
    return this.type;
  }

  public void setType(final InstagramActionTypeEnum type) {
    this.type = type;
  }
}
