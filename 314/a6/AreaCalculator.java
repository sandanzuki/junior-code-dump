import java.util.*;
import java.io.*;

public class AreaCalculator{
    public double calculate(Shape[] shapes){
        double sum = 0;
        for(Shape current : shapes){
            if(current != null) sum += current.area();
        }
        return sum;
    }
    
}