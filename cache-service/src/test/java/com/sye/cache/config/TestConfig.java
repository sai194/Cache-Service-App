package com.sye.cache.config;

import static org.mockito.Mockito.mock;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.sye.cache.service.CacheService;
import com.sye.cache.service.CacheServiceImpl;

@TestConfiguration
public class TestConfig {

	@Bean
	public CacheService cacheService() {
		return mock(CacheServiceImpl.class);
	}
}
