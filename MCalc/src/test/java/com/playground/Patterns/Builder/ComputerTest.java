package com.playground.Patterns.Builder;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class ComputerTest {

    @Test
    void computerTest() {
        Computer computer = new Computer.ComputerBuilder()
                .cpu("i5")
                .ram("16GB")
                .hdd("1TB")
                .gpu("RTX 2060")
                .hasBluetooth(true)
                .hasWifi(true)
                .build();

        log.info("Computer={}", computer);

        assertEquals("i5", computer.getCpu());
        assertEquals("16GB", computer.getRam());
        assertEquals("1TB", computer.getHdd());
        assertEquals("RTX 2060", computer.getGpu());
        assertTrue(computer.isHasBluetooth());
        assertTrue(computer.isHasWifi());

    }

}