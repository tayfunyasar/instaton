package com.instaton.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.instaton.constant.EndpointConstant;
import com.instaton.entity.social.instagram.InstagramUserEntity;
import com.instaton.service.instagram.InstagramUserService;

@RestController
@RequestMapping(EndpointConstant.API_ENDPOINT_INSTAGRAMUSER)
public class InstagramUserController {

  @Autowired private InstagramUserService service;

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public void add(@RequestBody final InstagramUserEntity input) {

    final InstagramUserEntity findByUsername = this.service.findByUsername(input.getUsername());
    findByUsername.setGender(input.getGender());

    this.service.save(findByUsername);
  }

  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public List<InstagramUserEntity> list() {

    return this.service.findTop100ByGenderIsNullOrderByPk();
  }
}
