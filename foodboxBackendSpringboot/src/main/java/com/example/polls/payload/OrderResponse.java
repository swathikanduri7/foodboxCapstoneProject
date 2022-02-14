package com.example.polls.payload;

import java.util.HashSet;
import java.util.Set;

import com.example.polls.model.Recipe;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderResponse {
	@JsonProperty("_id")
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
	}
	
}
