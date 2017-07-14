package com.camrade.controller;

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
import com.camrade.model.NoOfMedias;
import com.camrade.model.SaveImage;
import com.camrade.service.ProfileService;

@RestController
@CrossOrigin(origins="*")
public class ProfileController {
	
	@Autowired
	ProfileService profileService;
	
	@RequestMapping(value = "/user/profile/{id}/getnoofmedias", method = RequestMethod.GET)
	public ResponseEntity<?> getNoOfMedias(@PathVariable (value="id") String id){
		NoOfMedias noOfMedias=profileService.getNoOfMedias(Long.parseLong(id));
		return new ResponseEntity<NoOfMedias>(noOfMedias,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user/profile/updateprofile", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@RequestBody User userDetails){
		System.out.println(userDetails.getUserId()+" "+userDetails.getQuotes()+"Pass : "+userDetails.getPassword());
		User user=profileService.updateUser(userDetails);
		if(user!=null){
			return new ResponseEntity<User>(user,HttpStatus.OK);
		}
		return new ResponseEntity<User>(user,HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/user/profile/updateimage", method = RequestMethod.PUT)
	public ResponseEntity<?> saveImage(@RequestBody SaveImage imageDetails){
		Boolean status=profileService.saveImage(imageDetails);
		User user=null;
		if(status!=false){
			user=profileService.getUser(imageDetails.getUserId());
			if(user!=null){
				return new ResponseEntity<User>(user,HttpStatus.OK);
			}
			return new ResponseEntity<User>(user,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<User>(user,HttpStatus.BAD_REQUEST);
	}



}
