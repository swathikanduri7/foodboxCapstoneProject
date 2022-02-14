package com.example.polls.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FeedbackResponse {
	@JsonProperty("_id")
	private Long id;
	private String whichuser;
	private String email;
	private String name;
	private String msg;

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
