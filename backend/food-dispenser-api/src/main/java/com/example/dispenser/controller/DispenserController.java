package com.example.dispenser.controller;

import com.example.dispenser.model.ApiResponse;
import com.example.dispenser.model.FeedRecord;
import com.example.dispenser.model.FeedRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/")
public class DispenserController {
	@GetMapping("health")
	public String getHealthCheck() {
		log.info("Getting health check request");
		return "Service is up and running!";
	}

	@PostMapping("feed")
	public ResponseEntity<ApiResponse<FeedRecord>> feed(@RequestBody FeedRequest feedRequest) {
		log.info("Feeding the pet with request: " + feedRequest.toString());
		ApiResponse<FeedRecord> response = new ApiResponse<>(
				true,
				String.format("%s fed successfully at %d", feedRequest.getPetName(), feedRequest.getTimestamp()),
				null
		);

		return ResponseEntity.ok(response);
	}
}
