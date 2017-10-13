package com.instaton.security;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

public abstract class BaseAuthenticationToken extends UsernamePasswordAuthenticationToken {

	private static final long serialVersionUID = 1L;
	private String applicationSessionId;
	private String clientId;
	private String clientIp;
	private transient Object credentials;
	private String stoken;

	public BaseAuthenticationToken(Object principal, Object credentials) {
		super(principal, credentials);
	}

	public BaseAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
	}

	public String getApplicationSessionId() {
		return applicationSessionId;
	}

	public String getClientId() {
		return clientId;
	}

	public String getClientIp() {
		return clientIp;
	}

	@Override
	public Object getCredentials() {
		return credentials;
	}

	public String getStoken() {
		return stoken;
	}

	public void setApplicationSessionId(String applicationSessionId) {
		this.applicationSessionId = applicationSessionId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public void setCredentials(Object creds) {
		credentials = creds;
	}

	@Override
	public void setDetails(Object details) {
		if (details instanceof WebAuthenticationDetails) {
			this.setClientIp(((WebAuthenticationDetails) details).getRemoteAddress());
		}
	}

	public void setStoken(String stoken) {
		this.stoken = stoken;
	}

}
