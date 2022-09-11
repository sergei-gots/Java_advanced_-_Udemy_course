import java.util.*;

/**
 * @author Sergei Gots
 */
public class L7_Maps {

    public static void main(String[] args) {
        testMaps();
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
