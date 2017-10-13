package com.instaton.exception;

public class LoyaltyException extends Exception {

	private static final long serialVersionUID = 1L;

	private LoyaltyReturnCode returnCode;
	private String responseMessage;

	public LoyaltyException() {
		super();
	}

	public LoyaltyException(LoyaltyReturnCode loyaltyReturnCode, String message, String responseMessage) {
		super(message);
		this.setReturnCode(loyaltyReturnCode);
		this.setResponseMessage(responseMessage);
	}

	public LoyaltyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public LoyaltyException(String message, Throwable cause) {
		super(message, cause);
	}

	public LoyaltyException(String message) {
		super(message);
	}

	public LoyaltyException(Throwable cause) {
		super(cause);
	}

	public LoyaltyReturnCode getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(LoyaltyReturnCode returnCode) {
		this.returnCode = returnCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
}
