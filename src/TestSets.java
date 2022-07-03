import java.util.*;

/**
 * @author Sergei Gots
 */
public class TestSets {

    public static void main(String[] args) {

        testSets();
        mathWorkoutOnSets();
    }

    static void mathWorkoutOnSets(){
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        Set<Integer> setUnion;
        Set<Integer> setIntersection;
        Set<Integer> setDifference;

        set1.add(0);
        set1.add(1);
        set1.add(2);
        set1.add(3);
        set1.add(4);
        set1.add(5);

        set2.add(2);
        set2.add(3);
        set2.add(4);
        set2.add(5);
        set2.add(6);
        set2.add(7);

        System.out.println("Math.operations:");

        setUnion = new HashSet(set1);
        setUnion.addAll(set2);
        System.out.println("Union: " + set1  + ". set1.addAll(set2) is applied");

        setIntersection = new HashSet(set1);
        setIntersection.retainAll(set2);
        System.out.println("Intersection: " + setIntersection + ". set1.retainAll(set2) is applied");

        setDifference = new HashSet(set1);
        setDifference.removeAll(set2);
        System.out.println("Difference: " + setDifference  + ". set1.removeAll(set2) is applied");;

    }

    static void testSets() {
        //Unpredictable store order
        Set<String> hashSet = new HashSet<>();
        //Input order store order
        Set<String> linkedHashSet = new LinkedHashSet<>();
        //Natural ordering
        Set<String> treeSet = new TreeSet<>();

        testSet(hashSet);
        testSet(linkedHashSet);
        testSet(treeSet);

        System.out.println("hasSet.contains(\"Tom\") = " + hashSet.contains("Tom"));
        System.out.println("hasSet.contains(\"Tim\") = " + hashSet.contains("Tim"));
        System.out.println("hasSet.isEmpty() = " + hashSet.isEmpty());

        testUniqueness(hashSet);
    }

    static void testSet(Set<String> set) {
        System.out.println("testSet for a set instance of " + set.getClass());

        set.add("Mike");
        set.add("Katy");
        set.add("Tom");
        set.add("George");
        set.add("Donald");
        set.add("Tom");
        set.add("Tom");

        for (String name: set) {
            System.out.println(name);
        }

    }

    static void testUniqueness(Set<String> set) {
        System.out.println("testUniqueness for a set instance of " + set.getClass());

        set.add("Mike");
        set.add("Katy");
        set.add("Tom");
        set.add("George");
        set.add("Donald");
        set.add("Tom");
        set.add("Tom");

        //Set overrides method toString:
        System.out.println(set);

    }


}
