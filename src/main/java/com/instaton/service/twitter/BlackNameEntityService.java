package com.instaton.service.twitter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instaton.entity.black.blacknameentity.BlackNameEntity;
import com.instaton.repository.twitter.BlackNameEntityRepository;
import com.instaton.service.database.BaseService;

@Service
public class BlackNameEntityService implements BaseService {

  @Autowired private BlackNameEntityRepository repository;

  @Override
  public List<BlackNameEntity> findAll() {
    return this.repository.findAll();
  }

  public void save(final BlackNameEntity input) {

    this.repository.save(input);
  }
}
