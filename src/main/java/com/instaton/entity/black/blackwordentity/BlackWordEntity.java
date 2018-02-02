package com.instaton.entity.black.blackwordentity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.instaton.entity.AbstractEntity;

@Entity
@Table(
  name = "blackwordentity",
  uniqueConstraints = {@UniqueConstraint(columnNames = {"word"})}
)
public class BlackWordEntity extends AbstractEntity {

  private String word;

  public String getWord() {
    return this.word;
  }

  public void setWord(final String word) {
    this.word = word;
  }
}
