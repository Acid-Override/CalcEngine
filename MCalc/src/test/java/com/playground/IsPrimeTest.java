package com.playground;

import com.playground.IsPrime.IsPrime;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class IsPrimeTest {

    long startTime;
    long finishTime;

    @BeforeEach
    void setUp() {
        startTime = System.nanoTime();
    }
    @AfterEach
    void tearDown() {
        finishTime = System.nanoTime();
        long time = finishTime - startTime;
        log.info("Time : {}", TimeUnit.NANOSECONDS.toMillis(time));
    }

    @Test
    void isPrime() {
        assertTrue(IsPrime.isPrime(2));
    }

    @Test
    void testIsPrime() {
        assertTrue(IsPrime.isPrime(7));
    }

    @Test
    void testIsPrimewithNonPrime() {
        assertFalse(IsPrime.isPrime(14));
    }

    @Test
    void testWithLargePrimeNumber() {
        assertTrue(IsPrime.isPrime(1_000_000_007));
    }

    @Test
    void isPrimeRecursiveTrue() {
        IsPrime isPrime = new IsPrime();
        boolean result = isPrime.isPrimeRecursive(7);
        log.info("7 is prime={}", result);
        assertTrue(result);
    }

    @Test
    void isPrimeRecursiveFalse() {
        IsPrime isPrime = new IsPrime();
        boolean result = isPrime.isPrimeRecursive(8);
        log.info("8 is prime={}", result);
        assertFalse(result);
    }



    /** WINNER ***********************************************************************
     *
     * 10_000_007L
     * [INFO ] 2025-03-25 14:44:00.059 [main] IsPrimeTest - # of Primes Found=664579
     * [INFO ] 2025-03-25 14:44:00.062 [main] IsPrimeTest - Time : 2094
     *
     * 100_000_007L
     * [INFO ] 2025-03-25 13:43:07.483 [main] IsPrimeTest - # of Primes Found=5761456
     * [INFO ] 2025-03-25 13:43:07.486 [main] IsPrimeTest - Time : 24851
     */
    @Test
    void isPrimeVirtualThreadsGoogle() {
        long limit = 10_000_007L;
        long numThreads = 10;
        AtomicInteger primeCount = new AtomicInteger(0);

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            List<Runnable> tasks = new ArrayList<>();
            long rangeSize = limit / numThreads;

            for (long i = 0; i < numThreads; i++) {
                long start = i * rangeSize + 1;
                long end = (i == numThreads - 1) ? limit : (i + 1) * rangeSize;

                tasks.add(() -> {
                    for (long number = start; number <= end; number++) {
                        if (IsPrime.isPrime(number)) {
                            primeCount.incrementAndGet();
                        }
                    }
                });
            }
            tasks.forEach(executor::execute);
        }
        log.info("# of Primes Found={}", primeCount.get());
    }

    /**TIED FOR FIRST
     *
     * 10_000_007L
     * [INFO ] 2025-03-25 10:33:22.795 [main] IsPrimeTest - # of Primes Found=664579
     * [INFO ] 2025-03-25 10:33:22.803 [main] IsPrimeTest - Time : 2203
     *
     * 100_000_007L
     * [INFO ] 2025-03-25 11:24:42.223 [main] IsPrimeTest - # of Primes Found=5_761_456
     * [INFO ] 2025-03-25 11:24:42.229 [main] IsPrimeTest - Time : 34634
     *
     * 100_000_007L
     * note: removed .parallel()
     * [INFO ] 2025-03-25 13:08:24.685 [main] IsPrimeTest - # of Primes Found=5761456
     * [INFO ] 2025-03-25 13:08:24.690 [main] IsPrimeTest - Time : 197522
     *
     * [INFO ] 2025-03-25 12:04:50.701 [main] IsPrimeTest - # of Primes Found=50_847_535
     * [INFO ] 2025-03-25 12:04:50.719 [main] IsPrimeTest - Time : 928921 (15 min)
     */
    @Test
    void isPrimeVirtualThreadsIterate() {
        AtomicLong count = new AtomicLong();
        Thread vThread = Thread.ofVirtual().start(() -> {
            count.set(LongStream.rangeClosed(0, 10_000_007L).parallel().filter(IsPrime::isPrime).count());
        });
        try {
            vThread.join();
            log.info("# of Primes Found={}", count.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /** SECOND PLACE
     *
     * 10_000_007L
     * [INFO ] 2025-03-25 10:30:09.468 [main] IsPrimeTest - # of Primes Found=664579
     * [INFO ] 2025-03-25 10:30:09.480 [main] IsPrimeTest - Time : 7737
     */
    @Test
    void findPrimes() {
        List<Long> listOfPrimes = new ArrayList();
        for (long i = 2; i <= 10_000_007L; i++) {
            if (IsPrime.isPrime(i)) {
                listOfPrimes.add(i);
            }
        }
        log.info("# of Primes Found={}", listOfPrimes.size());
//        log.info("List of Primes={}", listOfPrimes);
    }

    /**
     *
     *  [INFO ] 2025-03-25 09:05:27.381 [main] IsPrimeTest - # of Primes Found=664579
     * [INFO ] 2025-03-25 09:05:27.385 [main] IsPrimeTest - Time : 36712
     */
    @Test
    void isPrimeCompletableFuture() {
        long count =
                LongStream.rangeClosed(0, 10_000_007L)
                        .parallel()
                        .mapToObj(i -> CompletableFuture.supplyAsync(() -> IsPrime.isPrime(i)).thenApplyAsync(isPrime -> {
                            if (isPrime) {
                                return i;
                            }
                            return null;
                        }))
                        .map(CompletableFuture::join)
                        .filter(Objects::nonNull)
                        .count();

        log.info("# of Primes Found={}", count);
    }














    @Test
    void findPrimesParallel() {
        long count = LongStream.rangeClosed(0, 10_000_007L).parallel().filter(IsPrime::isPrime).count();
        log.info("# of Primes Found={}", count);
    }

    @Test
    void isPrimeVirtualThreads() {
        long count = IntStream.rangeClosed(0,1_007).filter(IsPrime::isPrimeVirualThreads).count();
        log.info("# of Primes Found={}", count);
    }



    @Test
    void isPrimeExecutor() {
        Executor executor = Executors.newVirtualThreadPerTaskExecutor();
        ThreadFactory threadFactory = Executors.defaultThreadFactory();

        AtomicLong count = new AtomicLong();


        executor = new ThreadPoolExecutor(3, 10, 5L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2), threadFactory);

        executor.execute(() -> {
            count.set(IntStream.rangeClosed(0, 10_000_007).filter(IsPrime::isPrime).count());
        });

        log.info("# of Primes Found={}", count.get());

    }



}