package com.instaton.response;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseRequestUtils {

	private static Logger logger = LoggerFactory.getLogger(BaseRequestUtils.class);

	protected BaseRequestUtils() {
		throw new UnsupportedOperationException();
	}

	public static String getHeaderVal(HttpServletRequest request, String key) {
		if (request == null) {
			return StringUtils.EMPTY;
		}
		return request.getHeader(key);
	}

	public static String getPathname(HttpServletRequest request, String SERVER_CONTEXT_PATH) {
		String etrPathname = getHeaderVal(request, "etrPathname");
		if (etrPathname != null) {
			etrPathname = etrPathname.replace(SERVER_CONTEXT_PATH, "");
			return etrPathname;
		}
		return StringUtils.EMPTY;
	}

	public static String getReferer(HttpServletRequest request) {
		return request.getHeader("Referer");
	}

	public static String getSSOTokenFromRequest(String SsoTokenReqParamName, HttpServletRequest request) {
		if (logger.isDebugEnabled()) {
			logger.debug(RequestPrinter.debugString(request));
		}

		String code = request.getParameter(SsoTokenReqParamName);
		if (code == null) {
			code = "";
		}
		logger.debug("SSO Token is request parameter[ {} ]=[ {} ]", SsoTokenReqParamName, code);
		return code;
	}

	public static String getSubPageCode(HttpServletRequest request) {
		return getHeaderVal(request, "subPageCode");
	}

	public static boolean isAjaxRequest(HttpServletRequest request) {
		boolean isAjax = false;
		if (request.getHeader("X-Requested-With") != null) {
			if ("XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"))) {
				isAjax = true;
			}
			else {
				isAjax = false;
			}
		}
		return isAjax;
	}

	public static boolean isAllowedDomain(String refererHost, List<String> allowedDomainList) {
		if (refererHost != null) {
			for (String domainName : allowedDomainList) {
				logger.info("checking domainName = {}", domainName);
				if (StringUtils.equalsIgnoreCase(refererHost, domainName)) {
					logger.info("refererHost = ( {} ) equalsIgnoreCase domainName = ( {} )", refererHost, domainName);
					return true;
				}
			}
		}
		else {
			logger.error("refererHost is NULL - Returnin FALSE for isAllowedDomain");
			return false;
		}
		logger.error("refererHost is not in allowedDomainList {}", refererHost);
		return false;
	}

}
