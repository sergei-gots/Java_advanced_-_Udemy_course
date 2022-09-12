/*
 * Copyright (c) 2022.
 * To the Lesson 25 "Class java.util.concurrent.CountDownLatch".
 * To the video course "Java Advanced" by Neil Alishev (Udemy).
 */

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class L25_CountDownLatch {
    public static final int PROCESSORS_COUNT = 3;

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(PROCESSORS_COUNT);

        ExecutorService executorService = Executors.newFixedThreadPool(PROCESSORS_COUNT*2);
        int i=0;
        for(; i< PROCESSORS_COUNT; i++) {
            executorService.submit(new Processor(i+1, countDownLatch));
        }
        for(; i< 2*PROCESSORS_COUNT; i++) {
            executorService.submit(new AwaitedProcessor(i+1, countDownLatch));
        }

        executorService.shutdown();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("The main thread has been resumed.");
    }
}

class ProcessorWithLatch {
    final static int BASIC_SLEEP_INTERVAL = 1000;
    int id;
    CountDownLatch countDownLatch;
    public ProcessorWithLatch(int id, CountDownLatch countDownLatch) {
        this.id = id;
        this.countDownLatch = countDownLatch;
    }

    public void printInfo() {
        System.out.println("Thread of " + this.getClass().getName() + ": id = " + id + ", countDownLatch.getCount() = " + countDownLatch.getCount());
    }
}

class Processor extends ProcessorWithLatch implements Runnable {

    public Processor(int id, CountDownLatch countDownLatch) {
        super(id, countDownLatch);
    }

    @Override
    public void run() {

        printInfo();

        try {
            Thread.sleep(id * 3 * BASIC_SLEEP_INTERVAL);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        countDownLatch.countDown();

        //We will introduce this sleep-interval to show the moment at which the main thread will be unlatched.
        try {
            Thread.sleep(id * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        printInfo();
    }
}

class AwaitedProcessor   extends ProcessorWithLatch implements Runnable {

    public AwaitedProcessor(int id, CountDownLatch countDownLatch) {
            super(id, countDownLatch);
        }

        @Override
        public void run() {

            printInfo();

            try {
                Thread.sleep(id * 2 * BASIC_SLEEP_INTERVAL);
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            printInfo();
        }
}