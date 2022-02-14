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
//import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.example.polls.model.audit.DateAudit;



@Entity
@Table(name = "order_details")
public class Order extends DateAudit {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@NotBlank
    @Size(max = 40)
    private String whichuser;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "order_recipes",
            joinColumns = @JoinColumn(name = "order_details_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id"))
	private Set<Recipe> recipe = new HashSet<>();
	
	@NotBlank
    @Size(max = 40)
    private double total;
	
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Recipe> getRecipe() {
		return recipe;
	}

	public void setRecipe(Set<Recipe> recipe) {
		this.recipe = recipe;
	}
	
	
}
