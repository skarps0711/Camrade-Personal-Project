package com.camrade.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "audio")
public class Audio {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "audio_id")
	private Long audioId;

	@Column(name = "owner_id")
	private Long ownerId;

	@Column(name = "audio_path")
	private String audioPath;

	@Column(name = "permitted_persons_id")
	private String permittedPersonsId;
	
	@Column(name = "added_date")
	private Date addedDate;

	public Long getAudioId() {
		return audioId;
	}

	public void setAudioId(Long audioId) {
		this.audioId = audioId;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public String getAudioPath() {
		return audioPath;
	}

	public void setAudioPath(String audioPath) {
		this.audioPath = audioPath;
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

	public Audio() {
	}

}
