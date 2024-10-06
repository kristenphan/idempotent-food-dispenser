package com.example.dispenser.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
public class IdempotencyDetails implements Serializable {
	private String status;
	private String response;
	private Long expiry;
}
