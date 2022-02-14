package com.example.polls.payload;

import java.util.HashSet;
import java.util.Set;

import com.example.polls.model.Recipe;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CartRequest {
	@JsonProperty("_id")
	private Long id;
	private String dishname;
	private String quantity;
	private double price;
	private String dishimage;
	private int qty;
	
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
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	
	
	
	
	/*//@NotBlank
    //@Size(max = 140)
	private Long id;
	private String whichuser;
	private Set<Recipe> recipe = new HashSet<>();
	private double total;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getWhichuser() {
		return whichuser;
	}
	public void setWhichuser(String whichuser) {
		this.whichuser = whichuser;
	}
	public Set<Recipe> getRecipe() {
		return recipe;
	}
	public void setRecipe(Set<Recipe> recipe) {
		this.recipe = recipe;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}*/
	
}



