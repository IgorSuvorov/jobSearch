package com.example.dream_job.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Igor Suvorov
 */
@Configuration
@EntityScan("com.example.dream_job.model")
public class AppConfig {

}

