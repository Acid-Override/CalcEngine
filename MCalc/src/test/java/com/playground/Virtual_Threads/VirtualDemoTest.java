package com.playground.Virtual_Threads;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VirtualDemoTest {

    @Test
    void threadDemo() throws InterruptedException {
        VirtualDemo virtualDemo = new VirtualDemo();
        virtualDemo.threadDemo();
    }
}