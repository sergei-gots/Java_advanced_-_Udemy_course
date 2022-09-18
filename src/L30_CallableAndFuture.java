/*
 * Copyright (c) 2022.
 * To the Lesson 30 "Interfaces Callable and Future.
 * To the video course "Java Advanced" by Neil Alishev (Udemy).
 */


import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;


public class L30_CallableAndFuture {
    private static int result = 0;
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        //VARIANT I. Interface Runnable. Using a static field
        executorService.submit(()-> {
            System.out.println("Starting a Lambda-written anonymous Runnable-implementation");
            result++;
            try{
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Runnable written with Lambda-ex finished.");
        });
        //VARIANT II. Interface Callable. Using the same static field
        //an instance of the class Future will await a result from a Callable instance in some future
        Future<Integer> future =  executorService.submit(()-> {
            int result;
            Random random = new Random();
            System.out.println("Starting a Lambda-written anonymous Callable-implementation");
            try{
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Callable written with Lambda-ex finished.");
            result = random.nextInt(10);
            //Let's throw an Exception if the result is greater than 5.
            //It will be thrown as a cause within an ExecutionException
            //anc can be reached in a catch-section with Exception.getCause() method.
            if(result > 5) {
                throw new Exception("The result=" + result + " is greater than 5 ");
            }
            //Because of this return statement the piece of code above is interpreted with Java
            // as a Callable-anonymous-lambda-implementation.
            return result;
        });

        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("result = " + result);
        try {
            System.out.println("future.get() = " + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            e.printStackTrace();
            System.out.println("cause.getMessage() = " + cause.getMessage());
            System.out.println("cause = " + cause);
        }
    }
}

