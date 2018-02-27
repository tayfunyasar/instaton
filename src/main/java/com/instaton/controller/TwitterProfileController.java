package com.instaton.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.instaton.constant.EndpointConstant;
import com.instaton.service.twitter.impl.TwitterServiceImpl;

@RestController
@RequestMapping(EndpointConstant.API_ENDPOINT_PROFILE)
public class TwitterProfileController {

  @Autowired private TwitterServiceImpl twitterProfileService;

  @GetMapping("/current")
  public TwitterProfile getCurrent() {
    return this.twitterProfileService.getCurrent();
  }
}
