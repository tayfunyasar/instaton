package com.instaton.entity.generic.parameter;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.instaton.entity.AbstractEntity;

@Entity
@Table(name = "parameter")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParameterListItem extends AbstractEntity {

	@Override
	public String toString() {
		return "ParameterListItem [parameterNameText=" + parameterNameText + ", parameterCode=" + parameterCode + ", parameterValue=" + parameterValue + ", orderNum=" + orderNum + ", parameterType=" + parameterType + "]";
	}

	private String parameterNameText;
	private String parameterCode;
	private String parameterValue;
	private Integer orderNum;
	private Integer parameterType;

	public String getParameterNameText() {
		return parameterNameText;
	}

	public void setParameterNameText(String parameterNameText) {
		this.parameterNameText = parameterNameText;
	}

	public String getParameterCode() {
		return parameterCode;
	}

	public void setParameterCode(String parameterCode) {
		this.parameterCode = parameterCode;
	}

	public String getParameterValue() {
		return parameterValue;
	}

	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public Integer getParameterType() {
		return parameterType;
	}

	public void setParameterType(Integer parameterType) {
		this.parameterType = parameterType;
	}
}