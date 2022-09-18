/*
 * Copyright (c) 2022.
 * To the Lesson 28 "Deadlock-state: Method Lock.tryLock()".
 * To the video course "Java Advanced" by Neil Alishev (Udemy).
 */

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class L28_DeadLock {
    public static void main(String[] args) {
        L28_Runner runner = new L28_Runner();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                runner.thread1();
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                runner.thread2();
            }
        });

        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        runner.finished();
    }
}

class L28_Runner{
    private Account account1 = new Account();
    private Account account2 = new Account();
    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();
    private Random random = new Random();
    static  private int TRANSFERS_NUMBER = 10_000;
    public void thread1(){
        for (int i = 0; i < TRANSFERS_NUMBER; i++) {
            takeLocks(lock1, lock2);
            try {
                System.out.println("1."+ i);
                Account.transfer(account1, account2, random.nextInt(100));
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }
    public void thread2(){
        for (int i = 0; i < TRANSFERS_NUMBER; i++) {
            takeLocks(lock2, lock1);
            try {
                System.out.println("2."+ i);
                Account.transfer(account2, account1, random.nextInt(100));
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }
    public void finished(){
        System.out.println();
        System.out.println("account1.getBalance() = " + account1.getBalance());
        System.out.println("account2.getBalance() = " + account2.getBalance());
        System.out.println("Total balance = " +
                (account1.getBalance() + account2.getBalance()));
    }

    private void takeLocks(Lock lock1, Lock lock2){
        boolean lock1Taken = false;
        boolean lock2Taken = false;
        while (true) {
            try {
                lock1Taken = lock1.tryLock();
                lock2Taken = lock2.tryLock();
            }
            finally {
                if (lock1Taken && lock2Taken) {
                    return;
                }
                if (lock1Taken) {
                    lock1.unlock();
                } if (lock2Taken) {
                    lock2.unlock();
                }
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Account{
    int balance = 10_000;
    private Random random = new Random();
    public static void transfer(Account acc1, Account acc2, int amount){
        acc1.withdraw(amount);
        acc2.deposit(amount);
    }
    public void deposit(int amount){
        sleep();
        balance +=amount;
    }
    public void withdraw(int amount){
        sleep();
        balance-=amount;
    }
    private void sleep(){
        try {
            Thread.sleep(random.nextInt(3));
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public int getBalance(){
        return balance;
    }
    
}