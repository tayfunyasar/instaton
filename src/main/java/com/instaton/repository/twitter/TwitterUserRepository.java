package com.instaton.repository.twitter;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.instaton.entity.twitter.TwitterUserEntity;

@Repository
public interface TwitterUserRepository extends CrudRepository<TwitterUserEntity, Long> {

  @Override
  List<TwitterUserEntity> findAll();

  TwitterUserEntity findByScreenName(String screenName);

  List<TwitterUserEntity> findTop100ByGenderIsNullOrderById();
}
