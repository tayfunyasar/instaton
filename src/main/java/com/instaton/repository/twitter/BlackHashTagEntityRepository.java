package com.instaton.repository.twitter;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.instaton.entity.black.blackhashtagentity.BlackHashTagEntity;

@Repository
public interface BlackHashTagEntityRepository extends CrudRepository<BlackHashTagEntity, Long> {

	@Override
	List<BlackHashTagEntity> findAll();

}
