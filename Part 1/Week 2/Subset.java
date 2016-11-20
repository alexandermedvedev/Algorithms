package com.company;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class Subset {
    public static void main(String[] args) {
        int k = StdIn.readInt();

        RandomizedQueue<String> rq = new RandomizedQueue<String>();

        //while(!StdIn.isEmpty())
        //    rq.enqueue(StdIn.readString());

        String[] s = StdIn.readAllStrings();


        for (int i = 1; i < args.length; i++)
            rq.enqueue(StdIn.readString());


        for (int i = 0; i < k; i++)
            StdOut.println(rq.dequeue());
    }
}
