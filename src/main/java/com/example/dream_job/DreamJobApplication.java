package com.example.dream_job;


import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

@EntityScan("com.example.dream_job.model")
@SpringBootApplication(scanBasePackages = "com.example.dream_job")
public class DreamJobApplication {
	@Bean
	ModelMapper createModelMapper() {
		return new ModelMapper();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(DreamJobApplication.class, args);
		System.out.println("Go to http://localhost:8080/index");
	}
}