package com.instaton;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class InstatonApplication {

  public static void main(final String[] args) {
    SpringApplication.run(InstatonApplication.class, args);
  }
}
