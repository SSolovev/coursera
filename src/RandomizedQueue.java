import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size;
    private Item[] arr;

    public RandomizedQueue() {
        arr = (Item[]) new Object[10];
    }                // construct an empty randomized queue

    public boolean isEmpty() {
        return size==0;
    }                // is the queue empty?

    public int size() {
        return size;
    }              // return the number of items on the queue

    public void enqueue(Item item) {
        if(size==arr.length){
            resize(size*2);
        }
        arr[size]=item;
        int randomIndex= StdRandom.uniform(size);
        swap(randomIndex,size);
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
    private void swap(int i, int j){
        Item temp = arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
    // add the item

    public Item dequeue() {
        int randomIndex= StdRandom.uniform(size);
        size--;
        swap(randomIndex,size);
        Item val=arr[size];
        arr[size]=null;
        return val;
    }         // remove and return a random item

    public Item sample() {
        int randomIndex= StdRandom.uniform(size);
        return arr[randomIndex];
    }       // return (but do not remove) a random item

    public Iterator<Item> iterator() {
    }     // return an independent iterator over items in random order

    public static void main(String[] args) {
    }   // unit testing
}