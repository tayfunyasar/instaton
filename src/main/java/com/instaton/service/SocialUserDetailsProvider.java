package com.instaton.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SocialUserDetailsProvider implements SocialUserDetailsService {

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	public org.springframework.social.security.SocialUserDetails loadUserByUserId(String userId)
			throws UsernameNotFoundException {
		return (org.springframework.social.security.SocialUserDetails) userDetailsService.loadUserByUserId(userId);
	}

}