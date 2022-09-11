/*
 * Copyright (c) 2022.
 * To the Lesson 24 "Pattern Producer-Consumer. Part-II".
 * To the video course "Java Advanced" by Neil Alishev (Udemy).
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class L24_ProducerConsumerPattern_2 {

    public static void main(String[] args) {
        ProducerConsumer pc = new ProducerConsumer();
        Thread threadProducer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread threadConsumer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        threadProducer.start();
        threadConsumer.start();

        try {
            threadProducer.join();
            threadConsumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ProducerConsumer {

    //Let's assign here an object to be synchronized on
    //We will use not thread-safe interface Queue and class LinkedList
    private Queue<Integer> queue = new LinkedList<>();

    Random randomSleepInterval = new Random();
    //(note that we could synchronize on the this-object,
    //but here we will synchronize obviously on a special-created object):
    private final Object lock = new Object();
    //NOTE: Side objects that are used to be synchronized on
    //MUST BE constant objects (final) if they are not this-objects.

    private final int CAPACITY_LIMIT = 10;
    public void produce() throws InterruptedException {
        int value = 0;
        while(true) {
            synchronized (lock) {
                //Case when our queue is full:
                //we use while - not if - for safety.
                // We round once again through the while-contition
                // to be sure, that our queue has allowable number of items.
                while (queue.size() == CAPACITY_LIMIT) {
                    System.out.println("The queue is FULL - the PRODUCER will WAIT.");
                    lock.wait();
                }
                queue.offer(value++);
                lock.notify();
            }
            //This section (sleep for a random interval of time) is brought out the synchronized block
            //in order to provide the concurrency between producer and consumer threads
            Thread.sleep(randomSleepInterval.nextInt(1000));
        }
    }

    public void consume() throws InterruptedException {
        while(true) {
            synchronized (lock) {
                //Case when our queue is empty:
                while (queue.size() == 0) {
                    System.out.println("The queue is EMPTY - the CONSUMER will WAIT.");
                    lock.wait();
                }
                int value = queue.poll();

                System.out.println("The next taken value = " + value);
                System.out.println("The current queue.size() = " + queue.size());

                lock.notify();
            }
            //This section (sleep for a random interval of time) is brought out the synchronized block
            //in order to provide the concurrency between producer and consumer threads
            Thread.sleep(randomSleepInterval.nextInt(1000));
        }
    }
}
