import java.util.*;

public class L9_HashCodeAndEquals {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        //showMapAndSet();
        workoutPersons();
        //testStringSetAnd();

    }

    static void testStringSet() {
        Set<String> set = new HashSet<>();
        String str = "Hello";
        
        set.add("Hello");
        set.add("Hello");
        System.out.println("set = " + set);
        
        set.add(str);
        System.out.println("set = " + set);
    }

    static void workoutPersons() {
        Map<Person, String> map = new HashMap<>();
        Set<Person> set = new HashSet<>();

        Person person1 = new Person(1, "Mike");
        //Person person2 = new Person(2, "Katy");
        Person person2 = new Person(1, "Mike");

        map.put(person1, "123");
        map.put(person2, "123");

        set.add(person1);
        set.add(person2);

        System.out.println("map = " + map);
        System.out.println("set = " + set);

        person1.printHashCode();
    }

    static void showMapAndSet() {
        //See: primitive-type keys will be wrapped by Class-Wrapper "Integer":
        Map<Integer, String> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();

        map.put(1, "One");
        map.put(2, "Two");

        set.add(1);
        set.add(1); //but there will be only one element [1] in the set because sets store unique  values (keys).

        System.out.println("map = " + map);
        System.out.println("set = " + set);

    }
}

class Person {
        private int id;
        private String name;

        public Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && name.equals(person.name);
    }

    void printHashCode() {
        System.out.println("this = " + this);
        System.out.println("JDK 17.0.3: Objects.hash(id,name) = " + Objects.hash(id,name));
        System.out.println("JDK used in the UDEMY video course : 31*id + ((name!=null) ? name.hashCode() : 0) = "
                + 31*id + ((name!=null) ? name.hashCode() : 0));
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
