package com.ggit.gui;

import javax.swing.*;
import java.awt.BorderLayout;

public class SimulatorFrame extends JFrame {
	public SimulatorFrame() {
		super("Game of Life");
		add(new JScrollPane(new MainPanel()), BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
