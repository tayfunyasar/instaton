package com.instaton.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter.XFrameOptionsMode;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private Http401UnauthorizedEntryPoint authenticationEntryPoint;

	@Autowired
	private Http403AccessDeniedEntryPoint accessDeniedEntryPoint;

	protected void authorizeRequests(final HttpSecurity http) throws Exception {

		http.csrf().disable();

		http.authorizeRequests().anyRequest().permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/").permitAll();
		http.headers().httpStrictTransportSecurity().disable();
		http.headers().addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsMode.SAMEORIGIN));
		http.sessionManagement().sessionFixation().newSession();

//		//@formatter:off
//		http.authorizeRequests()
//			.antMatchers(EndpointConstant.API_ENDPOINT_GUEST_PATTERN).permitAll()
//			.antMatchers(EndpointConstant.API_ENDPOINT_GENERIC_PATTERN).permitAll()
//			.anyRequest().permitAll()
//			.antMatchers(EndpointConstant.API_ENDPOINT_PATTERN).authenticated();

		http.exceptionHandling()
			.authenticationEntryPoint(this.authenticationEntryPoint)
			.accessDeniedHandler(this.accessDeniedEntryPoint);
		//@formatter:on
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {

		SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);

		this.authorizeRequests(http);

		// http.addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class).csrf().csrfTokenRepository(csrfTokenRepository());
		//
		// http.headers()
		// .xssProtection() /* prevents cross site scripting */
		// .and()
		// .frameOptions()
		// .sameOrigin() /* prevents x-frame attacks. The request only comes from the same origin */
		// .and()
		// .headers()
		// .httpStrictTransportSecurity() /* LOGIN ADD SOME DESC */
		// .and()
		// .contentTypeOptions(); /* X-Content-Type-Options: nosniff */
		//
		// http.headers().cacheControl();
		//
		// http.sessionManagement().sessionFixation(); /* Creates new jsession id after login */

	}

	// // ignore static resources such as CSS or JS files.
	@Override
	public void configure(final WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/assets/**", "/logon/**", "/logon/**.jsp");
	}

	private CsrfTokenRepository csrfTokenRepository() {
		final HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setHeaderName("X-XSRF-TOKEN");
		return repository;
	}

}
