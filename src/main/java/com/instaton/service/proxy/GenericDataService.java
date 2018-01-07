package com.instaton.service.proxy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.instaton.cache.KeyGeneratorConstants;
import com.instaton.constant.CacheConstants;
import com.instaton.entity.generic.parameter.ParameterListItem;
import com.instaton.entity.generic.parameter.ParameterListOutput;
import com.instaton.exception.InstatonException;
import com.instaton.repository.ParameterRepository;

@Service
public class GenericDataService {

	@Autowired
	private ParameterRepository parameterRepository;

	@Cacheable(cacheNames = CacheConstants.PARAMETER_LIST, keyGenerator = KeyGeneratorConstants.INPUT_BASED_CACHE_KEY_GENERATOR)
	public ParameterListOutput getAllParameterList() throws InstatonException {
		Iterable<ParameterListItem> findAll = parameterRepository.findAll();

		ParameterListOutput output = new ParameterListOutput();
		List<ParameterListItem> parameterList = new ArrayList<>();

		Iterator<ParameterListItem> iterator = findAll.iterator();
		while (iterator.hasNext()) {
			parameterList.add(iterator.next());
		}

		output.setParameterList(parameterList);
		return output;
	}
}
