package com.example.polls.payload;

import org.springframework.web.multipart.MultipartFile;

public class RecipeRequest {
	private Long id;
	private String dishname;
	private String quantity;
	private double price;
	private String dishimage;
	private MultipartFile file; 
	
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
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	

}
