package com.instaton.entity.generic.parameter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.instaton.entity.BaseInput;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ParameterListInput extends BaseInput {

	private static final long serialVersionUID = 1L;

	private ParameterListInputData requestData;

	public ParameterListInputData getRequestData() {
		return requestData;
	}

	public void setRequestData(ParameterListInputData requestData) {
		this.requestData = requestData;
	}

	@Override
	public String toString() {
		return "ParameterListInput [requestData=" + requestData + "]";
	}

}