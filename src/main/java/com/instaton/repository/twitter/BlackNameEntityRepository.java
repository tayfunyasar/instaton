package com.instaton.repository.twitter;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.instaton.entity.black.blacknameentity.BlackNameEntity;

@Repository
public interface BlackNameEntityRepository extends CrudRepository<BlackNameEntity, Long> {

	@Override
	List<BlackNameEntity> findAll();

}
