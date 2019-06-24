package com.school.game;

import org.apache.shiro.spring.boot.autoconfigure.ShiroAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(exclude= {ShiroAutoConfiguration.class})
@RestController
@MapperScan("com.school.game.mapper")
public class SchoolGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolGameApplication.class, args);
	}

	@GetMapping("/test")
	public String test() {
		return "test ok";
	}
}
