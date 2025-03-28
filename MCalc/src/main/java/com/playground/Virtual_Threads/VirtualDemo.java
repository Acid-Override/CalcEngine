package com.playground.Virtual_Threads;

import lombok.SneakyThrows;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

public class VirtualDemo {

    Set<String> poolNames = ConcurrentHashMap.newKeySet();
    Set<String> pThreadNames = ConcurrentHashMap.newKeySet();

    public void threadDemo() throws InterruptedException {
        List<Thread> threads = IntStream.range(0, 1000)
                .mapToObj(i -> Thread.ofVirtual()
                        .unstarted(
                                () -> {
                                    String poolName = Thread.currentThread().getThreadGroup().getName();
                                    poolNames.add(poolName);
                                    String threadName = Thread.currentThread().getName();
                                    pThreadNames.add(threadName);
                                }
                        )
                ).toList();

        Instant begin = Instant.now();
        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();

            Instant end = Instant.now();
            System.out.println("Took " + (end.toEpochMilli() - begin.toEpochMilli()) + "ms");
        }
    }
}
