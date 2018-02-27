package com.instaton;

import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.instaton.component.QueryScheduler;
import com.instaton.service.twitter.BlackHashTagEntityService;
import com.instaton.service.twitter.BlackNameEntityService;
import com.instaton.service.twitter.BlackWordEntityService;
import com.instaton.service.twitter.TwitterUserService;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class InstatonApplication extends SpringBootServletInitializer {

  public static void main(final String[] args) throws BeansException, InterruptedException {
    final ConfigurableApplicationContext context =
        SpringApplication.run(InstatonApplication.class, args);

    context.getBean(BlackHashTagEntityService.class).findAll();
    context.getBean(TwitterUserService.class).findAll();
    context.getBean(BlackNameEntityService.class).findAll();
    context.getBean(BlackWordEntityService.class).findAll();

    context.getBean(QueryScheduler.class).schedule();
  }
}
