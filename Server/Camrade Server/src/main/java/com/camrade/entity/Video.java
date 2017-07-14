package com.camrade.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "video")
public class Video {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "video_id")
	private Long videoId;

	@Column(name = "owner_id")
	private Long ownerId;

	@Column(name = "video_path")
	private String videoPath;

	@Column(name = "permitted_persons_id")
	private String permittedPersonsId;
	
	@Column(name = "added_date")
	private Date addedDate;
	
	public Long getVideoId() {
		return videoId;
	}

	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public String getVideoPath() {
		return videoPath;
	}

	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
	}

	public String getPermittedPersonsId() {
		return permittedPersonsId;
	}

	public void setPermittedPersonsId(String permittedPersonsId) {
		this.permittedPersonsId = permittedPersonsId;
	}
	
	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	public Video(){}
}
