package com.instaton.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.UserProfile;

import com.instaton.utils.SocialSignInProvider;

public final class SocialUserDetailsHelper {

	private SocialUserDetailsHelper() {
	}

	public static String getImageUrl(ConnectionData connectionData) {
		String imageUrl = connectionData.getImageUrl();
		if (SocialSignInProvider.Twitter.equals(connectionData.getProviderId())) {
			imageUrl = imageUrl.replace("_normal", "").replace("http://", "https://");
		}
		return imageUrl;
	}

	public static String getFirstName(UserProfile userProfile) {
		return StringUtils.isNotBlank(userProfile.getFirstName()) ? userProfile.getFirstName() : userProfile.getName();
	}

}