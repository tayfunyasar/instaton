package com.instaton.cache;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CustomKeyGenerators {

  Logger logger = LoggerFactory.getLogger(CustomKeyGenerators.class);

  // @Autowired
  // private PartnerAuthenticationService partnerAuthenticationService;

  @Bean
  @Qualifier(KeyGeneratorConstants.INPUT_BASED_CACHE_KEY_GENERATOR)
  public KeyGenerator inputBasedCacheKeyGenerator() {
    return new KeyGenerator() {

      @Override
      public Object generate(Object target, Method method, Object... params) {
        List<Object> hashArray = CustomCacheKeyGeneratorHelper.cacheKeyGenerator(params);

        return Arrays.deepHashCode(hashArray.toArray());
      }
    };
  }

  // @Bean
  // @Qualifier(KeyGeneratorConstants.LOGIN_LEVEL_BASED_CACHE)
  // public KeyGenerator loginLevelBasedCacheKeyGenerator() {
  // return new KeyGenerator() {
  //
  // @Override
  // public Object generate(Object target, Method method, Object... params) {
  // List<Object> hashArray = CustomCacheKeyGeneratorHelper.cacheKeyGenerator(params);
  //
  // // if (partnerAuthenticationService.getCurrentAuthentication() != null)
  // // hashArray.add(partnerAuthenticationService.getCurrentAuthentication().getLoginLevel());
  // // else
  // // hashArray.add("00");
  // return Arrays.deepHashCode(hashArray.toArray());
  // }
  // };
  // }

  @Bean
  @Qualifier(KeyGeneratorConstants.USER_BASED_CACHE_KEY_GENERATOR)
  public KeyGenerator userBasedCacheKeyGenerator() {
    return new KeyGenerator() {

      @Override
      public Object generate(Object target, Method method, Object... params) {
        List<Object> hashArray = CustomCacheKeyGeneratorHelper.cacheKeyGenerator(params);

        // hashArray.add(partnerAuthenticationService.getCurrentAuthentication().getUserOpaqueId());
        // hashArray.add(partnerAuthenticationService.getCurrentAuthentication().getCurrentFirm().getFirmId());
        return Arrays.deepHashCode(hashArray.toArray());
      }
    };
  }

  @Bean
  @Qualifier(KeyGeneratorConstants.SESSION_SCOPED_CACHE_KEY_GENERATOR)
  public KeyGenerator sessionScopedCacheKeyGenerator() {
    return new KeyGenerator() {

      @Override
      public Object generate(Object target, Method method, Object... params) {
        List<Object> hashArray = CustomCacheKeyGeneratorHelper.cacheKeyGenerator(params);

        // hashArray.add(partnerAuthenticationService.getCurrentAuthentication().getCurrentFirm().getFirmId());
        // hashArray.add(partnerAuthenticationService.getCurrentAuthentication().getUserOpaqueId());
        // hashArray.add(partnerAuthenticationService.getCurrentAuthentication().getGarantiSessionId());
        return Arrays.deepHashCode(hashArray.toArray());
      }
    };
  }
}
