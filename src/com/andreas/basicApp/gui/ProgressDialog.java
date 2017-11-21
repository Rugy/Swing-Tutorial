package com.andreas.basicApp.gui;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

public class ProgressDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JButton cancelButton;
	private JProgressBar progressBar;
	private ProgressDialogListener progressDialogListener;

	public ProgressDialog(Window parent, String title) {
		super(parent, title, ModalityType.APPLICATION_MODAL);

		cancelButton = new JButton("Cancel");
		progressBar = new JProgressBar();

		setLayout(new FlowLayout());

		Dimension size = cancelButton.getPreferredSize();
		size.width = 400;
		progressBar.setMaximum(10);
		progressBar.setPreferredSize(size);
		progressBar.setStringPainted(true);
		progressBar.setString("Retrieving messages...");

		add(progressBar);
		add(cancelButton);

		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (progressDialogListener != null) {
					progressDialogListener.progressCancelled();
				}
			}
		});

		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				if (progressDialogListener != null) {
					progressDialogListener.progressCancelled();
				}
			}
		});

		pack();

		setLocationRelativeTo(parent);
	}

	public void setMaximum(int value) {
		progressBar.setMaximum(value);
	}

	public void setValue(int value) {
		int progress = 100 * value / progressBar.getMaximum();
		progressBar.setString(String.format("%d%% complete", progress));
		progressBar.setValue(value);
	}

	@Override
	public void setVisible(final boolean visible) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				if (visible == false) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else {
					progressBar.setValue(0);
					progressBar.setString("0% complete");
				}

				if (visible) {
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				} else {
					setCursor(Cursor.getDefaultCursor());
				}

				ProgressDialog.super.setVisible(visible);
			}
		});
	}

	public void setProgressDialogListener(ProgressDialogListener listener) {
		this.progressDialogListener = listener;
	}

}
