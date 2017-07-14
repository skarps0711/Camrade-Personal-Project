package com.camrade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.camrade.entity.Audio;

@Repository
public interface AudioRepository extends JpaRepository<Audio, Long>{
	
	List<Audio> findAllByOwnerId(Long userId);
}
