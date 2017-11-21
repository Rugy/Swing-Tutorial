package com.andreas.basicApp;

import javax.swing.SwingUtilities;

import com.andreas.basicApp.gui.MainFrame;

public class SwingApp {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				runApp();
			}
		});

	}

	public static void runApp() {
		new MainFrame("Hello");
	}

}
