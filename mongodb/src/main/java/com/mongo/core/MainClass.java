package com.mongo.core;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongo.model.Student;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

public class MainClass {

	public static void main(String[] args) throws UnknownHostException, JsonProcessingException {
		
		
		MongoClient client = new MongoClient("localhost", 27017);
		DB db = client.getDB("students");
		DBCollection collection = db.getCollection("studentData");
		System.out.println("Connection Established");
		
		
		//Insert Set of Data in DB
		List<DBObject> studentList = new ArrayList<DBObject>();
		
		for(int i=1; i<=10; i++) {
			Student student = new Student("Student:"+i);
			ObjectMapper Obj = new ObjectMapper();
			String json = Obj.writeValueAsString(student); 
			DBObject dbObject = (DBObject)JSON.parse(json);
			studentList.add(dbObject);
		}
		
		System.out.println(collection.insert(studentList));
		System.out.println("Done. Closing connection"); 
		//----------------------------------------
		
		
		
		 // Find/Read Data from DB
		 
		//Find the first record
		DBObject doc = collection.findOne();
		System.out.println(doc);
		
		
		//Find All
		 
		DBCursor cursor = collection.find();
		while(cursor.hasNext()) {
		    System.out.println(cursor.next());
		}
		
		
		//Get value of only one particular field.
		
		BasicDBObject allQuery = new BasicDBObject();
		BasicDBObject fields = new BasicDBObject();
		fields.put("school", 1);
		
		DBCursor cursor2 = collection.find(allQuery, fields);
		while (cursor2.hasNext()) {
			System.out.println(cursor2.next());
		}
		
		
		//Where clause
		
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("name", "Gagan"); //where name = "Gagan"
		
		DBCursor cursor3 = collection.find(whereQuery);
		while (cursor3.hasNext()) {
			System.out.println(cursor3.next());
		} 
		
		
		
		//In Clause
		BasicDBObject inQuery = new BasicDBObject();
		List<Integer> list = new ArrayList<Integer>();
		list.add(2);
		list.add(4);
		list.add(5);
		inQuery.put("rollNum", new BasicDBObject("$in", list)); // where rollNum IN (2,4,5)
		DBCursor cursor4 = collection.find(inQuery);
		while(cursor4.hasNext()) {
			System.out.println(cursor4.next());
		}
		
		
		//In Range - A > X > B
		BasicDBObject gtQuery = new BasicDBObject();
		gtQuery.put("rollNum", new BasicDBObject("$gt", 2).append("$lt", 5)); // where rollNum>2 and rollNum<5
		DBCursor cursor5 = collection.find(gtQuery);
		while(cursor5.hasNext()) {
			System.out.println(cursor5.next());
		}
		
		
		//Not equalTo
		BasicDBObject neQuery = new BasicDBObject();
		neQuery.put("rollNum", new BasicDBObject("$ne",5)); // where rollNum != 5
		DBCursor cursor6 = collection.find(neQuery);
		while(cursor6.hasNext()) {
			System.out.println(cursor6.next());
		}
		
		
		// And Operation
		BasicDBObject andQuery = new BasicDBObject();
		List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
		obj.add(new BasicDBObject("rollNum", new BasicDBObject("$gte",3)));
		obj.add(new BasicDBObject("name", "Student:7"));
		andQuery.put("$and", obj);
		DBCursor cursor7 = collection.find(andQuery);
		while(cursor7.hasNext()) {
			System.out.println(cursor7.next());
		}
		
		client.close();
	}

}
