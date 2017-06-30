package com.camrade.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camrade.entity.User;
import com.camrade.model.Login;
import com.camrade.model.SignupUser;
import com.camrade.repository.UserRepository;

import java.util.*;  
import javax.mail.*;  
import javax.mail.internet.*;  
import javax.activation.*; 


@Service
public class AuthendicateService {
	
	@Autowired
	UserRepository userRepo;
	
	public User validateUser(Login login){
		User user=null;
		try{
			user=userRepo.findByUserNameAndPassword(login.getUserName(), login.getPassword());
		}catch(NullPointerException e){
			user=null;
		}
		return user;
	}
	
	public User addUser(SignupUser newUser){
		User user=null;
		User addUser=new User();
		try{
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
			addUser.setAddress("");
			addUser.setQuotes("");
			addUser.setAbout("");
			addUser.setWork("");
			addUser.setSchoolName("");
			addUser.setCollegeName("");
			user=userRepo.save(addUser);
		}catch(Exception e){
			e.printStackTrace();
			user=null;
		}
		return user;
	}
	
	public User isUserNameExist(String newUserName){
		User user=null;
		try{
			user=userRepo.findByUserName(newUserName);
		}catch(NullPointerException e){
			user=null;
		}
		return user;
	}
	
	public User isEmailExist(String newEmail){
		User user=null;
		try{
			user=userRepo.findByEmail(newEmail);
		}catch(Exception e){
			user=null;
		}
		return user;
	}
	
	public boolean sendEmail(String userNameOrEmail){
		User user=null;
		String userName="";
		String email="";
	      // Recipient's email ID needs to be mentioned.
	      String to = "spaulmailme@gmail.com";

	      // Sender's email ID needs to be mentioned
	      String from = "mailtosureshkdm@gmail.com";

	      // Assuming you are sending email from localhost
	      String host = "localhost";

	      // Get system properties
	      Properties properties = System.getProperties();

	      // Setup mail server
	      properties.setProperty("mail.smtp.host", host);

	      // Get the default Session object.
	      Session session = Session.getDefaultInstance(properties);

	      
		if(userNameOrEmail.contains("@")){
			email=userNameOrEmail;
			try {
		         // Create a default MimeMessage object.
		         MimeMessage message = new MimeMessage(session);

		         // Set From: header field of the header.
		         message.setFrom(new InternetAddress(from));

		         // Set To: header field of the header.
		         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

		         // Set Subject: header field
		         message.setSubject("Mail check!");

		         // Now set the actual message
		         message.setText("Hello Saurabh");

		         // Send message
		         Transport.send(message);
		         System.out.println("Sent message successfully....");
		      }catch (MessagingException mex) {
		         mex.printStackTrace();
		      }
			
		}else{
			userName=userNameOrEmail;
		}
		return true;
	}
		
}
