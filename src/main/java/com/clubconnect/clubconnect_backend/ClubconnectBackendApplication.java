package com.clubconnect.clubconnect_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = "com.clubconnect.clubconnect_backend.user")
public class ClubconnectBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClubconnectBackendApplication.class, args);
	}

}
