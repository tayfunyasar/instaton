package com.instaton.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class InstatonSocialUserDetailsService implements SocialUserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InstatonSocialUserDetailsService.class);
    
    @Autowired
    private UserDetailsService userDetailsService;
   
    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException, DataAccessException {
        LOGGER.debug("Loading user by user id: {}", userId);

        UserDetails userDetails = userDetailsService.loadUserByUsername(userId);
        return new SocialUser(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
    }
}