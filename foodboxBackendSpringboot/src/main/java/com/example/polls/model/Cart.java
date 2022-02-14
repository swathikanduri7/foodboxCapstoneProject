package com.example.polls.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.example.polls.model.audit.DateAudit;


@Entity
@Table(name = "cart")
public class Cart extends DateAudit {
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotBlank
    @Size(max = 40)
    private String whichuser;

   /* @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "cart_recipes",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id"))
	private Set<Recipe> recipe = new HashSet<>();
    */
	//@NotBlank
    //@Size(max = 40)
	private String recipeItems;
	
    private double total = 0;
	
	

	public Cart() {
		super();
	}
	
	

	

	public String getRecipeItems() {
		return recipeItems;
	}





	public void setRecipeItems(String recipeItems) {
		this.recipeItems = recipeItems;
	}





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

	

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	

}
