package com.example.rssApp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RssAppApplication {

	private static final Logger logger = LoggerFactory.getLogger(RssAppApplication.class);

	public static void main(String[] args) {
		logger.info("Service started");
		logger.error("An error occurred", new RuntimeException("Example exception"));
		SpringApplication.run(RssAppApplication.class, args);
	}

}
