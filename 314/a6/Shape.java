import java.util.*;
import java.io.*;

public abstract class Shape implements Comparable{
    public abstract double area();
    public abstract Point position();
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Shape) return true;
        return false;
    }
        
    @Override
	public abstract int hashCode();
    @Override
    public abstract String toString();
    
    @Override
    public int compareTo(Object obj){
        if(obj instanceof Shape){
            Shape shape = (Shape) obj;
            if(this.area() > shape.area()) return 1;
            else if(this.area() == shape.area()) return 0;
            else return -1;
        }else{
            return 9999999;
        }
    }
}