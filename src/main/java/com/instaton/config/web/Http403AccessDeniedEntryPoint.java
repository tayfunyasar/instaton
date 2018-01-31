package com.instaton.config.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.instaton.constant.WebConstants;

/** Returns a 403 error code (AccessDenied) to the client. */
@Component
public class Http403AccessDeniedEntryPoint implements AccessDeniedHandler {

  private final Logger log = LoggerFactory.getLogger(Http403AccessDeniedEntryPoint.class);

  /** Always returns a 403 error code to the client. */
  @Override
  public void handle(
      HttpServletRequest request,
      HttpServletResponse response,
      AccessDeniedException accessDeniedException)
      throws IOException, ServletException {
    log.debug("Access Denied. Rejecting access");
    response.sendRedirect(request.getContextPath() + WebConstants.PAGE_ERROR_ACCESSDENIED);
  }
}
