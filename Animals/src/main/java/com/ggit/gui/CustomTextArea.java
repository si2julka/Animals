package com.ggit.gui;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.CompoundBorder;
import java.awt.Font;
import java.awt.GridBagConstraints;

public class CustomTextArea extends JTextArea {
	public CustomTextArea(String text, int rows, int cols, GridBagConstraints gbc, JPanel panel, int gridY) {
		super(text, rows, cols);
		setFont(new Font("Dialog", Font.BOLD, 12));
		setBackground(CustomColors.textBackgroundColor);
		setBorder(new CompoundBorder(
			BorderFactory.createLineBorder(CustomColors.borderColor, 2),
			BorderFactory.createEmptyBorder(LayoutParams.TOP_MARGIN,
				LayoutParams.LEFT_MARGIN, 0, 0)));
		gbc.gridx = 0;
		gbc.gridy = gridY;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(this, gbc);
	}
}
