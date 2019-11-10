package com.sye.cache.service;

import org.springframework.stereotype.Service;

@Service
public class CacheServiceImpl implements CacheService {

	private final InMemCache inMemCache= InMemCache.getInstance();
	private final FileCache fileCache = FileCache.getInstance();	
	@Override
	public Object get(String key) {
		Object ret = inMemCache.getEntry(key);
		if(null == ret) {
			ret = fileCache.getProperty(key);
			inMemCache.putEntry(key, ret);
		}
		return (ret!=null ? ret : "Not Found");
	}

	@Override
	public void put(String key, Object value) {
		inMemCache.putEntry(key, value);
		fileCache.setProperty(key, String.valueOf(value));
		
	}

}
