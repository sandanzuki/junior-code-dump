import java.util.*;
import java.io.*;

public class Rectangle extends Shape{
    Point a, b, position;
    double length, width;
    Double area;
    
    public Rectangle(){ //rectangle with random constraints
        Random rand = new Random();
        a = new Point(rand.nextDouble() * 100, rand.nextDouble() * 100); 
        b = new Point(rand.nextDouble() * 100, rand.nextDouble() * 100);
        position = a;
        length = Math.abs(a.getx() - b.getx());
        width = Math.abs(a.gety() - b.gety());
        area = this.area();
    }
    
    public Rectangle(Point one, Point two){
        a = one;
        b = two;
        position = a;
        length = Math.abs(a.getx() - b.getx());
        width = Math.abs(a.gety() - b.gety());
        area = this.area();
    }
    
    public double getlength(){
        return length;
    }
    
    public double getwidth(){
        return width;
    }
    
    public Point position(){
        return position;
    }
    
    public double area(){
        return length * width;
    }
    
    @Override
    public boolean equals(Object obj){
        if(obj == null) return false;
        if(this == obj) return true;
        if(!super.equals(obj)) return false;
        Rectangle shape;
        if(obj instanceof Rectangle){
            shape = (Rectangle) obj;
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
    
    public boolean congruent(Rectangle r){
        if(this.getlength() == r.getlength() && this.getwidth() == r.getwidth()){
            return true;
        }else if(this.getlength() == r.getwidth() && this.getwidth() == r.getlength()){
            return true;
        }else{
            return false;
        }
    }
    
    public String toString(){
        return String.format("Rectangle %s %.2f", position().toString(), this.area());
    }
}