package com.instaton.entity.black.blackhashtagentity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.instaton.entity.AbstractEntity;

@Entity
@Table(name = "blackhashtagentity", uniqueConstraints = { @UniqueConstraint(columnNames = { "keyword" }) })
public class BlackHashTagEntity extends AbstractEntity {

	private String keyword;

	public String getKeyword() {
		return this.keyword;
	}

	public void setKeyword(final String keyword) {
		this.keyword = keyword;
	}
}
