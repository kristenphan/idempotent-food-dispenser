package com.example.dispenser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.example.dispenser.repository")
public class IdempontentFoodDispenserApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdempontentFoodDispenserApplication.class, args);
	}

}
