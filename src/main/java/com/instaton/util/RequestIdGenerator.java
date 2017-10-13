package com.instaton.util;

import java.util.UUID;

public class RequestIdGenerator {

	public static String generateRequestId() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}
}
