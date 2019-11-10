package com.sye.cache.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

public class FileCache
{
   private final Properties configProp = new Properties();
    
   private FileCache()
   {
     
      InputStream in = this.getClass().getClassLoader().getResourceAsStream("country.properties");
      
      try {
          configProp.load(in);
      } catch (IOException e) {
          e.printStackTrace();
      }
   }
 

   private static class SingletonHelper
   {
      private static final FileCache INSTANCE = new FileCache();
   }
 
   public static FileCache getInstance()
   {
      return SingletonHelper.INSTANCE;
   }
    
   public String getProperty(String key){
      return configProp.getProperty(key);
   }
   
   public void setProperty(String key, String value){
	   configProp.setProperty(key, value);
	 }
    
   public Set<String> getAllPropertyNames(){
      return configProp.stringPropertyNames();
   }
    
   public boolean containsKey(String key){
      return configProp.containsKey(key);
   }
}