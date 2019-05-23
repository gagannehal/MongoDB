package com.rest.config;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class MongoDBConfig {
	
	private static MongoDBConfig mongoDBConfig;
	 
	 private static MongoClient mongoClient;
	    
	 private static DB db ;
	 
	 
	 private static final String dbHost = "localhost";
	 private static final int dbPort = 27017;
	 private static final String dbName = "students";
	 private static final String dbUser = "userName";
	 private static final String dbPassword = "password";

	 private MongoDBConfig(){};
	 
	 public static MongoDBConfig getInstance(){
	  if(mongoDBConfig == null){
		  mongoDBConfig = new MongoDBConfig();
	  }
	  return mongoDBConfig;
	 } 
	 
	 public DB getTestdb(){
	  if(mongoClient == null){
	   try {
	    mongoClient = new MongoClient(dbHost , dbPort);
	   } catch (UnknownHostException e) {
	    return null;
	   }
	  }
	  if(db == null)
	   db = mongoClient.getDB(dbName);
	  if(!db.isAuthenticated()){
	   boolean auth = db.authenticate(dbUser, dbPassword.toCharArray());
	  }
	  return db;
	 }

}
