package com.springboot.library.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelConfig {

	@Bean
	public ModelMapper getModelMapper() {

		ModelMapper modelMapper = new ModelMapper();
		return modelMapper;	
	}
}
