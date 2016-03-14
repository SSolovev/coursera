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
            first = new Node(item, null, null);
            last = first;
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
            first = new Node(item, null, null);
            last = first;
        } else {
            Node oldLast = last;
            last = new Node(item, oldLast, null);
            oldLast.previous=last;
        }
        size++;
    }         // add the item to the end

    public Item removeFirst() {
        if (size <= 0) {
            throw new NoSuchElementException("Error!");
        }
        Item removed = first.current;
        if (size == 1) {
            first = null;
            last = null;
        } else {

            first = first.previous;
            if (first != null) {
                first.next = null;
            }
        }
        size--;
        return removed;
    }               // remove and return the item from the front

    public Item removeLast() {
        if (size <= 0) {
            throw new NoSuchElementException("Error!");
        }
        Item removed = last.current;
        if (size == 1) {
            first = null;
            last = null;
        } else {
            last = last.next;
            if (last != null) {
                last.previous = null;
            }
        }
        size--;
        return removed;
    }             // remove and return the item from the end

    public Iterator<Item> iterator() {
        return new DequeIter();
    }      // return an iterator over items in order from front to end

    private class DequeIter implements Iterator<Item> {
        private Node current = null;
        private boolean pointerFresh = true;

        @Override
        public boolean hasNext() {
            if (pointerFresh && size > 0) {
                current = first;
                pointerFresh = false;
                return true;
            } else {
                return current != null;
            }
//            if(pointerFresh && current==null){
//                current=first;
//            }
//            if (current != null && current.previous != null) {
//                return true;
//            }
//            return false;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Error!");
            }

            Item cur = current.current;
            current = current.previous;
            return cur;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Error!");
        }
    }

    public static void main(String[] args) {
Deque deque = new Deque();
        deque.isEmpty();
        deque.addLast(1);
        deque.addLast(2);
        deque.removeFirst();
        deque.addLast(4);
        deque.removeFirst();


    }  // unit testing
}