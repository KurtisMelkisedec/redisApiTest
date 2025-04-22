package com.personalProject.shoply;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ShoplyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoplyApplication.class, args);
	}

}
