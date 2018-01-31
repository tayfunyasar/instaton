package com.instaton.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestHeader implements Serializable {

  private static final long serialVersionUID = 1L;
  private String applicationSessionId = "";
  private String channelType = "";
  private String clientId = "";
  private String clientIp = "";
  private String executiveOpaqueId = "";
  private String garantiSessionId = "";
  private String languageCode = "";
  private String loginLevel = "";
  private String opaqueId = "";
  private String previewInd = "";
  private BigDecimal referenceFirmId = new BigDecimal("0");
  private String requestId = "";
  private String screenCode = "";
  private String stokenCode = "";
  private String subChannelType = "";

  public String getApplicationSessionId() {
    return applicationSessionId;
  }

  public String getChannelType() {
    return channelType;
  }

  public String getClientId() {
    return clientId;
  }

  public String getClientIp() {
    return clientIp;
  }

  public String getExecutiveOpaqueId() {
    return executiveOpaqueId;
  }

  public String getGarantiSessionId() {
    return garantiSessionId;
  }

  public String getLanguageCode() {
    return languageCode;
  }

  public String getLoginLevel() {
    return loginLevel;
  }

  public String getOpaqueId() {
    return opaqueId;
  }

  public String getPreviewInd() {
    return previewInd;
  }

  public BigDecimal getReferenceFirmId() {
    return referenceFirmId;
  }

  public String getRequestId() {
    return requestId;
  }

  public String getScreenCode() {
    return screenCode;
  }

  public String getStokenCode() {
    return stokenCode;
  }

  public String getSubChannelType() {
    return subChannelType;
  }

  public void setApplicationSessionId(String applicationSessionId) {
    this.applicationSessionId = applicationSessionId;
  }

  public void setChannelType(String channelType) {
    this.channelType = channelType;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public void setClientIp(String clientIp) {
    this.clientIp = clientIp;
  }

  public void setExecutiveOpaqueId(String executiveOpaqueId) {
    this.executiveOpaqueId = executiveOpaqueId;
  }

  public void setGarantiSessionId(String garantiSessionId) {
    this.garantiSessionId = garantiSessionId;
  }

  public void setLanguageCode(String languageCode) {
    this.languageCode = languageCode;
  }

  public void setLoginLevel(String loginLevel) {
    this.loginLevel = loginLevel;
  }

  public void setOpaqueId(String opaqueId) {
    this.opaqueId = opaqueId;
  }

  public void setPreviewInd(String previewInd) {
    this.previewInd = previewInd;
  }

  public void setReferenceFirmId(BigDecimal referenceFirmId) {
    this.referenceFirmId = referenceFirmId;
  }

  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }

  public void setScreenCode(String screenCode) {
    this.screenCode = screenCode;
  }

  public void setStokenCode(String stokenCode) {
    this.stokenCode = stokenCode;
  }

  public void setSubChannelType(String subChannelType) {
    this.subChannelType = subChannelType;
  }
}
