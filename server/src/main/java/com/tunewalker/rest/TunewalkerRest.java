package com.tunewalker.rest;

import com.tunewalker.rest.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TunewalkerRest {
	static Logger logger = LoggerFactory.getLogger(TunewalkerRest.class);

	public static void main(String[] args) {
		SpringApplication.run(TunewalkerRest.class, args);
		logger.info("Tune Walker Application Starting");

	}

}
