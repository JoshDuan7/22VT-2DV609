package com.group3.gymup;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.group3.gymup.mapper")
@SpringBootApplication
public class GymupApplication {

	public static void main(String[] args) {
		SpringApplication.run(GymupApplication.class, args);

	}

}
