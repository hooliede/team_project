package com.example.main_project.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
	//dto와 entity를 자동으로 mapping 해주는 코드이다
	//직접 setter를 통해서 객체를 연결해야 했지만 어떠한 매핑 전략을 통해서 자동 매핑이 됨
	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
