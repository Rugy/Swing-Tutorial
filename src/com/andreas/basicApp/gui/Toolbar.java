package com.andreas.basicApp.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.event.EventListenerList;

public class Toolbar extends JToolBar implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton saveButton;
	private JButton refreshButton;
	private ToolbarListener toolbarListener;
	private EventListenerList listeners = new EventListenerList();

	public Toolbar() {
		saveButton = new JButton();
		refreshButton = new JButton();

		saveButton.addActionListener(this);
		refreshButton.addActionListener(this);

		setFloatable(false);

		String imageUrl = Utils.getImageFolder();

		int imageSize = 24;

		saveButton.setToolTipText("Save");
		ImageIcon saveIcon = Utils.createIcon(imageUrl + "ic_menu_save.png");
		saveButton.setIcon(Utils.scaleIcon(saveIcon, imageSize, imageSize));

		refreshButton.setToolTipText("Refresh");
		ImageIcon searchIcon = Utils
				.createIcon(imageUrl + "ic_menu_search.png");
		refreshButton
				.setIcon(Utils.scaleIcon(searchIcon, imageSize, imageSize));

		add(saveButton);
		add(refreshButton);
	}

	public void setToolbarListener(ToolbarListener stringListener) {
		listeners.add(ToolbarListener.class, stringListener);
		this.toolbarListener = stringListener;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton) e.getSource();
		if (clicked == saveButton) {
			if (toolbarListener != null) {
				toolbarListener.saveEventOccured();
			}
		} else if (clicked == refreshButton) {
			if (toolbarListener != null) {
				toolbarListener.refreshEventOccured();
			}
		}
	}

}
