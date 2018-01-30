package com.instaton.entity.generic.parameter;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.instaton.entity.AbstractEntity;

@Entity
@Table(name = "parameter")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParameterListItem extends AbstractEntity {

	private String parameterNameText;
	private String parameterCode;
	private String parameterValue;
	private Integer orderNum;
	private Integer parameterType;

	public Integer getOrderNum() {
		return this.orderNum;
	}

	public String getParameterCode() {
		return this.parameterCode;
	}

	public String getParameterNameText() {
		return this.parameterNameText;
	}

	public Integer getParameterType() {
		return this.parameterType;
	}

	public String getParameterValue() {
		return this.parameterValue;
	}

	public void setOrderNum(final Integer orderNum) {
		this.orderNum = orderNum;
	}

	public void setParameterCode(final String parameterCode) {
		this.parameterCode = parameterCode;
	}

	public void setParameterNameText(final String parameterNameText) {
		this.parameterNameText = parameterNameText;
	}

	public void setParameterType(final Integer parameterType) {
		this.parameterType = parameterType;
	}

	public void setParameterValue(final String parameterValue) {
		this.parameterValue = parameterValue;
	}
}