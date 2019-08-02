package com.stackroute.MusixAppAssignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EnableDiscoveryClient
public class MusixAppAssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusixAppAssignmentApplication.class, args);
	}

}
