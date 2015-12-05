// Assignment 6, CSCE-314
// Section: 502
// Student Name: Jessica Fang
// UIN: 224003796
// Kyra (Katherine) Drake, Colin Banigan, Alexandria Stacy

import java.util.*;
import java.util.regex.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.*;

public class Main{
    public static void main(String[] args){
        //Scanner keyboard = new Scanner(System.in);
        Shape[] allshapes;
        AreaCalculator calculations = new AreaCalculator();
        int numtri = 0;
        int numrect = 0;
        int numsq = 0;
        int numcirc = 0;
        int numline = 0;
        
        //System.out.println("Enter a command [R-andom or S-pecified]: ");
        String input = args[0];//keyboard.nextLine();
        
        if(Character.toUpperCase(input.charAt(0)) == 'R' && input.charAt(1) == ' '){ 
            String[] howmany = input.split("\\s+");
            if(howmany.length != 2){
                System.out.println("no.");
            }else{
                int n = Integer.parseInt(howmany[1]);
                allshapes = new Shape[n];
                while(n > 0){ //loop n times creating a random shape
                    Random rand = new Random();
                    switch(rand.nextInt(1000)%5){
                        case 0:
                            allshapes[n-1] = new Triangle();
                            numtri++;
                            System.out.println(allshapes[n-1]);
                            break;
                        case 1:
                            allshapes[n-1] = new Rectangle();
                            numrect++;
                            System.out.println(allshapes[n-1]);
                            break;
                        case 2:
                            allshapes[n-1] = new Square();
                            numsq++;
                            System.out.println(allshapes[n-1]);
                            break;
                        case 3:
                            allshapes[n-1] = new Circle();
                            numcirc++;
                            System.out.println(allshapes[n-1]);
                            break;
                        case 4:
                            allshapes[n-1] = new LineSegment();
                            numline++;
                            System.out.println(allshapes[n-1]);
                            break;
                        default:
                            System.out.println("404 not found"); //haha i'm so funny.
                    }
                    n--;
                }
            
                //call area calculator and print out total area
                System.out.printf("%d triangles, %d rectangles, %d squares, %d circles, %d line segments have a total area of %.2f\n", numtri, numrect, numsq, numcirc, numline, calculations.calculate(allshapes));
                sort(allshapes, allshapes.length);
                for(Shape temp : allshapes){
                    System.out.println(temp);
                }
                System.out.println("*** To test equals()");
                for(int i = 0; i < n; i++){
                    for(int j = i + 1; j < n; j++){
                        if(allshapes[i].equals(allshapes[j])) System.out.println(allshapes[i].toString() + " equals to " + allshapes[j].toString());
                    }
                }
            }
        }else if(Character.toUpperCase(input.charAt(0)) == 'S' && input.charAt(1) == ' '){
            input = input.substring(2); // get rid of command
            int counter = 0;
            //delimit input at ;
            String[] line = input.split(";");
            allshapes = new Shape[line.length];
            
            for(String temp : line){
                if(temp.charAt(0) == ' ') temp = temp.substring(1); // get rid of leading space
                String[] constraints = temp.split("\\s+"); // information for shape as array
                String shapetype = constraints[0].toUpperCase();
                if(constraints.length == 7 && shapetype.length() == 1 && shapetype.equals("T")){
                    double x1 = Double.parseDouble(constraints[1]);
                    double y1 = Double.parseDouble(constraints[2]);
                    double x2 = Double.parseDouble(constraints[3]);
                    double y2 = Double.parseDouble(constraints[4]);
                    double x3 = Double.parseDouble(constraints[5]);
                    double y3 = Double.parseDouble(constraints[6]);
                    Triangle shapetemp = new Triangle(new Point(x1,y1), new Point(x2,y2), new Point(x3,y3));
                    if(x1 < 0 || y1 < 0 || x2 < 0 || y2 < 0 || x3 < 0 || y3 < 0 || shapetemp.area() == 0){
                        System.out.printf("%s skipped; invalid shape or position extra credit \n", temp);
                        counter--;
                    }else{
                        allshapes[counter] = shapetemp; 
                        numtri++;
                        System.out.println(allshapes[counter]);
                    }
                }else if(constraints.length == 5 && shapetype.length() == 1 && shapetype.equals("R")){
                    double x1 = Double.parseDouble(constraints[1]);
                    double y1 = Double.parseDouble(constraints[2]);
                    double x2 = Double.parseDouble(constraints[3]);
                    double y2 = Double.parseDouble(constraints[4]);
                    Rectangle shapetemp = new Rectangle(new Point(x1,y1), new Point(x2,y2));
                    if(x1 >= x2 || y1 >= y2 || x1 < 0 || y1 < 0 || x2 < 0 || y2 < 0 || shapetemp.area() == 0){
                        System.out.printf("%s skipped; invalid shape or position extra credit \n", temp);
                        counter--;
                    }else{
                        allshapes[counter] = shapetemp;
                        numrect++;
                        System.out.println(allshapes[counter]);
                    }
                }else if(constraints.length == 4 &&shapetype.length() == 1 && shapetype.equals("S")){
                    double x = Double.parseDouble(constraints[1]);
                    double y = Double.parseDouble(constraints[2]);
                    double length = Double.parseDouble(constraints[3]);
                    Square shapetemp = new Square(new Point(x,y), length);
                    if(x < 0 || y < 0 || length < 0 || shapetemp.area() == 0){
                        System.out.printf("%s skipped; invalid shape or position extra credit \n", temp);
                        counter--;
                    }else{
                        allshapes[counter] = shapetemp;
                        numsq++;
                        System.out.println(allshapes[counter]);
                    }
                }else if(constraints.length == 4 && shapetype.length() == 1 && shapetype.equals("C")){
                    double x = Double.parseDouble(constraints[1]);
                    double y = Double.parseDouble(constraints[2]);
                    double radius = Double.parseDouble(constraints[3]);
                    Circle shapetemp = new Circle(new Point(x,y), radius);
                    if(x < 0 || y < 0 || radius < 0 || shapetemp.area() == 0){
                        System.out.printf("%s skipped; invalid shape or position extra credit \n", temp);
                        counter--;
                    }else{
                        allshapes[counter] = shapetemp;
                        numcirc++;
                        System.out.println(allshapes[counter]);
                    }
                }else if(constraints.length == 5 && shapetype.length() == 1 && shapetype.equals("L")){
                    double x1 = Double.parseDouble(constraints[1]);
                    double y1 = Double.parseDouble(constraints[2]);
                    double x2 = Double.parseDouble(constraints[3]);
                    double y2 = Double.parseDouble(constraints[4]);
                    LineSegment shapetemp = new LineSegment(new Point(x1,y1), new Point(x2,y2));
                    if(x1 < 0 || y1 < 0 || x2 < 0 || y2 < 0 || shapetemp.getlength() == 0){
                        System.out.printf("%s skipped; invalid shape or position extra credit \n", temp);
                        counter--;
                    }else{
                        allshapes[counter] = shapetemp;
                        numline++;
                        System.out.println(allshapes[counter]);
                    }
                }else{
                    System.out.printf("%s skipped; invalid shape or invalid number of arguments\n", temp);
                    counter--;
                }
                counter++;
            }
            //call area calculator and print out total area
            System.out.printf("%d triangles, %d rectangles, %d squares, %d circles, %d line segments have a total area of %.2f\n", numtri, numrect, numsq, numcirc, numline, calculations.calculate(allshapes));
            sort(allshapes,counter);
            for(Shape temp : allshapes){
                if(temp!= null) System.out.println(temp);
            }
            System.out.println("*** To test equals()");
            for(int i = 0; i < counter; i++){
                    for(int j = i+1; j < counter; j++){
                        if(allshapes[i].equals(allshapes[j])) System.out.println(allshapes[i].toString() + " equals to " + allshapes[j].toString());
                    }
            }
        }else{
            System.out.printf("invalid command: %s. \n", input);
        }
    }
    
    public static void sort(Shape[] shapes, int n){
        boolean flag = true;
        Shape temp;

        while(flag){
            flag = false;
            for(int i = 0; i < n-1; i++){
                if(shapes[i].compareTo(shapes[i+1]) == 1){
                    temp = shapes[i];
                    shapes[i] = shapes[i+1];
                    shapes[i+1] = temp;
                    flag = true; //swapped
                } 
            } 
        }
    } 
}