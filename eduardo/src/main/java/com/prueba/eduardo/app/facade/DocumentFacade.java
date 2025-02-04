package com.prueba.eduardo.app.facade;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.eduardo.app.domain.entity.Document;
import com.prueba.eduardo.app.domain.repository.DocumentRepository;

import lombok.RequiredArgsConstructor;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DocumentFacade {
	
	private final DocumentRepository repository;
	
	/**
	 * Find document by id
	 * @param id
	 * @return
	 */
	public Document findById(Long id) {
		return repository.getById(id);
	}
	
	/**
	 * find all documents
	 * @return
	 */
	public List<Document> findAll(){
		return repository.findAll();
	}
	
	/**
	 * Find all documents by user
	 * @param id
	 * @return
	 */
	public List<Document> findAllByUserId(Long userId){
		return repository.findAllByUserId(userId);
	}
	
	/**
	 * register new document
	 * @param doc
	 * @return
	 */
	@Transactional(readOnly =  false)
	public Document saveDocument(Document doc) {
		return repository.save(doc);
	}

	/**
	 * update document
	 * @param doc
	 * @return
	 */
	public Document updateDocument(Document doc) {
		return repository.save(doc);
	}
	
	/**
	 * Delete document
	 * @param entity
	 */
	public void deleteDocumet(Document entity) {
		repository.delete(entity);
	}
}
