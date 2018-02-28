package com.instaton.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.instaton.config.cache.KeyGeneratorConstants;
import com.instaton.config.exception.InstatonException;
import com.instaton.constant.CacheConstants;
import com.instaton.entity.generic.parameter.ParameterListInputData;
import com.instaton.entity.generic.parameter.ParameterListItem;
import com.instaton.entity.generic.parameter.ParameterListOutput;
import com.instaton.service.database.GenericDataService;

@Service
public class GenericService extends BaseService {

  private static final Logger logger = LoggerFactory.getLogger(GenericService.class);

  @Autowired private GenericDataService genericDataService;

  @Cacheable(
    cacheNames = CacheConstants.PARAMETER_LIST,
    keyGenerator = KeyGeneratorConstants.INPUT_BASED_CACHE_KEY_GENERATOR
  )
  public ParameterListOutput getParameterList(ParameterListInputData inputData)
      throws InstatonException {
    ParameterListOutput allParameterList = genericDataService.getAllParameterList();

    List<ParameterListItem> parameterList = allParameterList.getParameterList();

    List<ParameterListItem> responseParameterList = new ArrayList<>();
    for (ParameterListItem item : parameterList) {
      if (inputData.getParameterType() == item.getParameterType()) {
        responseParameterList.add(item);
      }
    }
    ParameterListOutput response = new ParameterListOutput();
    response.setParameterList(responseParameterList);
    return response;
  }
}
