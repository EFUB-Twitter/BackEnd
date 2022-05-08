package com.example.backend_efub_twitter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BackendEfubTwitterApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendEfubTwitterApplication.class, args);
	}

}
