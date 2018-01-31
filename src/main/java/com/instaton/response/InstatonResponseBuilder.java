package com.instaton.response;

import com.instaton.exception.InstatonReturnCode;

public class InstatonResponseBuilder {

  private static InstatonResponseBuilder instance = null;

  private InstatonResponse loyaltyResponse = null;

  private InstatonResponseBuilder() {
    loyaltyResponse = new InstatonResponse<>();
  }

  public static <T> InstatonResponseBuilder begin() {
    InstatonResponseBuilder __ins = getInstance();
    __ins.loyaltyResponse = new InstatonResponse<T>();
    return __ins;
  }

  // public static <T> LoyaltyResponse<T> buildResponseData(GTResponse<T> response) {
  // if (isResponseEmpty(response)) {
  // return emptyResponse(response);
  // }
  // return new LoyaltyResponse<>(LoyaltyReturnCode.get(response.getResponseHeader().getReturnCode()), response.getResponseData(), response.getResponseHeader().getResponseMessage(), response.getResponseHeader().getReasonCode());
  // }

  public static <T> InstatonResponse<T> buildSuccessResponseData(T response) {
    return InstatonResponseBuilder.begin()
        .code(InstatonReturnCode.SUCCESS)
        .data(response)
        .message("İşleminiz başarı ile gerçekleştirilmiştir.")
        .build();
  }
  //
  // public static <T> LoyaltyResponse<T> emptyResponse(GTResponse<T> response) {
  // return new LoyaltyResponse<>(LoyaltyReturnCode.get(response.getResponseHeader().getReturnCode()), response.getResponseHeader().getResponseMessage());
  // }

  public static InstatonResponseBuilder getInstance() {
    if (instance == null) {
      instance = new InstatonResponseBuilder();
    }

    return instance;
  }

  // public static <T> boolean isResponseEmpty(GTResponse<T> response) {
  // return Objects.isNull(response.getResponseData());
  // }

  public InstatonResponse build() {
    return loyaltyResponse;
  }

  public InstatonResponseBuilder code(InstatonReturnCode rc) {
    loyaltyResponse.setReturnCode(rc.getCode());

    loyaltyResponse.setReturnKey(rc.getKey());
    loyaltyResponse.setShowInModal(rc.getShowInModal());
    loyaltyResponse.setType(rc.getType());
    return this;
  }

  public InstatonResponseBuilder data(Object data) {
    loyaltyResponse.setData(data);
    return this;
  }

  public InstatonResponseBuilder message(String message) {
    loyaltyResponse.setMessage(message);
    return this;
  }

  public InstatonResponseBuilder modal(boolean showInModal) {
    loyaltyResponse.setShowInModal(showInModal);
    return this;
  }
}
