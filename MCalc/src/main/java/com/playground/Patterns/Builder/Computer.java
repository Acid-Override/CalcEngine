package com.playground.Patterns.Builder;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ToString
@Getter
public class Computer {
    private String cpu;
    private String ram;
    private String hdd;
    private String gpu;
    private boolean hasBluetooth;
    private boolean hasWifi;

    public Computer(String cpu, String ram, String hdd, String gpu, boolean hasBluetooth, boolean hasWifi) {
        this.cpu = cpu;
        this.ram = ram;
        this.hdd = hdd;
        this.gpu = gpu;
        this.hasBluetooth = hasBluetooth;
        this.hasWifi = hasWifi;
    }

    public static class ComputerBuilder {
        private String cpu;
        private String ram;
        private String hdd;
        private String gpu;
        private boolean hasBluetooth;
        private boolean hasWifi;

        public ComputerBuilder cpu(String cpu) {
            this.cpu = cpu;
            return this;
        }

        public ComputerBuilder ram(String ram) {
            this.ram = ram;
            return this;
        }

        public ComputerBuilder hdd(String hdd) {
            this.hdd = hdd;
            return this;
        }

        public ComputerBuilder gpu(String gpu) {
            this.gpu = gpu;
            return this;
        }

        public ComputerBuilder hasBluetooth(boolean hasBluetooth) {
            this.hasBluetooth = hasBluetooth;
            return this;
        }

        public ComputerBuilder hasWifi(boolean hasWifi) {
            this.hasWifi = hasWifi;
            return this;
        }

        public Computer build() {
            return new Computer(cpu, ram, hdd, gpu, hasBluetooth, hasWifi);
        }
    }
}
