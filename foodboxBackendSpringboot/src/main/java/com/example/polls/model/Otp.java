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
@Table(name = "otp")

public class Otp extends DateAudit {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


	public double getOtp() {
		return otp;
	}

	public void setOtp(double otp) {
		this.otp = otp;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@NotBlank
    @Size(max = 40)
    private double otp;
	
	@NotBlank
    @Size(max = 40)
    private String email;
	
	
}
