package com.instaton.repository.social;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.instaton.entity.social.BlackNameEntity;

@Repository
public interface BlackNameEntityRepository extends CrudRepository<BlackNameEntity, Long> {

  @Override
  List<BlackNameEntity> findAll();
}
