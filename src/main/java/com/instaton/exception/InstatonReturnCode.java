package com.instaton.exception;

import java.util.HashMap;
import java.util.Map;

public enum InstatonReturnCode {
  ACCESS_DENIED_EXCEPTION("10002", "ACCESS_DENIED_EXCEPTION", "EXCEPTION", Boolean.FALSE),
  APPLICATION_ERROR("-10", "APPLICATION_ERROR", ResponseType.ERROR, Boolean.TRUE),

  CMS_SERVICE_ERROR("-2", "CMS_SERVICE_ERROR", ResponseType.ERROR, Boolean.TRUE),

  CUSTOMER_NOT_FOUND("-1999", "CUSTOMER_NOT_FOUND", ResponseType.SUCCESS, Boolean.FALSE),

  CUSTOMER_UNDEFINED("-1200", "CUSTOMER_UNDEFINED", ResponseType.ERROR, Boolean.TRUE),

  DATA_DUPLICATE_ERROR("41", "DATA_DUPLICATE_ERROR", ResponseType.ERROR, Boolean.TRUE),
  DATA_ERROR("40", "DATA_ERROR", ResponseType.ERROR, Boolean.TRUE),

  DATA_NOT_FOUND("50", "DATA_NOT_FOUND", ResponseType.ERROR, Boolean.TRUE),

  EMPTY_RESULT("97", "EMPTY_RESULT", ResponseType.ERROR, Boolean.FALSE),
  GATEWAY_API_ERROR("98", "GATEWAY_API_ERROR", ResponseType.ERROR, Boolean.TRUE),

  GENERIC_ERROR("10003", "GENERIC_ERROR", ResponseType.ERROR, Boolean.TRUE),
  GT_ERROR("9999", "GT_ERROR", ResponseType.ERROR, Boolean.TRUE),

  GT_ERROR_9995("9995", "GT_ERROR_9996", ResponseType.ERROR, Boolean.TRUE),

  GT_ERROR_9996("9996", "GT_ERROR_9996", ResponseType.ERROR, Boolean.TRUE),

  GT_EXCEPTION("-999", "GT_EXCEPTION", ResponseType.ERROR, Boolean.TRUE),
  // Inputumuz yanlış.
  INPUT_ERROR("21", "INPUT_ERROR", ResponseType.ERROR, Boolean.TRUE),
  INVALID_LOGIN_LEVEL("20", "INVALID_LOGIN_LEVEL", ResponseType.ERROR, Boolean.TRUE),

  // Osman'ın mailine hitaben yazıldı
  // başlık: 'Test ortamında stoken çalışması'
  INVALID_STOKEN("93", "INVALID_STOKEN", ResponseType.ERROR, Boolean.TRUE),
  MARS_NOT_SELECTED("10000", "MARS_NOT_SELECTED", ResponseType.ERROR, Boolean.FALSE),

  NO_AUTHORIZED_TO_CHANGE("-20", "NO_AUTHORIZED_TO_CHANGE", ResponseType.ERROR, Boolean.TRUE),

  NOT_AUTHORIZED("91", "NOT_AUTHORIZED_ERROR", ResponseType.ERROR, Boolean.TRUE),
  PARALLEL_SERVICE_ERROR("10001", "PARALLEL_SERVICE_ERROR", ResponseType.ERROR, Boolean.TRUE),

  ROLES_NOT_FOUND("10000", "ROLES_NOT_FOUND", ResponseType.ERROR, Boolean.FALSE),
  SATATE_AUTH_ERROR("95", "STATE_AUTH_ERROR", ResponseType.ERROR, Boolean.TRUE),
  SERVICE_ERROR("-1", "SERVICE_ERROR", ResponseType.ERROR, Boolean.TRUE),

  SESSION_EXPIRED("96", "SESSION_EXPIRED", ResponseType.ERROR, Boolean.TRUE),

  SUCCESS("1", ResponseType.SUCCESS, ResponseType.SUCCESS, Boolean.FALSE),
  TERMINAL_ERROR("-21", "TERMINAL_ERROR", ResponseType.ERROR, Boolean.TRUE),
  UNSUCCESSFUL_LOGIN_COMPLETE(
      "-40", "UNSUCCESSFUL_LOGIN_COMPLETE", ResponseType.ERROR, Boolean.TRUE);

  private static final Map<String, InstatonReturnCode> lookup = new HashMap<>();

  private String code;
  private String key;
  private Boolean showInModal;
  private String type;

  static {
    for (InstatonReturnCode loyaltyReturnCode : InstatonReturnCode.values()) {
      lookup.put(loyaltyReturnCode.getCode(), loyaltyReturnCode);
    }
  }

  private InstatonReturnCode(String code, String key, String type, Boolean showInModal) {
    this.code = code;
    this.type = type;
    this.key = key;
    this.showInModal = showInModal;
  }

  public static InstatonReturnCode get(String code) {
    if (lookup.containsKey(code)) {
      return lookup.get(code);
    }
    return lookup.get("-1");
  }

  public String getCode() {
    return code;
  }

  public String getKey() {
    return key;
  }

  public Boolean getShowInModal() {
    return showInModal;
  }

  public String getType() {
    return type;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public void setShowInModal(Boolean showInModal) {
    this.showInModal = showInModal;
  }

  public void setType(String type) {
    this.type = type;
  }
}
