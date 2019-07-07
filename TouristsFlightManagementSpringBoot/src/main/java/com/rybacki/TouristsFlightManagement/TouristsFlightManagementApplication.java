package com.rybacki.TouristsFlightManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.rybacki.TouristsFlightManagement.repository")
@SpringBootApplication
public class TouristsFlightManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(TouristsFlightManagementApplication.class, args);
	}

}
