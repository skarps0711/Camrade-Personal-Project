package com.camrade.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camrade.entity.Audio;
import com.camrade.entity.Document;
import com.camrade.entity.Image;
import com.camrade.entity.User;
import com.camrade.entity.Video;
import com.camrade.model.NoOfMedias;
import com.camrade.model.SaveImage;
import com.camrade.repository.AudioRepository;
import com.camrade.repository.DocumentRepository;
import com.camrade.repository.ImageRepository;
import com.camrade.repository.UserRepository;
import com.camrade.repository.VideoRepository;

@Service
public class ProfileService {

	@Autowired
	UserRepository userRepo;
	@Autowired
	AudioRepository audioRepo;
	@Autowired
	DocumentRepository documentRepo;
	@Autowired
	ImageRepository imageRepo;
	@Autowired
	VideoRepository videoRepo;

	public NoOfMedias getNoOfMedias(Long userId) {

		NoOfMedias noOfMedias = new NoOfMedias();

		try {
			try {
				List<Audio> audios = audioRepo.findAllByOwnerId(userId);
				noOfMedias.setNoOfAudios(audios.size());
			} catch (NullPointerException e) {
				noOfMedias.setNoOfAudios(0);
			}
			try {
				List<Document> documents = documentRepo.findAllByOwnerId(userId);
				noOfMedias.setNoOfDocuments(documents.size());
			} catch (NullPointerException e) {
				noOfMedias.setNoOfDocuments(0);
			}
			try {
				List<Image> images = imageRepo.findAllByOwnerId(userId);
				noOfMedias.setNoOfImages(images.size());
			} catch (NullPointerException e) {
				noOfMedias.setNoOfImages(0);
			}
			try {
				List<Video> videos = videoRepo.findAllByOwnerId(userId);
				noOfMedias.setNoOfVideos(videos.size());
			} catch (NullPointerException e) {
				noOfMedias.setNoOfVideos(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return noOfMedias;
	}
	
	public User updateUser(User userDetails){
		User user=null;
		try{
			user=userRepo.save(userDetails);
		}catch(Exception e){
			e.printStackTrace();
			user=null;
		}
		return user;
	}
	
	public Boolean saveImage(SaveImage imageDetails){
		Boolean status=false;
		BufferedImage image=null;
		if(imageDetails.getInmageType().equalsIgnoreCase("profilePicture")){
			try{
				image=ImageIO.read(imageDetails.getImage());
				ImageIO.write(image, "jpg", new File("C:/Users/ssendrayan/Desktop/GitRepo/Camrade-Personal-Project/Camrade/src/assets/users-files/" + imageDetails.getUserId() + "/Profile_picture.jpg"));
				status=true;
			}catch(Exception e){
				status=false;
			}
		}
		else{
			try{
				image=ImageIO.read(imageDetails.getImage());
				ImageIO.write(image, "jpg", new File("C:/Users/ssendrayan/Desktop/GitRepo/Camrade-Personal-Project/Camrade/src/assets/users-files/" + imageDetails.getUserId() + "/Cover_image.jpg"));
				status=true;
			}catch(Exception e){
				status=false;
			}
		}
		return status;
	}
	
	public User getUser(Long userId){
		User user=null;
		try{
			user=userRepo.findByUserId(userId);
		}catch(Exception e){
			user=null;
		}
		return user;
	}
}
