package com.example.polls.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RecipeResponse {
	@JsonProperty("_id")
	private Long id;
	private String dishname;
	private String quantity;
	private double price;
	private String dishimage;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDishname() {
		return dishname;
	}
	public void setDishname(String dishname) {
		this.dishname = dishname;
	}
	
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDishimage() {
		return dishimage;
	}
	public void setDishimage(String dishimage) {
		this.dishimage = dishimage;
	}
	


}
