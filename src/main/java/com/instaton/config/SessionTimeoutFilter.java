package com.instaton.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.filter.GenericFilterBean;

import com.instaton.constant.ApplicationConstants;

public class SessionTimeoutFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
		HttpServletResponse httpResp = (HttpServletResponse) resp;
		HttpServletRequest httpReq = (HttpServletRequest) req;
		HttpSession session = httpReq.getSession();
		if (httpReq.getRemoteUser() != null) {
			long currTime = System.currentTimeMillis();
			long expiryTime = currTime + session.getMaxInactiveInterval() * 1000;
			long warningTime = 120;
			if (ApplicationConstants.SESSION_TIMEOUT != 0l) {
				expiryTime = currTime + ApplicationConstants.SESSION_TIMEOUT * 1000;
				session.setMaxInactiveInterval(Math.toIntExact(ApplicationConstants.SESSION_TIMEOUT + 30));
				warningTime = ApplicationConstants.SESSION_TIMEOUT - ApplicationConstants.SESSION_WARNING;
			}
			Cookie cookie = new Cookie("serverTime", String.valueOf(currTime));
			cookie.setPath("/");
			httpResp.addCookie(cookie);
			cookie = new Cookie("expiryTime", String.valueOf(expiryTime));
			cookie.setPath("/");
			httpResp.addCookie(cookie);
			cookie = new Cookie("warningTime", String.valueOf(warningTime));
			cookie.setPath("/");
			httpResp.addCookie(cookie);
		}
		filterChain.doFilter(req, resp);
	}
}