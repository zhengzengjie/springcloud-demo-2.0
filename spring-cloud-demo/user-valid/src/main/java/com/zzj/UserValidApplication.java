package com.zzj;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
@RequestMapping("/user")
public class UserValidApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserValidApplication.class, args);
	}

	@Value("${server.port}")
	private String port;
	
	@GetMapping("valid")
	public String valid(String name) {
		System.out.println("user-valid, name=" + name);
		if(name.startsWith("user")) {
			return "User name verification was successful. port = " + port;
		}
		return "User name validation failed, not starting with 'user'. port = " + port;
	}
}
