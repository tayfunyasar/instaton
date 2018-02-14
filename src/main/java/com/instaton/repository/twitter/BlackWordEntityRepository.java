package com.instaton.repository.twitter;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.instaton.entity.black.BlackWordEntity;

@Repository
public interface BlackWordEntityRepository extends CrudRepository<BlackWordEntity, Long> {

  @Override
  List<BlackWordEntity> findAll();
}
