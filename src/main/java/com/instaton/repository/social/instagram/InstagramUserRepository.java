package com.instaton.repository.social.instagram;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.instaton.entity.social.instagram.InstagramUserEntity;

@Repository
public interface InstagramUserRepository extends CrudRepository<InstagramUserEntity, Long> {

  @Override
  List<InstagramUserEntity> findAll();

  InstagramUserEntity findByUsername(String username);

  List<InstagramUserEntity> findTop100ByGenderIsNullOrderByPk();
}
