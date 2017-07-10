package com.camrade.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camrade.entity.User;
import com.camrade.model.SendEmail;
import com.camrade.model.SuggestUser;
import com.camrade.repository.UserRepository;

@Service
public class FriendsService {

	@Autowired
	UserRepository userRepo;

	public Boolean sendEmail(Long id, SendEmail sendEmail) {
		Long userId = id;
		String userEmail = "";
		Boolean status = false;
		try {
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
			Session session = Session.getDefaultInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

			// -- Create a new message --
			Message msg = new MimeMessage(session);

			// -- Set the FROM and TO fields --
			userEmail = userRepo.findByUserId(userId).getEmail();
			msg.setFrom(new InternetAddress(userEmail));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sendEmail.getEmail(), false));
			msg.setSubject("Message from Comrade");
			msg.setText(sendEmail.getMessage());
			msg.setSentDate(new Date());
			Transport.send(msg);
			System.out.println("Message sent.");
			status = true;
		} catch (Exception e) {
			status = false;
		}
		return status;
	}

	public List<SuggestUser> getRelatedUsers(String userField) {
		User user;
		List<User> users = new ArrayList<User>();
		List<SuggestUser> suggestUsers = new ArrayList<SuggestUser>();
		SuggestUser suggestUser = new SuggestUser();
		Pattern pattern = Pattern.compile("[0-9]\\d{9}");
		Matcher matcher = pattern.matcher(userField);

		if (userField.contains("@")) {
			try {
				user = userRepo.findByEmail(userField);
				suggestUser.setUserId(user.getUserId());
				suggestUser.setFirstName(user.getFirstName());
				suggestUser.setLastName(user.getLastName());
				suggestUser.setAlterName(user.getAlterName());
				suggestUser.setProfilePictue(user.getProfilePicture());
				suggestUsers.add(suggestUser);
			} catch (Exception e) {
				user = null;
			}
		} else if (matcher.matches()) {
			try {
				users = userRepo.findByPhoneNo(userField);
				for (int size = 0; size < users.size(); size++) {
					suggestUser = new SuggestUser();
					suggestUser.setUserId(users.get(size).getUserId());
					suggestUser.setFirstName(users.get(size).getFirstName());
					suggestUser.setLastName(users.get(size).getLastName());
					suggestUser.setAlterName(users.get(size).getAlterName());
					suggestUser.setProfilePictue(users.get(size).getProfilePicture());
					suggestUsers.add(suggestUser);
				}

			} catch (Exception e) {
				users = null;
			}
		} else {
			try {
				if (userField.contains(" ")) {
					String firstName = userField.substring(0, userField.indexOf(" "));
					String lastName = userField.substring(userField.indexOf(" ") + 1);
					users = userRepo.findByFirstNameAndLastName(firstName, lastName);
					for (int size = 0; size < users.size(); size++) {
						Boolean status = true;
						for (int a = 0; a < suggestUsers.size(); a++) {
							if (users.get(size).getUserId() == suggestUsers.get(a).getUserId()) {
								status = false;
							}
						}
						if (status == true) {
							suggestUser = new SuggestUser();
							suggestUser.setUserId(users.get(size).getUserId());
							suggestUser.setFirstName(users.get(size).getFirstName());
							suggestUser.setLastName(users.get(size).getLastName());
							suggestUser.setAlterName(users.get(size).getAlterName());
							suggestUser.setProfilePictue(users.get(size).getProfilePicture());
							suggestUsers.add(suggestUser);
						}
					}
					users = userRepo.findByFirstNameLike(firstName);
					for (int size = 0; size < users.size(); size++) {
						Boolean status = true;
						for (int a = 0; a < suggestUsers.size(); a++) {
							if (users.get(size).getUserId() == suggestUsers.get(a).getUserId()) {
								status = false;
							}
						}
						if (status == true) {
							suggestUser = new SuggestUser();
							suggestUser.setUserId(users.get(size).getUserId());
							suggestUser.setFirstName(users.get(size).getFirstName());
							suggestUser.setLastName(users.get(size).getLastName());
							suggestUser.setAlterName(users.get(size).getAlterName());
							suggestUser.setProfilePictue(users.get(size).getProfilePicture());
							suggestUsers.add(suggestUser);
						}
					}
					users = userRepo.findByLastNameLike(lastName);
					for (int size = 0; size < users.size(); size++) {
						Boolean status = true;
						for (int a = 0; a < suggestUsers.size(); a++) {
							if (users.get(size).getUserId() == suggestUsers.get(a).getUserId()) {
								status = false;
							}
						}
						if (status == true) {
							suggestUser = new SuggestUser();
							suggestUser.setUserId(users.get(size).getUserId());
							suggestUser.setFirstName(users.get(size).getFirstName());
							suggestUser.setLastName(users.get(size).getLastName());
							suggestUser.setAlterName(users.get(size).getAlterName());
							suggestUser.setProfilePictue(users.get(size).getProfilePicture());
							suggestUsers.add(suggestUser);
						}
					}
				} else {
					users = userRepo.findByFirstNameLike(userField);
					for (int size = 0; size < users.size(); size++) {
						Boolean status = true;
						for (int a = 0; a < suggestUsers.size(); a++) {
							if (users.get(size).getUserId() == suggestUsers.get(a).getUserId()) {
								status = false;
							}
						}
						if (status == true) {
							suggestUser = new SuggestUser();
							suggestUser.setUserId(users.get(size).getUserId());
							suggestUser.setFirstName(users.get(size).getFirstName());
							suggestUser.setLastName(users.get(size).getLastName());
							suggestUser.setAlterName(users.get(size).getAlterName());
							suggestUser.setProfilePictue(users.get(size).getProfilePicture());
							suggestUsers.add(suggestUser);
						}
					}
					users = userRepo.findByLastNameLike(userField);
					for (int size = 0; size < users.size(); size++) {
						Boolean status = true;
						for (int a = 0; a < suggestUsers.size(); a++) {
							if (users.get(size).getUserId() == suggestUsers.get(a).getUserId()) {
								status = false;
							}
						}
						if (status == true) {
							suggestUser = new SuggestUser();
							suggestUser.setUserId(users.get(size).getUserId());
							suggestUser.setFirstName(users.get(size).getFirstName());
							suggestUser.setLastName(users.get(size).getLastName());
							suggestUser.setAlterName(users.get(size).getAlterName());
							suggestUser.setProfilePictue(users.get(size).getProfilePicture());
							suggestUsers.add(suggestUser);
						}

					}
				}

			} catch (Exception e) {

			}
		}
		Long[] userIds = new Long[suggestUsers.size()];
		Long[] repeatedUserIndexes = new Long[suggestUsers.size()];
		for (int a = 0; a < suggestUsers.size(); a++) {
			System.out.println(suggestUsers.get(a).getUserId() + " qq");
			userIds[a] = suggestUsers.get(a).getUserId();
		}

		return suggestUsers;
	}
}
