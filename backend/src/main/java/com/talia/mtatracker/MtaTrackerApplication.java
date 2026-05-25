package com.talia.mtatracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MtaTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MtaTrackerApplication.class, args);
	}

}
