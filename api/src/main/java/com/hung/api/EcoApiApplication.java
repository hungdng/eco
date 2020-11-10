package com.hung.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.hung.data", "com.hung.common", "com.hung.api"})
@EntityScan("com.hung.data.entity")
@EnableJpaRepositories("com.hung.data.repository")

public class EcoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcoApiApplication.class, args);
	}

}
