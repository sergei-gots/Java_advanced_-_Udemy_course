import java.util.*;

public class L13_Comparable {

    public static void main(String[] args) {
        List<Person1> peopleList = new ArrayList<>();
        Set<Person1> peopleSet = new TreeSet<>();

        addElements(peopleList);
        System.out.println("peopleList = " + peopleList);
        System.out.println();

        addElements(peopleSet);
        System.out.println("peopleSet = " + peopleSet);
    }

    private static void addElements(Collection collection) {

        collection.add(new Person1(2, "Tom"));
        collection.add(new Person1(4, "George"));
        collection.add(new Person1(3, "Katy"));
        collection.add(new Person1(1, "Bob"));
    }
}
class Person1 implements Comparable<Person1> {
       private int id;
       private String name;
       public Person1(int id, String name) {
           this.id = id;
           this.name = name;
       }

    @Override
    public int compareTo(Person1 o) {
     /*    if(id > o.id) {
            return 1;
        } else if (id < o.id) {
            return -1;
        } else {
            return 0;
        }*/
        if(name.length() > o.name.length()){
            return 1;
        } else if (name.length() < o.name.length()) {
            return -1;
        } else {
            return 0;
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person1 person1 = (Person1) o;
        return id == person1.id && Objects.equals(name, person1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
       public String toString() {
           return "Person{" +
                   "id=" + id +
                   ", name='" + name + '\'' +
                   '}';
       }
}




