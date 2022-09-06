/*
 * Copyright (c) 2022.
 * To the Lesson 21 "Thread pool" of the video course "Java Advanced" by Neil Alishev (Udemy)
 */

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

public class L21_ThreadPool {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        //so now we have 2 threads in the pool

        for(int i = 0; i<5; i++) {
            //here we are submitting new works
            executorService.submit(new Work(i));
        }
        //so we have submitted 5 new works to our 2 threads

        //here we are declaring that we have ended submitting new works:
        executorService.shutdown();
        System.out.println("All task submitted");

        //wait until executing finishes, but not longer as timeout
        executorService.awaitTermination(1, TimeUnit.DAYS);

    }
}

class Work implements Runnable{

    public static long startTime =  System.currentTimeMillis();
    private int id;
    public Work(int id){
        this.id = id;
    }

    @Override
    public void run(){
        long endTime;
        try {
           Thread.sleep(5000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        endTime = System.currentTimeMillis() - startTime;
        System.out.println(endTime + " ms: Work " + id + " was completed");

    }

}
