/*
        * Copyright (c) 2022.
        * To the Lesson 22 "Patter Procuder-Consumer" of the video course "Java Advanced" by Neil Alishev (Udemy)
        */

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class L22_ProducerConsumerPattern {

    //Blocking queue as all the classses from the package java.util.concurrent is Thread safe
    //Adjusted to work with several threads
    static BlockingQueue <Integer> queue = new ArrayBlockingQueue <>(10);

    public static void main(String[] args) {
        Thread threadProducer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    produce();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread threadConsumer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    consume();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        threadProducer.start();
        threadConsumer.start();

        try {
            threadProducer.join();
            threadConsumer.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public static void produce() throws InterruptedException {
        Random random = new Random();
        while(true) {
            //put is intended to work with multiple threads
            queue.put(random.nextInt(100) );
        }
    }

    public static void consume() throws InterruptedException {
        Random random = new Random();
        while(true) {
            Thread.sleep(100);
            if(random.nextInt(10)==5) {
                //take is intended to work with multiple threads
                int nextItem = queue.take();
                System.out.println("nextItem = " + nextItem);
                System.out.println("queue.size() = " + queue.size());
            }
        }
    }
}
