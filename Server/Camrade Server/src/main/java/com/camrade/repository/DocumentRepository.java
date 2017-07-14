package com.camrade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.camrade.entity.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long>{

	List<Document> findAllByOwnerId(Long userId);

}
