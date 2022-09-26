import java.util.Arrays;

public class L5_MyLinkedList {
    private Node head;
    private int size;
    public void add(int value) {
        //if it's the first add within the list
        if(head == null) {
            this.head = new Node(value);
        } else {
            Node temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(new Node(value));
        }
        size++;
    }

    public int get(int index) {
        int currentIndex = 0;
        Node temp = head;

        while (temp != null) {
            if(currentIndex == index) {
                return temp.getValue();
            } else {
                temp = temp.getNext();
                currentIndex++;
            }
        }

        throw new IllegalArgumentException("Attempt to get an item with the index " + index
                + " but size of the list = " + size);
    }

    public int remove(int index) {
        int currentIndex = 0;
        Node temp = head;
        Node tempPrev = null;
        int value;

        while (temp != null) {
            if( currentIndex == index) {
                if(tempPrev != null) {
                    tempPrev.setNext(temp.getNext());
                } else {
                    head = temp.getNext();
                }
                size--;
                return temp.getValue();
            } else {
                tempPrev = temp;
                temp = temp.getNext();
                currentIndex++;
            }
        }
        throw new IllegalArgumentException("Attempt to remove an item with the index " + index
                + " but size of the list = " + size);
    }

    public String toString() {
        int [] result = new int[size];

        int idx = 0;
        Node temp = head;

        while (temp != null) {
            result[idx++] = temp.getValue();
            temp = temp.getNext();
        }

        return Arrays.toString(result);
    }

    private static class Node {
        private int value;
        private Node next;
        public Node(int value) {
            this.value = value;
        }
        public int getValue() {
            return value;
        }
        public void setValue(int value) {
            this.value = value;
        }
        public Node getNext() {
            return next;
        }
        public void setNext(Node next) {
            this.next = next;
        }
    }
}
