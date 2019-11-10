package com.sye.cache.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sye.cache.CacheServiceApplication;
import com.sye.cache.domain.Entry;
import com.sye.cache.service.CacheService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CacheServiceApplication.class)

public class CacheServiceControllerTest {

	
	@Autowired
	WebApplicationContext webApplicationContext;
	
	@Autowired
	CacheService cacheServiceImpl;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@After
	public void cleanUp() throws Exception {
		
	}
     
    @Test
    public void testGetEntry() throws Exception 
    {
       
       mockMvc.perform(get("/cache/C1"))
       .andExpect(status().isOk())
       .andExpect(content().string("Country1"));
    }
    
    @Test
    public void testGetEntry_notAvailable() throws Exception 
    {
       
       mockMvc.perform(get("/cache/NA"))
       .andExpect(status().isOk())
       .andExpect(content().string("Not Found"));
    }
    
    @Test
    public void testPutEntry() throws Exception {
    	
    	Entry entry = new Entry();
    	entry.key = "Test";
    	entry.value = "Test Country";
    	
    	ObjectMapper om = new ObjectMapper();
    	om.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
    	ObjectWriter ow = om.writer().withDefaultPrettyPrinter();
    	String requestJson = ow.writeValueAsString(entry);
    	
    	mockMvc.perform(put("/cache")
    	.contentType(MediaType.APPLICATION_JSON_UTF8)
    	.content(requestJson))
    	.andExpect(status().isOk());
    	
    	String ret = (String) cacheServiceImpl.get("Test");
		Assert.assertEquals(ret, ret, "Test Country");
    	
    }
     
}
