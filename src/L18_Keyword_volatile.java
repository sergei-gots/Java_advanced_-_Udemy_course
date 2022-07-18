/*
 * To the Lesson 18. "Keyword "volatile"
 */

import java.util.Scanner;

public class L18_Keyword_volatile {
    public static void main(String[] args) {
        MyThread1 myThread1 = new MyThread1();
        myThread1.start();

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        myThread1.shutdown();
    }
}

class MyThread1 extends Thread {
    private volatile boolean running = true;


    public void run() {
        while (running) {
            System.out.println("Hello");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown() {
        System.out.println("MyThread1.shutdown() is invoked");
        this.running = false;
    }
}