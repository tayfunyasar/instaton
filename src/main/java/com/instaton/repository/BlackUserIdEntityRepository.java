package com.instaton.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.instaton.entity.black.blackuserid.BlackUserIdEntity;

@Repository
public interface BlackUserIdEntityRepository extends CrudRepository<BlackUserIdEntity, Long> {

	@Override
	List<BlackUserIdEntity> findAll();

}
