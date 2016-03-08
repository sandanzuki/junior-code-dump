/**
 * This is the Engine, which handles the storage of all Relations Tables and
 * other functions related to them such as insertion of rows, manipulation of 
 * table elements, and algebraic functions handling those Relations
 * 
 * @author StaticLightningDragons
 */
package engine;

import java.util.*;
import java.io.*;

public class Engine{
    /**
     * {@value all_relations} HashMap of all relations kept in the engine
     * {@value f} File to read from or write to
     */
    HashMap<String, Relation> all_relations;
    File f;
    
    /**
     * Constructor
     */
    public Engine() {
        all_relations = new HashMap<String, Relation>(); // where string is the name of the table
    }
    
    /**
     * Retrieve all currently stored relations
     *
     * @return Returns the hashmap of relations
     */
    public HashMap<String, Relation> get_all_relations() {
        return all_relations;
    }
    
    /**
     * Retrieve a specified relation
     *
     * @param name Name of relation
     * @return Returns the relation with the specified name
     */
    public Relation get_relation(String name) {
        return all_relations.get(name);
    }
    
    /**
     * Attach a path to a file object
     *
     * @param name Name of the file
     * @return Returns newly created File object
     */
    public File attach_file(String name) { // attach a path to a file object
        f = new File(name);
        return f;
    }
    
    /**
     * Saves the contents of the Engine to the private file member f
     *
     * @param name String name of the file to save to
     * @throws IOException if file is not made correctly
     */
    public void write(String name) throws IOException{
        //String s = name;
        if (!name.endsWith(".db")) name = name + ".db";
        f = new File(name);
        FileWriter fw = new FileWriter(f.getAbsoluteFile());
        BufferedWriter output = new BufferedWriter(fw);
            
        for (String s : all_relations.keySet()) {
            output.write('*' + s);//* identifies a relation line
            output.newLine();
            
            ArrayList<Attribute> tmp_attribute_list = all_relations.get(s).get_attributes();
            for (Attribute a : tmp_attribute_list) {
                output.write(a.get_name()+"\\");
                if (a.get_type() == Attribute.Type.VARCHAR)
                    output.write("VARCHAR"+"\\");
                else
                    output.write("INTEGER"+"\\");
                if (a.get_primary())
                    output.write("true"+"\\");
                else
                    output.write("false"+"\\");
            }
            output.write('&');//& identifies an attribute line
            output.newLine();
            
            ArrayList<Tuple> tmp_tuple_list = all_relations.get(s).get_tuples();
            for (Tuple t : tmp_tuple_list) {
                ArrayList<String> tmp_string_list = t.get_values();
                output.write('-');//- identifies a tuple line
                for (String t_s : tmp_string_list) {
                    output.write(t_s+"\\");
                }
                output.newLine();
            }
        }
        output.close();
    }
    
    /**
     * Loads the contents of a file in our specified format and creates relations based on the contents of the file
     *
     * @param name String name of the file
     * @throws FileNotFoundException Thrown if OPEN argument is not found
     */
    public void open(String name) throws FileNotFoundException { // load the contents of a file
        if (!name.endsWith(".db")) name = name + ".db";
         f = new File(name);
         try {
            Scanner loader = new Scanner(f);
            String relation_name = "";
            while (loader.hasNextLine()) {
                String input = loader.nextLine();
                if (input.contains("*")) {     
                    relation_name = input.substring(1,input.length());
                    create_relation(relation_name);
                }
                else if (input.contains("&")) {//IS AN ATTRIBUTE DEFINITION LINE
                    String attribute_data = input.substring(0, input.length()-1);
                    ArrayList<Attribute> attribute_list = new ArrayList<Attribute>();
                    while (attribute_data.contains("\\")) {//THIS WHILE LOOP GETS THE ATTRIBUTE DATA FOR A RELATION
                        String attribute_name;
                        Attribute.Type attribute_type;
                        boolean attribute_key;
                        
                        int i = attribute_data.indexOf('\\');//GETS ATTRIBUTE NAME
                        attribute_name = attribute_data.substring(0, i);
                        attribute_data = attribute_data.substring(i+1,attribute_data.length());
    
                        int j = attribute_data.indexOf('\\');//GETS ATTRIBUTE TYPE
                        String att_type = attribute_data.substring(0, j);
                        if (att_type.equals("VARCHAR"))
                            attribute_type = Attribute.Type.VARCHAR;
                        else
                            attribute_type = Attribute.Type.INTEGER;
                        attribute_data = attribute_data.substring(j+1, attribute_data.length());
    
                        int k = attribute_data.indexOf('\\'); //GETS ATTRIBUTE KEY
                        String att_key = attribute_data.substring(0, k);
                        if (att_key.equals("true"))
                            attribute_key = true;
                        else
                            attribute_key = false;
                        
                        attribute_data = attribute_data.substring(k+1, attribute_data.length());
                        attribute_list.add(new Attribute(attribute_name, attribute_type, attribute_key));//ADDS A NEW ATTRIBUTE TO ATTRIBUTE LIST
                    }
                    if (all_relations.get(relation_name).set_attributes(attribute_list)==-1)//SETS ALL ATTRIBUTES WITH ATTRIBUTE LIST
                        System.out.println("setting attributes failed in LOAD");
                }
                else if (input.contains("-")) {//IS A TUPLE DEFINITION LINE
                    String tuple_data = input.substring(1,input.length());
                    ArrayList<String> tuple_strings = new ArrayList<String>();
                    while (tuple_data.contains("\\")) {
                        int i = tuple_data.indexOf('\\');
                        String s = tuple_data.substring(0,i);
                        tuple_strings.add(s);
                        tuple_data = tuple_data.substring(i+1, tuple_data.length());
                    }
                    if (all_relations.get(relation_name).add_tuple(new Tuple(tuple_strings))==-1)
                        System.out.println("adding tuple failed in LOAD");
                }
                else{
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            throw e;
        }
    }
    
    // close-cmd
    public void close(String name) {
       //Do nothing
    }
    
    /**
     * Shows a Relation by calling display, used for show-cmd
     *
     * @see Relation#display()
     * @param r Takes in a Relation to show
     */
    public void show(Relation r) {
        if (r != null) r.display();
    }
    
    /**
     * Exits the running program, used for exit-cmd
     */
    public void exit() {
        System.out.println("exiting...\n");
        System.exit(0);
    }
    
    /**
     * Creates a new relation and adds it to the hashmap
     * 
     * @param name String identifier used as a Key to identify each Relation
     * @return Returns 0 if success, -1 otherwise
     */
    public int create_relation(String name) {
        if (all_relations.containsKey(name))
            return -1;
        else{
        Relation tmp_relation = new Relation(name);
        all_relations.put(name, tmp_relation);
        }
        return 0;
    }
    
    /**
     * Creates a new Relation and adds it to the hashmap 
     *
     * @param r Relation being using to create a new Relation
     * @return Returns 0 if success, -1 otherwise
     */ 
    public int create_relation(Relation r) {
        if (r != null) {
            all_relations.put(r.get_name(), r);
            return 0;
        }
        return -1;
    }
    
    /**
     * Drops a relation from the hashmap
     * 
     * @param name Name of table to be removed
     * @return Returns 0 if success, -1 otherwise
     */
    public int drop_table(String name) {
        if (all_relations.containsKey(name)) {
            all_relations.remove(name);
            return 0;
        }
        else
            return -1;
    }
    
    /**
     * Inserts tuple into a relation
     * 
     * @param relation Relation to be be inserted into
     * @param tuple Tuple to be inserted into Relation
     * @return Returns 0 if success, -1 otherwise
     */
    public int insert_row(String relation, Tuple tuple) {
        Relation tmp_relation = all_relations.get(relation);
        
        if (tmp_relation != null) {
            tmp_relation.add_tuple(tuple);
        }else{
            return -1; // error
        }
        return 0;
    }

    /**
     * Deletes a tuple from a relation
     * 
     * @param relation Relation/Table to have a Tuple/Row deleted from
     * @param tuple Tuple/Row to be delted
     * @return Returns 0 if success, -1 otherwise
     */
    public int delete_row(String relation, Tuple tuple) {
        Relation tmp_relation = all_relations.get(relation);
        if (tmp_relation != null) {
            tmp_relation.delete_tuple(tuple);
        }else{
            return -1; // error
        }
        return 0;
    }
    
    /*
    public void delete_row(String relation, String condition, Attribute attr, String literal) {
        Relation tmp_relation = all_relations.get(relation);
        switch (condition) {
            case "equal_to":
                for (Tuple t : tmp_relation.get_tuples()) {
                    if (argument_one.equals(argument_two)) {
                        tmp_relation.add_tuple(t);
                    }
                }
            break;

            case "not_equal_to":
                for (Tuple t : tmp_relation.get_tuples()) {
                    if (!argument_one.equals(argument_two)) {
                        tmp_relation.add_tuple(t);
                    }
                }
            break;

            default:
                System.out.println("illegal comparison");
                return null;
        }
    }*/
    
    /**
     * Updates an existing tuple in a relation to a new set of values
     *   
     * @param relation Relation to have a Tuple Modified
     * @param t1 Tuple to be updated or changed
     * @param t2 Tuple the old Tuple is being updated from
     * @return Returns 0 if success, -1 otherwise
     */
    public int update_row (String relation, Tuple t1, Tuple t2) {
        Relation tmp_relation = all_relations.get(relation); // gets list of all possible relations
        if (tmp_relation != null) {
            ArrayList<Tuple> tmp_tuples = tmp_relation.get_tuples();
            for (int i = 0; i < tmp_tuples.size(); i++) {
                if (tmp_tuples.get(i).get_values() == t1.get_values()) { // finding row that needs to be updated	
                    t1.set_values(t2.get_values()); // updating old tuple with new tuples info.
                }
            }
        } else {
            return -1; // error
        }
        return 0;
    }

    // database queries
    /**
     * Overloaded String to String comparison for the select function
     * Takes in a condition and returns a relation table made up of rows depending on condition. 
     * 
     * @param from Relation table to take from
     * @param condition Operation condition to be tested
     * @param argument_one Operand one as string
     * @param argument_two Operand two as string
     * @param as What to call the new relation
     * @return Returns a relation if success, null otherwise
     */
    public Relation select(Relation from, String condition, String argument_one, String argument_two, String as) { // rows
        Relation tmp_relation = new Relation(as); 
        tmp_relation.set_attributes(from.get_attributes());
        switch (condition) {
            case "equal_to":
                for (Tuple t : from.get_tuples()) {
                    if (argument_one.equals(argument_two)) {
                        tmp_relation.add_tuple(t);
                    }
                }
            break;

            case "not_equal_to":
                for (Tuple t : from.get_tuples()) {
                    if (!argument_one.equals(argument_two)) {
                        tmp_relation.add_tuple(t);
                    }
                }
            break;

            default:
                System.out.println("illegal comparison");
                return null;
        }
        return tmp_relation;
    }

    /**
     * Overloaded Integer to Integer comparison for the select function
     * Takes in a condition and returns a relation table made up of rows depending on condition. 
     * 
     * @param from Relation table to take from
     * @param condition Operation condition to be tested
     * @param argument_one Operand one as string
     * @param argument_two Operand two as string
     * @param as What to call the new relation
     * @return Returns a relation if success, null otherwise
     */
    public Relation select(Relation from, String condition, Integer argument_one, Integer argument_two, String as) { // rows
        Relation tmp_relation = new Relation(as);
        tmp_relation.set_attributes(from.get_attributes());
        switch (condition) {
            case "greater_than":
                for (Tuple t : from.get_tuples()) {
                    if (argument_one > argument_two) {
                        tmp_relation.add_tuple(t);
                    }
                }
                break;

            case "less_than":
                for (Tuple t : from.get_tuples()) {
                    if (argument_one < argument_two) {
                        tmp_relation.add_tuple(t);
                    }
                }
                break;
            
            case "equal_to":
                for (Tuple t : from.get_tuples()) {
                    if (argument_one == argument_two) {
                        tmp_relation.add_tuple(t);
                    }
                }
                break;

            case "not_equal_to":
                for (Tuple t : from.get_tuples()) {
                    if (argument_one != argument_two) {
                        tmp_relation.add_tuple(t);
                    }
                }
                break;

            case "less_than_equal_to":
                for (Tuple t : from.get_tuples()) {
                    if (argument_one <= argument_two) {
                        tmp_relation.add_tuple(t);
                    }
                }
                break;

            case "greater_than_equal_to":
                for (Tuple t : from.get_tuples()) {
                    if (argument_one >= argument_two) {
                        tmp_relation.add_tuple(t);
                    }
                }
                break;

            default:
                System.out.println("illegal comparison");
                return null;
        }
        return tmp_relation;
    }

    /**
     * Overloaded Attribute to Attribute comparison for the select function
     * Takes in a condition and returns a relation table made up of rows depending on condition. 
     * 
     * @param from Relation table to take from
     * @param condition Operation condition to be tested
     * @param argument_one Operand one as string
     * @param argument_two Operand two as string
     * @param as What to call the new relation
     * @return Returns a relation if success, null otherwise
     */
    public Relation select(Relation from, String condition, Attribute argument_one, Attribute argument_two, String as) { // rows
        Relation tmp_relation = new Relation(as);
        tmp_relation.set_attributes(from.get_attributes());
        int index_of_atribute_one = from.get_attributes().indexOf(argument_one);
        int index_of_atribute_two = from.get_attributes().indexOf(argument_two);
        if (index_of_atribute_one == -1 || index_of_atribute_one == -1) System.out.println("one or both attributes not found");
        else{
            if (argument_one.get_type() == argument_two.get_type()) { // makes sure attributes are of same type
                if (argument_one.get_type() == Attribute.Type.INTEGER) { //Checks what type the attributes are
                    switch (condition) {
                        case "greater_than":
                            for (Tuple t : from.get_tuples()) {
                                if (Integer.parseInt(t.get_values().get(index_of_atribute_one)) > Integer.parseInt(t.get_values().get(index_of_atribute_two))) {
                                    tmp_relation.add_tuple(t);
                                }
                            }
                            break;

                        case "less_than":
                            for (Tuple t : from.get_tuples()) {
                                if (Integer.parseInt(t.get_values().get(index_of_atribute_one)) < Integer.parseInt(t.get_values().get(index_of_atribute_two))) {
                                    tmp_relation.add_tuple(t);
                                }
                            }
                            break;

                        case "equal_to":
                            for (Tuple t : from.get_tuples()) {
                                if (Integer.parseInt(t.get_values().get(index_of_atribute_one)) == Integer.parseInt(t.get_values().get(index_of_atribute_two))) {
                                    tmp_relation.add_tuple(t);
                                }
                            }
                            break;

                        case "not_equal_to":
                            for (Tuple t : from.get_tuples()) {
                                if (Integer.parseInt(t.get_values().get(index_of_atribute_one)) != Integer.parseInt(t.get_values().get(index_of_atribute_two))) {
                                    tmp_relation.add_tuple(t);
                                }
                            }
                            break;

                        case "less_than_equal_to":
                            for (Tuple t : from.get_tuples()) {
                                if (Integer.parseInt(t.get_values().get(index_of_atribute_one)) <= Integer.parseInt(t.get_values().get(index_of_atribute_two))) {
                                    tmp_relation.add_tuple(t);
                                }
                            }
                            break;

                        case "greater_than_equal_to":
                            for (Tuple t : from.get_tuples()) {
                                if (Integer.parseInt(t.get_values().get(index_of_atribute_one)) >= Integer.parseInt(t.get_values().get(index_of_atribute_two))) {
                                    tmp_relation.add_tuple(t);
                                }
                            }
                            break;

                        default:
                            System.out.println("illegal comparison");
                            return null;
                    }
                }else{
                    switch (condition) {
                       case "equal_to":
                            for (Tuple t : from.get_tuples()) {
                                if (t.get_values().get(index_of_atribute_one).equals(t.get_values().get(index_of_atribute_two))) {
                                    tmp_relation.add_tuple(t);
                                }
                            }
                            break;

                        case "not_equal_to":
                            for (Tuple t : from.get_tuples()) {
                                if (!t.get_values().get(index_of_atribute_one).equals(t.get_values().get(index_of_atribute_two))) {
                                    tmp_relation.add_tuple(t);
                                }
                            }
                            break;

                        default:
                            System.out.println("illegal comparison");
                            return null;
                    }
                }
            }else{
                System.out.println("illegal comparison");
                return null;
            }
        }
        return tmp_relation;
    }
    
    /**
     * Overloaded Attribute to String comparison for the select function
     * Takes in a condition and returns a relation table made up of rows depending on condition. 
     * 
     * @param from Relation table to take from
     * @param condition Operation condition to be tested
     * @param argument_one Operand one as string
     * @param argument_two Operand two as string
     * @param as What to call the new relation
     * @return Returns a relation if success, null otherwise
     */
    public Relation select(Relation from, String condition, Attribute argument_one, String argument_two, String as) { // rows
        Relation tmp_relation = new Relation(as);
        tmp_relation.set_attributes(from.get_attributes());
        int index_of_atribute_one = from.get_attributes().indexOf(argument_one);
        if (argument_one.get_type() == Attribute.Type.VARCHAR) { //Makes sure the attribute is of type VARCHAR
            switch (condition) {
                case "equal_to":
                    for (Tuple t : from.get_tuples()) {
                        if (t.get_values().get(index_of_atribute_one).equals(argument_two)) {
                            tmp_relation.add_tuple(t);
                        }
                    }
                    break;

                case "not_equal_to":
                    for (Tuple t : from.get_tuples()) {
                        if (!t.get_values().get(index_of_atribute_one).equals(argument_two)) {
                            tmp_relation.add_tuple(t);
                        }
                    }
                    break;

                default:
                    System.out.println("illegal comparison");
                    return null;
            }
        }else{
            System.out.println("illegal comparison");
            return null;
        }
        return tmp_relation;
    }

    /**
     * Overloaded Attribute to Integer comparison for the select function
     * Takes in a condition and returns a relation table made up of rows depending on condition. 
     * 
     * @param from Relation table to take from
     * @param condition Operation condition to be tested
     * @param argument_one Operand one as string
     * @param argument_two Operand two as string
     * @param as What to call the new relation
     * @return Returns a relation if success, null otherwise
     */
    public Relation select(Relation from, String condition, Attribute argument_one, Integer argument_two, String as) { // rows
        Relation tmp_relation = new Relation(as);
        tmp_relation.set_attributes(from.get_attributes());
        int index_of_atribute_one = from.get_attributes().indexOf(argument_one);
        if (argument_one.get_type() == Attribute.Type.INTEGER) { //Makes sure the attribute is of type Integer
            switch (condition) {
                case "greater_than":
                    for (Tuple t : from.get_tuples()) {
                        if (Integer.parseInt(t.get_values().get(index_of_atribute_one)) > argument_two) {
                            tmp_relation.add_tuple(t);
                        }
                    }
                    break;

                case "less_than":
                    for (Tuple t : from.get_tuples()) {
                        if (Integer.parseInt(t.get_values().get(index_of_atribute_one)) < argument_two) {
                            tmp_relation.add_tuple(t);
                        }
                    }
                    break;
                
                case "equal_to":
                    for (Tuple t : from.get_tuples()) {
                        if (Integer.parseInt(t.get_values().get(index_of_atribute_one)) == argument_two) {
                            tmp_relation.add_tuple(t);
                        }
                    }
                    break;

                case "not_equal_to":
                    for (Tuple t : from.get_tuples()) {
                        if (Integer.parseInt(t.get_values().get(index_of_atribute_one)) != argument_two) {
                            tmp_relation.add_tuple(t);
                        }
                    }
                    break;

                case "less_than_equal_to":
                    for (Tuple t : from.get_tuples()) {
                        if (Integer.parseInt(t.get_values().get(index_of_atribute_one)) <= argument_two) {
                            tmp_relation.add_tuple(t);
                        }
                    }
                    break;

                case "greater_than_equal_to":
                    for (Tuple t : from.get_tuples()) {
                        if (Integer.parseInt(t.get_values().get(index_of_atribute_one)) >= argument_two) {
                            tmp_relation.add_tuple(t);
                        }
                    }
                    break;

                default:
                    System.out.println("illegal comparison");
                    return null;
            }
        }else{
            System.out.println("illegal comparison");
            return null;
        }
        return tmp_relation;
    }

    /**
     * Takes a set of attributes and returns a relation table of the attributes given. 
     * 
     * @param r Relation table to take from
     * @param attributes List of attributes to keep
     * @return Returns a relation if success, null otherwise
     */
    public Relation project(Relation r, ArrayList<Attribute> attributes) { // columns
        Relation tmp_relation = new Relation("projection"); // placeholder for now, we can have a more detailed name later
        ArrayList<Attribute> tmp_attributes = r.get_attributes();
        ArrayList<Integer> attribute_indices = new ArrayList<Integer>();
        for (Attribute a : attributes) {
            if (tmp_attributes.contains(a)) {
                
                attribute_indices.add(tmp_attributes.indexOf(a));
            }else{
                //System.out.println(a);
                System.out.println("cannot project because attribute does not exist");
                return null; // error
            }
        }
        
        tmp_relation.set_attributes(attributes);
        
        for (Tuple t : r.get_tuples()) {
            ArrayList<String> tmp_values = t.get_values();  
            ArrayList<String> projected_values = new ArrayList<String>();
            for (int i : attribute_indices) {
                projected_values.add(tmp_values.get(i));
            }
            Tuple tmp_tuple = new Tuple(projected_values);
            tmp_relation.add_tuple(tmp_tuple);
        }
        
        return tmp_relation;
    }
    public Relation project(String s, Relation r, ArrayList<Attribute> attributes) { // columns
        Relation tmp_relation = new Relation(s); // placeholder for now, we can have a more detailed name later
        ArrayList<Attribute> tmp_attributes = r.get_attributes();
        ArrayList<Integer> attribute_indices = new ArrayList<Integer>();
        for (Attribute a : attributes) {
            if (tmp_attributes.contains(a)) {
                
                attribute_indices.add(tmp_attributes.indexOf(a));
            }else{
                //System.out.println(a);
                System.out.println("cannot project because attribute does not exist");
                return null; // error
            }
        }
        
        tmp_relation.set_attributes(attributes);
        
        for (Tuple t : r.get_tuples()) {
            ArrayList<String> tmp_values = t.get_values();  
            ArrayList<String> projected_values = new ArrayList<String>();
            for (int i : attribute_indices) {
                projected_values.add(tmp_values.get(i));
            }
            Tuple tmp_tuple = new Tuple(projected_values);
            tmp_relation.add_tuple(tmp_tuple);
        }
        
        return tmp_relation;
    }

    /** Takes a set of attributes and returns a relation table with renamed attributes. 
    * 
    * @param r Relation to rename attributes of
    * @param new_names List of attributes to rename
    * @return Returns a relation if success, null otherwise
    */
   public Relation rename(Relation r, ArrayList<String> new_names) {
       Relation tmp_relation = new Relation("renaming");
       tmp_relation.set_relation_to(r);
       ArrayList<Attribute> to_rename = new ArrayList<Attribute>();
       
       for (Attribute a : r.get_attributes()) {
           Attribute new_att = new Attribute(a);
           to_rename.add(new_att);
       }
       
       tmp_relation.set_attributes(to_rename);
           
       if (new_names.size() != to_rename.size()) {
           System.out.println("cannot rename because size mismatch");
           return null; // error
       }
       for (int i = 0; i < to_rename.size(); i++) tmp_relation.get_attributes().get(i).set_name(new_names.get(i));
       return tmp_relation;
   }
    
    /**
     * Check if it is possible to union or difference two relations. 
     * 
     * @param r1 First relation table to compare
     * @param r2 Second relation table to compare
     * @return Returns true if possible, false otherwise
     */
    public boolean can_relate(Relation r1, Relation r2) {
        ArrayList<Attribute> r1_attributes = r1.get_attributes();
        ArrayList<Attribute> r2_attributes = r2.get_attributes();
        
        if (r1_attributes.size() != r2_attributes.size()) return false;
        for (Attribute a : r1_attributes) if (!r2_attributes.contains(a)) {
            //System.out.println("2");
            return false;
        }
        return true;
    }
    
    /**
     * Union of two relations, if it is possible 
     * 
     * @param r1 First relation table to union
     * @param r2 Second relation table to union
     * @return Returns a relation if success, null otherwise
     */
    public Relation union(Relation r1, Relation r2) {
        Relation tmp_relation = new Relation(r1.get_name() + " union " + r2.get_name());
        if (!can_relate(r1,r2)) {
            System.out.println("cannot union because attribute mismatch"); // error
            return null;
        }
        
        tmp_relation.set_attributes(r1.get_attributes());
        
        ArrayList<Tuple> tmp_tuples = tmp_relation.get_tuples();
        tmp_tuples.addAll(r1.get_tuples());
        
        for (Tuple t : r2.get_tuples()) if (!tmp_tuples.contains(t)) tmp_relation.add_tuple(t);
        
        return tmp_relation;
    }
    
    /**
     * Difference of two relations, if it is possible 
     * everything that is in the first relation that is not
     * in the second relation
     * 
     * @param r1 First relation table to difference
     * @param r2 Second relation table to difference
     * @return Returns a relation if success, null otherwise
     */
    public Relation difference(Relation r1, Relation r2) {
        Relation tmp_relation = new Relation(r1.get_name() + " difference " + r2.get_name());
        if (!can_relate(r1,r2)) {
            System.out.println("cannot union because attribute mismatch"); // error
            return null;
        }
        
        tmp_relation.set_attributes(r1.get_attributes());
        
        ArrayList<Tuple> r1_tuples = r1.get_tuples();
        ArrayList<Tuple> r2_tuples = r2.get_tuples();
        // everything in r1 that is not in r2
        for (Tuple t : r1_tuples) if (!r2_tuples.contains(t)) tmp_relation.add_tuple(t);
        
        return tmp_relation;
    }
    
    /**
     * Cross product (Cartesian product) of two relations
     * 
     * @param r1 First relation table to cross
     * @param r2 Second relation table to cross
     * @return Returns a relation if success, null otherwise
     */
    public Relation cross_product(Relation r1, Relation r2) {
        Relation tmp_relation = new Relation(r1.get_name() + " cross product " + r2.get_name());
        
        ArrayList<Attribute> r1_attributes = r1.get_attributes();
        ArrayList<Attribute> r2_attributes = r2.get_attributes();
        
        ArrayList<Attribute> tmp_attributes = new ArrayList<Attribute>();
        tmp_attributes.addAll(r1_attributes);
        tmp_attributes.addAll(r2_attributes);
        
        tmp_relation.set_attributes(tmp_attributes);
        
        ArrayList<Tuple> r1_tuples = r1.get_tuples();
        ArrayList<Tuple> r2_tuples = r2.get_tuples();
        for (Tuple t1 : r1_tuples) {
            for (Tuple t2 : r2_tuples) {
                ArrayList<String> new_values = new ArrayList<String>();
                new_values.addAll(t1.get_values());
                new_values.addAll(t2.get_values());
                Tuple tmp_tuple = new Tuple(new_values);
                tmp_relation.add_tuple(new Tuple(new_values));
            }
        }
        
        return tmp_relation;
    }
    
    /**
     * Natural join of two relations, if it is possible
     * adds attributes and respective data of tuples from 
     * second relation to first relation if there is an attribute 
     * in common which can be used to join the two
     *
     * @param r1 First relation table to join
     * @param r2 Second relation table to join
     * @return Returns a relation if success, null otherwise
     */
    public Relation natural_join(Relation r1, Relation r2) {
        Relation tmp_relation = new Relation(r1.get_name() + " natural join " + r2.get_name());
        ArrayList<Attribute> r1_attributes = r1.get_attributes();
        ArrayList<Attribute> r2_attributes = r2.get_attributes();
        
        ArrayList<Attribute> tmp_attributes = new ArrayList<Attribute>();
        // add all attributes from r1
        for (Attribute a : r1_attributes) {
            tmp_attributes.add(a);
        }
        
        int joining_attribute_r1 = -1;
        int joining_attribute_r2 = -1;
        for (Attribute a : r2_attributes) {
            // add all the attributes from r2 except joining attribute
            if (!r1_attributes.contains(a)) {
                tmp_attributes.add(a);
            }else{
                joining_attribute_r1 = r1_attributes.indexOf(a);
                joining_attribute_r2 = r2_attributes.indexOf(a);
            }
        }
        
        if (joining_attribute_r2 == -1 || joining_attribute_r1 == -1) {
            System.out.println("cannot join because no attributes in common"); // error
            return null;
        }
        
        tmp_relation.set_attributes(tmp_attributes); 
        
        ArrayList<Tuple> r1_tuples = r1.get_tuples();
        ArrayList<Tuple> r2_tuples = r2.get_tuples();
        for (Tuple t1 : r1_tuples) {
            for (Tuple t2 : r2_tuples) {
                ArrayList<String> r1_values = t1.get_values();
                ArrayList<String> r2_values = t2.get_values();
                
                ArrayList<String> tmp_values = new ArrayList<String>();
                tmp_values.addAll(r1_values); // add all the values from t1
                
                //if the value of the joining attribute in r1 is the same as the value of the joining attribute in r2 then join
                if (r1_values.get(joining_attribute_r1).equals(r2_values.get(joining_attribute_r2))) {
                    tmp_values.addAll(t2.get_values()); // add all the values from t2
                    tmp_values.remove(r1_values.size() + joining_attribute_r2); //remove the repeat value from values added from r2
                    
                    Tuple tmp_tuple = new Tuple(tmp_values);
                    tmp_relation.add_tuple(tmp_tuple);
                }
            }           
        }
        return tmp_relation;
    }

    /**
     * 
     * @param temp_relation Relation that is the updates selection
     * @param difference Relation that is the difference of the selection and original
     * @param changed_tups Tups that were changed and are added to the difference
     */
    public void update(Relation temp_relation, Relation difference, ArrayList<Tuple> changed_tups) {
        for(Tuple t : changed_tups) {
            temp_relation.add_tuple(t);
        }    
    }

}