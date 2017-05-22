package com.instaton.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.connect.Connection;
import org.springframework.social.security.SocialAuthenticationToken;
import org.springframework.stereotype.Component;

import com.instaton.model.User;
import com.instaton.repository.UserRepository;
import com.instaton.utils.CurrentUserHolder;
import com.instaton.utils.NumberUtils;

@Component
public class AuthenticationService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private UserDetailsService userDetailsService;

	public User getCurrentUser() {
		return isCurrentUserAuthenticated() ? getOrFindCurrentUser() : null;
	}

	private User getOrFindCurrentUser() {
		CurrentUserHolder currentUserHolder = getCurrentUserHolder();
		if (currentUserHolder.hasNotCurrentUser()) {
			User currentUser = getAuthenticatedUserByAuthenticationName();
			currentUserHolder.setCurrentUser(currentUser);
		}
		return currentUserHolder.getCurrentUser();
	}

	private User getAuthenticatedUserByAuthenticationName() {
		String authenticationName = getAuthentication().getName();
		if (StringUtils.isNumeric(authenticationName)) {
			return userRepository.findById(NumberUtils.parseLong(authenticationName));
		} else {
			// If auto-authenticated after registration, find by email
			return userRepository.findByEmail(authenticationName);
		}
	}

	public boolean isCurrentUserAuthenticated() {
		return !hasRole("ROLE_ANONYMOUS");
	}

	public boolean isCurrentUserAdmin() {
		return hasRole("ROLE_ADMIN");
	}

	private boolean hasRole(String role) {
		return getAuthentication().getAuthorities().stream().filter(a -> a.getAuthority().equals(role)).findFirst()
				.isPresent();
	}

	private Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	private CurrentUserHolder getCurrentUserHolder() {
		return applicationContext.getBean(CurrentUserHolder.class);
	}

	public void authenticate(String email, String password) {
		Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	public void authenticate(Connection<?> connection, User user) {
		UserDetails userDetails = userDetailsService.loadUserByUserId(user.getId().toString());
		Authentication authentication = new SocialAuthenticationToken(connection, userDetails, null,
				userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

}