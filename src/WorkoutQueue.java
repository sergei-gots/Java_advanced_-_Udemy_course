/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class WorkoutQueue {

    public static void main(String[] args) {

        Queue<PersonInQueue> people = new LinkedList<>();

        addPeople(people);

        for (PersonInQueue person : people ) {
            System.out.println("Next person = " + person);
        }

        Queue<PersonInQueue> peopleABQ = new ArrayBlockingQueue<>(10);
        addPeople(peopleABQ);

        System.out.println("\nArrayBlockedQueue: ");

        System.out.println("peopleABQ.remove() = " + peopleABQ.remove());
        System.out.println("peopleABQ.peek() = " + peopleABQ.peek());

        for (PersonInQueue person : peopleABQ ) {
            System.out.println("Next person = " + person);
        }

        Queue<PersonInQueue> peopleABQ_capacity_3 = new ArrayBlockingQueue<>(3);
        try {
            addPeople(peopleABQ_capacity_3);
        } catch (IllegalStateException e){
            e.printStackTrace();
        }

        Queue<PersonInQueue> peopleABQ_capacity_3_1 = new ArrayBlockingQueue<>(3);
        offerPeople(peopleABQ_capacity_3_1);

    }

    private static void addPeople(Queue people) {
        addPeople(people, false);
    }

    private static void offerPeople(Queue people) {
        addPeople(people, true);
    }
    private static void addPeople(Queue people, boolean add_offer){

        PersonInQueue person1 = new PersonInQueue(1);
        PersonInQueue person2= new PersonInQueue(2);
        PersonInQueue person3 = new PersonInQueue(3);
        PersonInQueue person4= new PersonInQueue(4);

        if (add_offer == false) {
            people.add(person3);
            people.add(person2);
            people.add(person4);
            people.add(person1);
        } else {
            System.out.println("people.offer(person3) = " + people.offer(person3));
            System.out.println("people.offer(person2) = " + people.offer(person2));
            System.out.println("people.offer(person4) = " + people.offer(person4));
            System.out.println("people.offer(person1) = " + people.offer(person1));
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


