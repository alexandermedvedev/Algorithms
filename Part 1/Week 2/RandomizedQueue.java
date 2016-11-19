/*
 Randomized queue. A randomized queue is similar to a stack or queue, except that the item removed is chosen uniformly at random 
    from items in the data structure. Create a generic data type RandomizedQueue that implements the following API:
*/
import java.util.Iterator;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    Item[] elem;
    int size;
    int count;
        
    public RandomizedQueue() {                // construct an empty randomized queue
        size = 2;
        count = 0;
        elem = (Item[]) new Object[size];
    }
    
    public boolean isEmpty(){                 // is the queue empty?
        return (count == 0);
    }
    
    public int size(){                        // return the number of items on the queue
        return size;
    }
    
    public void enqueue(Item item){           // add the item
        if (item == null)
            throw new java.lang.NullPointerException();
        
        count++;
        if (count == size)
            Resize(2 * size);
        
        elem[count-1] = item;
    }
    
    public Item dequeue(){                    // remove and return a random item
        if (count == 0)
            throw new java.util.NoSuchElementException();
        
        int i = StdRandom.uniform(count);
        Item item = elem[i];
        elem[i] = elem[count-1];
        count--;
        return item;
    }
    
    public Item sample(){                     // return (but do not remove) a random item
        if (count == 0)
            throw new java.util.NoSuchElementException();
        
        int i = StdRandom.uniform(count);
        return elem[i];
    }
    
    public Iterator<Item> iterator(){         // return an independent iterator over items in random order
        StdRandom.shuffle(elem, 0, count - 1);
        return new RandomQueueIterator();
    }
    
    private class RandomQueueIterator implements Iterator<Item>{
        private int i;
        private int[] order;
        
        RandomQueueIterator(){
            i = 0;
            order = new int[count];
            
            for (int j = 0; j < count; j++)
                order[j] = j;
            
            StdRandom.shuffle(order);
        }
        
        public boolean hasNext(){
            return (i < count);
        }
        
        public void remove(){
            throw new UnsupportedOperationException();
        }
        
        public Item next(){
            if (i == count)
                throw new java.util.NoSuchElementException();
            
            return elem[order[i++]];
        }
    }
    
    private void Resize(int size){
        Item[] copy = (Item[]) new Object[size];
        for (int i = 0; i < count; i++)
            copy[i] = elem[i];
        
        elem = copy;
    }
    
    public static void main(String[] args){   // unit testing
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        rq.enqueue(1);
        rq.enqueue(2);
        rq.enqueue(3);
        rq.enqueue(4);
        for (int x = 0; x < 5; x++)
            StdOut.println(rq.sample());
        
         StdOut.println("TEST");
        StdOut.println(rq.dequeue());
        
        StdOut.println("TEST");
        
        for (int x : rq)
            StdOut.println(rq.sample());
    }
}

/*
Corner cases. The order of two or more iterators to the same randomized queue must be mutually independent; 
each iterator must maintain its own random order. 

//Throw a java.lang.NullPointerException if the client attempts to add a null item; 
//throw a java.util.NoSuchElementException if the client attempts to sample or dequeue an item from an empty randomized queue; 
//throw a java.lang.UnsupportedOperationException if the client calls the remove() method in the iterator; 
//throw a java.util.NoSuchElementException if the client calls the next() method in the iterator and there are no more items to return.

Performance requirements. Your randomized queue implementation must support each randomized queue operation 
    (besides creating an iterator) in constant amortized time. That is, any sequence of m randomized 
    queue operations (starting from an empty queue) should take at most cm steps in the worst case, 
    for some constant c. A randomized queue containing n items must use at most 48n + 192 bytes of memory. 
    Additionally, your iterator implementation must support operations next() and hasNext() 
    in constant worst-case time; and construction in linear time; you may (and will need to) use a linear amount of extra memory per iterator.
    
    */