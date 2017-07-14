package com.camrade.model;

import java.awt.Image;
import java.io.File;

public class SaveImage {
	
	private Long userId;
	private String inmageType;
	private File image;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getInmageType() {
		return inmageType;
	}
	public void setInmageType(String inmageType) {
		this.inmageType = inmageType;
	}
	
	
	public File getImage() {
		return image;
	}
	public void setImage(File image) {
		this.image = image;
	}
	public SaveImage(){}
}
