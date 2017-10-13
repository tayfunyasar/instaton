package com.instaton.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import com.instaton.config.filter.CsrfHeaderFilter;
import com.instaton.constant.EndpointConstant;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private Http401UnauthorizedEntryPoint authenticationEntryPoint;

	@Autowired
	private Http403AccessDeniedEntryPoint accessDeniedEntryPoint;

	// ignore static resources such as CSS or JS files.
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/assets/**", "/logon/**", "/logon/**.jsp");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);

		authorizeRequests(http);

		http.addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class).csrf().csrfTokenRepository(csrfTokenRepository());

		http.headers()
				.xssProtection() /* prevents cross site scripting */
				.and()
				.frameOptions()
				.sameOrigin() /* prevents x-frame attacks. The request only comes from the same origin */
				.and()
				.headers()
				.httpStrictTransportSecurity() /* LOGIN ADD SOME DESC */
				.and()
				.contentTypeOptions(); /* X-Content-Type-Options: nosniff */

		http.headers().cacheControl();

		http.sessionManagement().sessionFixation(); /* Creates new jsession id after login */

	}

	protected void authorizeRequests(HttpSecurity http) throws Exception {

		//@formatter:off
		http.authorizeRequests()
			.antMatchers(EndpointConstant.API_ENDPOINT_GUEST_PATTERN).permitAll()
			.antMatchers(EndpointConstant.API_ENDPOINT_GENERIC_PATTERN).permitAll()
			.anyRequest().permitAll()
			.antMatchers(EndpointConstant.API_ENDPOINT_PATTERN).authenticated();
		
		http.exceptionHandling()
			.authenticationEntryPoint(authenticationEntryPoint)
			.accessDeniedHandler(accessDeniedEntryPoint);
		//@formatter:on
	}

	private CsrfTokenRepository csrfTokenRepository() {
		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setHeaderName("X-XSRF-TOKEN");
		return repository;
	}

	@Bean
	public SessionTimeoutFilter sessionTimeoutFilter() {
		return new SessionTimeoutFilter();
	}

}
