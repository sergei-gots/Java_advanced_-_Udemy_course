/*
 * * Copyright (c) 2022.
 * To the Lesson 23 "Pattern Producer-Consumer. 
 *                      Methods Object.wait() and 
 *                              Object.notify()" 
 * To the video course "Java Advanced" by Neil Alishev (Udemy).
 */

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

        Thread threadConsume = new Thread(new Runnable() {
           @Override
           public void run() {
               try {
                   waitAndNotify.consume();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        });

        threadProduce.start();
        threadConsume.start();

        try {
            threadProduce.join();
            threadConsume.join();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }


}

class WaitAndNotify {
    public void produce() throws InterruptedException {
        synchronized (this) {
            System.out.println("Producing thread started.");
            wait();
            System.out.println("Producing thread resumed (notified)");
        }
    }

    public void consume() throws InterruptedException {

        Scanner scanner = new Scanner(System.in);

        synchronized (this) {
            System.out.println("Waiting for \"Enter\"-key press...");
            scanner.nextLine();
            System.out.println("\"Enter\"-key is pressed. method \"notify()\" will be called.");
            notify();
        }
    }
}