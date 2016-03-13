import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private class Node {
        private Item current;
        private Node next;
        private Node previous;

        public Node() {
        }

        public Node(Item current, Node next, Node previous) {
            this.current = current;
            this.next = next;
            this.previous = previous;
        }
    }

    private Node first;
    private Node last;
    private int size;

    public Deque() {
    }
    // construct an empty deque

    public boolean isEmpty() {
        return first == null;
    }              // is the deque empty?

    public int size() {
        return size;
    }                  // return the number of items on the deque

    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException("Error!");
        }
        if (first == null) {
            first = last = new Node(item, null, null);
        } else {
            Node oldFirst = first;
            first = new Node(item, null, oldFirst);
            oldFirst.next = first;
        }
        size++;
    }         // add the item to the front

    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException("Error!");
        }
        if (last == null) {
            first = last = new Node(item, null, null);
        } else {
            Node oldLast = last;
            last = new Node(item, oldLast, null);
        }
        size++;
    }         // add the item to the end

    public Item removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException("Error!");
        }
        Item removed = first.current;
        first = first.previous;
        if (first != null) {
            first.next = null;
        }
        size--;
        return removed;
    }               // remove and return the item from the front

    public Item removeLast() {
        if (size == 0) {
            throw new NoSuchElementException("Error!");
        }
        Item removed = last.current;
        last = last.next;
        if (last != null) {
            last.previous = null;
        }

        size--;
        return removed;
    }             // remove and return the item from the end

    public Iterator<Item> iterator() {
        return new DequeIter();
    }      // return an iterator over items in order from front to end

    private class DequeIter implements Iterator<Item> {
       private Node current = first;

        @Override
        public boolean hasNext() {
            if (current != null && current.previous != null) {
                return true;
            }
            return false;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Error!");
            }
            Item cur= current.current;
            current = current.previous;
            return cur;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Error!");
        }
    }

    public static void main(String[] args) {

        Deque<Integer> queue = new Deque<Integer>();
        for (int i = 0; i < 15; i++) {
            queue.addFirst(i);
        }
        System.out.println(queue.size());
        Iterator iter = queue.iterator();

        while (iter.hasNext()){
            System.out.println(iter.next());
        }
        for (int i = 0; i < 5; i++) {
            queue.addFirst(i);
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(queue.removeLast());
        }
//        for (Integer v : queue) {
//            System.out.println("Outer v: " + v);
////            for (Integer v2 : queue) {
////                System.out.println("Inner v: " + v2);
////            }
//        }
    }  // unit testing
}