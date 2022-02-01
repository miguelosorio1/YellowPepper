package com.cursojava.curso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CursoApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(CursoApplication.class, args);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
