package com.example.dispenser.service;

import com.example.dispenser.model.FeedRecord;
import com.example.dispenser.model.FeedResponse;
import com.example.dispenser.model.IdempotencyDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DispenserService {
	RedisService redisService;
	MongoService cosmosService;
	RetryTemplate mongoRetryTemplate;
	public DispenserService(RedisService redisService, MongoService mongoService, RetryTemplate mongoRetryTemplate) {
		this.redisService = redisService;
		this.cosmosService = mongoService;
		this.mongoRetryTemplate = mongoRetryTemplate;
	}

	/*
	 * This method is used to feed the pet. It takes the idempotency key, name of the pet, and the timestamp as input.
	 * @param idempotencyKey: The idempotency key for the request
	 * @param name: The name of the pet
	 * @param timestamp: The timestamp at which the pet is fed
	 * @return: The response for the request
	 */
	public FeedResponse feed(String idempotencyKey, String name, Long timestamp) {
		// Check if the request is already processed.
		// If the request is already processed, return the cached response.
		IdempotencyDetails cachedIdempotencyDetails = redisService.get(idempotencyKey);

		if (cachedIdempotencyDetails != null) {
			return FeedResponse.builder()
					.success(false)
					.message(cachedIdempotencyDetails.getResponse())
					.build();
		}

		// If the request is not processed, process the request by writing to Redis and Cosmos.
		long expiryInMillis = 24 * 60 * 60 * 1000; // 24 hours
		IdempotencyDetails idempotencyDetails = IdempotencyDetails.builder()
			.status("in-progress")
			.response(String.format("Request to feed %s at timestamp %d currently being processed", name, timestamp))
			.expiry(System.currentTimeMillis() + expiryInMillis)
			.build();

		redisService.set(idempotencyKey, idempotencyDetails);

		FeedRecord feedRecord = FeedRecord.builder()
			.name(name)
			.timestamp(timestamp)
			.build();

		try {
			// Try to write the feed record to Cosmos DB.
			mongoRetryTemplate.execute(retryContext -> {
				cosmosService.saveFeedRecord(feedRecord);
				return null;
			});

			// If the write to Cosmos DB is successful, update the response in Redis.
			idempotencyDetails.setResponse(String.format("%s fed successfully at timestamp %d", name, timestamp));
			idempotencyDetails.setStatus("completed");
			redisService.set(idempotencyKey, idempotencyDetails);

			return FeedResponse.builder()
					.success(true)
					.message(idempotencyDetails.getResponse())
					.build();
		} catch (Exception e) {
			// If there is an error while writing to Cosmos DB, update the response in Redis.
			log.error("Failed to feed the pet: {}", e.getMessage());
			idempotencyDetails.setStatus("failed");
			idempotencyDetails.setResponse(String.format("Failed to feed %s at timestamp %d. Please try again later.", name, timestamp));
			return FeedResponse.builder()
					.success(false)
					.message(idempotencyDetails.getResponse())
					.build();
		}
	}
}
