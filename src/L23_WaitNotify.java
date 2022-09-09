/*
 * * Copyright (c) 2022.
 * To the Lesson 23 "Pattern Producer-Consumer. 
 *                      Methods Object.wait() and 
 *                              Object.notify()" 
 * To the video course "Java Advanced" by Neil Alishev (Udemy).
 *
 *      Wait-and-Notify protocol is a low-level protocol of inter-thread coordination.
 *      Methods Wait-and-Notify are linked to the Object-instance
 *      Note that when you synchronize your block on another object-instance.
 *      By default Wait-and-Notify are linked to current object-instance (this).
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class L23_WaitNotify {

    public final static int CONSUMERS_IN_ARRAY_COUNT = 10;
    public static void main(String[] args) {

        WaitAndNotify waitAndNotify = new WaitAndNotify();

        Thread threadProducer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    waitAndNotify.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        Thread threadConsumer0 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    waitAndNotify.consume(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread threadConsumerWaiting10sec = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    waitAndNotify.consume(10000, true);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        List<Thread> consumers = new ArrayList<>();
        for (int i = 0; i<CONSUMERS_IN_ARRAY_COUNT; i++) {
            int index = i+1;
            consumers.add(new Thread(new Runnable() {
                 @Override
                 public void run() {
                     try {
                         waitAndNotify.consume(index);
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                 }
             }));
        }

        System.out.println("**** List of internal thread names **** ");
        System.out.println("threadProducer.getName() = " + threadProducer.getName());
        System.out.println("threadConsumer0.getName() = " + threadConsumer0.getName());
        for (Thread consumer:
                consumers) {
            System.out.println("consumer.getName() = " + consumer.getName());
        }
        System.out.println("*************************************** ");

        threadProducer.start();
        threadConsumer0.start();
        for (Thread consumer:
             consumers) {
            consumer.start();
        }
        threadConsumerWaiting10sec.start();

        try {
            threadConsumer0.join();
            threadProducer.join();
            for (Thread consumer:
                    consumers) {
                    consumer.join();
            }
            threadConsumerWaiting10sec.join();

        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class WaitAndNotify {
    /*
        I reverted here the suggested produce/consume pattern and
        call here as a producer a thread which generates (produces)
        signals: that are calls of notify/notifyAll methods which
        will resume my consumer part of the code.
     */
    static Scanner scanner = new Scanner(System.in);

    void waitForEnterKeyPressed() {
        System.out.println("\nPRODUCER> Waiting for \"Enter\"-key pressed...");
        scanner.nextLine();
        System.out.println("PRODUCER> \"Enter\"-key is pressed.");
    }
    public void produce() throws InterruptedException {

        System.out.println("PRODUCER> Producing thread started.");
      //  System.out.println("PRODUCER> this = " + this);

        for(int i = 0; i<L23_WaitNotify.CONSUMERS_IN_ARRAY_COUNT>>1; i++) {
            synchronized (this) {
                waitForEnterKeyPressed();
                //Notifies only one thread went to the waiting state as next first
                System.out.println("PRODUCER> Method \"notify()\" will be called " + (i+1) + " time.");
                //The signal to resume will be activated, but an intrinsic lock is not
                //given back until we are in the synchronized(this)-block of code.
                notify();
                System.out.println("PRODUCER> Method \"notify()\" is called " + (i+1) + " time. Sleep for 3 sec.... ");
                Thread.sleep(3000);
                System.out.println("PRODUCER> Leaving a synchronized-block => an intrinsic lock is being given back.");

            }
            Thread.sleep(3000);
        }

        synchronized (this) {
            waitForEnterKeyPressed();
            //Now we call the method "notifyAll().
            // It notifies all the rest threads which are in the waiting state.
            notifyAll();
            System.out.println("PRODUCER> Method \"notifyAll()\" has been just called. Now  will sleep 5 sec.");
            Thread.sleep(5000);

            System.out.println("PRODUCER> Leaving a synchronized-block => an intrinsic lock is being given back.");
        }

    }

    public void consume(int i) throws InterruptedException {
        consume(i, false);
    }
    public void consume(int i, boolean wait10sec) throws InterruptedException {

        synchronized (this) {
            System.out.println("CONSUMER> Thread-" + i + " started.");
            //give away INTRINSIC LOCK:
            if(!wait10sec) {
                wait();
            } else {
                System.out.println("CONSUMER> Thread-" + i + " will wait for notify-signal not much than 10 seconds.");
                wait(10000);
                System.out.println("CONSUMER> Thread-" + i + " waited for 10 seconds or for a notify-signal.");
            }
            //so now we are waiting for:
            // 1) an appropriate notify/notifyAll will be called
            // 2) the intrinsic lock will be given back to this thread

            System.out.println("CONSUMER> Thread-" + i + " resumed (notified)");
        }
    }

}