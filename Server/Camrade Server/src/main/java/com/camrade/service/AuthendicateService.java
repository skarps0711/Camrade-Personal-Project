package com.camrade.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camrade.entity.User;
import com.camrade.model.Login;
import com.camrade.model.SignupUser;
import com.camrade.repository.UserRepository;

import java.io.File;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

@Service
public class AuthendicateService {

	@Autowired
	UserRepository userRepo;

	public User validateUser(Login login) {
		User user = null;
		try {
			user = userRepo.findByUserNameAndPassword(login.getUserName(), login.getPassword());
		} catch (NullPointerException e) {
			user = null;
		}
		return user;
	}

	public User addUser(SignupUser newUser) {
		User user = null;
		User addUser = new User();
		try {
			addUser.setUserId(null);
			addUser.setFirstName(newUser.getFirstName());
			addUser.setLastName(newUser.getLastName());
			addUser.setUserName(newUser.getUserName());
			addUser.setAlterName("");
			addUser.setPassword(newUser.getPassword());
			addUser.setEmail(newUser.getEmail());
			addUser.setPhoneNo(newUser.getPhoneNo());
			addUser.setBirthDate(newUser.getBirthDate());
			addUser.setGender(newUser.getGender());
			addUser.setProfilePicture("");
			addUser.setCoverImage("");
			addUser.setAddress("");
			addUser.setQuotes("");
			addUser.setAbout("");
			addUser.setWork("");
			addUser.setSchoolName("");
			addUser.setCollegeName("");
			user = userRepo.save(addUser);
		} catch (Exception e) {
			e.printStackTrace();
			user = null;
		}
		return user;
	}

	public User isUserNameExist(String newUserName) {
		User user = null;
		try {
			user = userRepo.findByUserName(newUserName);
		} catch (NullPointerException e) {
			user = null;
		}
		return user;
	}

	public User isEmailExist(String newEmail) {
		User user = null;
		try {
			user = userRepo.findByEmail(newEmail);
		} catch (Exception e) {
			user = null;
		}
		return user;
	}

	public boolean sendEmail(String userNameOrEmail) {
		User user = null;
		Boolean status = false;
		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		// Get a Properties object
		Properties props = System.getProperties();
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");
		props.put("mail.store.protocol", "pop3");
		props.put("mail.transport.protocol", "smtp");
		final String username = "mailtosureshkdm@gmail.com";//
		final String password = "Surram.Log33";

		if (userNameOrEmail.contains("@")) {
			String email = userNameOrEmail;
			String userName = "";
			String userPassword = "";
			try {
				userName = userRepo.findByEmail(email).getUserName();
				userPassword = userRepo.findByEmail(email).getPassword();
			} catch (Exception e) {
				status = false;
			}
			try {
				Session session = Session.getDefaultInstance(props, new Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

				// -- Create a new message --
				Message msg = new MimeMessage(session);

				// -- Set the FROM and TO fields --
				msg.setFrom(new InternetAddress("mailtosureshkdm@gmail.com"));
				msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
				msg.setSubject("Camrade Account Password");
				msg.setText(
						"Hello user," + "\n\nPlease use the following credentials to login your Camrade account safely."
								+ "\n\nUser name : " + userName + "\n Password  : " + userPassword
								+ "\n\nDo not reply any mail." + "\n\nRegards" + "\nCamrade Admin");
				msg.setSentDate(new Date());
				Transport.send(msg);
				System.out.println("Message sent.");
				status = true;
			} catch (MessagingException e) {
				System.out.println("Erreur d'envoi, cause: " + e);
				status = false;
			}

		} else {
			String userName = userNameOrEmail;
			String userMail = "";
			String userPassword = "";
			try {
				userMail = userRepo.findByUserName(userName).getEmail();
				userPassword = userRepo.findByUserName(userName).getPassword();
			} catch (Exception e) {
				status = false;
			}
			try {
				Session session = Session.getDefaultInstance(props, new Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

				// -- Create a new message --
				Message msg = new MimeMessage(session);

				// -- Set the FROM and TO fields --
				msg.setFrom(new InternetAddress("mailtosureshkdm@gmail.com"));
				msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userMail, false));
				msg.setSubject("Camrade Account Password");
				msg.setText(
						"Hello user," + "\n\nPlease use the following credentials to login your Camrade account safely."
								+ "\n\nUser name : " + userName + "\n Password  : " + userPassword
								+ "\n\nDo not reply any mail." + "\n\nRegards" + "\nCamrade Admin");
				msg.setSentDate(new Date());
				Transport.send(msg);
				System.out.println("Message sent.");
				status = true;
			} catch (MessagingException e) {
				System.out.println("Erreur d'envoi, cause: " + e);
				status = false;
			}

		}
		return status;
	}

	public Boolean createUserDirectory(Long userId) {
		Boolean status = false;
		try {
			File file = new File("C:\\Users\\ssendrayan\\Desktop\\GitRepo\\Camrade-Personal-Project\\Camrade\\src\\assets\\users-files\\" + userId);
			if (!file.exists()) {
				if (file.mkdir()) {
					System.out.println("Directory is created!");
					status = true;
				} else {
					System.out.println("Failed to create directory!");
				}
			}
		} catch (Exception e) {

		}
		return status;
	}

	public Boolean deleteUser(User user) {
		Boolean status = false;
		try {
			userRepo.delete(user.getUserId());
			status = true;
		} catch (Exception e) {

		}
		return status;
	}

	public Boolean saveDefaultProfPicture(User user) {
		Boolean status = false;
		BufferedImage profileImage = null;
		BufferedImage coverImage = null;
		try {

			URL profileImgUrl = new URL("http://www.conexaojovem.com/fotosalunos/Oscarlina/SemImagem.jpg");
			URL coverImgUrl=new URL("https://mondomedia.com/application/views/channel/templates/default/_/images/default-background-cover.jpg");
			profileImage = ImageIO.read(profileImgUrl);
			coverImage = ImageIO.read(coverImgUrl);

			ImageIO.write(profileImage, "jpg", new File("C:/Users/ssendrayan/Desktop/GitRepo/Camrade-Personal-Project/Camrade/src/assets/users-files/" + user.getUserId() + "/Profile_picture.jpg"));
			ImageIO.write(coverImage, "jpg", new File("C:/Users/ssendrayan/Desktop/GitRepo/Camrade-Personal-Project/Camrade/src/assets/users-files/" + user.getUserId() + "/Cover_image.jpg"));
			// ImageIO.write(image, "gif",new File("C:\\out.gif"));
			// ImageIO.write(image, "png",new File("C:\\out.png"));
			user.setProfilePicture(user.getUserId() + "/Profile_picture.jpg");
			user.setCoverImage(user.getUserId()+ "/Cover_image.jpg");
			userRepo.save(user);
			status = true;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return status;
	}

	public Boolean deleteUserDirectory(User user) {
		Boolean status = false;
		File directory = new File("C:\\Users\\ssendrayan\\Desktop\\GitRepo\\Camrade-Personal-Project\\Camrade\\src\\assets\\users-files\\" + user.getUserId());

		// make sure directory exists
		if (!directory.exists()) {
			status=false;
		} else {
			try {
				delete(directory);
				status=true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return status;
	}

	public static void delete(File file) throws IOException {
		if (file.isDirectory()) {
			// directory is empty, then delete it
			if (file.list().length == 0) {
				file.delete();
			} else {
				// list all the directory contents
				String files[] = file.list();
				for (String temp : files) {
					// construct the file structure
					File fileDelete = new File(file, temp);
					// recursive delete
					delete(fileDelete);
				}
				// check the directory again, if empty then delete it
				if (file.list().length == 0) {
					file.delete();
				}
			}
		} else {
			// if file, then delete it
			file.delete();
		}
	}
}
