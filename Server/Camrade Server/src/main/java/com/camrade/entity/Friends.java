package com.camrade.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "friends")
public class Friends {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "friend_id")
	private Long friendId;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "friend_of")
	private Long friendOf;

	public Long getFriendId() {
		return friendId;
	}

	public void setFriendId(Long friendId) {
		this.friendId = friendId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getFriendOf() {
		return friendOf;
	}

	public void setFriendOf(Long friendOf) {
		this.friendOf = friendOf;
	}

	public Friends() {
	}
}
