package com.instaton.config.exception;

public class InstatonException extends Exception {

  private static final long serialVersionUID = 1L;

  private InstatonReturnCode returnCode;
  private String responseMessage;

  public InstatonException() {
    super();
  }

  public InstatonException(
      InstatonReturnCode loyaltyReturnCode, String message, String responseMessage) {
    super(message);
    this.setReturnCode(loyaltyReturnCode);
    this.setResponseMessage(responseMessage);
  }

  public InstatonException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public InstatonException(String message, Throwable cause) {
    super(message, cause);
  }

  public InstatonException(String message) {
    super(message);
  }

  public InstatonException(Throwable cause) {
    super(cause);
  }

  public InstatonReturnCode getReturnCode() {
    return returnCode;
  }

  public void setReturnCode(InstatonReturnCode returnCode) {
    this.returnCode = returnCode;
  }

  public String getResponseMessage() {
    return responseMessage;
  }

  public void setResponseMessage(String responseMessage) {
    this.responseMessage = responseMessage;
  }
}
