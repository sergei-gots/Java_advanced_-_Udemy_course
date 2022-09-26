/*
 * Copyright (c) 2022.
 * To the Lesson 34 "LAMBDA-expressions. Part I"
 * LAMBDA appears in Java 8.
 * To the video course "Java Advanced" by Neil Alishev (Udemy).
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class L34_LambdaEx_I {
    public static void main(String[] args) {

        threads();
        interfaceExecutable();
        intefaceComparatorImplementation();

    }

    static void intefaceComparatorImplementation(){
        List<String> list = new ArrayList<>();

        list.add("Hello");
        list.add("Goodbye");
        list.add("Bye");
        list.add("Hi");

        //Sorting using an instance of an anonymous class implementing the Interface Comparable
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() > o2.length()) {
                    return 1;
                } else if (o1.length() < o2.length()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        System.out.println("Sorted list \t\t\t\t\t\t = " + list);

        //Sorting using LAMBDA-ex and in an extremely short way (inverse order):
        list.sort((o1,o2)-> (o1.length()<o2.length())?1:
                (o1.length()>o2.length())?-1:0);
        System.out.println("Sorted list in an inverse order:\t = " + list);

    }
    static void threads() {
        //Before Java 8
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello from a thread!");
            }
        });

        //LAMBDA Ex (in Programming) is an anonymous function
        //which is defined WITHOUT an IDentifier (or NAME)

        //Any INTERFACE-implementing CLASS WITH THE ONLY METHOD can be described
        //with a LAMBDA-ex
        Thread thread2 = new Thread (()-> System.out.println("Hello from a thread created with a LAMBDA-ex!"));

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println();
    }

    static void interfaceExecutable(){
        Runner1 runner = new Runner1();

        //1. Using an anonymous class's instance as a parameter
        int b = 0;
        runner.run(new Executable() {
            @Override
            public void execute() {
                System.out.println("b = " + b);
                System.out.println("Hello from an instance of an anonymous implementation of Executable");
            }
        });

        //2. Using an instance of a class implementing the interface Executable as a parameter
        runner.run(new ExecutableImplementation());

        //3. Using a LAMBDA-ex as a parameter
        runner.run(()-> System.out.println("Hello from an instance of the interface Executable created with a LAMBDA-expression"));
        //3.1. When there are several calls within the implemented method we will use {...}
        runner.run(()-> {
                    System.out.println("Hello from another instance of the interface Executable created with a LAMBDA-expression");
                    System.out.println("Goodbye from another instance:)");
                }
            );

        //4. LAMBDA when where is also returned value:
        runner.runAndGetValue(()->{
            System.out.println("Hello from a LAMBDA for 'int execute()'");
            return 1;
        });

        //4.1 LAMBDA with returned value and with the only line:
        runner.runAndGetValue(()->2);

        //4.2 LAMBDA with the only param and return values:
        runner.runWithParamAndGetValue(x->x+5);
        runner.runWithParamAndGetValue(x->x+5, 4);

        //4.2.1. -- with several params:
        runner.runWithParamsAndGetValue((x,y)->x+y);
        runner.runWithParamsAndGetValue((x,y)->x+y, 5, 10);
        //and even shorter. Using a METHOD REFERENCE:
        runner.runWithParamsAndGetValue(Integer::sum);
        runner.runWithParamsAndGetValue(Integer::sum, 5,10);

        //Adding in LAMBDA-signature a variable.
        //It must be FINAL or EFFECTIVELY FINAL:
        int a = 1;
      //  a++; makes the variable 'a' not to be EFFECTIVELY FINAL and => is not allowed here
        runner.runWithParamAndGetValue(x->x+a, 2);

        System.out.println();
    }


}

//It is in principle interface which repeats the Interface Runnable
interface Executable {
        void execute();
}

interface ExecutableWithResult {
    int execute();
}

interface ExecutableWithParam {
    int executeWithParam(int x);
}

interface ExecutableWithParams {
    int executeWithParams(int x, int y);
}

class ExecutableImplementation implements Executable {
    public void execute(){
        System.out.println("Hello from an instance of  the ExecutableImplementation class:)");
    }
}
 class Runner1 {
    public void run(Executable e){
        e.execute();
    }
    public void runAndGetValue(ExecutableWithResult er) {
        int result = er.execute();
        System.out.println("Runner.runAndGetValue: result = " + result);
    }

    public void runWithParamAndGetValue(ExecutableWithParam ep) {
        int result = ep.executeWithParam(10);
        System.out.println("Runner.runWithParamAndGetValue: result = " + result);
    }

     public void runWithParamAndGetValue(ExecutableWithParam ep, int x) {
         int result = ep.executeWithParam(x);
         System.out.println("Runner.runWithParamAndGetValue: result = " + result);
     }

    public void runWithParamsAndGetValue(ExecutableWithParams eps) {
        int result = eps.executeWithParams(10, 5);
        System.out.println("Runner.runWithParamAndGetValue: result = " + result);
    }

     public void runWithParamsAndGetValue(ExecutableWithParams eps, int x, int y) {
         int result = eps.executeWithParams(x,y);
         System.out.println("Runner.runWithParamAndGetValue: result = " + result);
     }
}

