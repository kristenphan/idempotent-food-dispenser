package com.example.dispenser.model;

public record ApiResponse<T> (
		boolean success,
		String message,
		T data
) {
}
