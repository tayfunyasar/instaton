package com.instaton.constant;

import java.util.ArrayList;
import java.util.List;

public class CacheConstants {

	public static final String PARAMETER_LIST = "PARAMETER_LIST";

	public static final List<String> CACHE_LIST;

	private CacheConstants() {}

	static {
		CACHE_LIST = new ArrayList<>();
		CACHE_LIST.add(PARAMETER_LIST);
	}
}
