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

  public String getExcludedQuery() {
    return this.excludedQuery;
  }

  public List<String> getKeywordList() {
    return this.keywordList;
  }
}
