package com.mongo.model;

public class Student {
	
	private String name;
	private int rollNum;
	static String school;
	private static int counter = 0;
	
	static {
		school = "DPS";
	}

	public Student(String name) {
		super();
		this.name = name;
		this.rollNum = ++counter;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRollNum() {
		return rollNum;
	}

	public void setRollNum(int rollNum) {
		this.rollNum = rollNum;
	}

	public static String getSchool() {
		return school;
	}

	public static void setSchool(String school) {
		Student.school = school;
	}
	

}
