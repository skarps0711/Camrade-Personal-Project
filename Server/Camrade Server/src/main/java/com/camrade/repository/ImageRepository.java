package com.camrade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.camrade.entity.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long>{

	List<Image> findAllByOwnerId(Long userId);

}
