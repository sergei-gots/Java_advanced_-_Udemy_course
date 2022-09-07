/*
 * * Copyright (c) 2022.
 * To the Lesson 23 "Pattern Producer-Consumer. 
 *                      Methods Object.wait() and 
 *                              Object.notify()" 
 * To the video course "Java Advanced" by Neil Alishev (Udemy).
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class L23_WaitNotify {

    public static void main(String[] args) {

        WaitAndNotify waitAndNotify = new WaitAndNotify();

        Thread threadProduce = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    waitAndNotify.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread threadConsume1 = new Thread(new Runnable() {
           @Override
           public void run() {
               try {
                   waitAndNotify.consume(0);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        });

        List<Thread> consumers = new ArrayList<>();
        for (int i = 0; i<5; i++) {
            int finalI = i+1;
            consumers.add(new Thread(new Runnable() {
                 @Override
                 public void run() {
                     try {
                         waitAndNotify.consume(finalI);
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                 }
             }));
        }

        threadProduce.start();
        threadConsume1.start();

        for (Thread consumer:
             consumers) {

            consumer.start();


        }

        try {
            threadProduce.join();
            threadConsume1.join();
            for (Thread consumer:
                    consumers) {
                    consumer.join();
            }
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }


}

class WaitAndNotify {
    public void consume(int i) throws InterruptedException {
        synchronized (this) {
            System.out.println("CONSUMER> this = " + this);
            System.out.println("CONSUMER> Thread-" + i + " started.");
            wait();
            System.out.println("CONSUMER> Thread-" + i + " resumed (notified)");
        }
    }

    public void produce() throws InterruptedException {

        Scanner scanner = new Scanner(System.in);

        synchronized (this) {
            System.out.println("PRODUCER> this = " + this);
            System.out.println("PRODUCER> Waiting for \"Enter\"-key pressed...");
            scanner.nextLine();
            System.out.println("PRODUCER> \"Enter\"-key is pressed.");
            //Notifies only one thread went to the waiting state as first
            System.out.println("PRODUCER> Method \"notify()\" will be called.");
            notify();

            //Notifies only one thread went to the waiting state as next
            System.out.println("PRODUCER> Method \"notify()\" will be called.");
            notify();
            Thread.sleep(5000);
            //We can call the method "notifyAll().
            // It notifies all the threads which are in the waiting state.
            System.out.println("PRODUCER> Method \"notifyAll()\" will be called.");
            notifyAll();
        }
    }
}