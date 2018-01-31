package com.instaton.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.social.security.SpringSocialConfigurer;

import com.instaton.config.social.InstatonSocialUserDetailsService;
import com.instaton.config.web.Http401UnauthorizedEntryPoint;
import com.instaton.config.web.Http403AccessDeniedEntryPoint;

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

		http.headers().frameOptions().disable();

		http.formLogin().and().apply(new SpringSocialConfigurer());

		http.exceptionHandling().authenticationEntryPoint(this.authenticationEntryPoint).accessDeniedHandler(this.accessDeniedEntryPoint);
		//@formatter:on
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {

		SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);

		this.authorizeRequests(http);

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

	@Bean
	public PasswordEncoder passwordEncoder() {
		final PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

	@Bean
	public SocialUserDetailsService socialUsersDetailService() {
		return new InstatonSocialUserDetailsService(this.userDetailsService());
	}

}
