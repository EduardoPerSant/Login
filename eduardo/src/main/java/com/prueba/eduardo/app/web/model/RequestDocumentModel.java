package com.prueba.eduardo.app.web.model;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author Eduardo Pérez
 * @since Feb, 04, 2025
 */
@Getter
@Setter
@RequiredArgsConstructor
@ApiModel(value = "request document model")
public class RequestDocumentModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private MultipartFile[] files;
	
	private Long userId;

}
