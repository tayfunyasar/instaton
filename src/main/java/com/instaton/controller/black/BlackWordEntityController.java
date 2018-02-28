package com.instaton.controller.black;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.instaton.config.exception.InstatonException;
import com.instaton.constant.EndpointConstant;
import com.instaton.entity.social.BlackWordEntity;
import com.instaton.service.twitter.BlackWordEntityService;

@RestController
@RequestMapping(EndpointConstant.API_ENDPOINT_BLACKWORD)
public class BlackWordEntityController {

  @Autowired private BlackWordEntityService service;

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public void add(@RequestBody final BlackWordEntity input) throws InstatonException {

    this.service.save(input);
  }
}
