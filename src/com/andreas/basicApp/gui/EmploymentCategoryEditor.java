package com.andreas.basicApp.gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import com.andreas.basicApp.model.EmploymentCategory;

public class EmploymentCategoryEditor extends AbstractCellEditor implements
		TableCellEditor {

	private static final long serialVersionUID = 1L;

	private JComboBox<EmploymentCategory> combo;

	public EmploymentCategoryEditor() {
		combo = new JComboBox<EmploymentCategory>(EmploymentCategory.values());
	}

	@Override
	public Object getCellEditorValue() {
		return combo.getSelectedItem();
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		combo.setSelectedItem(value);
		combo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				fireEditingStopped();
			}
		});

		return combo;
	}

	@Override
	public boolean isCellEditable(EventObject e) {
		return true;
	}

}
