// Assignment 7, CSCE-314
// Section: 502
// Student Name: Jessica Fang
// UIN: 224003796
// Kyra (Katherine) Drake, Colin Banigan, Alexandria Stacy

import java.util.*;
import java.io.*;

public class Test2{
    public static void main(String[] args){
        LinkedList<Integer> empty_list = new LinkedList<Integer>();
        LinkedList<Integer> list = new LinkedList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6));

        System.out.println(empty_list);
        System.out.println(empty_list.reverse());
        System.out.println(list);
        System.out.println(list.reverse());

        int sum = 0;
        for (int e : list) { sum += e; }
        System.out.println(sum);        
    }
    
}