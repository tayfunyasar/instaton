package com.instaton.service.twitter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instaton.entity.GenderEnum;
import com.instaton.entity.twitter.TwitterProfileEntity;
import com.instaton.repository.twitter.TwitterUserRepository;
import com.instaton.service.database.BaseService;

@Service
public class TwitterUserService implements BaseService {

  @Autowired private TwitterUserRepository repository;

  @Override
  public List<TwitterProfileEntity> findAll() {
    return this.repository.findAll();
  }

  public List<TwitterProfileEntity> findAllByNotFemale() {
    final List<TwitterProfileEntity> users = new ArrayList<>();

    for (final TwitterProfileEntity twitterUser : this.findAll()) {
      if (twitterUser.getGender() != GenderEnum.FEMALE) {
        users.add(twitterUser);
      }
    }
    return users;
  }

  public void save(final TwitterProfileEntity input) {

    this.repository.save(input);
  }
}
