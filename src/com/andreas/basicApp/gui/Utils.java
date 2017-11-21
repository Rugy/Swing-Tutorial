package com.andreas.basicApp.gui;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.swing.ImageIcon;

public class Utils {

	public static String getImageFolder() {
		return "/com/andreas/basicApp/gui/images/";
	}

	public static String getFileExtension(String name) {
		int pointIndex = name.lastIndexOf(".");

		if (pointIndex == -1) {
			return null;
		}

		if (pointIndex == name.length() - 1) {
			return null;
		}

		return name.substring(pointIndex + 1, name.length());
	}

	public static ImageIcon createIcon(String path) {
		URL url = System.class.getResource(path);

		if (url == null) {
			System.out.println("Unable to load image: " + path);
		}

		ImageIcon icon = new ImageIcon(url);
		return icon;
	}

	public static ImageIcon scaleIcon(ImageIcon icon, int height, int width) {
		ImageIcon rescaledIcon = new ImageIcon(icon.getImage()
				.getScaledInstance(height, width, Image.SCALE_DEFAULT));

		return rescaledIcon;
	}

	public static Font createFont(String path) {
		URL url = System.class.getResource(path);

		if (url == null) {
			System.out.println("Unable to load font: " + path);
		}

		Font font = null;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, url.openStream());
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}

		return font;
	}

}
