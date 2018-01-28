package com.instaton.repository.twitter;

import org.springframework.stereotype.Repository;

import com.instaton.entity.generic.parameter.ParameterListItem;
import com.instaton.repository.ReadOnlyRepository;

@Repository
public interface ParameterRepository extends ReadOnlyRepository<ParameterListItem, Long> {

}
