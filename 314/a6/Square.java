import java.util.*;
import java.io.*;

public class Square extends Shape{
    Point a, position;
    double length;
    Double area;
    
    public Square(){ //square with random constraints
        Random rand = new Random();
        a = new Point(rand.nextDouble() * 100, rand.nextDouble() * 100);
        position = a;
        length = rand.nextDouble() * 100;
        area = this.area();
    }
    
    public Square(Point one, double l){
        a = one;
        length = l;
        position = a;
        area = this.area();
    }
    
    public Point position(){
        return position;
    }
    
    public double area(){
        return Math.pow(length,2);
    }
    
    public double getlength(){
        return length;
    }
    
    @Override
    public boolean equals(Object obj){
        if(obj == null) return false;
        if(this == obj) return true;
        if(!super.equals(obj)) return false;
        Square shape;
        if(obj instanceof Square){
            shape = (Square) obj;
            if(this.position().equals(shape.position()) && this.congruent(shape)) return true;
        }
        return false;
    }
    
    public int hashCode(){
		int hash = 13;
		hash = 7 * hash + this.position.hashCode();
		hash = 7 * hash + this.area.hashCode();
		return hash;
	}
    
    public boolean congruent(Square s){
        if(this.getlength() == s.getlength()) return true;
        return false;
    }
    
    public String toString(){
        return String.format("Square %s %.2f", position().toString(), this.area());
    }
}