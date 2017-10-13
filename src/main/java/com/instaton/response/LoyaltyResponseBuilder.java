package com.instaton.response;

import com.instaton.exception.LoyaltyReturnCode;

public class LoyaltyResponseBuilder {

	private static LoyaltyResponseBuilder instance = null;

	private LoyaltyResponse loyaltyResponse = null;

	private LoyaltyResponseBuilder() {
		loyaltyResponse = new LoyaltyResponse<>();
	}

	public static <T> LoyaltyResponseBuilder begin() {
		LoyaltyResponseBuilder __ins = getInstance();
		__ins.loyaltyResponse = new LoyaltyResponse<T>();
		return __ins;
	}

	// public static <T> LoyaltyResponse<T> buildResponseData(GTResponse<T> response) {
	// if (isResponseEmpty(response)) {
	// return emptyResponse(response);
	// }
	// return new LoyaltyResponse<>(LoyaltyReturnCode.get(response.getResponseHeader().getReturnCode()), response.getResponseData(), response.getResponseHeader().getResponseMessage(), response.getResponseHeader().getReasonCode());
	// }

	public static <T> LoyaltyResponse<T> buildSuccessResponseData(T response) {
		return LoyaltyResponseBuilder.begin().code(LoyaltyReturnCode.SUCCESS).data(response).message("İşleminiz başarı ile gerçekleştirilmiştir.").build();
	}
	//
	// public static <T> LoyaltyResponse<T> emptyResponse(GTResponse<T> response) {
	// return new LoyaltyResponse<>(LoyaltyReturnCode.get(response.getResponseHeader().getReturnCode()), response.getResponseHeader().getResponseMessage());
	// }

	public static LoyaltyResponseBuilder getInstance() {
		if (instance == null) {
			instance = new LoyaltyResponseBuilder();
		}

		return instance;
	}

	// public static <T> boolean isResponseEmpty(GTResponse<T> response) {
	// return Objects.isNull(response.getResponseData());
	// }

	public LoyaltyResponse build() {
		return loyaltyResponse;
	}

	public LoyaltyResponseBuilder code(LoyaltyReturnCode rc) {
		loyaltyResponse.setReturnCode(rc.getCode());

		loyaltyResponse.setReturnKey(rc.getKey());
		loyaltyResponse.setShowInModal(rc.getShowInModal());
		loyaltyResponse.setType(rc.getType());
		return this;
	}

	public LoyaltyResponseBuilder data(Object data) {
		loyaltyResponse.setData(data);
		return this;
	}

	public LoyaltyResponseBuilder message(String message) {
		loyaltyResponse.setMessage(message);
		return this;
	}

	public LoyaltyResponseBuilder modal(boolean showInModal) {
		loyaltyResponse.setShowInModal(showInModal);
		return this;
	}

}
