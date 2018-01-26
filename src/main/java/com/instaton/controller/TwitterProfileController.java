package com.instaton.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.instaton.service.twitter.TwitterProfileService;

@RestController
@RequestMapping("/api/profile")
public class TwitterProfileController {

	@Autowired
	private TwitterProfileService twitterProfileService;

	@GetMapping("/current")
	public TwitterProfile getCurrent() {
		return twitterProfileService.getCurrent();
	}
}
