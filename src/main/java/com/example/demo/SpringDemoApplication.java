package com.example.demo;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDemoApplication {
	private static final Logger logger = LogManager.getLogger(SpringDemoApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(SpringDemoApplication.class, args);
		logger.info("starting spring app");
	}

}
