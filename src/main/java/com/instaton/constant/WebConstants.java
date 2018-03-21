package com.instaton.constant;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConstants {

  public static final String PAGE_ERROR_ACCESSDENIED = "/error/access-denied";
  public static final String PAGE_ERROR_UNAUTHORIZED = "/error/un-authorized";

  @Value("#{'${instaton.twitter.searchkeywords}'.split(',')}")
  private List<String> keywordList;

  @Value("${instaton.twitter.excluded-query}")
  private String excludedQuery;

  @Value("${instaton.instagram.username}")
  private String instagramUsername;

  @Value("${instaton.instagram.password}")
  private String instagramPassword;

  public String getExcludedQuery() {
    return this.excludedQuery;
  }

  public String getInstagramPassword() {
    return this.instagramPassword;
  }

  public String getInstagramUsername() {
    return this.instagramUsername;
  }

  public List<String> getKeywordList() {
    return this.keywordList;
  }
}
