package com.instaton.repository.social.twitter;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.instaton.entity.social.twitter.SearchQueryEntity;

@Repository
public interface SearchQueryRepository extends CrudRepository<SearchQueryEntity, Long> {

  @Override
  List<SearchQueryEntity> findAll();

  List<SearchQueryEntity> findAllByOrderByLastVisitAsc();
}
