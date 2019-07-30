package com.project.jobbackenddeveloper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class JobBackendDeveloperApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobBackendDeveloperApplication.class, args);
	}

}
