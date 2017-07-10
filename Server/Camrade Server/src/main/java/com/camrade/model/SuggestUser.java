package com.camrade.model;

public class SuggestUser {

	private Long userId;
	private String firstName;
	private String lastName;
	private String alterName;
	private String profilePictue;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAlterName() {
		return alterName;
	}

	public void setAlterName(String alterName) {
		this.alterName = alterName;
	}

	public String getProfilePictue() {
		return profilePictue;
	}

	public void setProfilePictue(String profilePictue) {
		this.profilePictue = profilePictue;
	}

	public SuggestUser() {
	}
}
