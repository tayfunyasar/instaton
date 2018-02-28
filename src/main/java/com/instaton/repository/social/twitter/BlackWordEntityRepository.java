package com.instaton.repository.social.twitter;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.instaton.entity.social.BlackWordEntity;

@Repository
public interface BlackWordEntityRepository extends CrudRepository<BlackWordEntity, Long> {

  @Override
  List<BlackWordEntity> findAll();
}
