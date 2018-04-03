package com.instaton.constant;

public class EndpointConstant {

  public static final String API_ENDPOINT_PATTERN = "/api/**";

  public static final String API_ENDPOINT_GUEST_PATTERN = "/api/guest/**";

  public static final String API_ENDPOINT_GENERIC_PATTERN = "/api/generic/**";

  public static final String API_ENDPOINT_BLACKKEYWORD = "/api/blackhashtagentity/**";

  public static final String API_ENDPOINT_BLACKUSERID = "/api/blackuseridentity/**";

  public static final String API_ENDPOINT_BLACKWORD = "/api/blackwordentity/**";

  public static final String API_ENDPOINT_TWITTERUSER = "/api/twitteruser/**";

  public static final String API_ENDPOINT_PROFILE = "/api/profile/**";

  public static final String API_ENDPOINT_SEARCH = "/api/search/**";

  public static final String API_ENDPOINT_INSTAGRAMUSER = "/api/instagramuser/**";

  private EndpointConstant() {
    throw new IllegalAccessError();
  }
}
