package com.app.model;

public class Tester {

	private String name;
	private String location;

	public Tester(String name, String location) {
		super();
		this.name = name;
		this.location = location;
	}

	public Tester() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", location=" + location + "]";
	}

}
