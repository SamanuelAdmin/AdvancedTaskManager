package com.taskmanager;

import java.text.DecimalFormat;
import javax.sound.sampled.BooleanControl;
import javax.swing.*;
import java.awt.*;
import java.io.File;


public class MainFrame extends JFrame {
    public static int updatingDelay = 1000; // (in ms)
    public static int[] windowSize = {500, 500};
    public static int[] mainBorders = {20, 30}; // padding X, padding Y (borders in window between all components of panel)
    public static ImageIcon mainProgramLogo = new ImageIcon("src/resources/logo.png");
    public static SystemManager systemManager = new SystemManager(); // main class for getting system (cpu and ram) information

    public MainPanel mainPanel = new MainPanel(); // all elements will be added to this panel
    public JLabel cpuInfo;
    public JLabel ramInfo;
    public GraphPanel cpuLoadGraph = new GraphPanel();
    public GraphPanel ramLoadGraph = new GraphPanel();
    public SerialWorker serialWorker = new SerialWorker(); // for using COM port (to send data)


    private static ImageIcon resizeIcon(ImageIcon icon, int width, int height) { // set new size to icons
        Image image = icon.getImage();
        Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }


    public int updateCpuInfo() {
        int cpuLoadData = (int) systemManager.getCpuUsage();
        this.cpuInfo.setText("CPU: " + cpuLoadData + "%");

        this.cpuLoadGraph.addNewValue(cpuLoadData);

        return cpuLoadData;
    }

    public int updateRamInfo() {
        int ramLoadData = (int) systemManager.getRamUsage();
        this.ramInfo.setText(
                "RAM: " + ramLoadData + "%" + String.format(
                        " (%s Gb / %s Gb)",
                        (float) (systemManager.getTotalRam() - systemManager.getAvailableRam()) / 1024 / 1024 / 1024,
                        (float) systemManager.getTotalRam() / 1024 / 1024 / 1024
                )
        );

        this.ramLoadGraph.addNewValue(ramLoadData);

        return ramLoadData;
    }

    public void updateAllData() { // updating cpu and ram info in text, graph and display
        int cpuInfo = updateCpuInfo();
        int ramInfo = updateRamInfo();

        if (this.serialWorker.serialPort != null) {
            if (this.serialWorker.serialPort.isOpen()) {
                this.serialWorker.send(String.format("{\"cpuLoadData\": %s, \"ramLoadData\": %s}\n", cpuInfo, ramInfo));
                return;
            }
        }

        this.serialWorker = new SerialWorker();
    }

    public MainFrame(String windowTitle) {
        // initing a frame (window)

        this.loadGraph(); // load both graph
        this.loadLayout();
        this.loadWindowElements();

        this.setTitle(windowTitle);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(windowSize[0], windowSize[1]);

        this.setIconImage(
                Toolkit.getDefaultToolkit().getImage(
                        new File("src/main/resources/logo.png").toString()
                )
        );

        // updating info thread (used to update info (CPU, RAM) and show statistic)
        new Timer(updatingDelay, e -> {
            this.updateAllData();

            SwingUtilities.invokeLater(() -> {
                try {
                    this.cpuLoadGraph.repaint(); // updating cpu graph
                    this.ramLoadGraph.repaint(); // updating ram graph
                } catch (Exception error) {
                    System.out.println(error.getMessage());
                }
            });
        }).start();

        this.setContentPane(this.mainPanel);
        this.setVisible(true);
    }

    public void loadGraph() {
        this.cpuLoadGraph.graphColor = Color.WHITE; // cpu color theme
        this.ramLoadGraph.graphColor = Color.YELLOW;  // ram color theme
    }

    public void loadLayout() {
        BoxLayout mainLayout = new BoxLayout(this.mainPanel, BoxLayout.PAGE_AXIS);

        this.mainPanel.setLayout(mainLayout);
    }

    public void loadWindowElements() {
        ImageIcon cpuLogo =  resizeIcon(
                new ImageIcon("src/main/resources/icons/cpu_logo.png"),
                30, 30
        );

        ImageIcon ramLogo =  resizeIcon(
                new ImageIcon("src/main/resources/icons/ram_logo.png"),
                40, 40
        );

        cpuInfo = new JLabel();
        cpuInfo.setFont(new Font("Arial", Font.BOLD, 16));
        cpuInfo.setIcon(cpuLogo);
        cpuInfo.setIconTextGap(5);
        cpuInfo.setAlignmentX(Component.CENTER_ALIGNMENT);

        ramInfo = new JLabel();
        ramInfo.setFont(new Font("Arial", Font.BOLD, 16));
        ramInfo.setIcon(ramLogo);
        ramInfo.setIconTextGap(5);
        ramInfo.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.mainPanel.add(cpuInfo);
        this.mainPanel.add(this.cpuLoadGraph);
        this.mainPanel.add(ramInfo);
        this.mainPanel.add(this.ramLoadGraph);
    }

}
