package com.cursojava.curso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CursoApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(CursoApplication.class, args);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
