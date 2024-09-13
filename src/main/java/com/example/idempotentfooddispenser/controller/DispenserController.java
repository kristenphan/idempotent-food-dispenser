package com.example.idempotentfooddispenser.controller;

import com.example.idempotentfooddispenser.model.FeedRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/")
public class DispenserController {
	@GetMapping("health")
	public String getHealthCheck() {
		log.info("Getting health check request");
		return "Service is up and running!";
	}
}
