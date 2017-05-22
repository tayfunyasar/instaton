package com.instaton.utils;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.instaton.model.User;

@Component
@Scope("request")
public class CurrentUserHolder {

	private User currentUser;

	public boolean hasNotCurrentUser() {
		return currentUser == null;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

}