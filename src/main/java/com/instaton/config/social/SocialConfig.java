package com.instaton.config.social;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;

import com.instaton.service.AccountConnectionSignUpService;

@Configuration
@EnableSocial
public class SocialConfig implements SocialConfigurer {

  @Autowired private DataSource dataSource;

  @Autowired private AccountConnectionSignUpService accountConnectionSignUpService;

  @Override
  public void addConnectionFactories(
      final ConnectionFactoryConfigurer connectionFactoryConfigurer,
      final Environment environment) {
    // connectionFactoryConfigurer.addConnectionFactory(new TwitterConnectionFactory(this.consumerKey, this.consumerSecret));
  }

  @Override
  public UserIdSource getUserIdSource() {
    return () -> {
      final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      if (authentication == null) {
        throw new IllegalStateException("Unable to get a ConnectionRepository: no user signed in");
      }
      return authentication.getName();
    };
  }

  @Override
  public UsersConnectionRepository getUsersConnectionRepository(
      final ConnectionFactoryLocator connectionFactoryLocator) {
    final JdbcUsersConnectionRepository repository =
        new JdbcUsersConnectionRepository(
            this.dataSource, connectionFactoryLocator, Encryptors.noOpText());
    repository.setConnectionSignUp(this.accountConnectionSignUpService);
    return repository;
  }
}
