package com.instaton.repository.twitter;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.instaton.entity.twitter.TwitterProfileEntity;

@Repository
public interface TwitterUserRepository extends CrudRepository<TwitterProfileEntity, Long> {

  @Override
  List<TwitterProfileEntity> findAll();
}
