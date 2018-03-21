package com.instaton.service.twitter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.instaton.config.cache.KeyGeneratorConstants;
import com.instaton.constant.CacheConstants;
import com.instaton.entity.social.BlackNameEntity;
import com.instaton.repository.social.BlackNameEntityRepository;
import com.instaton.service.database.BaseService;

@Service
public class BlackNameEntityService implements BaseService {

  @Autowired private BlackNameEntityRepository repository;

  @Cacheable(
    cacheNames = CacheConstants.BLACK_NAME,
    keyGenerator = KeyGeneratorConstants.USER_BASED_CACHE_KEY_GENERATOR
  )
  @Override
  public List<BlackNameEntity> findAll() {
    return this.repository.findAll();
  }

  public void save(final BlackNameEntity input) {

    this.repository.save(input);
  }
}
