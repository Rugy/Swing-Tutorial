package com.andreas.basicApp.gui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;

public class ServerTreeCellRenderer implements TreeCellRenderer {

	private JCheckBox leafRenderer;
	private DefaultTreeCellRenderer nonLeafRenderer;
	private Color textForeground;
	private Color textBackground;
	private Color selectionForeground;
	private Color selectionBackground;

	public ServerTreeCellRenderer() {
		leafRenderer = new JCheckBox();
		nonLeafRenderer = new DefaultTreeCellRenderer();

		String imageUrl = Utils.getImageFolder();

		int imageSize = 20;

		nonLeafRenderer.setLeafIcon(Utils.scaleIcon(
				Utils.createIcon(imageUrl + "ic_menu_preferences.png"),
				imageSize, imageSize));
		nonLeafRenderer.setOpenIcon(Utils.scaleIcon(
				Utils.createIcon(imageUrl + "expander_ic_minimized.9.png"),
				imageSize, imageSize));
		nonLeafRenderer.setClosedIcon(Utils.scaleIcon(
				Utils.createIcon(imageUrl + "ic_btn_search_go.png"), imageSize,
				imageSize));

		textForeground = UIManager.getColor("Tree.textForeground");
		textBackground = UIManager.getColor("Tree.textBackground");
		selectionForeground = UIManager.getColor("Tree.selectionForeground");
		selectionBackground = UIManager.getColor("Tree.selectionBackground");
	}

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean selected, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {

		if (leaf) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
			ServerInfo nodeInfo = (ServerInfo) node.getUserObject();

			if (selected) {
				leafRenderer.setBackground(selectionBackground);
				leafRenderer.setForeground(selectionForeground);
			} else {
				leafRenderer.setBackground(textBackground);
				leafRenderer.setForeground(textForeground);
			}

			leafRenderer.setText(nodeInfo.toString());
			leafRenderer.setSelected(nodeInfo.isChecked());
			return leafRenderer;
		}

		return nonLeafRenderer.getTreeCellRendererComponent(tree, value,
				selected, expanded, leaf, row, hasFocus);
	}
}
