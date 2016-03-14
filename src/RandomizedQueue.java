import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size;
    private Item[] arr;

    public RandomizedQueue() {
        arr = (Item[]) new Object[10];
    }                // construct an empty randomized queue

    public boolean isEmpty() {
        return size == 0;
    }                // is the queue empty?

    public int size() {
        return size;
    }              // return the number of items on the queue

    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (size == arr.length) {
            resize(size * 2);
        }
        arr[size] = item;
        int randomIndex = 0;
        if (size > 1) {
            randomIndex = StdRandom.uniform(size);
        }
        swap(randomIndex, size);
        size++;

    }

    private void resize(int capacity) {
        assert capacity >= size;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            temp[i] = arr[i];
        }
        arr = temp;
    }

    private void swap(int i, int j) {
        Item temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    // add the item

    public Item dequeue() {
        if (size <= 0) {
            throw new NoSuchElementException();
        }
        int randomIndex = StdRandom.uniform(size);
        size--;
        swap(randomIndex, size);
        Item val = arr[size];
        arr[size] = null;
        return val;
    }         // remove and return a random item

    public Item sample() {
        if (size <= 0) {
            throw new NoSuchElementException();
        }
        int randomIndex = StdRandom.uniform(size);
        return arr[randomIndex];
    }       // return (but do not remove) a random item

    public Iterator<Item> iterator() {
        return new RandomIter();
    }     // return an independent iterator over items in random order

    private class RandomIter implements Iterator<Item> {
        private int pointer = 0;
        private int[] innerArr;


        public RandomIter() {
            innerArr = new int[size];

        }

//        private void resizeIter(int capacity) {
////            assert capacity >= size;
//            int[] temp = new int[capacity];
//            for (int i = 0; i < size; i++) {
//                temp[i] = innerArr[i];
//            }
//            innerArr = temp;
//        }

        @Override
        public boolean hasNext() {
            return pointer < (size - 1);
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Error!");
            }

            int index = 0;
            do {
                index = StdRandom.uniform(pointer + 1);
            } while (innerArr[index] == -1);
            innerArr[index] = -1;
            pointer++;
            return arr[index];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Error!");
        }
    }

    public static void main(String[] args) {


    }   // unit testing
}