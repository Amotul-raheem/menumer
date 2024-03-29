package com.menumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoAuditing
public class MenumerApplication {
	public static void main(String[] args) {
		SpringApplication.run(MenumerApplication.class, args);
	}

}
