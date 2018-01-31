package com.instaton.repository.twitter;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.instaton.entity.twitter.TwitterUser;

@Repository
public interface TwitterUserRepository extends CrudRepository<TwitterUser, Long> {

  @Override
  List<TwitterUser> findAll();
}
