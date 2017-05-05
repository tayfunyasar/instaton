package com.instaton.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.savedrequest.NullRequestCache;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// @formatter:off
		http
			.authorizeRequests()
				.antMatchers("/api/session")
					.permitAll()
				.antMatchers("/h2-console/**")
					.permitAll()
				.antMatchers("/api/**")
					.authenticated();
		
		http.
			headers().frameOptions().disable();
		
		http
			.requestCache().requestCache(new NullRequestCache());
		
		http.
			sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);

		http
			.csrf().disable();
		// @formatter:on
	}
}