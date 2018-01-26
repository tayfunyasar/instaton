package com.instaton.entity.black.blackuserid;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.instaton.entity.AbstractEntity;

@Entity
@Table(name = "blackuseridentity", uniqueConstraints = { @UniqueConstraint(columnNames = { "userId" }) })
public class BlackUserIdEntity extends AbstractEntity {

	private long userId;

	public long getUserId() {
		return this.userId;
	}

	public void setUserId(final long userId) {
		this.userId = userId;
	}
}
