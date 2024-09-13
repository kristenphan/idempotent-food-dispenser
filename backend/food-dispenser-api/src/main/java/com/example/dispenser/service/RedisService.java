package com.example.dispenser.service;

import com.example.dispenser.config.RedisConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class RedisService {
	RedisTemplate<String, Object> redisTemplate;

	@Autowired
	public RedisService(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public void set(String key, Object value) {
		log.info("Writing to Redis: key={}, value={}", key, value);
		redisTemplate.opsForValue().set(key, value);
	}

	public String get(String key) {
		log.info("Reading from Redis: key={}", key);
		return Objects.requireNonNull(redisTemplate.opsForValue().get(key)).toString();
	}
}
