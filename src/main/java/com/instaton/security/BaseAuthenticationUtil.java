package com.instaton.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class BaseAuthenticationUtil<T> {

	@SuppressWarnings("unused")
	private final Class<?> clazz;

	public BaseAuthenticationUtil(Class<?> clazz) {
		this.clazz = clazz;
	}

	public boolean isCurrentUserInRole(String authority) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		if (authentication != null) {
			return authentication.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(authority));
		}
		return false;
	}

	public abstract boolean isAuthenticated();

	public T getCurrentAuthentication() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return null;
		}
		return (T) authentication;
	}

}