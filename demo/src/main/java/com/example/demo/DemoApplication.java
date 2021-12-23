package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.example.demo.model.UserModel;

@SpringBootApplication
@EnableJpaAuditing //Bật audit mode để tự fill vào trường create_at, update_at không thông qua restful
public class DemoApplication {
	
	public static void main(String[] args) {
		final Logger logger = LoggerFactory.getLogger(UserModel.class);
		logger.info("haha");
		logger.warn("hehe");
		SpringApplication.run(DemoApplication.class, args);
	}

}
