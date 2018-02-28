package com.instaton.config.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class AuthenticationToken extends BaseAuthenticationToken {

  private static final long serialVersionUID = 1L;

  public AuthenticationToken(Object principal, Object credentials) {
    super(principal, credentials);
  }

  public AuthenticationToken(
      Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
    super(principal, credentials, authorities);
  }
}
