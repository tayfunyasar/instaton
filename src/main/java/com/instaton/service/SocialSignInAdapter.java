package com.instaton.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;

import com.instaton.config.SpringSecurityConfiguration;
import com.instaton.model.User;
import com.instaton.repository.UserRepository;
import com.instaton.utils.NumberUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class SocialSignInAdapter implements SignInAdapter {

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Override
	public String signIn(String userId, Connection<?> connection, NativeWebRequest nativeWebRequest) {
		if (authenticationService.isCurrentUserAuthenticated()) {
			if (isMigrationFromSocialUserToCurrentUserNeeded(userId)) {
				userService.saveSocialUser(connection.fetchUserProfile(), connection.createData());
			}
			return "/user";
		} else {
			User user = userRepository.findById(NumberUtils.parseLong(userId));
			authenticationService.authenticate(connection, user);

			return prepareRedirectUrl(nativeWebRequest);
		}
	}

	private boolean isMigrationFromSocialUserToCurrentUserNeeded(String userId) {
		return !authenticationService.getCurrentUser().getId().toString().equals(userId);
	}

	private String prepareRedirectUrl(NativeWebRequest nativeWebRequest) {
		HttpSession session = nativeWebRequest.getNativeRequest(HttpServletRequest.class).getSession(false);
		if (session != null) {
			SavedRequest savedRequest = (SavedRequest) session.getAttribute("SPRING_SECURITY_SAVED_REQUEST");
			if (savedRequest != null) {
				return savedRequest.getRedirectUrl();
			}
		}

		return SpringSecurityConfiguration.DEFAULT_SUCCESS_SIGNIN_URL;
	}

}