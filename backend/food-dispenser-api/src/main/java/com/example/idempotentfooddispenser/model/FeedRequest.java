package com.example.idempotentfooddispenser.model;

import lombok.Data;

@Data
public class FeedRequest {
	private String petName;
	private Long timestamp;
}
