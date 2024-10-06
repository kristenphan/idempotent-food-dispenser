package com.example.dispenser.service;

import com.example.dispenser.model.IdempotencyDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class RedisService {
	RedisTemplate<String, IdempotencyDetails> redisTemplate;

	@Autowired
	public RedisService(RedisTemplate<String, IdempotencyDetails> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public void set(String idempotencyKey, IdempotencyDetails idempotencyDetails) {
		log.info("Writing to Redis: key={}, value={}", idempotencyKey, idempotencyDetails);
		redisTemplate.opsForValue().set(idempotencyKey, idempotencyDetails);
	}

	public IdempotencyDetails get(String idempotencyKey) {
		log.info("Reading from Redis for key={}", idempotencyKey);
		return redisTemplate.opsForValue().get(idempotencyKey);
	}
}