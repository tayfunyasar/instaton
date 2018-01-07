package com.instaton.config;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.connect.web.ProviderSignInAttempt;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.linkedin.connect.LinkedInConnectionFactory;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;

@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {

	@Inject
	private DataSource dataSource;

	// @Inject
	// private TextEncryptor textEncryptor;

	@Inject
	private Environment environment;

	/**
	 * The locator for SaaS provider connection factories.
	 * When support for a new provider is added to Greenhouse, simply register the corresponding {@link ConnectionFactory} here.
	 * The current Environment is used to lookup the credentials assigned to the Greenhouse application by each provider during application registration.
	 * This bean is defined as a scoped-proxy so it can be serialized in support of {@link ProviderSignInAttempt provier sign-in attempts}.
	 */
	@Bean
	@Scope(value = "singleton", proxyMode = ScopedProxyMode.INTERFACES)
	public ConnectionFactoryLocator connectionFactoryLocator() {
		ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
		registry.addConnectionFactory(new TwitterConnectionFactory(environment.getProperty("spring.social.twitter.consumer-key"), environment.getProperty("spring.social.twitter.consumer-secret")));
		// registry.addConnectionFactory(new FacebookConnectionFactory(environment.getProperty("facebook.appId"), environment.getProperty("facebook.appSecret")));
		// registry.addConnectionFactory(new LinkedInConnectionFactory(environment.getProperty("linkedin.consumerKey"), environment.getProperty("linkedin.consumerSecret")));
		return registry;
	}

	/**
	 * The shared store for users' connection information.
	 * Uses a RDBMS-based store accessed with Spring's JdbcTemplate.
	 * The returned repository encrypts the data using the configured {@link TextEncryptor}.
	 */
	@Bean
	public UsersConnectionRepository usersConnectionRepository() {
		return new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator(), null);
	}

	/**
	 * A request-scoped bean that provides the data access interface to the current user's connections.
	 * Since it is a scoped-proxy, references to this bean MAY be injected at application startup time.
	 * If no user is authenticated when the target is resolved, an {@link IllegalStateException} is thrown.
	 * 
	 * @throws IllegalStateException
	 *             when no user is authenticated.
	 */
	@Bean
	@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
	public ConnectionRepository connectionRepository() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			throw new IllegalStateException("Unable to get a ConnectionRepository: no user signed in");
		}
		return usersConnectionRepository().createConnectionRepository(authentication.getName().toString());
	}

	/**
	 * A request-scoped bean representing the API binding to Facebook for the current user.
	 * Since it is a scoped-proxy, references to this bean MAY be injected at application startup time.
	 * The target is an authorized {@link Facebook} instance if the current user has connected his or her account with a Facebook account.
	 * Otherwise, the target is a new FacebookTemplate that can invoke operations that do not require authorization.
	 */
	@Bean
	@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
	public Facebook facebook() {
		Connection<Facebook> facebook = connectionRepository().findPrimaryConnection(Facebook.class);
		return facebook != null ? facebook.getApi() : null;
	}

	/**
	 * A proxy to the request-scoped API binding to Twitter for the current user.
	 * Since it is a scoped-proxy, references to this bean MAY be injected at application startup time.
	 * The target is an authorized {@link Twitter} instance if the current user has connected his or her account with a Twitter account.
	 * Otherwise, the target is a new TwitterTemplate that can invoke operations that do not require authorization.
	 */
	@Bean
	@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
	public Twitter twitter() {
		Connection<Twitter> twitter = connectionRepository().findPrimaryConnection(Twitter.class);
		return twitter != null ? twitter.getApi() : null;
	}

	//
	@Bean
	@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
	public SimpleSignInAdapter simpleSignInAdapter(RequestCache rq) {
		return new SimpleSignInAdapter(rq);
	}

	@Override
	public UserIdSource getUserIdSource() {
		return new UserIdSource() {

			@Override
			public String getUserId() {
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				if (authentication == null) {
					throw new IllegalStateException("Unable to get a ConnectionRepository: no user signed in");
				}
				return authentication.getName();
			}
		};
	}

}