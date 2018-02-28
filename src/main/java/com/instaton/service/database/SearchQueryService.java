package com.instaton.service.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instaton.entity.social.twitter.SearchQueryEntity;
import com.instaton.repository.social.twitter.SearchQueryRepository;

@Service
public class SearchQueryService implements BaseService {

  @Autowired private SearchQueryRepository repository;

  @Override
  public <T> List<T> findAll() {
    throw new IllegalAccessError();
  }

  public List<SearchQueryEntity> findAllByOrderByLastVisitAsc() {
    return this.repository.findAllByOrderByLastVisitAsc();
  }

  public void save(final SearchQueryEntity input) {

    this.repository.save(input);
  }
}
