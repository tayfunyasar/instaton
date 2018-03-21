package com.instaton.service.instagram;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.instaton.config.cache.KeyGeneratorConstants;
import com.instaton.constant.CacheConstants;
import com.instaton.entity.social.instagram.InstagramUserEntity;
import com.instaton.repository.social.instagram.InstagramUserRepository;
import com.instaton.service.database.BaseService;

@Service
public class InstagramUserService implements BaseService {

  @Autowired private InstagramUserRepository repository;

  @CacheEvict(
    cacheNames = CacheConstants.INSTAGRAM_USER,
    keyGenerator = KeyGeneratorConstants.USER_BASED_CACHE_KEY_GENERATOR
  )
  public void evictfindAll() {
    System.out.println("cacheevict");
  }

  @Cacheable(
    cacheNames = CacheConstants.INSTAGRAM_USER,
    keyGenerator = KeyGeneratorConstants.USER_BASED_CACHE_KEY_GENERATOR
  )
  @Override
  public List<InstagramUserEntity> findAll() {
    return this.repository.findAll();
  }

  public InstagramUserEntity findByUsername(final String username) {
    return this.repository.findByUsername(username);
  }

  public List<InstagramUserEntity> findTop100ByGenderIsNullOrderByPk() {

    return this.repository.findTop100ByGenderIsNullOrderByPk();
  }

  public void save(final InstagramUserEntity input) {

    this.repository.save(input);
  }
}
