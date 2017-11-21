package com.andreas.basicApp.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.andreas.basicApp.gui.FormEvent;
import com.andreas.basicApp.model.AgeCategory;
import com.andreas.basicApp.model.Database;
import com.andreas.basicApp.model.EmploymentCategory;
import com.andreas.basicApp.model.Gender;
import com.andreas.basicApp.model.Person;

public class Controller {

	Database db = new Database();

	public void addPerson(FormEvent ev) {
		String name = ev.getName();
		String occupation = ev.getOccupation();
		int ageCatId = ev.getAgeCat();
		String empCat = ev.getEmpCat();
		boolean isUs = ev.isUsCitizen();
		String taxId = ev.getTaxID();
		String gender = ev.getGender();

		AgeCategory ageCategory;

		switch (ageCatId) {
		case 0:
			ageCategory = AgeCategory.child;
			break;
		case 1:
			ageCategory = AgeCategory.adult;
			break;
		case 2:
			ageCategory = AgeCategory.senior;
			break;
		default:
			ageCategory = null;
			break;
		}

		EmploymentCategory empCategory;

		if (empCat.equals("employed")) {
			empCategory = EmploymentCategory.employed;
		} else if (empCat.equals("self-employed")) {
			empCategory = EmploymentCategory.selfEmployed;
		} else if (empCat.equals("unemployed")) {
			empCategory = EmploymentCategory.unemployed;
		} else {
			empCategory = EmploymentCategory.other;
			System.out.println(empCat);
		}

		Gender genderCat;

		if (gender.equals("male")) {
			genderCat = Gender.m;
		} else {
			genderCat = Gender.w;
		}

		Person person = new Person(name, occupation, ageCategory, empCategory,
				taxId, isUs, genderCat);

		db.addPerson(person);
	}

	public List<Person> getPeople() {
		return db.getPeople();
	}

	public void saveToFile(File file) throws IOException {
		db.saveToFile(file);
	}

	public void loadFromFile(File file) throws IOException {
		db.loadFromFile(file);
	}

	public void removePerson(int index) {
		db.removePerson(index);
	}

	public void save() throws SQLException {
		db.save();
	}

	public void connect() throws Exception {
		db.connect();
	}

	public void load() throws SQLException {
		db.load();
	}

	public void disconnect() {
		db.disconnect();
	}

	public void configure(int port, String user, String password)
			throws Exception {
		db.configure(port, user, password);
	}
}
