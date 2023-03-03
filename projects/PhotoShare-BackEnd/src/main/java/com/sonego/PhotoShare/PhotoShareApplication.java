package com.sonego.PhotoShare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class PhotoShareApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotoShareApplication.class, args);
	}

}
