package com.example.idempotentfooddispenser.model;

public record ApiResponse<T> (
		boolean success,
		String message,
		T data
) {
}
