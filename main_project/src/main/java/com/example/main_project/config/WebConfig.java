package com.example.main_project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		//CORS(Cross-Origin Resource Sharing)를 허용하는 규칙이래 일단
		
	}
	

}
