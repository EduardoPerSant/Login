package com.prueba.eduardo.app.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.eduardo.app.domain.entity.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

	public List<Document> findAllByUserId(Long userId);
}
