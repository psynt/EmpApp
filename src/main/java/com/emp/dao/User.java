package com.emp.dao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class User implements Comparable<User>{
	
	private final String username;
	private String password;
	private UserType type;
	private final Map<Product, Integer> cart;
	private final List<Order> pastOrders;
	
	public User(String uname, String pwd, UserType type) {
		this.username = uname;
		this.password = pwd;
		this.type = type;
		this.cart = new HashMap<>();
		this.pastOrders = new ArrayList<>();
	}
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	public Map<Product, Integer> getCart() {
		return cart;
	}

	public List<Order> getPastOrders() {
		return pastOrders;
	}


	public static enum UserType{Admin, Standard}
	
	public void addToCart(final Product item, final int qty) {
		this.cart.put(item, cart.getOrDefault(item, 0) + qty);
		if(cart.getOrDefault(item, 0) < 1)
			cart.remove(item);
	}

	public void addToCart(final Product item) {
		this.addToCart(item, 1);
	}
	
	public Order confirmOrder() {
		final Order order = new Order(Order.freshId(), this, LocalDateTime.now(), Map.copyOf(cart));
		pastOrders.add(order);
		cart.clear();
		return order;
	}

	@Override
	public int compareTo(User that) {
		final List<Order> thisOrders = this.pastOrders;
		final List<Order> thatOrders = that.pastOrders;
		if(thatOrders == null || thatOrders.isEmpty()) 
			return -1;
		if(thisOrders == null || thisOrders.isEmpty())
			return 1;
		return thisOrders.get(thisOrders.size()-1).time().compareTo(thatOrders.get(thatOrders.size()-1).time());
	}
	
	public String getSnippet() {
		final List<Order> orders = this.getPastOrders();
		if(orders.isEmpty()) return "";
		final LocalDateTime date = orders.get(orders.size() - 1).time();
		final String snippet = date.format(DateTimeFormatter.BASIC_ISO_DATE);
		return snippet;
	}
	
	public String getRedirectUrl() {
		return type == UserType.Admin ? "userManagement.jsp" : "StoreFront.jsp";
	}
}