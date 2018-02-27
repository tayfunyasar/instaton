package com.instaton.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.instaton.constant.EndpointConstant;
import com.instaton.entity.twitter.TwitterUserEntity;
import com.instaton.exception.InstatonException;
import com.instaton.service.twitter.TwitterUserService;

@RestController
@RequestMapping(EndpointConstant.API_ENDPOINT_TWITTERUSER)
public class TwitterUserController {

  @Autowired private TwitterUserService service;

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public void add(@RequestBody final TwitterUserEntity input) throws InstatonException {

    final TwitterUserEntity findByScreenName = this.service.findByScreenName(input.getScreenName());
    findByScreenName.setGender(input.getGender());

    this.service.save(findByScreenName);
  }

  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public List<TwitterUserEntity> list() throws InstatonException {

    return this.service.findTop10ByGenderIsNullOrderById();
  }
}
