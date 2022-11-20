package com.luidenterprises.stocktracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class StocktrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StocktrackerApplication.class, args);
	}

}
