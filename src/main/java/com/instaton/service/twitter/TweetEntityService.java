//package com.instaton.service.twitter;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.instaton.entity.twitter.TweetEntity;
//import com.instaton.repository.twitter.TweetEntityRepository;
//import com.instaton.service.database.BaseService;
//
//@Service
//public class TweetEntityService implements BaseService {
//
//  @Autowired private TweetEntityRepository repository;
//
//  public void deleteById(final Long id) {
//    this.repository.deleteById(id);
//  }
//
//  @Override
//  public List<TweetEntity> findAll() {
//    return this.repository.findAll();
//  }
//
//  public List<TweetEntity> findTop100ByOrderById() {
//
//    return this.repository.findTop100ByOrderById();
//  }
//
//  public void save(final TweetEntity input) {
//
//    this.repository.save(input);
//  }
//}
