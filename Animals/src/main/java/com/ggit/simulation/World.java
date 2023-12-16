package com.ggit.simulation;

import com.ggit.gui.SimulatorFrame;

import javax.swing.*;

public class World {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(SimulatorFrame::new);
    }
}
