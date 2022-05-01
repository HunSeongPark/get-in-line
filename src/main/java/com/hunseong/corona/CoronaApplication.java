package com.hunseong.corona;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CoronaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoronaApplication.class, args);
	}

}
