package com.taskmanager;

import com.fazecast.jSerialComm.SerialPort;

import java.io.IOException;
import java.nio.Buffer;


public class SerialWorker {
    SerialPort serialPort; // main COM port obj

    public SerialWorker() {
        // getting all COM ports
        SerialPort[] serialPorts = SerialPort.getCommPorts();
        System.out.println("Count of founded COM ports: " + serialPorts.length);

        if (serialPorts.length > 0) { // if something found
            this.serialPort = serialPorts[0]; // getting first port
            System.out.println("Port opened: " + this.serialPort.openPort()); // and opening it (YOU NEED ADMINS RIGHTS FOR IT)

            this.serialPort.setBaudRate(9600); // will be same with arduino code
            this.serialPort.setNumDataBits(8);
            this.serialPort.setNumStopBits(1);
            this.serialPort.setParity(SerialPort.NO_PARITY);
        }
    }

    public void send(String json) {
        if (this.serialPort.isOpen()) { // if we can use this port
            byte[] bytesToSend = json.getBytes();

            try {
                this.serialPort.getOutputStream().write(bytesToSend);
                this.serialPort.getOutputStream().flush(); // sending data
            } catch (Exception error) {
                System.out.println(error.getMessage());
            }
        }
    }
}
