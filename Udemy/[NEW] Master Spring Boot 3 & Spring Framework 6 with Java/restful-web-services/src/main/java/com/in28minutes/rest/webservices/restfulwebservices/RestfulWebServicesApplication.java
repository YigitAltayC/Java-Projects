package com.in28minutes.rest.webservices.restfulwebservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Social Media Application Rest API
 *
 * Key Resources:
 *
 * 	- Users
 * 	- Posts
 *
 * Key Details:
 *
 * 	- User: id, name, birthDate
 * 	- Post: id, description
 */

@SpringBootApplication
public class RestfulWebServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulWebServicesApplication.class, args);
	}

}
