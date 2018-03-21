package com.instaton.repository.social.twitter;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.instaton.entity.enums.PlatformEnum;
import com.instaton.entity.social.SearchQueryEntity;

@Repository
public interface SearchQueryRepository extends CrudRepository<SearchQueryEntity, Long> {

  List<SearchQueryEntity> findByPlatformOrderByLastVisitAsc(PlatformEnum platform);
}
