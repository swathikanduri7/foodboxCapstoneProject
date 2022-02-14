package com.example.polls.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
//import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.example.polls.model.audit.DateAudit;



@Entity
@Table(name = "recipe")
public class Recipe extends DateAudit {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotBlank
    @Size(max = 40)
    private String dishname;
    
    
    @NotBlank
    @Size(max = 40)
    private String quantity;
    
    
    private double price;
    
    @NotBlank
    @Size(max = 40)
    private String dishimage;
    
    

    public Recipe(Long id, @NotBlank @Size(max = 40) String dishname,
			@NotBlank @Size(max = 40) String quantity,
			@NotBlank @Size(max = 40) double price) {
		super();
		this.id = id;
		this.dishname = dishname;
		this.quantity = quantity;
		this.price = price;
	}

	public Recipe() {
		super();
	}

	public Recipe(@NotBlank @Size(max = 40) String dishname,
			@NotBlank @Size(max = 40) String quantity,
			@NotBlank @Size(max = 40) double price,
			@NotBlank @Size(max = 40) String dishimage) {
		super();
		this.dishname = dishname;
		this.quantity = quantity;
		this.price = price;
		this.dishimage = dishimage;
	}

	public Recipe(Long id, @NotBlank @Size(max = 40) String dishname,
			@NotBlank @Size(max = 40) String quantity,
			@NotBlank @Size(max = 40) double price,
			@NotBlank @Size(max = 40) String dishimage) {
		super();
		this.id = id;
		this.dishname = dishname;
		this.quantity = quantity;
		this.price = price;
		this.dishimage = dishimage;
	}

	public String getDishname() {
		return dishname;
	}

	public void setDishname(String dishname) {
		this.dishname = dishname;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
