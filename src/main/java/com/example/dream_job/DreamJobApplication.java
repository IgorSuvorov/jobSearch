package com.example.dream_job;


import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Properties;

@ComponentScan("com.example.dream_job.model")
@EntityScan("com.example.dream_job.model")
@SpringBootApplication
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