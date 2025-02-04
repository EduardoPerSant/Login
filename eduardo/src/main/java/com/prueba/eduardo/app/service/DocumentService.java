package com.prueba.eduardo.app.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.prueba.eduardo.app.domain.entity.Document;
import com.prueba.eduardo.app.facade.DocumentFacade;
import com.prueba.eduardo.app.web.model.DocumentModel;

import lombok.RequiredArgsConstructor;

/**
 * Document Service
 * @author Eduardo Pérez
 * @since Feb 03, 2025
 */
@Service
@RequiredArgsConstructor
public class DocumentService {

    @Value("${file.upload-dir}")
    private String uploadDir;
    
	private final DocumentFacade documentFacade;
	
	/**
	 * find by Id
	 * @param id
	 * @return instance of { {@link Document }
	 */
	private Document findById(Long id) {
		Document docu = documentFacade.findById(id);
		if(Objects.isNull(docu)) {
			 throw new IllegalStateException("No se encontro documento.");
		}
		return docu;
	}
	
	/**
	 * get document by id
	 * @param id
	 * @return instance of { {@link DocumentModel }}
	 */
	public DocumentModel getById(Long id) {
		return this.findById(id).toModel();
	}
	
	
	/**
	 * find by user Id
	 * @param userId
	 * @return list of { @link DocumentModel }
	 */
	public List<DocumentModel> findAllByUser(Long userId) {
	    List<Document> documents = documentFacade.findAllByUserId(userId);
	    if (documents.isEmpty()) {
	        return Collections.emptyList();  
	    }
	    return documents.stream()
	            .map(Document::toModel)
	            .collect(Collectors.toList());
	}
	
	/**
	 * save new Document
	 * @param doc
	 * @return instance of { {@link DocumentModel }
	 */
	public DocumentModel saveDocumet(DocumentModel model) {
		Document entity = model.toEntity();
		entity.setUploadDate(LocalDateTime.now());
		entity = documentFacade.saveDocument(entity);
		return model;
	}
	
	/**
	 * update document
	 * @param model
	 * @return instance of { {@link DocumentModel }
	 */
	public DocumentModel updateDocument(DocumentModel model) {
		Document entity = documentFacade.updateDocument(model.toEntity());
		return entity.toModel();
	}
	
	/**
	 * delete documen
	 * @param id
	 */
	public void deleteDocument(Long id) {
		Document entity = this.findById(id);
		documentFacade.deleteDocumet(entity);
	}
	
	/**
	 * 
	 * @param files
	 * @throws IOException
	 */
	 public void storeFiles(MultipartFile[] files, Long userId) throws IOException {
        // Crear directorio si no existe
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        for (MultipartFile file : files) {
            // Nombre del archivo
            String fileName = file.getOriginalFilename();
            // Ruta donde se almacenará el archivo
            Path targetLocation = Paths.get(uploadDir + "/" + fileName);

            // Guardar archivo en el sistema de archivos
            Files.copy(file.getInputStream(), targetLocation);

            // Guardar los metadatos en la base de datos
            Document document = new Document();
            document.setUserId(userId);
            document.setFileName(fileName);
            document.setFileSize(file.getSize());
            document.setFileType(file.getContentType());
            document.setUploadDate(java.time.LocalDateTime.now());
            
            documentFacade.saveDocument(document);
        }
    }
	
}
