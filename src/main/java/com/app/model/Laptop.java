package com.app.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Laptop {
	@Id
	private Integer id;
	private String model;
	private String brand;

	public Laptop() {
		super();
	}

	public Laptop(Integer id, String model, String brand) {
		super();
		this.id = id;
		this.model = model;
		this.brand = brand;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Override
	public String toString() {
		return "Laptop [id=" + id + ", model=" + model + ", brand=" + brand + "]";
	}

}
