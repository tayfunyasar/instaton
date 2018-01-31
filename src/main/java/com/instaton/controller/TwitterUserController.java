package com.instaton.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.instaton.constant.EndpointConstant;
import com.instaton.entity.twitter.TwitterUser;
import com.instaton.exception.InstatonException;
import com.instaton.service.twitter.TwitterUserService;
import com.instaton.service.twitter.impl.TwitterServiceImpl;

@RestController
@RequestMapping(EndpointConstant.API_ENDPOINT_TWITTERUSER)
public class TwitterUserController {

  @Autowired private TwitterUserService service;

  @Autowired private TwitterServiceImpl twitterService;

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public void add(@RequestBody final TwitterUser input) throws InstatonException {

    // final SearchResults search = this.twitterService.getSearch();

    // TwitterProfile currentUser = null;
    // for (final Tweet tweet : search.getTweets()) {
    // final TwitterProfile user = tweet.getUser();
    //
    // if (user.getId() == input.getUserId()) {
    // currentUser = user;
    // break;
    // }
    // if (StringUtils.equals(user.getScreenName(), input.getScreenName())) {
    // currentUser = user;
    // break;
    // }
    // }
    // final TwitterUser convert = ConvertUtil.convert(currentUser);
    // convert.setGender(input.getGender());
    // convert.setUserId(input.getUserId());
    final TwitterUser convert = new TwitterUser();
    convert.setScreenName(input.getScreenName());

    this.service.save(convert);
  }

  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public List<TwitterUser> list() throws InstatonException {

    return this.service.findAll();
  }
}
