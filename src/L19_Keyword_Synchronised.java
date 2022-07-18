/*
 * Copyright (c) 2022.
 * To the Lesson 19 "Keyword Synchronised" of the video course "Java Advanced" by Neil Alishev (Udemy)
 */

public class L19_Keyword_Synchronised {

    private int counter;

    public static void main(String[] args) {
        L19_Keyword_Synchronised workout = new L19_Keyword_Synchronised();
        workout.doWork();
    }

    public synchronized void increment(){

        counter++;
    }

    public void doWork() {
        Thread thread1 = new Thread(new Runnable(){
            @Override
            public void run() {
                for(int i = 0; i<10000; i++) {
                    //counter++;
                    increment();
                    if (counter == 5000) {
                        System.out.println("thread1: counter == " + counter) ;
                    }
                }
            }
        });

        Thread thread2 = new Thread(new Runnable(){
            @Override
            public void run() {
                for(int i = 0; i<10000; i++) {
                    //counter++;
                    increment();
                    if (counter == 5000) {
                        System.out.println("thread2: counter == " + counter);
                    }
                }
            }
        });

        thread1.start();
        thread2.start();

        try {
            //with that we will wait until thread1 and thread2 will complete their work:
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main thread: counter = " + counter);
    }


}
