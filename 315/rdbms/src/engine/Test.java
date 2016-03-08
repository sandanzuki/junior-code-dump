/*Test class running main() function to test functions
 * - create_relation
 * - set_attributes
 * - insert_row
 * - project
 * - union
 * - difference
 * - cross product
 * - natural join
 * - select
 */
package engine;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Test{
    public static void main(String[] args) throws IOException{
        Engine my_engine = new Engine();
		
        /* TEST FOR WHEN WE HAVE A DATA FILE TO READ FROM
        File my_file = new File("test.txt");
        if(my_engine.read(my_file)==0){
            System.out.println("File read successfully");
            }else System.out.println("File read failed");
        */
        my_engine.create_relation("Empty");
        my_engine.get_all_relations().get("Empty").display();
        
        // CREATE ARTIST TABLE
        my_engine.create_relation("Artists");// CREATE TABLE "ARTISTS"
            ArrayList<Attribute> artist_attributes = new ArrayList<Attribute>();
            artist_attributes.add(new Attribute("Artist Name", Attribute.Type.VARCHAR, false));
            artist_attributes.add(new Attribute("Debut Year", Attribute.Type.INTEGER, false));
        my_engine.get_all_relations().get("Artists").set_attributes(artist_attributes); // add attributes "ARTIST NAME", "DEBUT YEAR" TO TABLE "Artists"
            ArrayList<String> artist_row1 = new ArrayList<String>();
            artist_row1.add("Imagine Dragons");
            artist_row1.add("2008");
		my_engine.insert_row("Artists", new Tuple(artist_row1)); // INSERT ROW "Imagine Dragons", "2008" TO TABLE "Artists"
            ArrayList<String> artist_row2 = new ArrayList<String>();
            artist_row2.add("Black Eyed Peas");
            artist_row2.add("2004");
        my_engine.insert_row("Artists", new Tuple(artist_row2)); // INSERT ROW "Black Eyed Peas, "2004" TO TABLE "Artists"	
        my_engine.get_all_relations().get("Artists").display(); // DISPLAY TABLE "Artists"
		
		// CREATE ALBUMS TABLE
        my_engine.create_relation("Albums");
            ArrayList<Attribute> album_attributes = new ArrayList<Attribute>();
            album_attributes.add(new Attribute("Album Name", Attribute.Type.VARCHAR, false));
            album_attributes.add(new Attribute("Artist Name", Attribute.Type.VARCHAR, false));
            album_attributes.add(new Attribute("Album Release Year", Attribute.Type.INTEGER, false));
        my_engine.get_all_relations().get("Albums").set_attributes(album_attributes); // add attributes "ARTIST NAME", "DEBUT YEAR" TO TABLE "Artists"
            ArrayList<String> album_row1 = new ArrayList<String>();
            album_row1.add("Bleeding out");
            album_row1.add("Imagine Dragons");
            album_row1.add("2008");
        my_engine.insert_row("Albums", new Tuple(album_row1)); // INSERT ROW "Bleeding out", "Imagine Dragons", "2008" TO TABLE "Artists"
            ArrayList<String> album_row2 = new ArrayList<String>();
            album_row2.add("The E.N.D.");
            album_row2.add("Black Eyed Peas");
            album_row2.add("2007");
        my_engine.insert_row("Albums", new Tuple(album_row2)); // INSERT ROW "The E.N.D.","Black Eyed Peas, "2004" TO TABLE "Artists"	
        my_engine.get_all_relations().get("Albums").display(); // DISPLAY TABLE "Albums"
        
        my_engine.create_relation("Albums2");
            ArrayList<Attribute> album2_attributes = new ArrayList<Attribute>();
            album2_attributes.add(new Attribute("Album Name", Attribute.Type.VARCHAR, false));
            album2_attributes.add(new Attribute("Artist Name", Attribute.Type.VARCHAR, false));
            album2_attributes.add(new Attribute("Album Release Year", Attribute.Type.INTEGER, false));
        my_engine.get_all_relations().get("Albums2").set_attributes(album2_attributes); // add attributes "ARTIST NAME", "DEBUT YEAR" TO TABLE "Artists"
            ArrayList<String> album2_row1 = new ArrayList<String>();
            album2_row1.add("Chasing Cars");
            album2_row1.add("Snow Patrol");
            album2_row1.add("2006");
        my_engine.insert_row("Albums2", new Tuple(album2_row1));
            ArrayList<String> album2_row2 = new ArrayList<String>();
            album2_row2.add("The E.N.D.");
            album2_row2.add("Black Eyed Peas");
            album2_row2.add("2007");
        my_engine.insert_row("Albums2", new Tuple(album2_row2));
        
        // PROJECT
        ArrayList<Attribute> test_attribute = new ArrayList<Attribute>();
            test_attribute.add(new Attribute("Artist Name", Attribute.Type.VARCHAR, false));
        my_engine.create_relation(my_engine.project(my_engine.get_all_relations().get("Albums"), test_attribute));
        my_engine.get_all_relations().get("projection").display();
        
        // UNION
        my_engine.create_relation(my_engine.union(my_engine.get_all_relations().get("Albums"), my_engine.get_all_relations().get("Artists")));
        
        my_engine.create_relation(my_engine.union(my_engine.get_all_relations().get("Albums"), my_engine.get_all_relations().get("Albums2")));
        my_engine.get_all_relations().get("Albums union Albums2").display();        
        
        // DIFFERENCE
        my_engine.create_relation(my_engine.difference(my_engine.get_all_relations().get("Albums"), my_engine.get_all_relations().get("Artists")));
        
        my_engine.create_relation(my_engine.difference(my_engine.get_all_relations().get("Albums"), my_engine.get_all_relations().get("Albums2")));
        my_engine.show(my_engine.get_all_relations().get("Albums difference Albums2"));
        
        // CROSS PRODUCT
        my_engine.create_relation(my_engine.cross_product(my_engine.get_all_relations().get("Albums"), my_engine.get_all_relations().get("Artists")));
        my_engine.get_all_relations().get("Albums cross product Artists").display();
        
        // NATURAL JOIN
        my_engine.create_relation(my_engine.natural_join(my_engine.get_all_relations().get("Albums"), my_engine.get_all_relations().get("Artists")));
        my_engine.get_all_relations().get("Albums natural join Artists").display();

        // SELECT
        //String to String
        my_engine.create_relation(my_engine.select(my_engine.get_all_relations().get("Artists"), "equal_to", "hi", "hi", "Select - String to String"));
        my_engine.get_all_relations().get("Select - String to String").display();
        //Integer to Integer
        my_engine.create_relation(my_engine.select(my_engine.get_all_relations().get("Artists"), "greater_than", 5, 2, "Select - Integer to Integer"));
        my_engine.get_all_relations().get("Select - Integer to Integer").display();
        //Attribute to Attribute
        my_engine.create_relation(my_engine.select(my_engine.get_all_relations().get("Artists"), "equal_to", my_engine.get_all_relations().get("Artists").get_attribute("Artist Name"), my_engine.get_all_relations().get("Artists").get_attribute("Artist Name"), "Select - Attribute to Attribute"));
        my_engine.get_all_relations().get("Select - Attribute to Attribute").display();
        //Attribute to Integer
        my_engine.create_relation(my_engine.select(my_engine.get_all_relations().get("Artists"), "less_than", my_engine.get_all_relations().get("Artists").get_attribute("Debut Year"), 2006, "Select - Attribute to Integer"));
        my_engine.get_all_relations().get("Select - Attribute to Integer").display();
        //Attribute to String
        my_engine.create_relation(my_engine.select(my_engine.get_all_relations().get("Artists"), "equal_to", my_engine.get_all_relations().get("Artists").get_attribute("Artist Name"), "Imagine Dragons", "Select - Attribute to String"));
        my_engine.get_all_relations().get("Select - Attribute to String").display();

        
       //SAVE TEST
        my_engine.write("Engine1");
        
       //LOAD TEST that copies my_engine
        Engine my_engine2 = new Engine();
        my_engine2.open("Engine1");
        //Test if open was successful by displaying a table from my_engine in my_engine2
        System.out.println("IN ENGINE 2 now");
        my_engine2.get_all_relations().get("Albums cross product Artists").display();
        
        //Test if open can correctly load a Relation with a name, and no set Attributes
        my_engine2.get_all_relations().get("Empty").display();
        
        while (true){ // infinite loop to check if exit works
            my_engine.exit();
        }


    }
}