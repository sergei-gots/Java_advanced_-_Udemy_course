import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Sergei Gots
 */
public class L5_Arrays {
    enum ListsTestEnum {
        ADD,
        ADD_TO_THE_BEGIN,
        GET,
        REMOVE_FIRST
    }

    public static void main(String[] args) {
        L5_MyLinkedList l5_MyLinkedList = new L5_MyLinkedList();
        l5_MyLinkedList.add(1);
        l5_MyLinkedList.add(2);
        l5_MyLinkedList.add(10);

        System.out.println("myLinkedList = " + l5_MyLinkedList);
        System.out.println(l5_MyLinkedList.get(0));
        System.out.println(l5_MyLinkedList.get(1));
        System.out.println(l5_MyLinkedList.get(2));
        //Next line causes IllegalArgumentException:
        //System.out.println(myLinkedList.get(3));

        l5_MyLinkedList.remove(1);
        System.out.println("myLinkedList = " + l5_MyLinkedList);

        l5_MyLinkedList.remove(1);
        System.out.println("myLinkedList = " + l5_MyLinkedList);

        l5_MyLinkedList.remove(0);
        System.out.println("myLinkedList = " + l5_MyLinkedList);


    }
    
    void linkedListVsArrayListTest() {
        
        List <Integer> linkedList = new LinkedList<>();
        List <Integer> arrayList = new ArrayList<>();

        long timeLinkedList = 0;
        long timeArrayList = 0;
        int testNumber = 10;

        linkedList.add(1);
        linkedList.get(0);
        linkedList.size();
        linkedList.remove(0);

        ListsTestEnum listsTest = ListsTestEnum.REMOVE_FIRST;
        for (int i = 0; i < testNumber; i++) {
            timeArrayList += measureTime(arrayList, listsTest);
            timeLinkedList+= measureTime(linkedList, listsTest);

            arrayList.clear();
            linkedList.clear();
        }

        System.out.println("timeArrayList average = " + timeArrayList/testNumber);
        System.out.println("timeLinkedList average = " + timeLinkedList/testNumber);
    }


    private static long measureTime(List<Integer>list, ListsTestEnum listsTest) {

        long result;

        if(listsTest == ListsTestEnum.GET
                || listsTest == ListsTestEnum.REMOVE_FIRST) {

            for (int i = 0; i <100000 ; i++) {
                list.add(i);
            }
        }

        long start = System.currentTimeMillis();

        if(listsTest == ListsTestEnum.ADD) {
            for (int i = 0; i < 10000000 ; i++) {
                list.add(i);
            }
        } else if (listsTest == ListsTestEnum.GET) {
            for (int i = 0; i < 100000; i++) {
                list.get(i);
            }
        } else if (listsTest == ListsTestEnum.ADD_TO_THE_BEGIN) {
            for (int i = 0; i <100000 ; i++) {
                list.add(0, i);
            }
        } else if (listsTest == ListsTestEnum.REMOVE_FIRST) {
            for (int i = 0; i <100000 ; i++) {
                list.add(0, i);
            }
        }

        long end = System.currentTimeMillis();

        result = end - start;
        System.out.println(list.getClass().getName() + ": " + result);
        return result;
    }

    void intArrayListExample() {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        System.out.println(list);

        System.out.println(list.get(0));
        System.out.println(list.get(9));

        System.out.println(list.size());

        for (int i = 0; i < 10; i++) {
            System.out.print(list.get(i)+ ", ");
        }
        System.out.println();

        for (Integer x: list) {
            System.out.print(x + ", ");
        }
        System.out.println();

        list.remove(5);
        System.out.println(list);
    }

    void intArrayExample() {
        int[] x = new int[3];
        for (int i = 0; i < 4; i++) {
            x[i] = 1;
        }
    }
}
