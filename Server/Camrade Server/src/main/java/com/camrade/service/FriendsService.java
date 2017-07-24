package com.camrade.service;

import java.io.Console;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

import com.camrade.entity.FriendRequest;
import com.camrade.entity.Friends;
import com.camrade.entity.Notification;
import com.camrade.entity.User;
import com.camrade.model.CheckFriend;
import com.camrade.model.SendEmail;
import com.camrade.model.SuggestUser;
import com.camrade.model.SuggestUserInput;
import com.camrade.repository.FriendRequestRepository;
import com.camrade.repository.FriendsRepository;
import com.camrade.repository.NotificationRepository;
import com.camrade.repository.UserRepository;

@Service
public class FriendsService {

	@Autowired
	UserRepository userRepo;
	@Autowired
	FriendsRepository friendsRepo;
	@Autowired
	FriendRequestRepository friendRequestRepo;
	@Autowired
	NotificationRepository notifyRepo;

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

	public List<SuggestUser> getRelatedUsers(SuggestUserInput suggestInput) {
		User user;
		String userField = suggestInput.getSearchDetails();
		List<User> users = new ArrayList<User>();
		List<SuggestUser> suggestUsers = new ArrayList<SuggestUser>();
		SuggestUser suggestUser = new SuggestUser();
		Pattern pattern = Pattern.compile("[0-9]\\d{9}");
		Matcher matcher = pattern.matcher(userField);
		if (userField.contains("@")) {
			try {
				user = userRepo.findByEmail(userField);
				if (user.getUserId() == suggestInput.getUserId()) {
					users = null;
				} else {
					suggestUser.setUserId(user.getUserId());
					suggestUser.setFirstName(user.getFirstName());
					suggestUser.setLastName(user.getLastName());
					suggestUser.setAlterName(user.getAlterName());
					suggestUser.setProfilePicture(user.getProfilePicture());
					suggestUsers.add(suggestUser);
				}
			} catch (Exception e) {
				user = null;
			}
		} else if (matcher.matches()) {
			try {
				users = userRepo.findByPhoneNo(userField);
				for (int size = 0; size < users.size(); size++) {
					if (users.get(size).getUserId() == suggestInput.getUserId()) {
						users = null;
					} else {
						suggestUser = new SuggestUser();
						suggestUser.setUserId(users.get(size).getUserId());
						suggestUser.setFirstName(users.get(size).getFirstName());
						suggestUser.setLastName(users.get(size).getLastName());
						suggestUser.setAlterName(users.get(size).getAlterName());
						suggestUser.setProfilePicture(users.get(size).getProfilePicture());
						suggestUsers.add(suggestUser);
					}

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
						if (users.get(size).getUserId() == suggestInput.getUserId()) {
							status = false;
						}
						if (status == true) {
							suggestUser = new SuggestUser();
							suggestUser.setUserId(users.get(size).getUserId());
							suggestUser.setFirstName(users.get(size).getFirstName());
							suggestUser.setLastName(users.get(size).getLastName());
							suggestUser.setAlterName(users.get(size).getAlterName());
							suggestUser.setProfilePicture(users.get(size).getProfilePicture());
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
						if (users.get(size).getUserId() == suggestInput.getUserId()) {
							status = false;
						}
						if (status == true) {
							suggestUser = new SuggestUser();
							suggestUser.setUserId(users.get(size).getUserId());
							suggestUser.setFirstName(users.get(size).getFirstName());
							suggestUser.setLastName(users.get(size).getLastName());
							suggestUser.setAlterName(users.get(size).getAlterName());
							suggestUser.setProfilePicture(users.get(size).getProfilePicture());
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
						if (users.get(size).getUserId() == suggestInput.getUserId()) {
							status = false;
						}
						if (status == true) {
							suggestUser = new SuggestUser();
							suggestUser.setUserId(users.get(size).getUserId());
							suggestUser.setFirstName(users.get(size).getFirstName());
							suggestUser.setLastName(users.get(size).getLastName());
							suggestUser.setAlterName(users.get(size).getAlterName());
							suggestUser.setProfilePicture(users.get(size).getProfilePicture());
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
						if (users.get(size).getUserId() == suggestInput.getUserId()) {
							status = false;
						}
						if (status == true) {
							suggestUser = new SuggestUser();
							suggestUser.setUserId(users.get(size).getUserId());
							suggestUser.setFirstName(users.get(size).getFirstName());
							suggestUser.setLastName(users.get(size).getLastName());
							suggestUser.setAlterName(users.get(size).getAlterName());
							suggestUser.setProfilePicture(users.get(size).getProfilePicture());
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
						if (users.get(size).getUserId() == suggestInput.getUserId()) {
							status = false;
						}
						if (status == true) {
							suggestUser = new SuggestUser();
							suggestUser.setUserId(users.get(size).getUserId());
							suggestUser.setFirstName(users.get(size).getFirstName());
							suggestUser.setLastName(users.get(size).getLastName());
							suggestUser.setAlterName(users.get(size).getAlterName());
							suggestUser.setProfilePicture(users.get(size).getProfilePicture());
							suggestUsers.add(suggestUser);
						}

					}
				}

			} catch (Exception e) {

			}
		}
		return suggestUsers;
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

	public List<SuggestUser> checkFriend(Long userId, List<SuggestUser> suggestUser) {
		for (int a = 0; a < suggestUser.size(); a++) {
			Boolean status = false;
			try {
				Friends friends = friendsRepo.findByUserIdAndFriendOf(userId, suggestUser.get(a).getUserId());
				if (friends != null) {
					status = true;
				} else {
					friends = friendsRepo.findByFriendOfAndUserId(userId, suggestUser.get(a).getUserId());
					if (friends != null) {
						status = true;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (status == true) {
				suggestUser.get(a).setIsFriend(true);
			} else {
				FriendRequest request = friendRequestRepo.findByRequestFromAndRequestTo(userId,
						suggestUser.get(a).getUserId());
				if (request != null) {
					suggestUser.get(a).setIsRequested(true);
				} else {
					suggestUser.get(a).setIsRequested(false);
				}
				suggestUser.get(a).setIsFriend(false);
				suggestUser.get(a).setIsRequestedMe(false);
			}
		}

		return suggestUser;
	}

	public SuggestUser getUserDetails(CheckFriend checkFriend) {
		SuggestUser suggestUser = new SuggestUser();
		try {
			User user = userRepo.findByUserId(checkFriend.getFriendOf());
			suggestUser.setUserId(user.getUserId());
			suggestUser.setFirstName(user.getFirstName());
			suggestUser.setLastName(user.getLastName());
			suggestUser.setAlterName(user.getAlterName());
			suggestUser.setProfilePicture(user.getProfilePicture());

			Boolean status = false;
			try {
				Friends friends = friendsRepo.findByUserIdAndFriendOf(checkFriend.getUserId(),
						checkFriend.getFriendOf());
				if (friends != null) {
					status = true;
				} else {
					friends = friendsRepo.findByFriendOfAndUserId( checkFriend.getUserId(),checkFriend.getFriendOf());
					if (friends != null) {
						status = true;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (status == true) {
				suggestUser.setIsFriend(true);
				suggestUser.setIsRequested(false);
				suggestUser.setIsRequestedMe(false);
			} else {
				FriendRequest request = friendRequestRepo.findByRequestFromAndRequestTo(checkFriend.getUserId(),
						suggestUser.getUserId());
				if (request != null) {
					suggestUser.setIsRequested(true);
				} else {
					suggestUser.setIsRequested(false);
				}
				request = friendRequestRepo.findByRequestFromAndRequestTo(suggestUser.getUserId(),
						checkFriend.getUserId());
				if (request != null) {
					suggestUser.setIsRequestedMe(true);
				} else {
					suggestUser.setIsRequestedMe(false);
				}
				suggestUser.setIsFriend(false);
			}
		} catch (Exception e) {

		}
		return suggestUser;
	}

	public Boolean SendFriendRequest(CheckFriend requestDetails) {
		Boolean status = false;
		try {
			FriendRequest request = new FriendRequest();
			request.setRequestFrom(requestDetails.getUserId());
			request.setRequestTo(requestDetails.getFriendOf());
			request.setStatus("pending");
			request = friendRequestRepo.save(request);
			if (request != null) {
				status = true;
			}
		} catch (Exception e) {
			status = false;
		}
		return status;
	}

	public List<SuggestUser> myFriendRequests(Long userId) {
		List<SuggestUser> myRequests = new ArrayList<SuggestUser>();
		try {
			List<FriendRequest> friendRequest = new ArrayList<FriendRequest>();
			friendRequest = friendRequestRepo.findByRequestTo(userId);
			for (int a = 0; a < friendRequest.size(); a++) {
				SuggestUser request = new SuggestUser();
				User user = userRepo.findByUserId(friendRequest.get(a).getRequestFrom());
				request.setUserId(user.getUserId());
				request.setFirstName(user.getFirstName());
				request.setLastName(user.getLastName());
				request.setAlterName(user.getAlterName());
				request.setProfilePicture(user.getProfilePicture());
				request.setIsRequested(false);
				request.setIsFriend(false);
				request.setIsRequestedMe(true);
				myRequests.add(request);
			}
			if (myRequests.size() < 1) {
				myRequests = null;
			}
		} catch (Exception e) {
			myRequests = null;
		}
		return myRequests;
	}
	
	public Boolean acceptFriendRequest(CheckFriend friendDetails){
		Boolean status=false;
		try{
			Friends addFriend=new Friends();
			addFriend.setUserId(friendDetails.getUserId());
			addFriend.setFriendOf(friendDetails.getFriendOf());			
			friendsRepo.save(addFriend);
			FriendRequest request=friendRequestRepo.findByRequestFromAndRequestTo(friendDetails.getFriendOf(), friendDetails.getUserId());
			friendRequestRepo.delete(request);
			// set notification
			User user=userRepo.findByUserId(friendDetails.getUserId());
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			Date date = new Date();
			Notification notification=new Notification();
			notification.setNotifyFrom(friendDetails.getUserId());
			notification.setNotifyTo(friendDetails.getFriendOf());
			notification.setNotifyMessage(user.getFirstName()+" "+user.getLastName()+" accepted your friend request");
			notification.setAddedDate(dateFormat.parse(dateFormat.format(date)));
			notification.setStatus("unseen");
			notifyRepo.save(notification);		
			status=true;
		}catch(Exception e){
			status=false;
		}
		return status;
	}
	
	public Boolean deleteFriendRequest(CheckFriend friendDetails){
		Boolean status=false;
		try{		
			FriendRequest request=friendRequestRepo.findByRequestFromAndRequestTo(friendDetails.getFriendOf(), friendDetails.getUserId());
			friendRequestRepo.delete(request);
			status=true;
		}catch(Exception e){
			status=false;
		}
		return status;
	}
}
