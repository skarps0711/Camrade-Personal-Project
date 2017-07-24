package com.camrade.model;

import java.util.Date;

import javax.persistence.Column;

public class NotificationDetails {

	private Long notificationId;
	private Long notifyFrom;
	private Long notifyTo;
	private String fromProfilePicture;
	private String notifyMessage;
	private Date addedDate;
	private String status;

	public Long getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(Long notificationId) {
		this.notificationId = notificationId;
	}

	public Long getNotifyFrom() {
		return notifyFrom;
	}

	public void setNotifyFrom(Long notifyFrom) {
		this.notifyFrom = notifyFrom;
	}

	public Long getNotifyTo() {
		return notifyTo;
	}

	public void setNotifyTo(Long notifyTo) {
		this.notifyTo = notifyTo;
	}

	public String getFromProfilePicture() {
		return fromProfilePicture;
	}

	public void setFromProfilePicture(String fromProfilePicture) {
		this.fromProfilePicture = fromProfilePicture;
	}

	public String getNotifyMessage() {
		return notifyMessage;
	}

	public void setNotifyMessage(String notifyMessage) {
		this.notifyMessage = notifyMessage;
	}

	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public NotificationDetails() {

	}
}
