/*
* Copyright (c) 2022.
* To the Lesson 35 "LAMBDA-expressions. Part II"
* LAMBDA appears in Java 8.
* To the video course "Java Advanced" by Neil Alishev (Udemy).
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class L35_LambdaEx_II {
    public static void main(String[] args) {

        //Let's have an array and ArrayList of integers:
        int [] arr = new int[10];
        List<Integer> list = new ArrayList<Integer>();

        //Fill them up with values:
        for(int i=0; i<10; i++) {
            arr[i]=i+1;
            list.add(i+1);
        }
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
        System.out.println("list                 = " + list);
        System.out.println();

        System.out.println("I. MAPPING:");
        //Modify each item of them with the formula x=x*2
        //(with a traditional pathway):
        for(int i=0; i<10; i++) {
            arr[i]*=2;
            list.set(i, list.get(i)*2);
        }
        System.out.println("x[i]=x[i]*2:");
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
        System.out.println("list                 = " + list);

        //Modify each item of them with the formula x=x*2
        //(with the LAMBDA-pathway):
        //a method IntStream map(IntUnaryOperator op) maps IntStream.
        arr = Arrays.stream(arr).map(a -> a*2).toArray();
            //This mapper-interface assigns a mapping rule will be applied to the source IntStream:
            //Interface IntUnaryOperator {
            //      int applyAsInt(int x);
            // }

        //For a List instances we will apply a method
        //public abstract <R, A> R collect(     java.util.stream.Collector<? super T, A, R> collector )
        //and a FACTORY PATHWAY to get a Collector-interface instance.
        list = list.stream().map(a -> a*2).collect(Collectors.toList());

        System.out.println("x[i]=x[i}*2");
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
        System.out.println("list                 = " + list);
        System.out.println();

        //Let's change all the items of the array 'arr' to '3':
        arr = Arrays.stream(arr).map(a->3).toArray();
        System.out.println("arr[i]=3");
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
        //And add to them '1'
        arr = Arrays.stream(arr).map(a->a+1).toArray();
        System.out.println("arr[i]=arr[i]+1");
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
        System.out.println();

        System.out.println("II. FILTERS:");
        for(int i=0; i<10; i++) {
            arr[i]=i+1;
            list.set(i, i+1);
        }
        System.out.println("Filter: only even numbers");
        //We use here a method
        //public abstract IntStream filter(java.util.function.IntPredicate predicate )
        //of a class java.util.stream.IntStream
        arr = Arrays.stream(arr).filter(a->a%2==0).toArray();
        list = list.stream().filter(a->a%2==0).collect(Collectors.toList());
        System.out.println("arr[i] <=> arr[i]%2==0");
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
        System.out.println("list                 = " + list);
        System.out.println();
    }
}
