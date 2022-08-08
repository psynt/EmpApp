package com.emp.dao;

import java.util.List;

public record Product(
		String name,
		int price,
		String category,
		int rating,
		List<String> reviews) {
	
	public static final List<CharSequence> STARS = List.of("", "*", "**", "***", "****", "*****");
	
	public CharSequence stars() {
		return STARS.get(rating);
	}

}
