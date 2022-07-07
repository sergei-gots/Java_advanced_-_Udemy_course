/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

import java.util.LinkedList;
import java.util.Queue;

public class WorkoutQueue {

    public static void main(String[] args) {

        PersonInQueue person1 = new PersonInQueue(1);
        PersonInQueue person2= new PersonInQueue(2);
        PersonInQueue person3 = new PersonInQueue(3);
        PersonInQueue person4= new PersonInQueue(4);

        Queue<PersonInQueue> people = new LinkedList<>();

        people.add(person3);
        people.add(person2);
        people.add(person4);
        people.add(person1);

        for (PersonInQueue person : people ) {
            System.out.println("Next person = " + person);
        }
    }
}

class PersonInQueue {
    private int id;

    public PersonInQueue(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PersonInQueue{" +
                "id=" + id +
                '}';
    }


}


