package com.instaton.service.twitter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.instaton.cache.KeyGeneratorConstants;
import com.instaton.constant.CacheConstants;
import com.instaton.entity.black.BlackHashTagEntity;
import com.instaton.repository.twitter.BlackHashTagEntityRepository;
import com.instaton.service.database.BaseService;

@Service
public class BlackHashTagEntityService implements BaseService {

  @Autowired private BlackHashTagEntityRepository repository;

  @Cacheable(
    cacheNames = CacheConstants.BLACK_HASHTAG,
    keyGenerator = KeyGeneratorConstants.USER_BASED_CACHE_KEY_GENERATOR
  )
  @Override
  public List<BlackHashTagEntity> findAll() {
    return this.repository.findAll();
  }

  public void save(final BlackHashTagEntity blackKeywordItem) {

    this.repository.save(blackKeywordItem);
  }
}
