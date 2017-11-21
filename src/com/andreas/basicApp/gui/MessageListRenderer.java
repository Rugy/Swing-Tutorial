package com.andreas.basicApp.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import com.andreas.basicApp.model.Message;

public class MessageListRenderer implements ListCellRenderer<Message> {

	private JPanel panel;
	private JLabel label;
	private Color normalColor;
	private Color selectedColor;

	public MessageListRenderer() {
		panel = new JPanel();
		label = new JLabel();

		selectedColor = new Color(210, 210, 255);
		normalColor = Color.WHITE;

		String imagePath = Utils.getImageFolder() + "ic_menu_info_details.png";
		label.setIcon(Utils.scaleIcon(Utils.createIcon(imagePath), 24, 24));
		label.setFont(Utils.createFont(
				"/com/andreas/basicApp/gui/fonts/CrimewaveBB.ttf").deriveFont(
				Font.PLAIN, 20));

		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel.add(label);
	}

	@Override
	public Component getListCellRendererComponent(
			JList<? extends Message> list, Message value, int index,
			boolean isSelected, boolean cellHasFocus) {

		Message message = value;
		label.setText(message.getTitle());

		panel.setBackground(cellHasFocus ? selectedColor : normalColor);

		if (list.getModel().getElementAt(0) == value && isSelected) {
			panel.setBackground(selectedColor);
		}

		return panel;
	}

}
