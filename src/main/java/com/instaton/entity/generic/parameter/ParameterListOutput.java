package com.instaton.entity.generic.parameter;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.instaton.entity.ResponseData;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ParameterListOutput extends ResponseData {

  private static final long serialVersionUID = 1L;
  private List<ParameterListItem> parameterList;

  public List<ParameterListItem> getParameterList() {
    return parameterList;
  }

  public void setParameterList(List<ParameterListItem> parameterList) {
    this.parameterList = parameterList;
  }
}
