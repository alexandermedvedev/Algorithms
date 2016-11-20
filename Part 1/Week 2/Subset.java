import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class Subset {
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        int k = Integer.parseInt(args[0]);
        String s;
        
        while(!StdIn.isEmpty()){
            s = StdIn.readString();
            rq.enqueue(s);
        }
        //String[] s = StdIn.readAllStrings();


        //for (int i = 1; i < args.length; i++)
        //    rq.enqueue(StdIn.readString());


        for (int i = 0; i < k; i++)
            StdOut.println(rq.dequeue());
    }
}
