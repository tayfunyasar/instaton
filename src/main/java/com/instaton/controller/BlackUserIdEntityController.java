package com.instaton.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.instaton.constant.EndpointConstant;
import com.instaton.entity.black.BlackUserIdEntity;
import com.instaton.exception.InstatonException;
import com.instaton.service.twitter.BlackUserIdEntityService;

@RestController
@RequestMapping(EndpointConstant.API_ENDPOINT_BLACKUSERID)
public class BlackUserIdEntityController {

  @Autowired private BlackUserIdEntityService service;

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public void add(@RequestBody final BlackUserIdEntity input) throws InstatonException {

    this.service.save(input);
  }

  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public List<BlackUserIdEntity> list() throws InstatonException {

    return this.service.findAll();
  }
}
