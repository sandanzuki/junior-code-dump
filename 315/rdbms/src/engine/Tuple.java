/**
 * This Class Tuple defines an ArrayList of String representing data
 * in the rows of the Relations.
 * 
 * @author StaticLightningDragons
 */
package engine;

import java.util.*;
import java.lang.*;

public class Tuple{
    /**
     * {@value values} ArrayList of Strings containing the values of a tuple
     */
    private ArrayList<String> values;
    
    /**
     * Constructor
     * 
     * @param v ArrayList of Strings
     */
    public Tuple(ArrayList<String> v) {
        values = v;
    }
    
    /**
     * Copy constructor
     * 
     * @param t Tuple to make a copy of
     */
    public Tuple(Tuple t) {
        this.values = t.values;
    }
    
    /**
     * Get the contents of a tuple
     *
     * @return Returns an arraylist of strings defining the contents of a tuple
     */
    public ArrayList<String> get_values() {
        return values;
    }
    
    /**
     * Set the list of values in a tuple to a the given list of values
     *
     * @param v ArrayList of Strings to set to Tuple values
     */
    public void set_values(ArrayList<String> v) {
        values = v;
    }
    
    /**
     * Override hashCode function, produce a hash code to uniquely identify each tuple
     *
     * @return Returns the hash value for the tuple
     */
    @Override
    public int hashCode() {
        int hash = 5;
        for (String e : values) hash = 89*hash + (e != null ? e.hashCode() : 0);
        return hash;
    }
    
    /**
     * Override equals function, check if two tuples are equal
     *
     * @param obj Object being compared to this Tuple
     * @return Returns true if the tuples are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Tuple)) return false;
        if (obj == this) return true;
        return this.values.equals(((Tuple) obj).get_values());
    }
}