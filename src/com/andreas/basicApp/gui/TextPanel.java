package com.andreas.basicApp.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TextPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextArea textArea;

	public TextPanel() {
		textArea = new JTextArea();

		textArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		textArea.setFont(new Font("Serif", Font.PLAIN, 20));

		setPreferredSize(new Dimension(400, 400));
		setLayout(new BorderLayout());

		add(new JScrollPane(textArea), BorderLayout.CENTER);
	}

	public void appendText(String text) {
		textArea.append(text);
	}

	public void setText(String text) {
		textArea.setText(text);
	}
}
