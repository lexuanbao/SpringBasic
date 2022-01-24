package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing //Bật audit mode để tự fill vào trường create_at, update_at không thông qua restful
public class DemoRestfulApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoRestfulApplication.class, args);
	}

}
