package com.camrade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.camrade.entity.FriendRequest;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {

	FriendRequest findByRequestFromAndRequestTo(Long requestFrom, Long requestTo);
	FriendRequest findByRequestToAndRequestFrom(Long requestTo,Long requestFrom);
	List<FriendRequest> findByRequestTo(Long requestTo);
}
