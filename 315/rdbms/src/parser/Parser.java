package parser;

import engine.*;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * 
 * @author StaticLightningDragons
 *
 */
public class Parser {
    private Scanner input; // is this used? if not we can delete and also have empty constructor for parser.
    private HashMap<String, Relation> temp_hash = new HashMap<String, Relation>();
    private Integer hash_id = 0;
    
    final ArrayList<String> op_symbols = new ArrayList<String>(Arrays.asList("==", "!=", "<", ">", "<=", ">="));
    final ArrayList<String> query_words = new ArrayList<String>(Arrays.asList("select", "project", "rename", "union", "join", "-", "+", "*"));

    /**
     * {@value engine} 
     */
    private Engine engine = new Engine();

    /**
     * Constructor
     */
    public Parser() {
        Scanner input = null; // using's java's scanner for now. may have to write our own if things get complicated.
    }
   
    /**
     * 
     * @return Returns the engine used by this parser
     */
    public Engine get_engine() {
        return engine;
    }
    
    /**
     * Parse the given string
     *
     * @param input String to be processed
     * @throws FileNotFoundException Thrown if OPEN argument is not found
     */
    public void parse(String input) throws FileNotFoundException {
        try {
            input = input.trim();
            //System.out.println(input);
            if(input.length() != 0) program(input);
        } catch(NullPointerException e) {
            System.out.println("Error: The parser had an error. If using boolean logic && or || then this has not been implemented. Apologies");
        }
    }
    
    /**
     * Get rid of leading and trailing whitespaces of all strings inside the array of strings
     *
     * @param str Strings to be processed
     * @return Array of strings without leading and trailing white space
     */
    private String[] trim_all(String[] str) {
        for(int i = 0; i < str.length; i++) str[i] = str[i].trim();
        return str;
    }
    
    /**
     * Check if the input is a query or a command and call the corresponding parser
     *
     * @param input String to be processed
     * @throws FileNotFoundException Thrown if OPEN argument is not found
     */
    private void program(String input) throws FileNotFoundException {
        input = input.replace(";", "");
        if (input.contains("<-")) {
            query(input);
        } else {
            command(input);
        }
    }
    
    /**
     * Process query
     *
     * @param input String to be processed
     */
    private void query(String input) {
        String[] query_parts = input.split("<-");
        trim_all(query_parts);

        String relation_name = query_parts[0];
        String expr_param = query_parts[1];
        String expr;
        if (expr_param.contains(" ")) expr = "(" + expr_param + ")";
        else expr = expr_param;
        String created_relation = atomic_expr(expr);        
        
        Relation relation = temp_hash.get(created_relation);
        if (relation == null) relation = engine.get_relation(created_relation);
        
        if (engine.get_all_relations().containsKey(relation_name)) {
            engine.get_relation(relation_name).set_relation_to(relation);
        } else {
            engine.create_relation(relation_name);
            engine.get_relation(relation_name).set_relation_to(relation);
        }
    }
    
    /**
     * Process command
     *
     * @param input String to be processed
     * @throws FileNotFoundException Thrown if OPEN argument is not found
     */
    private void command(String input) throws FileNotFoundException {
        //command ::= open-cmd | close-cmd | write-cmd | exit-cmd | show-cmd | create-cmd | update-cmd | insert-cmd | delete-cmd
        String cmd = input;
        String cmd_params = "";

        if (input.contains("CREATE TABLE")) {
            input = input.replace("CREATE TABLE", "CREATE");
        }
        if (input.contains("INSERT INTO")) {
            input = input.replace("INSERT INTO", "INSERT");
        }
        if (input.contains("DELETE FROM")) {
            input = input.replace("DELETE FROM", "DELETE");
        }
        
        if (!input.contains("EXIT")) {
            int i = input.indexOf(" ");
            cmd = input.substring(0,i);
            cmd_params = input.substring(i+1);
        }

        switch (cmd) {
            case "EXIT":
                engine.exit();
                break;
            
            case "OPEN":
                open(cmd_params);
                break;
            
            case "CLOSE":
                close(cmd_params);
                break;
            
            case "WRITE":
                write(cmd_params);
                break;
            
            case "SHOW":
                show(cmd_params);
                break;
            
            case "CREATE":
                create(cmd_params);
                break;
            
            case "UPDATE":
                update(cmd_params);
                break;
            
            case "INSERT":
                insert(cmd_params);
                break;
                
            case "DELETE":
                delete(cmd_params);
                break;
            
            default:
                System.out.println("invalid command");
                break; 
        }
    }
    
    /**
     * Open a relation
     * 
     * @param input Name of file to be opened
     * @throws FileNotFoundException Thrown if OPEN argument is not found
     */
    private void open(String input) throws FileNotFoundException {
        if(!input.endsWith(".db")) input = input + ".db";
        engine.open(input);
    }
    
    /**
     * Close a relation
     * 
     * @param input Relation to close
     */
    private void close(String input) {
        // not needed
    }
    
    
    /**
     * Write a relation to a file
     * 
     * @param input Name of file to be made
     */
    private void write(String input) {
        if (!input.endsWith(".db")) input = input + ".db";
        try {
            engine.write(input);
            System.out.println("File written to " + input);
        } catch (IOException e) {
            System.out.println("Write failed");
        }
    }
    
    /**
     * Show a relation
     * 
     * @param input Relation name to be show
     */
    private void show(String input) {
        Relation r = new Relation(input);
        if(input.contains("(")){
            r.set_relation_to(temp_hash.get(atomic_expr(input)));
        }
        else {
            r.set_relation_to(engine.get_relation(atomic_expr(input)));
        }
        engine.show(r);
    }
    
    /**
     * Processes an atomic expression
     *
     * @param input Command to process
     * @return Returns relation
     */
    private String atomic_expr(String input) {
        // atomic-expr ::= relation-name | ( expr )
        if (input.startsWith("(") && input.endsWith(")")) {
            input = input.substring(1, input.length() - 1);
            input = input.trim();
            return expr(input);
        } else {
            return input;
        }
    }
    
    /**
     * Process expression and call the corresponding function
     *
     * @param input String to be processed
     * @return Returns a relation
     */
    private String expr(String input) {
        //expr ::= atomic-expr | selection | projection | renaming | union | difference | product | natural-join
        input = input.replace(";","");
        input = input.trim();

        String expr_command = "";
        String expr_params = "";
        String[] left_right;
        
        int open_count = 0;
        int close_count = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(') open_count++;
            else if (input.charAt(i) == ')') close_count++;
        }
        
        if (open_count != close_count) {
            System.err.println("parentheses mismatch"); //ERROR NONMATCHING PARENS 
            return null;
        }
        
        if (input.contains("(")) {
            int begin = input.indexOf("(");
            expr_command = input.substring(0, begin).trim();
            expr_params = input.substring(begin).trim();
            //System.out.println(expr_command);
            //System.out.println(expr_params);
        } 
        
        if (input.startsWith("project")) {
            return project(expr_params);
            
        } else if (input.startsWith("select")) {
            return select(expr_params);
            
        } else if (input.startsWith("rename")) {
            return rename(expr_params);
            
        } else if (input.contains("+")) {
            left_right = input.split("\\+");
            trim_all(left_right);
            return union(left_right);
            
        } else if (input.contains("-")) {
            left_right = input.split("\\-");
            trim_all(left_right);
            return difference(left_right);
            
        } else if (input.contains("*")) {
            left_right = input.split("\\*");
            trim_all(left_right);
            return product(left_right);
        } else if (input.contains("JOIN")) {
                left_right = input.split("JOIN");
                left_right = trim_all(left_right);
                if(left_right.length > 2)
                {
                    left_right[1] = left_right[1] + " JOIN " + left_right[2];
                    left_right[2] = null;
                }
                //System.out.println(left_right[0]);
                //System.out.println(left_right[1]);
                return natural_join(left_right);
        } else {
            System.out.println("expr command not recognized");
            
        }
        
        return input;
    }

    /**
     * Create a relation
     * 
     * @param input String to be processed
     */
    private void create(String input) {
        int i = input.indexOf('(');
        String relation_name = input.substring(0, i-1); // NAME OF RELATION TO BE CREATED
        input = input.substring(i+1,input.length());
        
        Relation temp_relation = new Relation(relation_name); // CREATES NEW RELATION
        
        ArrayList<Attribute> att_list = new ArrayList<Attribute>(); // List of attributes
        ArrayList<String> var_names = new ArrayList<String>(); // List of attribute column names
        ArrayList<Integer> var_sizes = new ArrayList<Integer>(); // List of attribute sizes
        ArrayList<Attribute.Type> var_types = new ArrayList<Attribute.Type>(); // List of attribute types
        ArrayList<String> primary_keys = new ArrayList<String>(); // List of attributes that are primary keys

        // while(number of "(" remaining != number of ")" remaining)
        while ((input.length()-input.replace("(", "").length()) != (input.length()-input.replace(")", "").length())) {

            int x = input.indexOf(')');
            int c = input.indexOf(',');
            String var_info;
            if(x<c||c==-1)
            {
                var_info = input.substring(0,x+1);
                input = input.substring(x+2).trim();
            }
            else if(c!=-1&&x>c){
                var_info = input.substring(0,c);
                input = input.substring(c+2).trim();
            }
            else {
                var_info = "";
            }
            
            if (x == 0) {
                //fixes if there is double '))'
            } else if (var_info.contains("VARCHAR")) { 
                //System.out.println(var_info);
                Attribute.Type var_type = Attribute.Type.VARCHAR;
                var_info = var_info.replace(" VARCHAR", "");
                
                var_info = var_info.replace(", ","");
                int y = var_info.indexOf('(');
                String var_name = var_info.substring(0,y);
                
                int z = var_info.indexOf(')');
                int var_size = Integer.valueOf(var_info.substring(y+1,z));
                
                var_names.add(var_name);
                var_sizes.add(var_size);
                var_types.add(Attribute.Type.VARCHAR);
            } else {
                Attribute.Type var_type = Attribute.Type.INTEGER;
                var_info = var_info.replace(" INTEGER", "");
                var_info = var_info.replace(", ","");
                String var_name = var_info.replace(")", "");
                
                var_names.add(var_name);
                var_sizes.add(-1);
                var_types.add(Attribute.Type.INTEGER);
            }
        }
        
        input = input.replace("PRIMARYKEY(", "");
        
        while (input.contains(")")) {
            int x;
            if (input.contains(",")) {
                x = input.indexOf(",");
            } else {
                x = input.indexOf(")");
            }
            primary_keys.add(input.substring(0,x));
            input = input.substring(x+1);
        }
        
        for (int j = 0; j < var_names.size(); j++) {
            
            boolean is_primary;
            
            if (primary_keys.contains(var_names.get(j)))
                is_primary = true;  
            else
                is_primary = false;
            
            if (var_types.get(j) == Attribute.Type.VARCHAR)
                att_list.add(new Attribute(var_names.get(j), Attribute.Type.VARCHAR, is_primary )); //WILL HAVE SIZE PARAMETER
            else
                att_list.add(new Attribute(var_names.get(j), Attribute.Type.INTEGER, is_primary));
        }
        
        temp_relation.set_attributes(att_list);
        
        engine.get_all_relations().put(relation_name,temp_relation);
    }
    
    /**
     * Insert a tuple into a relation
     *
     * @param input String to be processed
     */
    private void insert(String input) {
        int i;
        String relation;
        ArrayList<String> parameters = new ArrayList<String>();
        ArrayList<String> row = new ArrayList<String>();
        Relation relation_to_insert_from = new Relation("temp");

        if (input.contains("VALUES FROM RELATION")) { // inserts row from relation

            i = input.indexOf("VALUES FROM RELATION");
            relation = input.substring(0,i).trim();
            input = input.substring(relation.length() + 21).trim();

            relation_to_insert_from = temp_hash.get(expr(input));

            ArrayList<Attribute> temp = engine.get_relation(relation).get_attributes();

            for (Tuple t : relation_to_insert_from.get_tuples()) {
                engine.insert_row(relation, new Tuple(t.get_values()));
            }

        } else if (input.contains("VALUES FROM")) { // inserts row from input

            i = input.indexOf("VALUES FROM");
            relation = input.substring(0,i).trim();
            input = input.substring(relation.length() + 10).trim();

            i = input.indexOf("(");
            input = input.substring(i+1);

            while (input.contains(")")) {
                if (input.contains(",")) {
                    i = input.indexOf(",");
                } else {
                    i = input.indexOf(")");
                }
                parameters.add(input.substring(0,i).trim());
                input = input.substring(i+1);

                for (i = 0; i < parameters.size(); ++i) {
                    parameters.get(i).trim();
                    parameters.set(i, parameters.get(i).replace("\"", ""));
                }
            }
            
            engine.insert_row(relation, new Tuple(parameters));
        } else {
            System.out.println("insert grammar not correct");
        }
    }

    /** Delete a tuple from a relation
     * 
     * @param input
     */
    private void delete(String input) {
        String[] left_right = input.split("WHERE"); 
        
        String left = left_right[0].trim();
        String right = left_right[1].trim();
        String[] cond_params = new String[2];        

        cond_params[0] = left;
        cond_params[1] = "(select " +right + " " + left + ")";
        engine.get_relation(left).set_relation_to(temp_hash.get(difference(cond_params)));
        
    }
    
    /** Update one or more tuples in a relation based on a condition
     * 
     * @param input
     */
    private void update(String input) {      
        String[] relation_right = trim_all(input.split("SET"));
        String relation = atomic_expr(relation_right[0]);
        String[] change_cond = trim_all(relation_right[1].split("WHERE"));
        if(input.length() - input.replaceAll(",", "").length() > engine.get_relation(relation).get_attributes().size()-1)
        {
            System.err.println("ERROR in update, too many attributes being changed for this relation");
        }
        
        //System.out.println(change_cond[0] + "\n" + change_cond[1]);
        String changes = change_cond[0];
        String cond = change_cond[1];
        String[] cond_params;

        
        String[] attr_params = changes.split(",");
        String hold_hash_id = hash_id.toString();
        Relation temp_relation = new Relation(hold_hash_id);
        ArrayList<Tuple> changed_tups = new ArrayList<Tuple>();
        String selection_input = "(select (" + cond + ") " + relation + ")";
        temp_relation.set_relation_to(temp_hash.get(atomic_expr(selection_input)));
        //temp_relation.display();
        for (String a : attr_params) {
            int i = 0;
            String[] processing = trim_all(a.split("=")); 
            String attr = processing[0];
            String title = processing[1].replaceAll("\"", "");
            //System.out.println(attr + " " + title);
            for(Attribute att : temp_relation.get_attributes()) {
                if(att.get_name().equals(attr))
                    i = temp_relation.get_attributes().indexOf(att);
            }
            //System.out.println(i);
            for (Tuple t : temp_relation.get_tuples()) {
               // System.out.println(t.get_values());
                t.get_values().set(i, title);
                changed_tups.add(t);
            }
        }
        String diffs[] = {relation, hold_hash_id};
        engine.update(temp_relation, temp_hash.get(difference(diffs)), changed_tups);

        //temp_relation.display();
        
    }

    /**
     * Selection
     *
     * @param expr_params String to be processed
     * @return name of relation
     */
    private String select(String expr_params) {
        hash_id++;
        String[] left_right;
        if (!expr_params.contains("(") || !expr_params.contains(")")) {
            System.out.println("invalid command");
            return expr_params;
        }
        
        int open_paren = expr_params.indexOf("(");
        int close_paren = expr_params.indexOf(")");
        String from_relation = expr_params.substring(close_paren+1).trim();
        int integer;
        String op, left, right;
        String cond = expr_params.substring(open_paren + 1, close_paren);
        
        if (cond.contains("==")) {
            left_right = cond.split("==");
            op = "equal_to";
            
        } else if (cond.contains("!=")) {
            left_right = cond.split("!=");
            op = "not_equal_to";
            
        } else if (cond.contains("<=")) {
            left_right = cond.split("<=");
            op = "less_than_equal_to";
            
        } else if (cond.contains(">=")) {
            left_right = cond.split(">=");
            op = "greater_than_equal_to";
            
        } else if (cond.contains("<")) {
            left_right = cond.split("<");
            op = "less_than";
            
        } else if (cond.contains(">")) {
            left_right = cond.split(">");
            op = "greater_than";
            
        } else {
            System.err.println("invalid condition");
            return null;
        }
        
        boolean compare_attribute = true;
        left = left_right[0].trim();
        right = left_right[1].trim();
        if(right.contains("\"")){
            right = right.replaceAll("\"", "");
            compare_attribute = false;
        }
        
        Integer hold_hash_id = hash_id;

        String relation_name = atomic_expr(from_relation);
        Relation temp_relation = temp_hash.get(relation_name);
        if(temp_relation == null) temp_relation = engine.get_relation(relation_name);
        
        try { // Second argument is INTEGER
            if(temp_relation.get_attribute(left).get_type() == Attribute.Type.VARCHAR) throw new IllegalArgumentException();
            integer = Integer.valueOf(right);
            temp_hash.put(hold_hash_id.toString(), 
                          engine.select(temp_relation, op, temp_relation.get_attribute(left), integer, hold_hash_id.toString())
                         );
            return hold_hash_id.toString();
            
        } catch (IllegalArgumentException e) { // Second argument is VARCHAR
            
            if(compare_attribute == true) temp_hash.put(hold_hash_id.toString(), engine.select(temp_relation, op, temp_relation.get_attribute(left), temp_relation.get_attribute(right), hold_hash_id.toString())); //this line says that attributes doesn't exist.
            else temp_hash.put(hold_hash_id.toString(), engine.select(temp_relation, op, temp_relation.get_attribute(left), right, hold_hash_id.toString()));
            return hold_hash_id.toString();
        }
    }
    
    /**
     * Projection
     *
     * @param expr_params String to be processed
     */
    private String project(String expr_params) {
        hash_id++;
        Relation relation = new Relation(hash_id.toString());
        ArrayList<String> parameters = new ArrayList<String>();
        ArrayList<Attribute> attributes = new ArrayList<Attribute>();
        
        int open_parens = expr_params.indexOf("(");
        int close_parens = expr_params.indexOf(")");
        String attr_params = expr_params.substring(open_parens+1, close_parens);
        String relation_params = expr_params.substring(close_parens+1).trim();
        String[] projected_attr = attr_params.split(",");
        
        Relation relation_to_project = temp_hash.get(atomic_expr(relation_params.trim()));
        
        if (relation_to_project == null) relation_to_project = engine.get_relation(atomic_expr(relation_params.trim()));
        relation.set_relation_to(relation_to_project);
        for (String s : projected_attr) {
            attributes.add(relation.get_attribute(s.trim()));
        }
        temp_hash.put(hash_id.toString(), engine.project(hash_id.toString(),relation, attributes));
        return hash_id.toString();
    }
    
    /**
     * Renaming
     *
     * @param expr_params String to be processed
     * @return name of relation
     */    
    private String rename(String expr_params) {
        hash_id++;
        
        int open_paren = expr_params.indexOf("(");
        int close_paren = expr_params.indexOf(")");
        
        String from_relation = expr_params.substring(close_paren+1).trim();
        
        String attrs = expr_params.substring(open_paren + 1, close_paren).trim();
        String[] new_attrs = attrs.split(",");
        trim_all(new_attrs);
        
        ArrayList<String> attribute_names = new ArrayList<String>(Arrays.asList(new_attrs));
 
        String relation_to_rename = atomic_expr(from_relation);
        Relation temp_relation = new Relation("temp");
        if(temp_hash.get(relation_to_rename) != null) temp_relation.set_relation_to(temp_hash.get(relation_to_rename));
        else if(engine.get_relation(relation_to_rename) != null) temp_relation.set_relation_to(engine.get_relation(relation_to_rename));
        temp_relation.set_relation_to(engine.rename(temp_relation, attribute_names));
        temp_hash.put(hash_id.toString(), temp_relation);
        //engine.get_all_relations().get("animals").display();
        
        return hash_id.toString();

    }
    
    /**
     * Union
     *
     * @param atomic_exprs Strings to be processed
     * @return name of relation
     */
    private String union(String[] atomic_exprs) {
        String left = atomic_expr(atomic_exprs[0].trim());
        String right = atomic_expr(atomic_exprs[1].trim());
        
        Relation left_relation = temp_hash.get(left);
        if (left_relation == null) left_relation = engine.get_relation(left);
        
        Relation right_relation = temp_hash.get(right);
        if (right_relation == null) right_relation = engine.get_relation(right);
        
        hash_id++;
        temp_hash.put(hash_id.toString(), engine.union(left_relation, right_relation));
        return hash_id.toString();
    }
    
    /**
     * Difference
     *
     * @param atomic_exprs Strings to be processed
     * @return name of relation
     */
    private String difference(String[] atomic_exprs) {
        String left = atomic_expr(atomic_exprs[0].trim());
        String right = atomic_expr(atomic_exprs[1].trim());
        
        Relation left_relation = temp_hash.get(left);
        if (left_relation == null) left_relation = engine.get_relation(left);
        
        Relation right_relation = temp_hash.get(right);
        if (right_relation == null) right_relation = engine.get_relation(right);
        
        hash_id++;
        temp_hash.put(hash_id.toString(), engine.difference(left_relation, right_relation));
        return hash_id.toString();
    }
    
    /**
     * Cartesian Product
     *
     * @param atomic_exprs Strings to be processed
     * @return name of String
     */
    private String product(String[] atomic_exprs) {
        String left = atomic_expr(atomic_exprs[0].trim());
        String right = atomic_expr(atomic_exprs[1].trim());
        
        Relation left_relation = temp_hash.get(left);
        if (left_relation == null) left_relation = engine.get_relation(left);
        
        Relation right_relation = temp_hash.get(right);
        if (right_relation == null) right_relation = engine.get_relation(right);
        
        hash_id++;
        temp_hash.put(hash_id.toString(), engine.cross_product(left_relation, right_relation));
        return hash_id.toString();
    }
    
    /**
     * Natural Join
     *
     * @param atomic_exprs Strings to be processed
     * @return name of relation
     */
    private String natural_join(String[] atomic_exprs) {
        String left = atomic_expr(atomic_exprs[0].trim());
        String right = atomic_expr(atomic_exprs[1].trim());
        
        Relation left_relation = temp_hash.get(left);
        if (left_relation == null) left_relation = engine.get_relation(left);
        
        Relation right_relation = temp_hash.get(right);
        if (right_relation == null) right_relation = engine.get_relation(right);
        
        hash_id++;
        temp_hash.put(hash_id.toString(), engine.natural_join(left_relation, right_relation));
        return hash_id.toString();
    }
}