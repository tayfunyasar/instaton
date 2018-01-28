package com.instaton;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories
@EnableSpringConfigured
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class InstatonApplication {

	public static void main(String[] args) {
		SpringApplication.run(InstatonApplication.class, args);
	}
}
