package com.example.polls.payload;

public class JwtAuthenticationResponse {
    private String token;
    private boolean admin = false;

    public JwtAuthenticationResponse(String token,boolean admin) {
        this.token = token;
        this.admin = admin;
    }
    public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	
}
