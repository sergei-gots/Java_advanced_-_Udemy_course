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
        //sorts corresponding string's lengths:
        Collections.sort(animals, new StringLengthComparator());
        System.out.println("animals = " + animals);

        Collections.sort(animals);
        System.out.println("animals = " + animals);
        Collections.sort(animals, new Comparator<String>() {
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
        });
        System.out.println("animals = " + animals);

        List<Integer> numbers = new ArrayList<>();
        numbers.add(5);
        numbers.add(0);
        numbers.add(500);
        numbers.add(1000);

        Collections.sort(numbers);
        System.out.println("numbers = " + numbers);
    
        Collections.sort(numbers, new BackwardsIntegerComparator());
        System.out.println("numbers = " + numbers);

        Collections.sort(numbers);
        System.out.println("numbers = " + numbers);
        Collections.sort(numbers, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(o1 > o2) {
                    return -1;
                } else if (o1 < o2) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        System.out.println("numbers = " + numbers);

        //Let's sort our own Objects:
        List<Person> people = new ArrayList<>();
        people.add(new Person(3, "Mike"));
        people.add(new Person(2, "Katy"));
        people.add(new Person(1, "Bob"));

        //Let's sort people by their id:
        Collections.sort(people, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                if(o1.getId()>o2.getId()){
                    return 1;
                } else if (o1.getId()<o2.getId()) {
                    return -1;
                } else {
                    return 0;
                } 
            }
        });

        System.out.println("people = " + people);
    }
}

class Person {
    private int id;
    private String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
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

class BackwardsIntegerComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
        if(o1 > o2) {
            return -1;
        } else if (o1 < o2) {
            return 1;
        } else {
            return 0;
        }
    }
}
