package com.camrade.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "image")
public class Image {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "image_id")
	private Long imageId;

	@Column(name = "owner_id")
	private Long ownerId;

	@Column(name = "image_path")
	private String imagePath;

	@Column(name = "permitted_persons_id")
	private String permittedPersonsId;
	
	@Column(name = "added_date")
	private Date addedDate;

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
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

	public Image(){}
}
