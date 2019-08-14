package com.stackroute.MusixAppAssignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableCaching
@ComponentScan("com.stackroute") //to scan packages mentioned

public class MusixAppAssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusixAppAssignmentApplication.class, args);
	}

}
