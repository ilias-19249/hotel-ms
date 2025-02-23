package com.example.learn_handling_exceptions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class LearnHandlingExceptionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnHandlingExceptionsApplication.class, args);
	}

}
