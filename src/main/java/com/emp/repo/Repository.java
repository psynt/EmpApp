package com.emp.repo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.emp.dao.Order;
import com.emp.dao.Product;
import com.emp.dao.User;
import com.emp.dao.User.UserType;

public class Repository {
	
	public static final Map<String, User> USERS = new HashMap<>();
	public static final Map<String, Product> PRODUCTS = new HashMap<>();
	public static final Map<Integer, Order> ORDERS = new HashMap<>();

	static {
		final Product p1 = new Product("Laptop", 2000, "Electronics", 4, null);
		PRODUCTS.put(p1.name(), p1);
		final Product p2 = new Product("Charger", 20, "Electronics", 3, null);
		PRODUCTS.put(p2.name(), p2);
		final Product p3 = new Product("Cable", 2, "Electronics", 5, null);
		PRODUCTS.put(p3.name(), p3);

		final User bobJohn = new User("John", "123", UserType.Admin);
		USERS.put(bobJohn.getUsername(), bobJohn);
		USERS.put("Bob", new User("Bob", "123", UserType.Standard));
		
		final Order o1 = new Order(Order.freshId(), bobJohn, LocalDateTime.now(), Map.of(p2, 2, p3, 1));
		bobJohn.getPastOrders().add(o1);
	}
	
	
	public static List<User> getUsersByOrderDateDesc(){
		final List<User> users = new ArrayList<>();
		USERS.values().forEach(it -> users.add(it));;
		Collections.sort(users);
		return users;
	}

}
