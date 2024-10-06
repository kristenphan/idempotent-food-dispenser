package com.example.dispenser.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.support.RetryTemplate;

@Configuration
@EnableRetry
public class RetryConfig {
	@Bean
	public RetryTemplate mongoRetryTemplate() {
		return RetryTemplate.builder()
				.maxAttempts(3) // Maximum 3 retries
				.fixedBackoff(10000) // 10 seconds backoff
				.build();
	}
}
