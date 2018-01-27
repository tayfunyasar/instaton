package com.instaton.service.twitter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instaton.entity.GenderEnum;
import com.instaton.entity.twitter.TwitterUser;
import com.instaton.repository.twitter.TwitterUserRepository;
import com.instaton.service.database.BaseService;

@Service
public class TwitterUserService implements BaseService {

	@Autowired
	private TwitterUserRepository repository;

	@Override
	public List<TwitterUser> findAll() {
		return this.repository.findAll();
	}

	public List<TwitterUser> findAllByGenderMale() {
		final List<TwitterUser> users = new ArrayList<>();

		for (final TwitterUser twitterUser : this.findAll()) {
			if (twitterUser.getGender() == GenderEnum.MALE) {
				users.add(twitterUser);
			}
		}
		return users;
	}

	public void save(final TwitterUser input) {

		this.repository.save(input);
	}

}
