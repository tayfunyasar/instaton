package com.instaton.cache;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class CustomCacheKeyGeneratorHelper {

  public static final Logger logger = LoggerFactory.getLogger(CustomCacheKeyGeneratorHelper.class);

  public static final int NO_PARAM_KEY = 0;

  public static final int NULL_PARAM_KEY = 53;

  private CustomCacheKeyGeneratorHelper() {}

  public static List<Object> cacheKeyGenerator(Object... params) {
    List<Object> hashArray = new ArrayList<>();
    if (params.length == 0) {
      hashArray.add(NO_PARAM_KEY);
    } else if (params.length == 1) {
      Object param = params[0];
      if (param == null) {
        hashArray.add(NULL_PARAM_KEY);
      } else if (!param.getClass().isArray()) {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = "";
        try {
          json = ow.writeValueAsString(param);
        } catch (JsonProcessingException e) {
          logger.error("CustomCacheKeyGeneratorHelper: JsonProcessingException {}", e);
        }
        hashArray.add(json);
      }
    } else {
      ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
      String json = "";
      try {
        json = ow.writeValueAsString(params);
      } catch (JsonProcessingException e) {
        logger.error("CustomCacheKeyGeneratorHelper5: JsonProcessingException {}", e);
      }
      hashArray.add(json);
    }
    return hashArray;
  }
}
