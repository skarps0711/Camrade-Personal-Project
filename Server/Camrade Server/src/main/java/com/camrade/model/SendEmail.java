package com.camrade.model;

public class SendEmail {

	private String email;
	private String message;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	SendEmail() {
	}

	SendEmail(String email, String message) {
		this.email = email;
		this.message = message;
	}
}
