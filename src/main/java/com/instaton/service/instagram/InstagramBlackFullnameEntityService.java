package com.instaton.service.instagram;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.instaton.config.cache.KeyGeneratorConstants;
import com.instaton.constant.CacheConstants;
import com.instaton.entity.social.instagram.black.InstagramBlackUsernameEntity;
import com.instaton.repository.social.instagram.InstagramBlackFullnameEntityRepository;
import com.instaton.service.database.BaseService;

@Service
public class InstagramBlackFullnameEntityService implements BaseService {

  @Autowired private InstagramBlackFullnameEntityRepository repository;

  @Cacheable(
    cacheNames = CacheConstants.BLACK_NAME,
    keyGenerator = KeyGeneratorConstants.USER_BASED_CACHE_KEY_GENERATOR
  )
  @Override
  public List<InstagramBlackUsernameEntity> findAll() {
    return this.repository.findAll();
  }

  public void save(final InstagramBlackUsernameEntity input) {

    this.repository.save(input);
  }
}
