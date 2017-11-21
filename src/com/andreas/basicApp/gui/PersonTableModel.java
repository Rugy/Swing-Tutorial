package com.andreas.basicApp.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.andreas.basicApp.model.EmploymentCategory;
import com.andreas.basicApp.model.Person;

public class PersonTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	public List<Person> db;
	private String[] colNames = { "ID", "Name,", "Occupation", "Age Category",
			"Employment Category", "Us Citizen", "Tax ID" };

	public PersonTableModel() {
	}

	@Override
	public String getColumnName(int column) {
		return colNames[column];
	}

	@Override
	public boolean isCellEditable(int row, int col) {

		switch (col) {
		case 2:
			return true;
		case 4:
			return true;
		case 5:
			return true;
		default:
			return false;
		}

	}

	@Override
	public void setValueAt(Object value, int row, int col) {
		if (db == null) {
			return;
		}

		Person person = db.get(row);

		switch (col) {
		case 2:
			person.setOccupation((String) value);
			break;
		case 4:
			person.setEmpCat((EmploymentCategory) value);
			break;
		case 5:
			person.setUsCitizen((boolean) value);
			break;
		default:
			return;
		}
	}

	public void setData(List<Person> db) {
		this.db = db;
	}

	@Override
	public int getColumnCount() {
		return 7;
	}

	@Override
	public int getRowCount() {
		return db.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Person person = db.get(row);

		switch (col) {
		case 0:
			return person.getId();
		case 1:
			return person.getName();
		case 2:
			return person.getOccupation();
		case 3:
			return person.getAgeCat();
		case 4:
			return person.getEmpCat();
		case 5:
			return person.isUsCitizen();
		case 6:
			return person.getTaxID();
		default:
			return null;
		}

	}

	@Override
	public Class<?> getColumnClass(int col) {

		switch (col) {
		case 0:
			return Integer.class;
		case 1:
			return String.class;
		case 2:
			return String.class;
		case 3:
			return String.class;
		case 4:
			return EmploymentCategory.class;
		case 5:
			return Boolean.class;
		case 6:
			return String.class;
		default:
			return null;
		}

	}
}
