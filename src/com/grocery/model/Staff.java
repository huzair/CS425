package com.grocery.model;

public class Staff {

	private String name;
	private double salary;
	private String jobTitle;
	private Address address;
	
	public Staff(String name, double salary, String jobTitle, Address address){
		this.name = name;
		this.salary = salary;
		this.jobTitle = jobTitle;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
