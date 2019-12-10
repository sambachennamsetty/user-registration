package com.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
@Entity
public class Mobile {

	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "mygen")
	@GenericGenerator(name = "mygen", strategy = "com.app.config.MyGenerator")
	public String mobileId;
	public String name;
	public Date date;

	public Mobile() {
		super();
	}
	
	

	public Mobile(String mobileId, String name, Date date) {
		super();
		this.mobileId = mobileId;
		this.name = name;
		this.date = date;
	}



	public Mobile(String mobileId, String name) {
		super();
		this.mobileId = mobileId;
		this.name = name;
	}

	public String getMobileId() {
		return mobileId;
	}

	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	public void setMobileId(String mobileId) {
		this.mobileId = mobileId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Mobile [mobileId=" + mobileId + ", name=" + name + "]";
	}

}
