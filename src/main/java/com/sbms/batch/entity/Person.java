package com.sbms.batch.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Person {

	@Id
	private String personId;

	private String personName;

	private String mobileno;

	public String getPersonId() {
		return personId;
	}

	public String getPersonName() {
		return personName;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public Person() {
		super();
	}

	public Person(String personId, String personName, String mobileno) {
		super();
		this.personId = personId;
		this.personName = personName;
		this.mobileno = mobileno;
	}

	@Override
	public String toString() {
		return "Person [personId=" + personId + ", personName=" + personName + ", mobileno=" + mobileno + "]";
	}

}
