package com.instaton.repository.social.twitter;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.instaton.entity.social.BlackHashTagEntity;

@Repository
public interface BlackHashTagEntityRepository extends CrudRepository<BlackHashTagEntity, Long> {

  @Override
  List<BlackHashTagEntity> findAll();
}
