package com.instaton.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.instaton.security.AuthenticationToken;
import com.instaton.security.BaseAuthenticationUtil;

@Service
public class AuthenticationService extends BaseAuthenticationUtil<AuthenticationToken> {

	public AuthenticationService() {
		super(AuthenticationToken.class);
	}

	@Override
	public boolean isAuthenticated() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();

		return authentication != null;
	}

}
