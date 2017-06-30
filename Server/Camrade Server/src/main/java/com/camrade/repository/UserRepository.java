package com.camrade.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.camrade.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByUserNameAndPassword(String userName,String password);
	User findByUserName(String userName);
	User findByEmail(String email);
}
