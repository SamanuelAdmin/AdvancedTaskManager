package com.taskmanager;

import java.lang.management.*;
import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;
import oshi.software.os.OperatingSystem;
import oshi.hardware.CentralProcessor;
import oshi.hardware.HardwareAbstractionLayer;


public class SystemManager {
    long[] prevTicks = new long[CentralProcessor.TickType.values().length];
    CentralProcessor processor;
    GlobalMemory memory;

    public SystemManager() {
        SystemInfo systemInfo = new SystemInfo();

        HardwareAbstractionLayer hardware = systemInfo.getHardware();
        this.processor = hardware.getProcessor();
        this.memory = hardware.getMemory();
    }

    public double getCpuUsage() { // get cpu usage in %
        double cpuLoad = this.processor.getSystemCpuLoadBetweenTicks( this.prevTicks ) * 100;
        this.prevTicks = this.processor.getSystemCpuLoadTicks();
        return cpuLoad;
    }

    public long getAvailableRam() {
        return this.memory.getAvailable(); // in bytes
    }

    public long getTotalRam() {
        return this.memory.getTotal(); // in bytes
    }

    public double getRamUsage() {
        return (1.0 - ((double) this.getAvailableRam() / this.getTotalRam())) * 100; // get ram usage in %
    }
}
