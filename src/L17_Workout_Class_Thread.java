/*
 * Lesson 17. Introducing in multithreading in Java.
 */

public class L17_Workout_Class_Thread {
    public static void main(String[] args) throws InterruptedException {
        //workoutMyThread();
        workoutRunnable();
    }

    static void workoutMyThread() throws InterruptedException {
        System.out.println("Hello world!");

        MyThread myThread = new MyThread();
        MyThread myThread2 = new MyThread();

        //This is a wrong way to launch a new thread:
        //myThread.run();
        //To launch a new thread we should use the method start():
        myThread.start();
        myThread2.start();

        System.out.println("I'm going to sleep");
        Thread.sleep(3000);
        System.out.println("I am awake");

        System.out.println("Hello from the main thread");
    }

    static void workoutRunnable(){
        Thread thread = new Thread(new Runner());
        thread.start();
        System.out.println("Hello from the main thread");
    }
}

class Runner implements Runnable {
    @Override
    public void run() {
        for(int i=0; i<1000; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("Hello from a MyThread-instance " + i);
        }
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        for(int i=0; i<1000; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("Hello from a MyThread-instance " + i);
        }
    }
}
