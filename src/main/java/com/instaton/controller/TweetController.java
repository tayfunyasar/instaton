//package com.instaton.controller;
//
//import java.io.IOException;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.instaton.constant.EndpointConstant;
//import com.instaton.entity.twitter.TweetEntity;
//import com.instaton.service.twitter.TweetEntityService;
//
//@RestController
//@RequestMapping(EndpointConstant.API_ENDPOINT_TWEET)
//public class TweetController {
//
//  @Autowired private TweetEntityService tweetEntityService;
//
//  @GetMapping("/list")
//  public List<TweetEntity> getTweetList() throws IOException {
//
//    return this.tweetEntityService.findTop100ByOrderById();
//  }
//}
