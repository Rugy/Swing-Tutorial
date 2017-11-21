package com.andreas.basicApp.model;

import java.io.Serializable;

public class Person implements Serializable {

	private static final long serialVersionUID = 1L;

	private static int count = 1;

	private int id;
	private String name;
	private String occupation;
	private AgeCategory ageCat;
	private EmploymentCategory empCat;
	private String taxID;
	private boolean usCitizen;
	private Gender gender;

	public Person(String name, String occupation, AgeCategory ageCat,
			EmploymentCategory employCat, String taxId, boolean usCitizen,
			Gender gender) {
		this.name = name;
		this.occupation = occupation;
		this.ageCat = ageCat;
		this.empCat = employCat;
		this.taxID = taxId;
		this.usCitizen = usCitizen;
		this.gender = gender;

		this.id = count;
		count++;
	}

	public Person(int id, String name, String occupation, AgeCategory ageCat,
			EmploymentCategory employCat, String taxId, boolean usCitizen,
			Gender gender) {
		this(name, occupation, ageCat, employCat, taxId, usCitizen, gender);

		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public AgeCategory getAgeCat() {
		return ageCat;
	}

	public void setAgeCat(AgeCategory ageCat) {
		this.ageCat = ageCat;
	}

	public EmploymentCategory getEmpCat() {
		return empCat;
	}

	public void setEmpCat(EmploymentCategory empCat) {
		this.empCat = empCat;
	}

	public String getTaxID() {
		return taxID;
	}

	public void setTaxID(String taxID) {
		this.taxID = taxID;
	}

	public boolean isUsCitizen() {
		return usCitizen;
	}

	public void setUsCitizen(boolean usCitizen) {
		this.usCitizen = usCitizen;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return id + ": " + name;
	}

}
