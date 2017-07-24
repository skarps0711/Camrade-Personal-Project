package com.camrade.model;

public class CheckFriend {

	private Long UserId;
	private Long FriendOf;

	public Long getUserId() {
		return UserId;
	}

	public void setUserId(Long userId) {
		UserId = userId;
	}

	public Long getFriendOf() {
		return FriendOf;
	}

	public void setFriendOf(Long friendOf) {
		FriendOf = friendOf;
	}

	CheckFriend() {
	}
}
