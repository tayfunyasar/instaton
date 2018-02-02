package com.instaton.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.instaton.constant.EndpointConstant;
import com.instaton.entity.black.blackwordentity.BlackWordEntity;
import com.instaton.exception.InstatonException;
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
