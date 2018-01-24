package com.instaton.entity.generic.parameter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ParameterListInputData {

	private String groupNameText;
	private String parameterCode;
	private String maxNotifListSize;
	private String parameterSubCode;
	private String fromParameterCode;
	private String fromTransactionalData;
	private Integer parameterType;

	public String getParameterSubCode() {
		return parameterSubCode;
	}

	public void setParameterSubCode(String parameterSubCode) {
		this.parameterSubCode = parameterSubCode;
	}

	public String getFromTransactionalData() {
		return fromTransactionalData;
	}

	public void setFromTransactionalData(String fromTransactionalData) {
		this.fromTransactionalData = fromTransactionalData;
	}

	public String getGroupNameText() {
		return groupNameText;
	}

	public void setGroupNameText(String groupNameText) {
		this.groupNameText = groupNameText;
	}

	public String getParameterCode() {
		return parameterCode;
	}

	public void setParameterCode(String parameterCode) {
		this.parameterCode = parameterCode;
	}

	public String getFromParameterCode() {
		return fromParameterCode;
	}

	public void setFromParameterCode(String fromParameterCode) {
		this.fromParameterCode = fromParameterCode;
	}

	public String getMaxNotifListSize() {
		return maxNotifListSize;
	}

	public void setMaxNotifListSize(String maxNotifListSize) {
		this.maxNotifListSize = maxNotifListSize;
	}

	@Override
	public String toString() {
		return "ParameterListInputData [groupNameText=" + groupNameText + ", parameterCode=" + parameterCode + ", maxNotifListSize=" + maxNotifListSize + ", parameterSubCode=" + parameterSubCode + ", fromParameterCode=" + fromParameterCode + ", fromTransactionalData=" + fromTransactionalData + "]";
	}

	public Integer getParameterType() {
		return parameterType;
	}

	public void setParameterType(Integer parameterType) {
		this.parameterType = parameterType;
	}

}