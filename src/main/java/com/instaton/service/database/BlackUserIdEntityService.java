package com.instaton.service.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instaton.entity.black.blackuserid.BlackUserIdEntity;
import com.instaton.repository.BlackUserIdEntityRepository;

@Service
public class BlackUserIdEntityService implements BaseService {

	@Autowired
	private BlackUserIdEntityRepository repository;

	@Override
	public List<BlackUserIdEntity> findAll() {
		return this.repository.findAll();
	}

	public void save(final BlackUserIdEntity input) {

		this.repository.save(input);
	}

}
