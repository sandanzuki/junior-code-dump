import java.util.*;
import java.io.*;

public class Point{
    double xpos;
    double ypos;
    
    public Point(double x, double y){
        xpos = x;
        ypos = y;
    }
    
    public double getx(){
        return xpos;
    }
    
    public double gety(){
        return ypos;
    }
    
    @Override
    public boolean equals(Object obj){
        if(obj == null) return false;
        if(this == obj) return true;
        if(obj instanceof Point){
            Point point = (Point) obj;
            if(this.xpos == point.getx() && this.ypos == point.gety()) return true;
        }
        return false;
    }
    
    public String toString(){
        return String.format("(%.2f,%.2f)", getx(), gety());
    }
    
}