package com.camrade.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "notification")
public class Notification {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "notification_id")
	private Long notificationId;

	@Column(name = "notify_from")
	private Long notifyFrom;

	@Column(name = "notify_to")
	private Long notifyTo;

	@Column(name = "notify_message")
	private String notifyMessage;

	@Column(name = "added_date")
	private Date addedDate;

	@Column(name = "status")
	private String status;

	private User user;

	/*@ManyToOne
    @JoinColumn(name = "notify_from")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}*/

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

	public Notification() {
	}
}
