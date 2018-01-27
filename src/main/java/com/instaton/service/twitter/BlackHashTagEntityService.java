package com.instaton.service.twitter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instaton.entity.black.blackhashtagentity.BlackHashTagEntity;
import com.instaton.repository.twitter.BlackHashTagEntityRepository;
import com.instaton.service.database.BaseService;

@Service
public class BlackHashTagEntityService implements BaseService {

	@Autowired
	private BlackHashTagEntityRepository repository;

	@Override
	public List<BlackHashTagEntity> findAll() {
		return this.repository.findAll();
	}

	public void save(BlackHashTagEntity blackKeywordItem) {

		this.repository.save(blackKeywordItem);
	}

}
