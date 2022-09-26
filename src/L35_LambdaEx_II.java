/*
* Copyright (c) 2022.
* To the Lesson 35 "LAMBDA-expressions. Part II"
* LAMBDA appears in Java 8.
* To the video course "Java Advanced" by Neil Alishev (Udemy).
*/

import java.util.*;
import java.util.stream.Collectors;

public class L35_LambdaEx_II {

    //Let's have an array and ArrayList of integers:
    static final int INITIAL_CAPACITY = 10;
    static  int [] arr = new int[INITIAL_CAPACITY];
    static  List<Integer> list = new ArrayList<>(INITIAL_CAPACITY);

    static {
        initData();
        printResult("Source data:");
    }

    static void allocateData() {
        arr = new int[INITIAL_CAPACITY];
        list = new ArrayList<>(10);
    }
    static void initData() {
        if(list.isEmpty()) {
            for(int i=0; i<INITIAL_CAPACITY; i++) {
                int value = i+1;
                list.add(value);
                arr[i]=value;
            }
        }
        for(int i=0; i<INITIAL_CAPACITY; i++) {
            int value = i+1;
            arr[i]=value;
            list.set(i, value);
        }
    }
    static void resetData() {
        if(arr.length!=INITIAL_CAPACITY || list.size()!=INITIAL_CAPACITY) {
            //System.out.println("ALLOCATION: arr.length/list.size()" + arr.length + '/' + list.size());
            allocateData();
        }
        initData();
    }

    static void printResult(String header) {
        if(header != null) {
            System.out.println(header);
        }
        printResultArray("");
        printResultList("");
        System.out.println();
    }

    static void printResultArray(String header) {
        boolean isUnit = header !=null && header != "";
        if(isUnit) {
            System.out.println(header);
        }
        System.out.println("Arrays.toString(array) = " + Arrays.toString(arr));
        if(isUnit) {
            System.out.println();
        }
    }

    static void printResultList(String header) {
        boolean isUnit = header !=null && header != "";
        if(isUnit) {
            System.out.println(header);
        }
        System.out.println("list                   = " + list);
        if(isUnit) {
            System.out.println();
        }
    }
    public static void main(String[] args) {
        System.out.println("*** OPERATIONS ON STREAM *****");
        System.out.println("I. MAPPING:");
        for(int i=0; i<10; i++) {
            arr[i]*=2;                           //Modify each item of them with the formula x=x*2
            list.set(i, list.get(i)*2);          //(with a traditional pathway).
        }
        printResult("x[i]=x[i]*2:");

        System.out.println("-- MAPPING with LAMBDA:");
        resetData();
        //Let's modify each item with the formula x=x*2 with the LAMBDA-ex
        //The used mapper-interface assigns a mapping rule will be applied to the source IntStream:
        //Interface IntUnaryOperator {
        //      int applyAsInt(int x);
        // }
        arr = Arrays.stream(arr).map(a -> a*3).toArray();
        //For a List instances we will apply a method
        //public abstract <R, A> R collect(     java.util.stream.Collector<? super T, A, R> collector )
        //and a FACTORY PATHWAY to get a Collector-interface instance.
        list = list.stream().map(a -> a*3).collect(Collectors.toList());
        printResult("x[i]=x[i}*3");

        //Let's change all the items of the array 'arr' to '3':
        arr = Arrays.stream(arr).map(a->3).toArray();
        printResultArray("x[i]=3");
        //And add to them '1'
        arr = Arrays.stream(arr).map(a->a+1).toArray();
        list = list.stream().map(a -> a+1).collect(Collectors.toList());
        printResult("then x[i]=x[i]+1");

        System.out.println("II. FILTERS:");
        resetData();
        System.out.println("Filter: only even numbers");
        //We use here a method
        //public abstract IntStream filter(java.util.function.IntPredicate predicate )
        //of a class java.util.stream.IntStream
        arr = Arrays.stream(arr).filter(a->a%2==0).toArray();
        list = list.stream().filter(a->a%2==0).collect(Collectors.toList());
        printResult("x[i] <=> x[i]%2==0");

        System.out.println("III. FOREACH()-method");
        resetData();
        /* IntStream public abstract void forEach(java.util.function.IntConsumer action ) */
        System.out.println("Let's print all the odd numbers from an Array 'arr':");
        Arrays.stream(arr).forEach(a-> { if (a%2 == 0)
                                            System.out.print(a + " ");
                                        });
        System.out.println();

        System.out.println("Let's print all numbers from a List 'list':");
        //Let's use here a radical contraction
        list.stream().forEach(System.out::print);
        System.out.println();
        System.out.println();

        System.out.println("""
                           IV. REDUCE
                           /* At the 1st iteration by default acc = x[0] and this iteration includes x[0] and [1]
                            * We can assign another initial value to the 'acc' with a param 'identity':
                            * java.util.OptionalInt  reduce((int identity, (IntBinaryOperator op)
                            *           of a class java.util.stream.IntStream.
                            * In such case:
                            *   - 1st iteration involves acc = identity and x[0]
                            *   - we don't call  
                            *      public int getAsInt() 
                            *           of a class java.util.OptionalInt.
                            *""");


        int sum = Arrays.stream(arr).reduce((acc, b) -> acc + b).getAsInt();
        int product = list.stream().reduce((acc, b)-> acc*b).get();
        System.out.println("sum = " + sum);
        System.out.println("product = " + product);

        System.out.println("""
            Let's:
                1) Filter odd numbers in array;
                2) Multiply them by 2":   """);
        arr = Arrays.stream(arr).filter(a->(a%2)!=0).map(a->a*2).toArray();
        printResultArray("'filter(a->(a%2)==0).map(a->a*2)':");

        Set<Integer> setSource = new HashSet<>();
        for(int i = 0; i < 10;i++) {
            setSource.add(i+1);
        }
        Set <Integer> setResult = setSource.stream().map(a->a*3).collect(Collectors.toSet());
        System.out.println("""
                Applying a method 
                    <R> Stream<R>  map(java.util.Function <? super T, extends R>mapper)
                        of a class java.util.Stream<T>
                to a set-instance.
                A function will be 'a->a*3':""");
        System.out.println("setSource = " + setSource);
        System.out.println("setResult = " + setResult);
    }
}
