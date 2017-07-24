package com.camrade.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.camrade.entity.User;
import com.camrade.model.CheckFriend;
import com.camrade.model.ProcessCheck;
import com.camrade.model.SendEmail;
import com.camrade.model.SuggestUser;
import com.camrade.model.SuggestUserInput;
import com.camrade.model.UserFieldCheck;
import com.camrade.service.FriendsService;

@RestController
@CrossOrigin(origins = "*")
public class FriendsController {

	@Autowired
	FriendsService friendsService;

	@RequestMapping(value = "/user/{id}/invitefriend", method = RequestMethod.POST)
	public ResponseEntity<?> inviteFriend(@RequestBody SendEmail sendEmail, @PathVariable(value = "id") String id) {
		Long userId = Long.parseLong(id);
		Boolean status = friendsService.sendEmail(userId, sendEmail);
		if (status == true) {
			return new ResponseEntity<ProcessCheck>(new ProcessCheck(101, true), HttpStatus.OK);
		}
		return new ResponseEntity<ProcessCheck>(new ProcessCheck(102, false), HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/user/searchfriends", method = RequestMethod.POST)
	public ResponseEntity<?> searchFriends(@RequestBody SuggestUserInput suggestInput) {
		List<SuggestUser> suggestUsers = friendsService.getRelatedUsers(suggestInput);
		List<SuggestUser> suggestUsersFinal= friendsService.checkFriend(suggestInput.getUserId(), suggestUsers);
		return new ResponseEntity<List<SuggestUser>>(suggestUsersFinal, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/{id}/getuser", method = RequestMethod.GET)
	public ResponseEntity<?> getUser(@PathVariable(value = "id") String id) {
		Long userId = Long.parseLong(id);
		User user = friendsService.getUser(userId);
		if (user == null) {
			return new ResponseEntity<User>(user, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user/checkfriendexist", method = RequestMethod.POST)
	public ResponseEntity<?> checkFriend(@RequestBody CheckFriend checkFriend) {
		SuggestUser suggestUser=friendsService.getUserDetails(checkFriend);
		return new ResponseEntity<SuggestUser>(suggestUser, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user/sendfriendrequest", method = RequestMethod.POST)
	public ResponseEntity<?> sendFriendRequest(@RequestBody CheckFriend requestDetails) {
		Boolean status=friendsService.SendFriendRequest(requestDetails);
		if(status==true){
			return new ResponseEntity<ProcessCheck>(new ProcessCheck(101,true),HttpStatus.OK);
		}
		return new ResponseEntity<ProcessCheck>(new ProcessCheck(101,true),HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/user/{id}/myfriendrequests", method = RequestMethod.GET)
	public ResponseEntity<?> getMyFriendRequests(@PathVariable(value = "id") String id) {
		Long userId = Long.parseLong(id);
		List<SuggestUser> myRequests = null;
		myRequests = friendsService.myFriendRequests(userId);
		if (myRequests != null) {
			return new ResponseEntity<List<SuggestUser>>(myRequests, HttpStatus.OK);
		}
		return new ResponseEntity<List<SuggestUser>>(myRequests, HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/user/acceptfriendrequest", method = RequestMethod.POST)
	public ResponseEntity<?> acceptFriendRequest(@RequestBody CheckFriend friendDetails) {
		Boolean status=friendsService.acceptFriendRequest(friendDetails);
		if(status==true){
			return new ResponseEntity<ProcessCheck>(new ProcessCheck(101,true),HttpStatus.OK);
		}
		return new ResponseEntity<ProcessCheck>(new ProcessCheck(101,true),HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/user/deletefriendrequest", method = RequestMethod.POST)
	public ResponseEntity<?> deleteFriendRequest(@RequestBody CheckFriend requestDetails) {
		Boolean status=friendsService.deleteFriendRequest(requestDetails);
		if(status==true){
			return new ResponseEntity<ProcessCheck>(new ProcessCheck(101,true),HttpStatus.OK);
		}
		return new ResponseEntity<ProcessCheck>(new ProcessCheck(101,true),HttpStatus.BAD_REQUEST);
	}
}
