package com.instaton.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Service;

import com.instaton.entity.social.twitter.UserProfile;
import com.instaton.repository.TwitterUserProfileRepository;

@Service
public class AccountConnectionSignUpService implements ConnectionSignUp {

  private static final Logger LOG = LoggerFactory.getLogger(AccountConnectionSignUpService.class);

  @Autowired private TwitterUserProfileRepository twitterUserProfileRepository;

  @Override
  public String execute(final Connection<?> connection) {
    final org.springframework.social.connect.UserProfile profile = connection.fetchUserProfile();
    final String userId = UUID.randomUUID().toString();
    // TODO: Or simply use: r = new Random(); r.nextInt(); ???
    LOG.debug("Created user-id: " + profile.getId());

    final UserProfile userProfile = new UserProfile(userId, profile);

    this.twitterUserProfileRepository.save(userProfile);

    return userId;
  }
}
