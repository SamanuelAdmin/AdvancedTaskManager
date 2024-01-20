package com.taskmanager;

import javax.swing.*;

public class MainPanel extends JPanel {
    public MainPanel() {
        // adding borders (padding)
        this.setBorder(BorderFactory.createEmptyBorder(
                MainFrame.mainBorders[1], MainFrame.mainBorders[0],
                MainFrame.mainBorders[1] + 5, MainFrame.mainBorders[0]
        ));
    }
}
