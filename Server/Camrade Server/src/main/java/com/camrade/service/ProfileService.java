package com.camrade.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
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
import com.camrade.model.PasswordChange;
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

	public User updateUser(User userDetails) {
		User user = null;
		try {
			user = userRepo.save(userDetails);
		} catch (Exception e) {
			e.printStackTrace();
			user = null;
		}
		return user;
	}

	public Boolean saveImage(SaveImage imageDetails) throws IOException {
		Boolean status = false;
		String receiveImage = "data:image/png;base64," + imageDetails.getByteImage();
		String base64Image = receiveImage.split(",")[1];
		byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);
		BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageBytes));
		System.out.println(image);
		if (imageDetails.getImageType().equalsIgnoreCase("changeProfilePicture")) {
			try {
				ImageIO.write(image, "jpg",
						new File(
								"C:/Users/ssendrayan/Desktop/GitRepo/Camrade-Personal-Project/Camrade/src/assets/users-files/"
										+ imageDetails.getUserId() + "/Profile_picture.jpg"));
				status = true;

			} catch (Exception e) {
				e.printStackTrace();
				status = false;
			}
		} else if (imageDetails.getImageType().equalsIgnoreCase("changeCoverImage")) {
			try {

				ImageIO.write(image, "jpg",
						new File(
								"C:/Users/ssendrayan/Desktop/GitRepo/Camrade-Personal-Project/Camrade/src/assets/users-files/"
										+ imageDetails.getUserId() + "/Cover_image.jpg"));
				status = true;
			} catch (Exception e) {
				status = false;
			}
		} else if (imageDetails.getImageType().equalsIgnoreCase("removeProfilePicture")) {
			try {
				BufferedImage profileImage = null;
				URL profileImgUrl = new URL("http://www.conexaojovem.com/fotosalunos/Oscarlina/SemImagem.jpg");
				profileImage = ImageIO.read(profileImgUrl);
				ImageIO.write(profileImage, "jpg",
						new File(
								"C:/Users/ssendrayan/Desktop/GitRepo/Camrade-Personal-Project/Camrade/src/assets/users-files/"
										+ imageDetails.getUserId() + "/Profile_picture.jpg"));
				status = true;
			} catch (Exception e) {
				status = false;
			}
		} else {
			try {
				BufferedImage coverImage = null;
				URL coverImgUrl = new URL(
						"https://mondomedia.com/application/views/channel/templates/default/_/images/default-background-cover.jpg");
				coverImage = ImageIO.read(coverImgUrl);
				ImageIO.write(coverImage, "jpg",
						new File(
								"C:/Users/ssendrayan/Desktop/GitRepo/Camrade-Personal-Project/Camrade/src/assets/users-files/"
										+ imageDetails.getUserId() + "/Cover_image.jpg"));
				status = true;
			} catch (Exception e) {
				status = false;
			}
		}
		return status;
	}

	public User getUser(Long userId) {
		User user = null;
		try {
			user = userRepo.findByUserId(userId);
		} catch (Exception e) {
			user = null;
		}
		return user;
	}
	
	public User changePassword(PasswordChange passwordChange){
		User user=null;
		try{
			user=userRepo.findByUserId(passwordChange.getUserId());
			if(user.getPassword().equals(passwordChange.getOldPassword())){
				user.setPassword(passwordChange.getNewPassword());
				user=userRepo.save(user);
			}
			else{
				user=null;
			}
		}catch(Exception e){
			user=null;
		}
		return user;
	}
}
