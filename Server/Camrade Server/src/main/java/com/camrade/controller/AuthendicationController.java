package com.camrade.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.camrade.entity.User;
import com.camrade.model.Login;
import com.camrade.model.SignupUser;
import com.camrade.model.UserFieldCheck;
import com.camrade.service.AuthendicateService;

@RestController
@CrossOrigin(origins="*")
public class AuthendicationController {

	@Autowired
	AuthendicateService authService;

	@RequestMapping(value = "/users/authendicateuser", method = RequestMethod.POST)
	public ResponseEntity<?> validateUser(@RequestBody Login login) {
		User user;
		user = authService.validateUser(login);
		if (user != null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<User>(user, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/users/adduser", method = RequestMethod.POST)
	public ResponseEntity<?> addUser(@RequestBody SignupUser newUser){
		User user=null;
		user=authService.addUser(newUser);
		if(user!=null){
			return new ResponseEntity<User>(user,HttpStatus.OK);
		}
		else{
			return new ResponseEntity<User>(user,HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/users/isusernameexist", method = RequestMethod.POST)
	public ResponseEntity<?> isUserNameExist(@RequestBody String newUserName){
		User user=null;
		user=authService.isUserNameExist(newUserName);
		if(user==null){
			return new ResponseEntity<UserFieldCheck>(new UserFieldCheck(100, false),HttpStatus.OK);
		}
		else{
			return new ResponseEntity<UserFieldCheck>(new UserFieldCheck(101, true),HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/users/isemailexist", method = RequestMethod.POST)
	public ResponseEntity<?> isEmailExist(@RequestBody String newEmail){
		User user=null;
		user=authService.isEmailExist(newEmail);
		if(user==null){
			return new ResponseEntity<UserFieldCheck>(new UserFieldCheck(100, false),HttpStatus.OK);
		}
		else{
			return new ResponseEntity<UserFieldCheck>(new UserFieldCheck(101, true),HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/users/sendemail", method = RequestMethod.POST)
	public ResponseEntity<?> findUser(@RequestBody String userNameOrEmail){
		UserFieldCheck userFieldCheck;
		Boolean result=authService.sendEmail(userNameOrEmail);
		if(result==true){
			return new ResponseEntity<UserFieldCheck>(new UserFieldCheck(101, true),HttpStatus.OK);
		}
		else{
			return new ResponseEntity<UserFieldCheck>(new UserFieldCheck(100, false),HttpStatus.OK);
		}
	}

}
