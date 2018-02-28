package com.instaton.controller.response;

import java.io.Serializable;

import com.instaton.config.exception.InstatonReturnCode;

public class InstatonResponse<T> implements Serializable {

  private static final long serialVersionUID = -4072370032137500624L;

  private transient T data;
  private String message;
  private String reasonCode;
  private String returnCode;
  private String returnKey;
  private Boolean showInModal;
  private String type;

  public InstatonResponse() {
    super();
  }

  public InstatonResponse(InstatonReturnCode rc) {
    super();
    this.returnCode = rc.getCode();
    this.reasonCode = null;
    this.returnKey = rc.getKey();
    this.type = rc.getType();
    this.showInModal = rc.getShowInModal();
    this.data = null;
    this.message = "";
  }

  public InstatonResponse(InstatonReturnCode rc, String responseMessage) {
    super();
    this.returnCode = rc.getCode();
    this.returnKey = rc.getKey();
    this.type = rc.getType();
    this.showInModal = rc.getShowInModal();
    this.message = responseMessage;
    this.data = null;
  }

  public InstatonResponse(InstatonReturnCode rc, T data) {
    super();
    this.returnCode = rc.getCode();
    this.returnKey = rc.getKey();
    this.type = rc.getType();
    this.showInModal = rc.getShowInModal();
    this.data = data;
    this.message = "";
  }

  public InstatonResponse(InstatonReturnCode rc, T data, String message, String reasonCode) {
    super();
    this.returnCode = rc.getCode();
    this.reasonCode = reasonCode;
    this.returnKey = rc.getKey();
    this.type = rc.getType();
    this.showInModal = rc.getShowInModal();
    this.data = data;
    this.message = message;
  }

  public T getData() {
    return data;
  }

  public String getMessage() {
    return message;
  }

  public String getReasonCode() {
    return reasonCode;
  }

  public String getReturnCode() {
    return returnCode;
  }

  public String getReturnKey() {
    return returnKey;
  }

  public Boolean getShowInModal() {
    return showInModal;
  }

  public String getType() {
    return type;
  }

  public void setData(T data) {
    this.data = data;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public void setReasonCode(String reasonCode) {
    this.reasonCode = reasonCode;
  }

  public void setReturnCode(String returnCode) {
    this.returnCode = returnCode;
  }

  public void setReturnKey(String returnKey) {
    this.returnKey = returnKey;
  }

  public void setShowInModal(Boolean showInModal) {
    this.showInModal = showInModal;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return "LoyaltyResponse [returnCode="
        + returnCode
        + "reasonCode="
        + reasonCode
        + ", returnKey="
        + returnKey
        + ", data="
        + data
        + ", message="
        + message
        + ", type="
        + type
        + ", showInModal="
        + showInModal
        + "]";
  }
}
