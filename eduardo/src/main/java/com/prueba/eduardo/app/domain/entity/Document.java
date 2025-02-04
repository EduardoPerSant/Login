package com.prueba.eduardo.app.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import com.prueba.eduardo.app.web.model.DocumentModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "documents")
@Entity
public class Document{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "file_name")
	private String fileName;
	
	@Column(name = "file_size")
	private Long fileSize;
	
	@Column(name = "file_type")
	private String fileType;
	
	@Column(name = "upload_date")
	private LocalDateTime uploadDate;
	
	/**
	 * Método para transformar la entidad a modelo
	 * @return instance of { {@link DocumentModel }
	 */
	public DocumentModel toModel() {
		DocumentModel model = new DocumentModel();
		BeanUtils.copyProperties(this, model);
		model.setUploadDate(this.uploadDate.toString());
		return model;
	}

}
