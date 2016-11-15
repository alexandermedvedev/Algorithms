/*Dequeue. A double-ended queue or deque (pronounced "deck") is a generalization of a stack and a queue that supports adding 
    and removing items from either the front or the back of the data structure. Create a generic data type Deque that implements the following API:

*/
import java.util.Iterator;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size;
    
    private class Node{
        Item item;
        Node next;
        Node previous;
    }
    
    public Deque()  { // construct an empty deque
        first = last = null;
        size = 0;
    }
    
    public boolean isEmpty(){                 // is the deque empty?
        return (size == 0);
    }
    
    public int size(){                        // return the number of items on the deque
        return size;
    }
    
    public void addFirst(Item item){          // add the item to the front
        if (item == null){
            throw new NullPointerException();
        }
        else if (first == null)
        {
            first = new Node();
            first.item = item;
            first.next = null;
            first.previous = null;
            last = first;
            size++; 
        }
        else {
            Node firstOld = first;
            first = new Node();
            first.item = item;
            first.next = firstOld;
            first.previous = null;
            firstOld.previous = first;
            size++;
        }
    }

    public void addLast(Item item){           // add the item to the end
        if (item == null){
            throw new NullPointerException();
        }
        else if (last == null)
        {
            last = new Node();
            last.item = item;
            last.next = null;
            last.previous = null;
            first = last;
            size++; 
        }
        else {        
            Node lastOld = last;
            last = new Node();
            last.item = item;
            last.next = null;
            last.previous = lastOld;
            lastOld.next = last;
            size++;
        }
    }

    public Item removeFirst(){                // remove and return the item from the front
        if (size > 0){
            Item item = first.item;
            first = first.next;
            size--;
            return item;
        }
        else
            throw new java.util.NoSuchElementException();
    }
    
    public Item removeLast(){                 // remove and return the item from the end
        if (size > 0){
            Item item = last.item;
            last = last.previous;
            size--;
            return item;
        }
        else
            throw new java.util.NoSuchElementException();
    }
    
    public Iterator<Item> iterator() {        // return an iterator over items in order from front to end
        return new DequeueIterator();
    }
    
    private class DequeueIterator implements Iterator<Item> {
        private Node current = first;
        
        public boolean hasNext(){
            return (current != null);
        }
        
        public void remove(){
            throw new java.lang.UnsupportedOperationException();
        }
        
        public Item next(){
            if (current != null){
                Item item = current.item;
                current = current.next;
                return item;
            }
            else
                throw new java.util.NoSuchElementException();
        }
    }

    public static void main(String[] args){   // unit testing
        Deque<Integer> d = new Deque<Integer>();
        d.addLast(10);
        StdOut.println("TEST");
        d.addFirst(5);
        StdOut.println("TEST");
        d.addFirst(4);
        d.addFirst(3);
        d.addFirst(2);
        d.addFirst(1);
        d.addLast(6);
        d.addLast(7);
        d.addLast(8);
        d.addLast(9);
        d.addLast(11);
        
        StdOut.println(d.size());
        StdOut.println("TEST");
        
        for(int i : d){
        StdOut.println(i);
        }
    }
}

/*

Corner cases. Throw a java.lang.NullPointerException if the client attempts to add a null item; 
throw a java.util.NoSuchElementException if the client attempts to remove an item from an empty deque; 
throw a java.lang.UnsupportedOperationException if the client calls the remove() method in the iterator; 
throw a java.util.NoSuchElementException if the client calls the next() method in the iterator and there are no more items to return.

Performance requirements.   Your deque implementation must support each deque operation in constant worst-case time. 
    A deque containing n items must use at most 48n + 192 bytes of memory. and use space proportional to the number 
    of items currently in the deque. Additionally, your iterator implementation must support each operation (including construction) 
    in constant worst-case time.
    
*/