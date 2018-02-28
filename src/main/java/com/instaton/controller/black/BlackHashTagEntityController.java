package com.instaton.controller.black;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.instaton.config.exception.InstatonException;
import com.instaton.constant.EndpointConstant;
import com.instaton.entity.social.BlackHashTagEntity;
import com.instaton.service.twitter.BlackHashTagEntityService;

@RestController
@RequestMapping(EndpointConstant.API_ENDPOINT_BLACKKEYWORD)
public class BlackHashTagEntityController {

  @Autowired private BlackHashTagEntityService service;

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public void add(@RequestBody final BlackHashTagEntity inputData) throws InstatonException {

    this.service.save(inputData);
  }

  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public List<BlackHashTagEntity> list() throws InstatonException {

    return this.service.findAll();
  }
}
