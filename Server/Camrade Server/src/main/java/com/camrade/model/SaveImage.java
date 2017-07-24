package com.camrade.model;



public class SaveImage {
	
	private Long userId;
	private String imageType;
	private String byteImage;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getImageType() {
		return imageType;
	}
	public void setImageType(String inmageType) {
		this.imageType = inmageType;
	}
	
	public String getByteImage() {
		return byteImage;
	}
	public void setByteImage(String byteImage) {
		this.byteImage = byteImage;
	}
	public SaveImage(){}
}
