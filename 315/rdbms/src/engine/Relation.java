/**
 * The Relation Class, meant to represent Tables and contains a list of
 * Attributes defining its column names and types, as well as a list of
 * Tuples defining the contents of the Relations
 * 
 * @author StaticLightningDragons
 */
package engine;

import java.util.*;

// the table
public class Relation{
    /**
     * {@value name} String for the name of the table
     * {@value attributes} ArrayList containing the Attributes defining each column
     * {@value tuples} ArrayList containing the tuples defining the row contents
     */
    private String name;
    private ArrayList<Attribute> attributes;
    private ArrayList<Tuple> tuples;
    
    /**
     * Constructor
     *
     * @param n String name of the Relation
     */
    public Relation(String n) {
        name = n;
        attributes = new ArrayList<Attribute>();
        tuples = new ArrayList<Tuple>();
	}
    
    /**
     * Copy constructor
     *
     * @param r Relation to make a copy of
     */
    public Relation(Relation r) {
        name = r.get_name();
        attributes = r.get_attributes();
        tuples = r.get_tuples();
	}
    
    /**
     * Copy a relation
     *
     * @param r Relation to make a copy of
     */
    public void set_relation_to(Relation r) {
        this.set_attributes(r.get_attributes());
        this.tuples = r.get_tuples();
    }
    
    /**
     * Get the name of the relation
     *
     * @return Returns the name of the relation
     */
    public String get_name() {
        return name;
    }

    /**
     * Get an arraylist containing all the tuples in the relation
     *
     * @return Returns an arrayList of the tuples in the relation
     */
    public ArrayList<Tuple> get_tuples() {
        return tuples;
    }
    
    /**
     * Get the attributes of the relation
     *
     * @return Returns an arrayList of the attributes of the relation
     */
    public ArrayList<Attribute> get_attributes() {
        return attributes;
    }
    
    /**
     * Get a specific attribute
     *
     * @param n String name identifying Attribute
     * @return Returns the attribute with the name specified if success, null if fail
     */
    public Attribute get_attribute(String n) {
        for (Attribute a : attributes) {
            if (a.get_name().equals(n)) {
                return a;
            }
        }
        return null;
    }
    
    /**
     * Set the attribute list to the given an attribute list
     *
     * @param a Attribute list to be copied to this Relation
     * @return Returns 0 if success, -1 if failed
     */
    public int set_attributes(ArrayList<Attribute> a) {
        try {
            attributes = a;
        } catch (Exception e) {
            return -1;
        }
        return 0;
    }
    
    /**
     * Adds a tuple to a Relation's ArrayList of Tuples while
     * checking if types match types of ArrayList of Attributes
     *
     * @param t Tuple of data members being added to a Relation
     * @return Returns 0 if success, -1 if failed
     */
    public int add_tuple(Tuple t) {
        if (t.get_values().size() != attributes.size()) {
            System.out.println("invalid tuple");
            return -1;
        }
        
        for (int i = 0; i < attributes.size(); i++) {
            if (attributes.get(i).get_type() == Attribute.Type.VARCHAR) {
                // This does not need to do anything, because tuples.getvalues() is already all Strings
            } else { 
                //This tries to convert the String entered to an Integer, throws exception if fails
                try {
                    Integer.valueOf(t.get_values().get(i));
                } catch(IllegalArgumentException e) {
                    System.out.println("ERROR: tuple contains invalid type");
                }
            }
        }
        tuples.add(t);
        return 0;
    }
    
    /**
     * Deletes a Tuple from this Relation
     * 
     * @param t Tuple being deleted
     * @return Returns 0 if success, -1 if failed
     */
    public int delete_tuple(Tuple t) {
        try {
            tuples.remove(t);
        } catch(Exception e) {
            return -1;
        }
        return 0;
    }
    
    /**
     * Displays a Relation Table by traversing its list of Attributes
     * and list of Tuples and calling formated print functions
     */
    public void display() {
        //System.out.println("Relation: " +  name);
        for (Attribute a : attributes) {
            System.out.printf("%-25s", a.get_name());
        }
        
        System.out.println();
        for (int i = 0; i < attributes.size()*20; i++) System.out.print("-");
        System.out.println();
        for (Tuple t : tuples) {
            ArrayList<String> tmp_array = t.get_values();
            for (String s : tmp_array) {
                System.out.printf("%-25s", s);
            }
            System.out.println(); // after each tuple
        }
        System.out.println(); //after whole table
    }
}