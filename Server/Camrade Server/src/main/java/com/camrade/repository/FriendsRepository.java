package com.camrade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.camrade.entity.Friends;

@Repository
public interface FriendsRepository extends JpaRepository<Friends, Long>{
	Friends findByUserIdAndFriendOf(Long userId,Long friendOf);
	Friends findByFriendOfAndUserId(Long friendOf,Long userId);
}
