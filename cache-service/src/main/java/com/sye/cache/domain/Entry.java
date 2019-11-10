package com.sye.cache.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Entry {
	public String value;
	public String key;
	@JsonIgnore
	public Entry left;
	@JsonIgnore
	public Entry right;
	
}