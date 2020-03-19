import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int size;

    // construct an empty deque
    public Deque() {
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        Node node = new Node(item);
        node.next = first;
        first = node;
        if (first.next != null) {
            first.next.previous = first;
        }
        if (node.next == null) {
            last = node;
        }
    }

    // add the item to the back
    public void addLast(Item item) {
        Node node = new Node(item);
        node.previous = last;
        last = node;
        if (last.previous != null) {
            last.previous.next = last;
        }
        if (first == null) {
            first = node;
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (first == null) {
            return null;
        }
        Node previousFirst = first;
        first = previousFirst.next;
        if (first == null) {
            last = null;
        } else {
            first.previous = null;
        }
        return previousFirst.value;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (last == null) {
            return null;
        }
        Node previousLast = last;
        last = previousLast.previous;
        if (last == null) {
            first = null;
        } else {
            last.next = null;
        }
        return previousLast.value;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return null;
    }

    private class Node {
        Item value;
        Node next;
        Node previous;

        Node(Item value) {
            this.value = value;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
    }
}
