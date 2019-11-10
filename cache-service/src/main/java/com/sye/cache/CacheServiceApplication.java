package com.sye.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sye.cache.service.CacheService;

@SpringBootApplication
public class CacheServiceApplication implements CommandLineRunner{

	@Autowired
	CacheService cacheService;
	
	public static void main(String[] args) {
		SpringApplication.run(CacheServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		cacheService.put("IND", "India");
		cacheService.put("US", "United States");
		cacheService.put("ENG", "England");
		cacheService.put("JPN", "Japan");
		cacheService.put("RU", "Russia");
		cacheService.put("DE", "Germany");
		cacheService.put("CA", "Canada");
		
	}

}
