package com.camrade.model;

public class SuggestUser {

	private Long userId;
	private String firstName;
	private String lastName;
	private String alterName;
	private String profilePicture;
	private Boolean isFriend;
	private Boolean isRequested;
	private Boolean isRequestedMe;

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

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public Boolean getIsFriend() {
		return isFriend;
	}

	public void setIsFriend(Boolean isFriend) {
		this.isFriend = isFriend;
	}

	public Boolean getIsRequested() {
		return isRequested;
	}

	public void setIsRequested(Boolean isRequested) {
		this.isRequested = isRequested;
	}

	public Boolean getIsRequestedMe() {
		return isRequestedMe;
	}

	public void setIsRequestedMe(Boolean isRequestedMe) {
		this.isRequestedMe = isRequestedMe;
	}

	public SuggestUser() {
	}
}
