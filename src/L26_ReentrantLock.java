/*
 * Copyright (c) 2022.
 * To the Lesson 26 "Class java.util.concurrent.locks.ReentrantLock".
 * To the video course "Java Advanced" by Neil Alishev (Udemy).
 */

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class L26_ReentrantLock {

    static final int THREADS_PAIRS_COUNT = 10;
    public static void main(String[] args) throws InterruptedException {
        Task task = new Task();

        ExecutorService executorService = Executors.newFixedThreadPool(THREADS_PAIRS_COUNT);
        for (int i =0; i<THREADS_PAIRS_COUNT; i++ ) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    task.threadWithLock();
                }
            });
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    task.threadWithoutLock();
                }
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.HOURS);
        task.showCounter();
    }
}

class Task {
    static final int GOAL_VALUE = 10000000;
    private int counterWithLock = 0;
    private int counterWithoutLock = 0;
    private Lock lock = new ReentrantLock();

    public void threadWithLock() {
        lock.lock();
        for(int i = 0; i<GOAL_VALUE; i++) {
            counterWithLock++;
        }
        lock.unlock();
    }

    public void threadWithoutLock() {
        for(int i = 0; i<GOAL_VALUE; i++) {
            counterWithoutLock++;
        }
    }

    public void showCounter() {
        System.out.println("counter (with lock)    = " + counterWithLock);
        System.out.println("counter (without lock) = " + counterWithoutLock);
    }
}