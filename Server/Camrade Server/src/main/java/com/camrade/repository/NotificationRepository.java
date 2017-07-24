package com.camrade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.camrade.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long>{
List<Notification> findByNotifyToOrderByAddedDateDesc(Long notifyTo);
}
