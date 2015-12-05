import java.util.*;
import java.io.*;

public class Triangle extends Shape{
    Point a, b, c, position;
    double ab, bc, ca;
    Double area;
    
    public Triangle(){ //triangle with random constraints
        Random rand = new Random();
        a = new Point(rand.nextDouble() * 100, rand.nextDouble() * 100); //one 
        b = new Point(rand.nextDouble() * 100, rand.nextDouble() * 100); //two
        c = new Point(rand.nextDouble() * 100, rand.nextDouble() * 100); //three
        position = a;
        ab = Math.sqrt(Math.pow(b.getx() - a.getx(),2) + Math.pow(b.gety() - a.gety(),2));
        bc = Math.sqrt(Math.pow(c.getx() - b.getx(),2) + Math.pow(c.gety() - b.gety(),2));
        ca = Math.sqrt(Math.pow(a.getx() - c.getx(),2) + Math.pow(a.gety() - c.gety(),2));
        area = this.area();
    }
    
    public Triangle(Point one, Point two, Point three){
        a = one;
        b = two;
        c = three;
        position = a;
        area = this.area();
    }
    
    public Point position(){
        return position;
    }
    
    public double area(){
        double ax = a.getx()*(b.gety() - c.gety());
        double bx = b.getx()*(c.gety() - a.gety());
        double cx = c.getx()*(a.gety() - b.gety());
        
        return Math.abs(ax+bx+cx)/2;
    }
    
    public Point geta(){
        return a;
    }
    
    public Point getb(){
        return b;
    }
    
    public Point getc(){
        return c;
    }
    
    public double getab(){
        return ab;
    }
    
    public double getbc(){
        return bc;
    }
    
    public double getca(){
        return ca;
    }
    
    @Override
    public boolean equals(Object obj){
        if(obj == null) return false;
        if(this == obj) return true;
        if(!super.equals(obj)) return false;
        Triangle shape;
        if(obj instanceof Triangle){
            shape = (Triangle) obj;
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
    
    public boolean congruent(Triangle t){
        if(this.getab() == t.getab() && this.getbc() == t.getbc() && this.getca() == t.getca()){
            return true;
        }else if(this.getab() == t.getbc() && this.getbc() == t.getca() && this.getca() == t.getab()){
            return true;
        }else if(this.getab() == t.getca() && this.getbc() == t.getab() && this.getca() == t.getbc()){
            return true;
        }else{
            return false;
        }
    }
    
    public String toString(){
        return String.format("Triangle %s %.2f", position().toString(), this.area());
    }
}