package com.prueba.eduardo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication()
@EnableJpaRepositories(basePackages = "com.prueba.eduardo.app.domain.repository")
public class EduardoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EduardoApplication.class, args);
	}

}
