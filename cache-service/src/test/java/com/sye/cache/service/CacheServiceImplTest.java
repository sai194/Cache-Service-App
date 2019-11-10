package com.sye.cache.service;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CacheServiceImplTest {
	
	@Autowired
	CacheService cacheServiceImpl;
	
	@Test
	public void testGetEntry() {
		
		String ret = (String) cacheServiceImpl.get("C1");
		Assert.assertEquals(ret, ret, "Country1");
	}
	
	@Test
	public void testGetEntry_noFound() {
		
		String ret = (String) cacheServiceImpl.get("NA");
		Assert.assertNull(ret);
	}
	
	@Test
	public void testPutEntry() {
		
		cacheServiceImpl.put("Test","Test Country");
		String ret = (String) cacheServiceImpl.get("Test");
		Assert.assertEquals(ret, ret, "Test Country");
	}
	

}
