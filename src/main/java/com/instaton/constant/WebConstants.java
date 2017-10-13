package com.instaton.constant;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource(value = { "classpath:instaton-${instaton.profile}.properties" }, ignoreResourceNotFound = true)
@Lazy(false)
@Component
public class WebConstants {

	public static final int AWAIT_TERMINATION_SECONDS = 500;

	public static final String PAGE_ERROR_ACCESSDENIED = "/error/access-denied";
	public static final String PAGE_ERROR_UNAUTHORIZED = "/error/un-authorized";
	public static final String PAGE_HOME = "/anasayfa";
	public static final String PAGE_ROOT = "/";
	public static final String PAGE_SERVICE_NOT_AVAILABLE = "/error/service-not-available";
	public static final String SSO_LOGOUT_URL = "/api/logout";
	public static final String SSO_SESSION_HEARTBEAT = "/api/login/heartbeat";

	public String PARTNER_GATEWAY_screenCode;// screenCode

	// @Value("${partner.gateway.screenCode}")
	public void setPGatewayScreenCode(String screenCode) {
		this.PARTNER_GATEWAY_screenCode = screenCode;
	}

}
