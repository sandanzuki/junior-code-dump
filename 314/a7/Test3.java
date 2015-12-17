// Assignment 7, CSCE-314
// Section: 502
// Student Name: Jessica Fang
// UIN: 224003796
// Kyra (Katherine) Drake, Colin Banigan, Alexandria Stacy

import java.util.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Collection;

public class Test3{
    public static void main(String args[]){
		Shop<Potion> monies4college = new Shop<Potion>();
        
        Vector<Potion> stock = new Vector<Potion>();
		stock.add(new HPpotion());
		stock.add(new WMPpotion());
		stock.add(new MPpotion());
		stock.add(new SMPpotion());
        
        monies4college.sell(stock);
        
        System.out.println("shop: " + monies4college);
        
        LinkedList<Potion> bag = new LinkedList<Potion>();
		monies4college.buy(2,bag);
        
		System.out.println("bag: " + bag);
        System.out.println("shop: " + monies4college);
        
        Stack<Potion> moarstock = new Stack<Potion>();
		moarstock.push(new SMPpotion());
        moarstock.push(new HPpotion());
        
        monies4college.sell(moarstock);
        System.out.println("shop: " + monies4college);
        
        monies4college.sell(new MPpotion());
		monies4college.sell(new WMPpotion());
        
        System.out.println("shop: " + monies4college);
        bag.add(monies4college.buy());
        System.out.println("bag: " + bag);
        System.out.println("shop: " + monies4college);
    }
    
    static abstract class Potion{
        String type;
        public abstract String toString();
    }

    static class MPpotion extends Potion{
        public MPpotion(){
            type = "MP";
        }

        public String toString(){
            return type + " potion";
        }
    }

    static class WMPpotion extends MPpotion{
        public WMPpotion(){
            type = "Weak MP";
        }

        public String toString(){
            return type + " potion";
        }
    }

    static class SMPpotion extends MPpotion{
        public SMPpotion(){
            type = "Strong MP";
        }

        public String toString(){
            return type + " potion";
        }
    }

    static class HPpotion extends Potion{
        public HPpotion(){
            type = "HP";
        }

        public String toString(){
            return type + " potion";
        }
    }
}

