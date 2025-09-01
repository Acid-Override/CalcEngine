package com.playground.Virtual_Threads.Claude;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

@Slf4j
public class VirtualThreadsDemo {

    private static final int TASK_COUNT = 10_000;
    private static final int SLEEP_DURATION_MS = 100;

    public static void main(String[] args) throws Exception {
        log.info("Java " + Runtime.version() + " Virtual Threads Demo");
        log.info("======================================");

        // Demo with platform threads
        runPlatformThreadsDemo();

        // Demo with virtual threads
        runVirtualThreadsDemo();

        // Demo showing thread creation performance
        compareThreadCreationPerformance();
    }

    private static void runPlatformThreadsDemo() throws Exception {
        log.info("\nRunning demo with platform threads...");

        Instant start = Instant.now();

        try (ExecutorService executor = Executors.newFixedThreadPool(200)) {
            CountDownLatch latch = new CountDownLatch(TASK_COUNT);
            AtomicInteger activeThreads = new AtomicInteger(0);
            AtomicInteger maxActiveThreads = new AtomicInteger(0);

            for (int i = 0; i < TASK_COUNT; i++) {
                final int taskId = i;
                executor.submit(() -> {
                    try {
                        int current = activeThreads.incrementAndGet();
                        maxActiveThreads.updateAndGet(max -> Math.max(max, current));

                        // Simulate some blocking work (e.g., network I/O, DB operation)
                        Thread.sleep(SLEEP_DURATION_MS);

                        activeThreads.decrementAndGet();
                        return taskId;
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return -1;
                    } finally {
                        latch.countDown();
                    }
                });
            }

            latch.await();

            Instant end = Instant.now();
            Duration duration = Duration.between(start, end);

            log.info("Platform threads execution completed.");
            log.info("Tasks: " + TASK_COUNT);
            log.info("Max concurrent active threads: " + maxActiveThreads.get());
            log.info("Total execution time: " + duration.toMillis() + "ms");
        }
    }

    private static void runVirtualThreadsDemo() throws Exception {
        log.info("\nRunning demo with virtual threads...");

        Instant start = Instant.now();

        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            CountDownLatch latch = new CountDownLatch(TASK_COUNT);
            AtomicInteger activeThreads = new AtomicInteger(0);
            AtomicInteger maxActiveThreads = new AtomicInteger(0);

            for (int i = 0; i < TASK_COUNT; i++) {
                final int taskId = i;
                executor.submit(() -> {
                    try {
                        int current = activeThreads.incrementAndGet();
                        maxActiveThreads.updateAndGet(max -> Math.max(max, current));

                        // Simulate some blocking work (e.g., network I/O, DB operation)
                        Thread.sleep(SLEEP_DURATION_MS);

                        activeThreads.decrementAndGet();
                        return taskId;
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return -1;
                    } finally {
                        latch.countDown();
                    }
                });
            }

            latch.await();

            Instant end = Instant.now();
            Duration duration = Duration.between(start, end);

            log.info("Virtual threads execution completed.");
            log.info("Tasks: " + TASK_COUNT);
            log.info("Max concurrent active threads: " + maxActiveThreads.get());
            log.info("Total execution time: " + duration.toMillis() + "ms");
        }
    }

    private static void compareThreadCreationPerformance() {
        log.info("\nComparing thread creation performance...");

        // Create 100,000 platform threads
        Instant platformStart = Instant.now();
        AtomicInteger platformCounter = new AtomicInteger();

        try {
            IntStream.range(0, 100_000)
                    .forEach(i -> {
                        try {
                            Thread thread = new Thread(() -> {
                                platformCounter.incrementAndGet();
                            });
                            thread.start();
                            thread.join();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    });
        } catch (OutOfMemoryError e) {
            log.warn("Platform threads creation failed with OutOfMemoryError after {} threads", 
                    platformCounter.get());
        }

        Instant platformEnd = Instant.now();
        Duration platformDuration = Duration.between(platformStart, platformEnd);

        // Create 100,000 virtual threads
        Instant virtualStart = Instant.now();
        AtomicInteger virtualCounter = new AtomicInteger();

        IntStream.range(0, 100_000)
                .forEach(i -> {
                    try {
                        Thread thread = Thread.startVirtualThread(() -> {
                            virtualCounter.incrementAndGet();
                        });
                        thread.join();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });

        Instant virtualEnd = Instant.now();
        Duration virtualDuration = Duration.between(virtualStart, virtualEnd);

        log.info("Platform threads created: " + platformCounter.get());
        log.info("Platform threads creation time: " + platformDuration.toMillis() + "ms");
        log.info("Virtual threads created: " + virtualCounter.get());
        log.info("Virtual threads creation time: " + virtualDuration.toMillis() + "ms");
        log.info("Virtual threads are approximately {}x faster to create and start.",
                String.format("%.2f", (double) platformDuration.toMillis() / virtualDuration.toMillis()));
    }
}