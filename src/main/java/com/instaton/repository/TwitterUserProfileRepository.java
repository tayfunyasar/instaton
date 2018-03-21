package com.instaton.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.instaton.entity.social.twitter.UserProfile;

@Repository
public interface TwitterUserProfileRepository extends CrudRepository<UserProfile, String> {}
