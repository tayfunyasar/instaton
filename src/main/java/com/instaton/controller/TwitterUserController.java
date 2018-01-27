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

@RestController
@RequestMapping(EndpointConstant.API_ENDPOINT_TWITTERUSER)
public class TwitterUserController {

	@Autowired
	private TwitterUserService service;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void add(@RequestBody final TwitterUser input) throws InstatonException {

		this.service.save(input);
	}

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public List<TwitterUser> list() throws InstatonException {

		return this.service.findAll();
	}

}
