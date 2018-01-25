package com.instaton.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.instaton.entity.twitter.CustomSearchResults;
import com.instaton.service.TwitterProfileService;

@RestController
@RequestMapping("/api/search")
public class TwitterSearchController {

	@Autowired
	private TwitterProfileService twitterProfileService;

	@GetMapping("/current")
	public CustomSearchResults getCurrent() {
		return twitterProfileService.getSearch();
	}

}
