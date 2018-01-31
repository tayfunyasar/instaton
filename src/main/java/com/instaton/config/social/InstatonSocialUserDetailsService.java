package com.instaton.config.social;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;

public class InstatonSocialUserDetailsService implements SocialUserDetailsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(InstatonSocialUserDetailsService.class);
	private UserDetailsService userDetailsService;

	public InstatonSocialUserDetailsService(final UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Override
	public SocialUserDetails loadUserByUserId(final String userId) {
		LOGGER.debug("Loading user by user id: {}", userId);

		final UserDetails userDetails = this.userDetailsService.loadUserByUsername(userId);
		return new SocialUser(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
	}
}