package com.example.dispenser.controller;

import com.example.dispenser.model.ApiResponse;
import com.example.dispenser.model.FeedRecord;
import com.example.dispenser.model.FeedRequest;
import com.example.dispenser.model.FeedResponse;
import com.example.dispenser.service.DispenserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/")
public class DispenserController {
	DispenserService dispenserService;

	public DispenserController(DispenserService dispenserService) {
		this.dispenserService = dispenserService;
	}

	@GetMapping("health")
	public String getHealthCheck() {
		log.info("Getting health check request");
		return "Food Dispenser API is up and running!";
	}

	@PostMapping("feed")
	public ResponseEntity<ApiResponse<FeedRecord>> feed(
			@RequestHeader("Idempotency-Key") String idempotencyKey,
			@RequestBody FeedRequest feedRequest) {
		log.info("Feeding the pet with idempotencyKey={} and request={}", idempotencyKey, feedRequest.toString());

		FeedResponse feedResponse = dispenserService.feed(idempotencyKey, feedRequest.getPetName(), feedRequest.getTimestamp());

		ApiResponse<FeedRecord> apiResponse = new ApiResponse<>(
				feedResponse.isSuccess(),
				feedResponse.getMessage(),
				null
		);

		if (feedResponse.isSuccess()) {
			return ResponseEntity.ok(apiResponse);
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
		}
	}
}
