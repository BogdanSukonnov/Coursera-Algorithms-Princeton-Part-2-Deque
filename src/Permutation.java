import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public final class Permutation {

    private Permutation() {
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException("expected one int argument - number of items to print!");
        }
        int numberOfItemsToPrint = Integer.parseInt(args[0]);
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            randomizedQueue.enqueue(StdIn.readString());
        }
        Iterator<String> iterator = randomizedQueue.iterator();
        for (int repetition = 1; repetition <= numberOfItemsToPrint; ++repetition) {
            StdOut.println(iterator.next());
        }
    }
}
