import java.util.*;

/**
 * @author Sergei Gots
 */
public class TestMaps {

    public static void main(String[] args) {
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

        System.out.println(hashSet.contains("Tim"));
        System.out.println(hashSet.contains("Tom"));

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


    static void testMaps() {
        //There is no order guarantied
        Map <Integer, String> hashMap = new HashMap<>();
        //Entries will be stored in the order of their input to the Map
        Map <Integer, String> linkedHashMap = new LinkedHashMap<>();
        //Entries will be sorted in NATURAL ORDERING, that means - (in java) in LEXICOGRAPHIC ORDER
        Map <Integer, String> treeMap = new TreeMap<>();

        testMap(hashMap);
        testMap(linkedHashMap);
        testMap(treeMap);

    }

    static void testMap(Map<Integer, String> map){

        System.out.println("testMap for map instance of the class " + map.getClass());

        map.put(39, "Bob");
        map.put(12, "Mike");
        map.put(78, "Tim");
        map.put(0, "Tom");
        map.put(1500, "Lewis");
        map.put(7, "Bob");

        for (Map.Entry<Integer, String> entry:
                map.entrySet()
             ) {
            System.out.println(entry.getKey() + ": " + entry.getValue());

        }
    }

    static void hashMapWorkout() {

        Map<Integer, String> map = new HashMap<>();

        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");

        System.out.println(map);

        map.put(3, "Another one for 3");
        System.out.println(map);

        System.out.println(map.get(1));
        System.out.println(map.get(10));

        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
