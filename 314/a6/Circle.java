import java.util.*;
import java.io.*;

public class Circle extends Shape{
    Point a, position;
    double radius;
    Double area;
    
    public Circle(){ //circle with random constraints
        Random rand = new Random();
        a = new Point(rand.nextDouble() * 100, rand.nextDouble() * 100);
        position = a;
        radius = rand.nextDouble() * 100;
        area = this.area();
    }
    
    public Circle(Point one, double r){
        a = one;
        radius = r;
        position = a;
        area = this.area();
    }
    
    public Point position(){
        return position;
    }

    public double area(){
        return Math.PI * Math.pow(radius,2);
    }
    
    public double getradius(){
        return radius;
    }
    
    @Override
    public boolean equals(Object obj){
        if(obj == null) return false;
        if(this == obj) return true;
        if(!super.equals(obj)) return false;
        Circle shape;
        if(obj instanceof Circle){
            shape = (Circle) obj;
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
    
    public boolean congruent(Circle c){
        if(this.getradius() == c.getradius()) return true;
        return false;
    }
    
    public String toString(){
        return String.format("Circle %s %.2f", position().toString(), this.area());
    }
}