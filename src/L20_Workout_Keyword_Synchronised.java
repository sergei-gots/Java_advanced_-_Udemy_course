/*
 * Copyright (c) 2022.
 * To the Lesson 19 "Keyword Synchronised" of the video course "Java Advanced" by Neil Alishev (Udemy)
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class L20_Workout_Keyword_Synchronised {

    private int counter;

    public static void main(String[] args) {

        if(false) {
            new L20_Workout_Keyword_Synchronised().performWork();
        }
        else {
            new Worker().performWork();
        }

    }

    public void increment(){

        synchronized(this) {
            counter++;
        }
    }

    public void performWork() {
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


class Worker {
    private Random random = new Random();
    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();
    public void performWork(){
        long before = System.currentTimeMillis();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                work();
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                work();
            }
        });

        thread1.start();
        thread2.start();



        //work();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long after = System.currentTimeMillis();

        System.out.println("Performing a work took " + (after - before) + " ms ");

        //to be sure that our program works correctly:
        System.out.println("list1.size() = " + list1.size());
        System.out.println("list2.size() = " + list2.size());
    }

    private void work() {
        for(int i = 0; i < 1000; i++) {
            addToList1();
            addToList2();
        }
    }
    private synchronized void addToList1() {

        try {
            Thread.sleep(1);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        list1.add(random.nextInt(100));
    }

    private synchronized void addToList2() {

        try {
            Thread.sleep(1);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        list2.add(random.nextInt(100));
    }
}
