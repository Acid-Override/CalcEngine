package com.playground.IsPrime;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
public class IsPrime {
    public static boolean isPrime(long number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i*i <= number; i++) {
            log.trace("{}", i);
            if (number % i == 0) {
                log.trace("{} is not prime", number);
                return false;
            }
        }
        log.trace("{} is prime", number);
        return true;
    }

    public static boolean isPrimeVirualThreads(int number) {
        if (number <= 1) {
            return false;
        }

        AtomicBoolean result = new AtomicBoolean(false);


//        Executor executor = Executors.newVirtualThreadPerTaskExecutor();

//            executor = new ThreadPoolExecutor(3, 10, 5L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2), threadFactory);
//            executor = new ThreadPoolExecutor(3, 10, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2), threadFactory);
//            executor.execute(() -> {
                Thread.startVirtualThread(() -> {
                log.trace("Thread={}", Thread.currentThread().threadId());
                result.set(isPrime(number));

                });
//            });







//        Thread thread = new Thread(threadGroup, () -> {
//            result.set(isPrime(number));
//        });
//        thread.start();
        return result.get();
    }

    public boolean isPrimeRecursive(int number) {
        return isPrimeRecursive(number, 2);
    }

    private boolean isPrimeRecursive(int number, int divisor) {

        if (number <= 2 && number >= 0) {
            return true;
        } else if (number % divisor == 0) {
            return false;
        } else if (divisor * divisor > number) {
            return true;
        }
        return isPrimeRecursive(number, divisor + 1);
    }
}
