/*
 * Copyright (c) 2022.
 * To the Lesson 27 "Class java.util.concurrent.Semaphore".
 * To the video course "Java Advanced" by Neil Alishev (Udemy).
 *
 * Class Semaphore is used when we want to limit access to a resource.
 * So we can divide this limited resource btw multiple threads
 */

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class L27_Semaphore {

    public static void main(String[] args) {

        //part1_Intro();
        part2_TaskConnections();
    }

    static void part2_TaskConnections(){
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for(int i = 0; i<200; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    Connection connection = Connection.getConnection();
                    try {
                        connection.work();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    static void part1_Intro(){
        //We will set number of active permissions for the access to a resource
        //in the constructor: int permits = 3
        Semaphore semaphore = new Semaphore(3);
        semaphore.release();

        try {
            semaphore.acquire();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        semaphore.availablePermits();

        System.out.println("semaphore.availablePermits() = " + semaphore.availablePermits());

        for(int i = 0; i<3; i++) {
            try {
                semaphore.acquire();
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("All the permits have been acquired");

        try {
            semaphore.acquire();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Programm can't reach here");
    }
}

class Connection {
    //We will use SINGLETON programming pattern:
    static private Connection connection = new Connection();

    private int connectionsCount;
    private int worksCount;
    private Semaphore semaphore = new Semaphore(10);
    private Random random = new Random();
    private Connection() {
    }

    static public Connection getConnection() {
        return connection;
    }
    public void work() throws InterruptedException {
        synchronized (this) {
            worksCount++;
            System.out.println("worksCount = " + worksCount);
        }
        semaphore.acquire();
        try {
            performWork();
        }
        finally {
             semaphore.release();
        }
    }
    private void performWork() throws InterruptedException {
        synchronized (this) {
            connectionsCount++;
            System.out.println("connectionsCount before executing the main job = " + connectionsCount);
        }

        Thread.sleep(1000 + random.nextInt(3000));

        synchronized (this){
            connectionsCount--;
            System.out.println("connectionsCount after executing the main job = " + connectionsCount);
        }
    }
}