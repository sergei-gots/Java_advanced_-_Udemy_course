import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class WorkoutComparator {

    public static void main(String[] args) {
        List<String> animals = new ArrayList<>();

        animals.add("dog");
        animals.add("cat");
        animals.add("frog");
        animals.add("kangaroo");
        animals.add("bird");
        animals.add("giraffe");

        //sorts corresponding the natural ordering
        Collections.sort(animals);
        System.out.println("animals = " + animals);
        //sorts corresponding string's lenghts:
        Collections.sort(animals, new StringLengthComparator());
        System.out.println("animals = " + animals);


        List<Integer> numbers = new ArrayList<>();
        numbers.add(5);
        numbers.add(0);
        numbers.add(500);
        numbers.add(1000);

        Collections.sort(numbers);
        System.out.println("numbers = " + numbers);

    }
}

class StringLengthComparator implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        if(o1.length() > o2.length()){
            return 1;
        } else if (o1.length() < o2.length()) {
            return -1;
        } else {
            return 0;
        }
    }
}
