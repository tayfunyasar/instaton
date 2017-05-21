package com.instaton.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.instaton.model.SocialUserDetails;
import com.instaton.model.User;
import com.instaton.repository.UserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) {
		User user = userRepository.findByEmail(email);

		if (Objects.nonNull(user)) {
			return createUserDetailsFrom(user);
		}

		throw new UsernameNotFoundException("No user found with email: " + email);
	}

	public UserDetails loadUserByUserId(String userId) {
		User user = userRepository.findById(Long.parseLong(userId));

		if (Objects.nonNull(user)) {
			return createUserDetailsFrom(user);
		}

		throw new UsernameNotFoundException("No user found with user id: " + userId);
	}

	private UserDetails createUserDetailsFrom(User user) {
		SocialUserDetails socialUserDetails = new SocialUserDetails(user.getId().toString(), user.getPassword(),
				getAuthorities(user));

		socialUserDetails.setId(user.getId());
		socialUserDetails.setFirstName(user.getFirstName());
		socialUserDetails.setLastName(user.getLastName());

		return socialUserDetails;
	}

	private List<GrantedAuthority> getAuthorities(User user) {
		return Arrays.asList(user.getUserRole().getAuthorities()).stream().map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

}