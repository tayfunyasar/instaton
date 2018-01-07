package com.instaton.controller.social.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.BlockOperations;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/connecttwitter")
public class SocialLoginController {
	
	@Autowired
	private Twitter twitter;

	@RequestMapping(method = RequestMethod.GET)
	public String helloTwitter(Model model) {
		BlockOperations blockOperations = twitter.blockOperations();
		
		return "redirect:/connect/twitter";
	}
}