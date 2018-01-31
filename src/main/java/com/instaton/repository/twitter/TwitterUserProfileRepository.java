package com.instaton.repository.twitter;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.instaton.entity.twitter.UserProfile;

@Repository
public interface TwitterUserProfileRepository extends CrudRepository<UserProfile, String> {}
