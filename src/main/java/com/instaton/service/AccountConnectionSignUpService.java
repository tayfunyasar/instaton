package com.instaton.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Service;

import com.instaton.entity.social.twitter.UserProfile;
import com.instaton.repository.social.twitter.TwitterUserProfileRepository;

import java.util.UUID;

@Service
public class AccountConnectionSignUpService implements ConnectionSignUp {

  private static final Logger LOG = LoggerFactory.getLogger(AccountConnectionSignUpService.class);

  @Autowired private TwitterUserProfileRepository twitterUserProfileRepository;

  public String execute(Connection<?> connection) {
    org.springframework.social.connect.UserProfile profile = connection.fetchUserProfile();
    String userId = UUID.randomUUID().toString();
    // TODO: Or simply use: r = new Random(); r.nextInt(); ???
    LOG.debug("Created user-id: " + profile.getId());

    UserProfile userProfile = new UserProfile(userId, profile);

    twitterUserProfileRepository.save(userProfile);

    return userId;
  }
}
