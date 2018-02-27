package com.instaton.service.twitter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.instaton.cache.KeyGeneratorConstants;
import com.instaton.constant.CacheConstants;
import com.instaton.entity.twitter.TwitterUserEntity;
import com.instaton.repository.twitter.TwitterUserRepository;
import com.instaton.service.database.BaseService;

@Service
public class TwitterUserService implements BaseService {

  @Autowired private TwitterUserRepository repository;

  @CacheEvict(
    cacheNames = CacheConstants.TWITTER_USER,
    keyGenerator = KeyGeneratorConstants.USER_BASED_CACHE_KEY_GENERATOR
  )
  public void evictfindAll() {
    System.out.println("cacheevict");
  }

  @Cacheable(
    cacheNames = CacheConstants.TWITTER_USER,
    keyGenerator = KeyGeneratorConstants.USER_BASED_CACHE_KEY_GENERATOR
  )
  @Override
  public List<TwitterUserEntity> findAll() {
    return this.repository.findAll();
  }

  public TwitterUserEntity findByScreenName(final String screenName) {
    return this.repository.findByScreenName(screenName);
  }

  public List<TwitterUserEntity> findTop10ByGenderIsNullOrderById() {

    return this.repository.findTop100ByGenderIsNullOrderById();
  }

  public void save(final TwitterUserEntity input) {

    this.repository.save(input);
  }
}
