package com.instaton.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SearchController {

  @Autowired private TwitterTemplate twitterTemplate;

  @RequestMapping(value = "/search", method = RequestMethod.GET)
  public SearchResults search() {
    SearchResults search = twitterTemplate.searchOperations().search("istinyepark", 500);

    return search;
  }
}
