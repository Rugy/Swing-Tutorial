package com.andreas.basicApp.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Database {

	private List<Person> people;
	private Connection con;

	private int port;
	private String user;
	private String password;

	public Database() {
		people = new ArrayList<>();
	}

	public void configure(int port, String user, String password)
			throws Exception {
		this.port = port;
		this.user = user;
		this.password = password;

		if (con != null) {
			disconnect();
		}
		connect();
	}

	public void connect() throws Exception {
		if (con != null && !con.isClosed()) {
			return;
		}

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new Exception("Driver not found");
		}
		String url = "jdbc:mysql://localhost:" + port + "/swingtest";
		con = DriverManager.getConnection(url, user, password);
	}

	public void disconnect() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void save() throws SQLException {

		if (con == null) {
			return;
		}

		String checkSql = "Select count(*) as count from people where id=?";
		PreparedStatement checkStatement = con.prepareStatement(checkSql);

		String insertSql = "insert into people (id, name, age, employmentStatus, tax_id, us_citizen, gender, occupation) values (?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement insertStatement = con.prepareStatement(insertSql);

		String updateSql = "update people set name=?, age=?, employmentStatus=?, tax_id=?, us_citizen=?, gender=?, occupation=? where id=?";
		PreparedStatement updateStatement = con.prepareStatement(updateSql);

		for (Person person : people) {
			int id = person.getId();
			String name = person.getName();
			String occ = person.getOccupation();
			AgeCategory ageCat = person.getAgeCat();
			EmploymentCategory empCat = person.getEmpCat();
			String tax = person.getTaxID();
			boolean usCitizen = person.isUsCitizen();
			Gender gender = person.getGender();

			checkStatement.setInt(1, id);
			ResultSet checkResult = checkStatement.executeQuery();
			checkResult.next();
			int count = checkResult.getInt(1);

			if (count == 0) {
				int col = 1;
				insertStatement.setInt(col++, id);
				insertStatement.setString(col++, name);
				insertStatement.setString(col++, ageCat.name());
				insertStatement.setString(col++, empCat.name());
				insertStatement.setString(col++, tax);
				insertStatement.setBoolean(col++, usCitizen);
				insertStatement.setString(col++, gender.name());
				insertStatement.setString(col++, occ);

				insertStatement.executeUpdate();
			} else {
				int col = 1;

				updateStatement.setString(col++, name);
				updateStatement.setString(col++, ageCat.name());
				updateStatement.setString(col++, empCat.name());
				updateStatement.setString(col++, tax);
				updateStatement.setBoolean(col++, usCitizen);
				updateStatement.setString(col++, gender.name());
				updateStatement.setString(col++, occ);
				updateStatement.setInt(col++, id);

				updateStatement.executeUpdate();
			}
		}

		updateStatement.close();
		insertStatement.close();
		checkStatement.close();
	}

	public void load() throws SQLException {

		if (con == null) {
			return;
		}

		people.clear();

		String sql = "Select id, name, age, employmentStatus, tax_id, us_citizen, gender, occupation from people order by name desc";
		Statement selectStatement = con.createStatement();
		ResultSet results = selectStatement.executeQuery(sql);

		while (results.next()) {
			int id = results.getInt("id");
			String name = results.getString("name");
			String ageCat = results.getString("age");
			String empCat = results.getString("employmentStatus");
			String tax = results.getString("tax_id");
			boolean isUs = results.getBoolean("us_citizen");
			String gender = results.getString("gender");
			String occ = results.getString("occupation");

			people.add(new Person(id, name, occ, AgeCategory.valueOf(ageCat),
					EmploymentCategory.valueOf(empCat), tax, isUs, Gender
							.valueOf(gender)));
		}

		results.close();
		selectStatement.close();
	}

	public void addPerson(Person person) {
		people.add(person);
	}

	public List<Person> getPeople() {
		return Collections.unmodifiableList(people);
	}

	public void saveToFile(File file) throws IOException {
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);

		Person[] persons = people.toArray(new Person[people.size()]);

		oos.writeObject(persons);

		oos.close();
	}

	public void loadFromFile(File file) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);

		try {
			Person[] persons = (Person[]) ois.readObject();
			people.clear();
			people.addAll(Arrays.asList(persons));

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		ois.close();
	}

	public void removePerson(int index) {
		people.remove(index);
	}
}
