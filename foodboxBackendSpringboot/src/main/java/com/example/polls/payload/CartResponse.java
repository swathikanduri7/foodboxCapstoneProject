package com.example.polls.payload;

import java.util.HashSet;
import java.util.Set;

import com.example.polls.model.Recipe;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CartResponse {

	@JsonProperty("_id")
	 private Long id;
	 private String whichuser;
	 private Set<CartRequest> recipe = new HashSet<>();
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
	public Set<CartRequest> getRecipe() {
		return recipe;
	}
	public void setRecipe(Set<CartRequest> recipe) {
		this.recipe = recipe;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
}


