package com.ggit.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.ggit.simulation.Simulation;

public class MainPanel extends JPanel implements ActionListener {

    private final CustomTextArea jTextFieldStats;
    private final CustomButton resumeBtn;
    private final CustomButton pauseBtn;
    private final JPanel mapPanel;
    private Timer timer;
    private int gridY = 0;

    public MainPanel() {
        setLayout(new GridBagLayout());
        setBackground(CustomColors.backgroundColor);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(LayoutParams.TOP_MARGIN, LayoutParams.LEFT_MARGIN,
                LayoutParams.BOTTOM_MARGIN, LayoutParams.RIGHT_MARGIN);

        pauseBtn = new CustomButton("Zatrzymaj grę", true, gbc, this, gridY++);
        resumeBtn = new CustomButton("Wznów grę", false, gbc, this, gridY++);

        jTextFieldStats = new CustomTextArea("Statystyki...",
                LayoutParams.TEXT_AREA_ROWS, LayoutParams.TEXT_AREA_COLS,
                gbc, this, gridY++);

        mapPanel = new MapPanel(Simulation.getWorldMap());
        mapPanel.setBackground(CustomColors.backgroundColor);
        mapPanel.setBorder(null);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = gridY + 1;
        gbc.ipadx = LayoutParams.MAP_SCALE * Simulation.getWorldMap().getWidth();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        add(mapPanel, gbc);

        startGame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == resumeBtn) resumeGame();
        else if (source == pauseBtn) pauseGame();
        else onNextDay();
    }

    private void startGame() {
        timer = new Timer(1000, this);
        renderMap();
        timer.start();
    }

    private void resumeGame() {
        setEnabledForButtons(true);
        timer.start();
    }

    private void pauseGame() {
        setEnabledForButtons(false);
        timer.stop();
    }

    private void onNextDay() {
        jTextFieldStats.setText(Simulation.getWorldMap().getStatistics().toString());
        renderMap();
    }

    private void setEnabledForButtons(boolean onStart) {
        pauseBtn.setEnabled(onStart);
        resumeBtn.setEnabled(!onStart);
    }

    private void renderMap() {
        if (Simulation.getWorldMap().getAnimals().isEmpty()) {
            timer.stop();
            return;
        }
        Simulation.simulateDay();
        mapPanel.repaint();
    }
}
