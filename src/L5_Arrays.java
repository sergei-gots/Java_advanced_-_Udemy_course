import java.util.*;

/**
 * @author Sergei Gots
 */
public class L5_Arrays {
    enum ListsTestEnum {
        ADD,
        ADD_TO_THE_BEGIN,
        GET,
        REMOVE_FIRST_ITEM
    }

    public static void main(String[] args) {
        intArrayExample();
        intArrayListExample();
        myLinkedList();
        performanceTest();
    }

    /** Will sleep for 1 second.
     * Expected to be used in 'catch(Exception e){...}' - section
     * after a call of 'e.printStackTrace();'
     * to ensure that a stack trace will have been printed
     * before any other possible action.
     **/
    static void delay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    static void myLinkedList() {

        L5_MyLinkedList myLinkedList = new L5_MyLinkedList();

        System.out.println("*** MyLinkedList workout ***");

        myLinkedList.add(1);
        myLinkedList.add(2);
        myLinkedList.add(10);

        System.out.println("myLinkedList = " + myLinkedList);
        System.out.println("myLinkedList.get(0) = " + myLinkedList.get(0));
        System.out.println("myLinkedList.get(1) = " + myLinkedList.get(1));
        System.out.println("myLinkedList.get(2) = " + myLinkedList.get(2));

        //Next line will cause IllegalArgumentException:
        try {
            System.out.println("myLinkedList.get(3) = " + myLinkedList.get(3));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            delay();
        }

        System.out.println("myLinkedList.remove(1) = " + myLinkedList.remove(1));
        System.out.println("myLinkedList = " + myLinkedList);

        myLinkedList.remove(1);
        System.out.println("myLinkedList = " + myLinkedList);

        myLinkedList.remove(0);
        System.out.println("myLinkedList = " + myLinkedList);

        System.out.println();
    }
    
    static void performanceTest() {
        List <Integer> arrayList = new ArrayList<>();
        List <Integer> linkedList = new LinkedList<>();

        long timeArrayList = 0;
        long timeLinkedList = 0;
        final int testNumber = 3;

        System.out.println("*** Performance Test: ArrayList vs LinkedList ***");

        for (ListsTestEnum test: ListsTestEnum.values()) {
            System.out.println(test);
            for (int i = 0; i < testNumber; i++) {
                System.out.print('a');
                timeArrayList += measureTime(arrayList, test);
                System.out.print('l');
                timeLinkedList += measureTime(linkedList, test);

                arrayList.clear();
                linkedList.clear();
            }
            System.out.println();
            System.out.println(test + ": time(ArrayList) average  = " +
                    String.format( "%,6d", timeArrayList / testNumber));
            System.out.println(test + ": time(LinkedList) average = " +
                    String.format("%,6d", timeLinkedList / testNumber));
            double rate = (double)(timeLinkedList)/(double)timeArrayList;
            System.out.println(test + ": rate(ArrayList/LinkedList) average = " +
                    String.format("%,.2f", rate) );
            System.out.println();
        }
    }

    private static long measureTime(List<Integer>list, ListsTestEnum test) {

        final int BASIC_ITERATIONS_NUMBER               = 100_000;
        final int ADD_ITERATIONS_NUMBER                 = 200 * BASIC_ITERATIONS_NUMBER;
        final int GET_ITERATIONS_NUMBER                 = 2 * BASIC_ITERATIONS_NUMBER;
        final int REMOVE_FIRST_ITEM_ITERATIONS_NUMBER   = BASIC_ITERATIONS_NUMBER;

        long time0;

        if(test == ListsTestEnum.GET) {
            for (int i = 0; i < GET_ITERATIONS_NUMBER ; i++) {
                list.add(i);
            }
        } else if (test == ListsTestEnum.REMOVE_FIRST_ITEM) {
            for (int i = 0; i < REMOVE_FIRST_ITEM_ITERATIONS_NUMBER ; i++) {
                    list.add(i);
            }
        }

        time0 = System.currentTimeMillis();
        switch(test) {
            case ADD:
                for (int i = 0; i < ADD_ITERATIONS_NUMBER ; i++) {
                    list.add(i);
                } break;
            case GET:
                for (int i = 0; i < GET_ITERATIONS_NUMBER; i++) {
                    list.get(i);
                } break;
            case ADD_TO_THE_BEGIN:
                for (int i = 0; i < REMOVE_FIRST_ITEM_ITERATIONS_NUMBER ; i++) {
                    list.add(0, i);
                } break;
            case REMOVE_FIRST_ITEM:
            for (int i = 0; i < BASIC_ITERATIONS_NUMBER ; i++) {
                list.remove(0);
            }
        }
        return System.currentTimeMillis() - time0;
    }

    static void intArrayListExample() {
        System.out.println("*** ArrayList<Integer> example ***");
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        System.out.println("list: " + list);

        System.out.println("list.get(0) = " + list.get(0));
        System.out.println("list.get(9) = " + list.get(9));

        System.out.println("list.size() = " + list.size());

        for (int i = 0; i < 10; i++) {
            System.out.println("list.get(i) = " + list.get(i));
        }
        System.out.println();

        //foreach-loop
        for (Integer x: list) {
            System.out.print(x + ", ");
        }
        System.out.println();

        System.out.println("list.remove(5) = " + list.remove(5));;
        System.out.println("list = " + list);
        System.out.println();
    }

    static void intArrayExample() {
        int[] x = new int[3];

        System.out.println("*** int-Array example ***");

        try {
            for (int i = 0; i < 4; i++) {
                x[i] = i+1;
            }
        } catch(IndexOutOfBoundsException e) {
            e.printStackTrace();
            delay();
        }
        System.out.println("Arrays.toString(x) = " + Arrays.toString(x));
        System.out.println();
    }
}
