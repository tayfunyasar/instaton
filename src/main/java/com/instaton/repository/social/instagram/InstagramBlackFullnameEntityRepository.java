package com.instaton.repository.social.instagram;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.instaton.entity.social.instagram.black.InstagramBlackUsernameEntity;

@Repository
public interface InstagramBlackFullnameEntityRepository
    extends CrudRepository<InstagramBlackUsernameEntity, Long> {

  @Override
  List<InstagramBlackUsernameEntity> findAll();
}
