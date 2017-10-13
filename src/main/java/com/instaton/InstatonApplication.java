package com.instaton;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class InstatonApplication {

	public static void main(String[] args) {
		SpringApplication.run(InstatonApplication.class, args);
	}
}
