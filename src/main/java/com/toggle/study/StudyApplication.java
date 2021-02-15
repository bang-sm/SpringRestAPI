package com.toggle.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudyApplication {

	static {
		System.setProperty("spring.profiles.active", "development");
	  }
	

	public static void main(String[] args) {
		SpringApplication.run(StudyApplication.class, args);
	}

}
