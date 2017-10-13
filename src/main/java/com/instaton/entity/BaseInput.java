package com.instaton.entity;

import java.io.Serializable;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.instaton.util.BaseInputUtils;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseInput implements Serializable {

	private static final long serialVersionUID = 1L;
	private RequestHeader requestHeader;

	@Override
	public boolean equals(Object obj) {

		if (obj == null) {
			return false;
		}

		return hashCode() == obj.hashCode();
	}

	public String generateCacheKey() {
		return String.valueOf(hashCode());
	}

	public RequestHeader getRequestHeader() {
		return requestHeader;
	}

	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
		builder.append(BaseInputUtils.getCurrTst());
		return builder.toHashCode();
	}

	public void setRequestHeader(RequestHeader requestHeader) {
		this.requestHeader = requestHeader;
	}

}
