import java.util.*;
import java.io.*;

public class LineSegment extends Shape{
    Point a, b, position;
    double length;
    Double area;
    
    public LineSegment(){ //line segment with random constraints
        Random rand = new Random();
        a = new Point(rand.nextDouble() * 100, rand.nextDouble() * 100); 
        b = new Point(rand.nextDouble() * 100, rand.nextDouble() * 100);
        position = a;
        length = Math.sqrt(Math.pow(b.getx() - a.getx(),2) + Math.pow(b.gety() - a.gety(),2));
        area = this.area();
    }
    
    public LineSegment(Point one, Point two){
        a = one;
        b = two;
        position = a;
        length = Math.sqrt(Math.pow(b.getx() - a.getx(),2) + Math.pow(b.gety() - a.gety(),2));
        area = this.area();
    }
    
    public Point position(){
        return position;
    }
    
    public double area(){
        return 0;
    }
    
    public double getlength(){
        return length;
    }
    
   @Override
    public boolean equals(Object obj){
        if(obj == null) return false;
        if(this == obj) return true;
        if(!super.equals(obj)) return false;
        LineSegment shape;
        if(obj instanceof LineSegment){
            shape = (LineSegment) obj;
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
    
    public boolean congruent(LineSegment l){
        if(this.getlength() == l.getlength()) return true;
        return false;
    }
    
    public String toString(){
        return String.format("LineSegment %s %.2f", position().toString(), this.area());
    }
}