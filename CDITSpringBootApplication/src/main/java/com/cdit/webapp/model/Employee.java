package com.cdit.webapp.model;


public class Employee {

	private String firstName;
	private String lastName;
	private float salary;	
	
	public Employee() {
	}

	public Employee(String firstName, String lastName, float salary) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.salary = salary;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public float getSalary() {
		return this.salary;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setSalary(float salary) {
		this.salary = salary;
	}
	
}
