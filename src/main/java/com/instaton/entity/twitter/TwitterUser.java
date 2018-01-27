package com.instaton.entity.twitter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.instaton.entity.AbstractEntity;
import com.instaton.entity.GenderEnum;

@Entity
@Table(name = "twitteruser", uniqueConstraints = { @UniqueConstraint(columnNames = { "userId" }) })
public class TwitterUser extends AbstractEntity {

	private long userId;

	@Enumerated(EnumType.STRING)
	private GenderEnum gender;

	public long getUserId() {
		return this.userId;
	}

	public void setUserId(final long userId) {
		this.userId = userId;
	}

	public GenderEnum getGender() {
		return gender;
	}

	public void setGender(GenderEnum gender) {
		this.gender = gender;
	}
}
