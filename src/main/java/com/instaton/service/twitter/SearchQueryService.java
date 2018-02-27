package com.instaton.service.twitter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instaton.entity.twitter.SearchQueryEntity;
import com.instaton.repository.twitter.SearchQueryRepository;
import com.instaton.service.database.BaseService;

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
