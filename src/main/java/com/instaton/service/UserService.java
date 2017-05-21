package com.instaton.service;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.UserProfile;
import org.springframework.stereotype.Service;

import com.instaton.model.User;
import com.instaton.model.UserConnection;
import com.instaton.repository.UserConnectionRepository;
import com.instaton.repository.UserRepository;
import com.instaton.utils.NumberUtils;

@Service
public class UserService {

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserConnectionRepository userConnectionRepository;

	public User saveSocialUser(UserProfile userProfile, ConnectionData connectionData) {
		String email = userProfile.getEmail();
		User user = initializeSocialUser(email);

		String imageUrl = SocialUserDetailsHelper.getImageUrl(connectionData);
		if (user.isNotPersisted()) {
			user.setEmail(email);
			user.setFirstName(SocialUserDetailsHelper.getFirstName(userProfile));
			user.setLastName(userProfile.getLastName());
			user.setPassword(UUID.randomUUID().toString());
			user.setSocialImageUrl(imageUrl);
			userRepository.save(user);

		} else {
			user.setSocialImageUrl(imageUrl);
		}

		UserConnection userConnection = initializeUserConnection(connectionData);
		userConnection.setUserId(user.getId().toString());
		userConnection.setAccessToken(connectionData.getAccessToken());
		userConnection.setDisplayName(connectionData.getDisplayName());
		userConnection.setExpireTime(connectionData.getExpireTime());
		userConnection.setImageUrl(imageUrl);
		userConnection.setProfileUrl(connectionData.getProfileUrl());
		userConnection.setProviderId(connectionData.getProviderId());
		userConnection.setProviderUserId(connectionData.getProviderUserId());
		userConnection.setRefreshToken(connectionData.getRefreshToken());
		userConnection.setSecret(connectionData.getSecret());
		userConnection.setRank(0);
		userConnection = userConnectionRepository.saveAndFlush(userConnection);

		user.addUserConnection(userConnection);

		user = userRepository.saveAndFlush(user);

		return user;
	}

	private User initializeSocialUser(String email) {
		if (authenticationService.isCurrentUserAuthenticated()) {
			return authenticationService.getCurrentUser();
		}

		User user = new User();
		if (StringUtils.isNotBlank(email)) {
			User existingUser = userRepository.findByEmail(email);
			if (existingUser != null) {
				user = existingUser;
			}
		}
		return user;
	}
	
    private UserConnection initializeUserConnection(ConnectionData connectionData) {
        UserConnection existingUserConnection = userConnectionRepository.findByProviderIdAndProviderUserId(connectionData.getProviderId(), connectionData.getProviderUserId());
        if (existingUserConnection != null) {
            User oldSocialUser = userRepository.findById(NumberUtils.parseLong(existingUserConnection.getUserId()));
            oldSocialUser.getUserConnections().clear();
            userRepository.save(oldSocialUser);
        }

        return new UserConnection();
    }

}
