package com.example.dispenser.config;

import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MongoConfig {
	@Value("${spring.data.mongodb.uri}")
	private String mongoUri;
	@Value("${spring.data.mongodb.database}")
	private String mongoDatabase;

	@Bean
	public MongoTemplate mongoTemplate() {
		return new MongoTemplate(new SimpleMongoClientDatabaseFactory(MongoClients.create(mongoUri), mongoDatabase));
	}
}
