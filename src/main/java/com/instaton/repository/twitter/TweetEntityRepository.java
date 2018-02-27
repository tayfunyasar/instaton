//package com.instaton.repository.twitter;
//
//import java.util.List;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import com.instaton.entity.twitter.TweetEntity;
//
//@Repository
//public interface TweetEntityRepository extends JpaRepository<TweetEntity, Long> {
//
//  @Override
//  void deleteById(Long id);
//
//  @Override
//  List<TweetEntity> findAll();
//
//  List<TweetEntity> findTop100ByOrderById();
//}
