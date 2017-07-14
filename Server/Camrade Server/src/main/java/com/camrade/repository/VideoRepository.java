package com.camrade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.camrade.entity.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long>{

	List<Video> findAllByOwnerId(Long userId);

}
