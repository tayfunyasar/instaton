package com.instaton.service.twitter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.instaton.config.cache.KeyGeneratorConstants;
import com.instaton.constant.CacheConstants;
import com.instaton.entity.social.BlackWordEntity;
import com.instaton.repository.social.twitter.BlackWordEntityRepository;
import com.instaton.service.database.BaseService;

@Service
public class BlackWordEntityService implements BaseService {

  @Autowired private BlackWordEntityRepository repository;

  @Cacheable(
    cacheNames = CacheConstants.BLACK_WORD,
    keyGenerator = KeyGeneratorConstants.USER_BASED_CACHE_KEY_GENERATOR
  )
  @Override
  public List<BlackWordEntity> findAll() {
    return this.repository.findAll();
  }

  public void save(final BlackWordEntity input) {

    this.repository.save(input);
  }
}
