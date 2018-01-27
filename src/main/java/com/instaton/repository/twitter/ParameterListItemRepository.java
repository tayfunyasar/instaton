package com.instaton.repository.twitter;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.instaton.entity.generic.parameter.ParameterListItem;

@Repository
public interface ParameterListItemRepository extends PagingAndSortingRepository<ParameterListItem, Long> {

}
