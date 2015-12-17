// Assignment 7, CSCE-314
// Section: 502
// Student Name: Jessica Fang
// UIN: 224003796
// Kyra (Katherine) Drake, Colin Banigan, Alexandria Stacy

import java.util.*;
import java.io.*;

public class Test1{
    public static void main(String[] args){
        Node<Integer> five = new Node<Integer>(5,null);
        Node<Integer> four = new Node<Integer>(3,five);
        Node<Integer> three = new Node<Integer>(2,four);
        Node<Integer> two = new Node<Integer>(1,three);
        Node<Integer> one = new Node<Integer>(1,two);
        
        print(one);
        System.out.println(sum(one));
    }
    
    public static <T> long sum(Node<T> list){
        long sum = 0;
        for(T e : list){
            if(e instanceof Number){
                Number a = (Number) e;
                sum += a.longValue();
            }
        }
        return sum;
    }
    
    public static <T> void print(Node<T> list){
        System.out.print("[ ");
        for(T e : list){
            System.out.print(e + " ");
        }
        System.out.println("]");
    }
    
}