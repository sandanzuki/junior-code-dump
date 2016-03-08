package parser;

import java.util.*;
import java.io.*;

/**
 * 
 * @author StaticLightningDragons
 *
 */
public class Test {
    public static void main(String[] args) throws FileNotFoundException{
        Parser my_parser = new Parser();
        Scanner line = new Scanner(System.in);
        
        while(line.hasNextLine()){
            String input = line.nextLine();
            my_parser.parse(input);
        }
    }    
}