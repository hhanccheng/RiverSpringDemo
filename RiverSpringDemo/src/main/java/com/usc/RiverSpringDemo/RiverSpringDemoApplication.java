package com.usc.RiverSpringDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.usc")
@ComponentScan("com.usc")
@EnableJpaRepositories("com.usc.dao")
public class RiverSpringDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RiverSpringDemoApplication.class, args);
	}

}
