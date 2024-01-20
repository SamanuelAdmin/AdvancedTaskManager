package com.taskmanager;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import com.formdev.flatlaf.*;


public class Main {
    public static String programTitle = "Task manager"; // title of main window

    public static void setTheme() {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        setTheme(); // set dark theme

        MainFrame mainFrame = new MainFrame(programTitle); // creating and starting window
    }
}