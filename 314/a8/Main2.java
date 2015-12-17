// Assignment 8, CSCE-314
// Section: 502
// Student Name: Jessica Fang
// UIN: 224003796
// Kyra (Katherine) Drake, Colin Banigan, Alexandria Stacy

public class Main2{
    public static void main(String[] args){
        Timer time = new Timer();  
        Counter fifteen = new Counter(15,"\n15 second message",time); 
        Counter seven = new Counter(7,"\n7 second message",time); 

        new Thread(time).start();
        new Thread(seven).start();
        new Thread(fifteen).start(); 
    }
}