package com.ggit.gui;

import javax.swing.JButton;
import java.awt.GridBagConstraints;

public class CustomButton extends JButton {
	public CustomButton(String text, boolean enabled, GridBagConstraints gbc, MainPanel panel, int gridY) {
		super(text);
		addActionListener(panel);
		setEnabled(enabled);
		gbc.gridx = 0;
		gbc.gridy = gridY;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(this, gbc);
	}
}
