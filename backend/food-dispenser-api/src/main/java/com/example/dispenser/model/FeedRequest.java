package com.example.dispenser.model;

import lombok.Data;

@Data
public class FeedRequest {
	private String petName;
	private Long timestamp;
}
