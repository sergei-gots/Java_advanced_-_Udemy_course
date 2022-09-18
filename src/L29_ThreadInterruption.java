/*
 * Copyright (c) 2022.
 * To the Lesson 29 "Threads Interruption.
 * Methods Thread.currentThread, Thread.interrupt() and Thread.isInterrupted".
 * To the video course "Java Advanced" by Neil Alishev (Udemy).
 */

import java.util.Random;

public class L29_ThreadInterruption {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Thread will be started.");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                boolean initialSleep = true;
                for(int i =0; i<1_000_000_000; i++){
                    if(initialSleep) {
                        try {
                            initialSleep = false;
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            System.out.println("Thread was interrupted during Thread.sleep(millis: 2000) (i=" + i + ")");
                        }
                    }

                    if(Thread.currentThread().isInterrupted()){
                        System.out.println("Thread was interrupted (i=" + i + ")");
                        break;
                    }
                    Math.sin(random.nextDouble());
                }
            }
        });

        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
        Thread.sleep(1000);
        thread.interrupt();
        //thread.join();

        System.out.println("Thread has finished");
    }
}
