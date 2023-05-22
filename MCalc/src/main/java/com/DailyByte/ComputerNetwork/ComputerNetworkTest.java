package com.DailyByte.ComputerNetwork;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComputerNetworkTest {

    @Test
    void computerNetworkSetup() {
        ComputerNetwork cnt = new ComputerNetwork();
        cnt.computerNetwork(new int[][] {{1, 0},{1, 0}});
    }
}