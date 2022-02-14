package com.example.polls.model;
//import java.util.HashSet;
//import java.util.Set;

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
@Table(name = "feedback")
public class Feedback extends DateAudit {
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
	
	@NotBlank
    @Size(max = 40)
    private String email;

	@NotBlank
    @Size(max = 40)
    private String name;
	
	@NotBlank
    @Size(max = 40)
    private String msg;
	
	
	public Feedback() {
		super();
	}

	public Feedback(
			@NotBlank @Size(max = 40) String email,
			@NotBlank @Size(max = 40) String name,
			@NotBlank @Size(max = 40) String msg,
			@NotBlank @Size(max = 40) String whichuser) {
		super();
		this.whichuser = whichuser;
		this.email = email;
		this.name = name;
		this.msg = msg;
	}
	
	

	public Feedback(Long id,
			@NotBlank @Size(max = 40) String email,
			@NotBlank @Size(max = 40) String name,
			@NotBlank @Size(max = 40) String msg, @NotBlank @Size(max = 40) String whichuser) {
		super();
		this.id = id;
		this.whichuser = whichuser;
		this.email = email;
		this.name = name;
		this.msg = msg;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	
}
