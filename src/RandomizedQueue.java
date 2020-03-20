import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public final class RandomizedQueue<Item> implements Iterable<Item> {

    private static final short GROW_FACTOR = 2;
    private static final short CUTTING_THRESHOLDS_PERCENT = 25;
    private static final short CUTTING_SIZE_PERCENT = 50;
    private Object[] array;
    private int size;

    // construct an empty randomized queue

    public RandomizedQueue() {
        array = (Item[]) new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("enque with null");
        }
        if (size == array.length) {
            // enlarge
            Object[] oldArray = array;
            array = new Object[size * GROW_FACTOR];
            copyArray(array, oldArray);
        }
        array[size] = item;
        ++size;
    }

    // remove and return a random item
    public Item dequeue() {
        if (size == 0) {
            throw new NoSuchElementException("dequeue for empty");
        }
        int indexToDeque = StdRandom.uniform(size);
        Item toReturn = (Item) array[indexToDeque];
        array[indexToDeque] = array[size - 1];
        array[size - 1] = null;
        --size;
        if (size < array.length * CUTTING_THRESHOLDS_PERCENT / 100) {
            // cut
            Object[] oldArray = array;
            array = new Object[array.length * CUTTING_SIZE_PERCENT / 100];
            copyArray(array, oldArray);
        }
        return toReturn;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (size == 0) {
            throw new NoSuchElementException("sample for empty");
        }
        return (Item) array[StdRandom.uniform(size)];
    }

    // return an independent iterator over items in random order
    @Override
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        randomizedQueue.enqueue("First");
        randomizedQueue.enqueue("Second");
        randomizedQueue.enqueue("Third");
        StdOut.printf("created queue with %s elements, sample - %s\n", randomizedQueue.size(), randomizedQueue.sample());
        Iterator<String> iterator = randomizedQueue.iterator();
        while (iterator.hasNext()) {
            StdOut.printf("%s\n", iterator.next());
        }
        StdOut.printf("dequeue %s now %s\n", randomizedQueue.dequeue(), randomizedQueue.size());
        StdOut.printf("dequeue %s now %s\n", randomizedQueue.dequeue(), randomizedQueue.size());
    }

    private void copyArray(Object[] destination, Object[] source) {
        for (int index = 0; index < size; ++index) {
            destination[index] = source[index];
        }
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private final Object[] iteratorArray;
        private int nextIndex = 0;

        RandomizedQueueIterator() {
            iteratorArray = new Object[size];
            copyArray(iteratorArray, array);
            StdRandom.shuffle(iteratorArray);
        }

        @Override
        public boolean hasNext() {
            return nextIndex < iteratorArray.length;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("next when no next");
            }
            return (Item) iteratorArray[nextIndex++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("can't remove through iterator");
        }
    }
}
