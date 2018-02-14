package com.instaton.service.twitter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instaton.entity.black.BlackUserIdEntity;
import com.instaton.repository.twitter.BlackUserIdEntityRepository;
import com.instaton.service.database.BaseService;

@Service
public class BlackUserIdEntityService implements BaseService {

  @Autowired private BlackUserIdEntityRepository repository;

  @Override
  public List<BlackUserIdEntity> findAll() {
    return this.repository.findAll();
  }

  public void save(final BlackUserIdEntity input) {

    this.repository.save(input);
  }
}
