package com.camrade.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "friend_request")
public class FriendRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "request_id")
	private Long requestId;

	@Column(name = "request_from")
	private Long requestFrom;

	@Column(name = "request_to")
	private Long requestTo;

	@Column(name = "status")
	private String status;

	public Long getRequestId() {
		return requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}

	public Long getRequestFrom() {
		return requestFrom;
	}

	public void setRequestFrom(Long requestFrom) {
		this.requestFrom = requestFrom;
	}

	public Long getRequestTo() {
		return requestTo;
	}

	public void setRequestTo(Long requestTo) {
		this.requestTo = requestTo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public FriendRequest() {
	}
}
