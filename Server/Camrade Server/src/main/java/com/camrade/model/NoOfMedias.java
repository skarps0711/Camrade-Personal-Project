package com.camrade.model;

public class NoOfMedias {

	private Integer noOfAudios;
	private Integer noOfDocuments;
	private Integer noOfImages;
	private Integer noOfVideos;

	
	public Integer getNoOfAudios() {
		return noOfAudios;
	}

	public void setNoOfAudios(Integer noOfAudios) {
		this.noOfAudios = noOfAudios;
	}

	public Integer getNoOfDocuments() {
		return noOfDocuments;
	}

	public void setNoOfDocuments(Integer noOfDocuments) {
		this.noOfDocuments = noOfDocuments;
	}

	public Integer getNoOfImages() {
		return noOfImages;
	}

	public void setNoOfImages(Integer noOfImages) {
		this.noOfImages = noOfImages;
	}

	public Integer getNoOfVideos() {
		return noOfVideos;
	}

	public void setNoOfVideos(Integer noOfVideos) {
		this.noOfVideos = noOfVideos;
	}

	public NoOfMedias() {
	}

	public NoOfMedias(Integer audios, Integer documents, Integer images, Integer videos) {
		this.noOfAudios = audios;
		this.noOfDocuments = documents;
		this.noOfImages = images;
		this.noOfVideos = videos;
	}
}
