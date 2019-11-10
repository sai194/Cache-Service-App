package com.sye.cache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sye.cache.domain.Entry;
import com.sye.cache.service.CacheService;

@RestController
@RequestMapping(value="/cache")
public class CacheServiceController {
	
	@Autowired
	CacheService cacheService;
	
	@GetMapping("/{key}")
	public ResponseEntity<Object> get(@PathVariable String key) {
		
		Object ret = cacheService.get(key);
		
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<Object> put(@RequestBody Entry entry) {
		cacheService.put(entry.key, entry.value);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

}
