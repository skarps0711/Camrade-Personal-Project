package com.camrade.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camrade.entity.Notification;
import com.camrade.repository.NotificationRepository;

@Service
public class NotificationService {
	
	@Autowired
	NotificationRepository notifyRepo;
	
	public List<Notification> getMyNotifications(Long userId){
		List<Notification> notifications=null;
		try{
			notifications=notifyRepo.findByNotifyToOrderByAddedDateDesc(userId);
	
		}catch(Exception e){
			notifications=null;
		}
		return notifications;
	}
}
