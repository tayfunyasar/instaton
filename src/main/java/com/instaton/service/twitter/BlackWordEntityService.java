package com.instaton.service.twitter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instaton.entity.black.blackwordentity.BlackWordEntity;
import com.instaton.repository.twitter.BlackWordEntityRepository;
import com.instaton.service.database.BaseService;

@Service
public class BlackWordEntityService implements BaseService {

  @Autowired private BlackWordEntityRepository repository;

  @Override
  public List<BlackWordEntity> findAll() {
    return this.repository.findAll();
  }

  public void save(final BlackWordEntity input) {

    this.repository.save(input);
  }
}
