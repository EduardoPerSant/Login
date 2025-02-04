package com.prueba.eduardo.app.web.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import com.prueba.eduardo.app.domain.entity.Document;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Document model
 * @author Eduardo Pérez
 * @since Feb 03, 2025
 */
@Getter
@Setter
@RequiredArgsConstructor
@ApiModel(value = "Model to document")
public class DocumentModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "id document", example = "1")
	private Long id;
	
	@ApiModelProperty(value="user id")
	private Long userId;
	
	@ApiModelProperty(value = "file Name", example = "documento1")
	private String fileName;

	@ApiModelProperty(value = "file size", example = "10")
	private Long fileSize;
	
	@ApiModelProperty(value = "file type", example = "pdf")
	private String fileType;
	
	@ApiModelProperty(value = "updload date", example = "03-01-2025")
	private String uploadDate;
	
	/**
	 * convert to entity
	 * @return
	 */
	public Document toEntity() {
		Document entity = new Document();
		BeanUtils.copyProperties(this, entity);
		return entity;
	}

}
