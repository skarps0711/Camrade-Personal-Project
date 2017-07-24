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

import com.camrade.entity.Notification;
import com.camrade.service.NotificationService;

@RestController
@CrossOrigin(origins="*")
public class NotificationController {
	
	@Autowired
	NotificationService notifyService;
	
	@RequestMapping(value = "/user/{id}/mynotifications", method = RequestMethod.GET)
	public ResponseEntity<?> getMyNotifications(@PathVariable(value="id") String id){
		Long userId=Long.parseLong(id);
		List<Notification> notifications=notifyService.getMyNotifications(userId);
		System.out.println(notifications.get(0).getAddedDate());
		System.out.println(notifications.get(1).getAddedDate());
		System.out.println(notifications.get(2).getAddedDate());
		return new ResponseEntity<List<Notification>>(notifications,HttpStatus.OK);
	}

}
