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

import com.camrade.model.ProcessCheck;
import com.camrade.model.SendEmail;
import com.camrade.model.SuggestUser;
import com.camrade.service.FriendsService;

@RestController
@CrossOrigin(origins="*")
public class FriendsController {
	
	@Autowired
	FriendsService friendsService;
	
	@RequestMapping(value = "/user/{id}/invitefriend", method = RequestMethod.POST)
	public ResponseEntity<?> inviteFriend(@RequestBody SendEmail sendEmail,@PathVariable(value="id") String id){
		Long userId=Long.parseLong(id);
		Boolean status=friendsService.sendEmail(userId, sendEmail);
		if(status==true){
			return new ResponseEntity<ProcessCheck>(new ProcessCheck(101,true),HttpStatus.OK);
		}
		return new ResponseEntity<ProcessCheck>(new ProcessCheck(102,false),HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/user/searchfriends", method = RequestMethod.POST)
	public ResponseEntity<?> searchFriends(@RequestBody String userField){
		List<SuggestUser> suggestUsers=friendsService.getRelatedUsers(userField);
		return new ResponseEntity<List<SuggestUser>>(suggestUsers,HttpStatus.OK);
	}

}
