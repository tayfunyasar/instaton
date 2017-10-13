package com.instaton.constant;

import com.instaton.entity.generic.parameter.ParameterListItem;
import com.instaton.entity.generic.parameter.ParameterListOutput;

public class ApplicationConstants {

	public static long SESSION_TIMEOUT = 0l;
	public static long SESSION_WARNING = 0l;

	private ApplicationConstants() {}

	public static void setTimeout(ParameterListOutput sessionParameters) {
		for (ParameterListItem item : sessionParameters.getParameterList()) {
			if (item.getParameterCode().equals("SESSION_TIMEOUT")) {
				SESSION_TIMEOUT = Long.parseLong(item.getParameterValue());
			}
			else if (item.getParameterCode().equals("SESSION_TIMEOUT_WARNING")) {
				SESSION_WARNING = Long.parseLong(item.getParameterValue());
			}
		}
	}
}
