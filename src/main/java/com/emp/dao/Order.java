package com.emp.dao;

import java.time.LocalDateTime;
import java.util.Map;

public record Order(
		int id,
		User user,
		LocalDateTime time,
		Map<Product, Integer> products) {
	
	private static int num = 1;
	
	public static int freshId() {
		return num++;
	}
	
}
