import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

/**
 * To the Lesson 16 "Interface Iterable"
 **/

public class WorkoutIterable {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        for (int x:
             list) {
            System.out.println("x = " + x);
        }

        System.out.println();
        //the code above equals the next code:

        Iterable<Integer> iterable = list;
        for (int x:
             iterable) {
            System.out.println("x = " + x);
        }

        System.out.println();
        //the next code would be used until Java 1.5:

        Iterator<Integer> iterator = list.iterator();
        System.out.println("iterator.hasNext() = " + iterator.hasNext());
        while(iterator.hasNext()) {
            System.out.println("iterator.next() = " + iterator.next());
        }
        System.out.println("iterator.hasNext() = " + iterator.hasNext());

        System.out.println();
        System.out.println("Let's try to remove an element within iteration-loops:\n");

        boolean tryRemoveFromForEachLoop = false;
        if(tryRemoveFromForEachLoop) {
            for (int x :
                    list) {
                try {
                    System.out.println("Let's try to remove an element with the index 1 within FOREACH-LOOP:");
                    System.out.println("list.remove(1) = " + list.remove(1));
                } catch (Exception e) {
                    System.out.println("It is impossible to print out this message"
                            + "because the process will exit withthe code \"1\" after this try");
                }
                System.out.println("x = " + x);
            }
        }

        System.out.println("Let's try to remove an element with the index 1 with a classic loop with an iterator\n");

        iterator = list.iterator();
        int idx = 0;

        while(iterator.hasNext()){
            try {
                System.out.println("Next - iterator.remove()");
                iterator.remove();
                System.out.println("an item was removed. Current position = " + idx);
            } catch(IllegalStateException e){
                e.printStackTrace();
                System.out.println("IllegalStateException was thrown when idx = " + idx);
            }
            System.out.println("iterator.next() = " + iterator.next()
                + ", idx = " + idx);
            idx++;
        }
    }
}
