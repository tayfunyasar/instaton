package com.instaton.service;

public class BaseService {

	public <T> boolean isEmpty(T data) {
		if (data == null) {
			return true;
		}
		return false;
	}

}
