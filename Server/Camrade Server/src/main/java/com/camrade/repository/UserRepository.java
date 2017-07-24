package com.camrade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.camrade.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByUserNameAndPassword(String userName,String password);
	User findByUserName(String userName);
	User findByEmail(String email);
	User findByUserId(Long userId);
	List<User> findByPhoneNo(String phoneNo);
	List<User> findByFirstNameAndLastName(String firstName,String lastName);
	List<User> findByFirstNameLike(String firstName);
	List<User> findByLastNameLike(String lastName);
}
