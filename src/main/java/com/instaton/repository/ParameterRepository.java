package com.instaton.repository;

import org.springframework.stereotype.Repository;

import com.instaton.entity.generic.parameter.ParameterListItem;
import com.instaton.repository.base.ReadOnlyRepository;

@Repository
public interface ParameterRepository extends ReadOnlyRepository<ParameterListItem, Long> {

}
