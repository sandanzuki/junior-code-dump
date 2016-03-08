package application;

import engine.*;
import parser.*;

import java.io.*;
import java.util.*;

/**
 * 
 * @author StaticLightningDragons
 *
 */

public class Application {

	/**
     * Menu options for load a new database or create a new database 
     * 
     * @return menu option selected
     */
    public static String load_or_new() {

        Boolean incorrect_input = true;
        String input;
        Scanner in = new Scanner(System.in);

        String instructions = "";

        System.out.println("\nWhat would you like to do:\n");
        System.out.println("1. Start new database");
        System.out.println("2. Load Database");
        System.out.print("Input number: ");

        while (incorrect_input) {
            input = in.nextLine().trim();

            switch (input) {
            case "1":
                System.out.println("\n");
                incorrect_input = false;
                instructions = "new";
                break;

            case "2":
                System.out.println("\n");
                incorrect_input = false;
                instructions = "load";
                break;

            default:
                System.out.print("Not a valid input. Please select again: ");
                break;
            }
        }
        return instructions;
    }

    /**
     * Menu options for the main menu which include Manage Music, Veiw Music Library, Search, Save Database, and Quit.
     * 
     * @return menu option selected
     */
    public static String main_menu() {

        Boolean incorrect_input = true;
        String input;
        Scanner in = new Scanner(System.in);

        String instructions = "";

        System.out.println("\n------Main Menu------\n");
        System.out.println("1. Manage Music");
        System.out.println("2. View Music Library");
        System.out.println("3. Search");
        System.out.println("4. Save Database");
        System.out.println("5. Quit");
        System.out.print("Input number: ");

        while (incorrect_input) {
            input = in.nextLine().trim();

            switch (input) {
                case "1":
                    System.out.println("\n");
                    incorrect_input = false;
                    instructions = "manage";
                    break;

                case "2":
                    System.out.println("\n");
                    incorrect_input = false;
                    instructions = "view";
                    break;

                case "3":
                    System.out.println("\n");
                    incorrect_input = false;
                    instructions = "search";
                    break;

                case "4":
                    System.out.println("\n");
                    incorrect_input = false;
                    instructions = "save";
                    break;

                case "5":
                    System.out.println("\n");
                    incorrect_input = false;
                    instructions = "quit";
                    break;

                default:
                    System.out.print("Not a valid input. Please select again: ");
                    break;
            }
        }

        return instructions;
    }

    /**
     * Menu options for the manage music menu which include 
     * Add Song, Add Artist, Add Album, Delete Song, Delete Artist, and Delete Album.
     * 
     * @return menu option selected
     */
    public static String manage_music() {

        Boolean incorrect_input = true;
        String input;
        Scanner in = new Scanner(System.in);

        String instructions = "";

        System.out.println("\n-----Manage Music----\n");
        System.out.println("1. Add Song");
        System.out.println("2. Add Artist");
        System.out.println("3. Add Album");
        System.out.println("4. Delete Song");
        System.out.println("5. Delete Artist");
        System.out.println("6. Delete Album");
        System.out.print("Input number: ");

        while (incorrect_input) {
        input = in.nextLine().trim();

            switch (input) {
                case "1":
                    System.out.println("\n");
                    incorrect_input = false;
                    instructions = "add song";
                    break;

                case "2":
                    System.out.println("\n");
                    incorrect_input = false;
                    instructions = "add artist";
                    break;

                case "3":
                    System.out.println("\n");
                    incorrect_input = false;
                    instructions = "add album";
                    break;

                case "4":
                    System.out.println("\n");
                    incorrect_input = false;
                    instructions = "delete song";
                    break;

                case "5":
                    System.out.println("\n");
                    incorrect_input = false;
                    instructions = "delete artist";
                    break;

                case "6":
                    System.out.println("\n");
                    incorrect_input = false;
                    instructions = "delete album";
                    break;

                case "Main Menu":
                    System.out.println("\n");
                    incorrect_input = false;
                    instructions = "menu";
                    break;

                default:
                    System.out.print("Not a valid input. Please select again: ");
                    break;
            }
        }
        return instructions;
    }

    /**
     * Menu options for the veiw music library menu which include 
     * Veiw All Songs, Veiw All Artists, Veiw All Albums, View Artist's Albums, View Album's Tracks, and View Artist's Songs.
     * 
     * @return menu option selected
     */
    public static String view_music_library() {

        Boolean incorrect_input = true;
        String input;
        Scanner in = new Scanner(System.in);

        String instructions = "";

        System.out.println("\n-----View Library----\n");
        System.out.println("1. View All Songs");
        System.out.println("2. View All Artists");
        System.out.println("3. View All Albums");
        System.out.println("4. View Artist's Albums");
        System.out.println("5. View Album's Tracks");
        System.out.println("6. View Artist's Songs");
        System.out.print("Input number: ");

        while (incorrect_input) {
            input = in.nextLine().trim();

            switch (input) {
                case "1":
                    System.out.println("\n");
                    incorrect_input = false;
                    instructions = "view songs";
                    break;

                case "2":
                    System.out.println("\n");
                    incorrect_input = false;
                    instructions = "view artists";
                    break;

                case "3":
                    System.out.println("\n");
                    incorrect_input = false;
                    instructions = "view albums";
                    break;

                case "4":
                    System.out.println("\n");
                    incorrect_input = false;
                    instructions = "view artists albums";
                    break;

                case "5":
                    System.out.println("\n");
                    incorrect_input = false;
                    instructions = "view album tracklist";
                    break;

                case "6":
                    System.out.println("\n");
                    incorrect_input = false;
                    instructions = "view artists tracks";
                    break;

                case "Main Menu":
                    System.out.println("\n");
                    incorrect_input = false;
                    instructions = "menu";
                    break;

                default:
                    System.out.print("Not a valid input. Please select again: ");
                    break;
            }
        }
        return instructions;
    }

    /**
     * Menu options for the search music library menu which include 
     * Search Songs, Search Artists, and Search Albums.
     * 
     * @return menu option selected
     */
    public static String search_music_library() {

        Boolean incorrect_input = true;
        String input;
        Scanner in = new Scanner(System.in);

        String instructions = "";

        System.out.println("\n-----Search Music----\n");
        System.out.println("1. Search Songs");
        System.out.println("2. Search Artists");
        System.out.println("3. Search Albums");
        System.out.print("Input number: ");

        while (incorrect_input) {
            input = in.nextLine().trim();

            switch (input) {
                case "1":
                    System.out.println("\n");
                    incorrect_input = false;
                    instructions = "search songs";
                    break;

                case "2":
                    System.out.println("\n");
                    incorrect_input = false;
                    instructions = "search artists";
                    break;

                case "3":
                    System.out.println("\n");
                    incorrect_input = false;
                    instructions = "search albums";
                    break;

                case "Main Menu":
                    System.out.println("\n");
                    incorrect_input = false;
                    instructions = "menu";
                    break;

                default:
                    System.out.print("Not a valid input. Please select again: ");
                    break;
            }
        }
        return instructions;
    }

    /**
    * Gathers information from user about the song they wish to add and then
    * creates an array list of strings made up of commands for the parser.
    * 
    * @return ArrayList of Strings to be sent to the parser
    */
    public static ArrayList<String> add_song() {

        ArrayList<String> parse_commands = new ArrayList<String>();
        String name = "";
        String genre = "";
        Integer length_in_seconds = 0;
        Integer minutes = 0;
        Integer seconds = 0;
        String length = "";
        String artist = "";
        String album = "";

        Scanner in = new Scanner(System.in);

        System.out.println("\n------Add a Song-----\n");
        System.out.print("Name of the song: ");
        while (name.equals("")) {
            name = in.nextLine().trim();
        }

        System.out.print("Genre of the song: ");
        while (genre.equals("")) {
            genre = in.nextLine().trim();
        }

        System.out.print("Length of the song in seconds: ");
        while (length_in_seconds == 0) {
            if (in.hasNextInt()) {
                length_in_seconds = in.nextInt();
            } else {
                in.next();
            }
        }
        
        minutes = length_in_seconds / 60;
        seconds = length_in_seconds % 60;
        if (seconds > 9) {
            length = minutes + ":" + seconds;
        } else {
            length = minutes + ":0" + seconds;
        }

        System.out.print("Name of the album the song is on: ");
        while (album.equals("")) {
            album = in.nextLine().trim();
        }

        System.out.print("Name of the artist: ");
        while (artist.equals("")) {
            artist = in.nextLine().trim();
        }

        parse_commands.add("INSERT INTO Songs VALUES FROM (\"" + name + "\", \"" + genre + "\", " + length.toString() + ");");
        parse_commands.add("INSERT INTO Is On VALUES FROM (\"" + name + "\", \"" + album + "\");");


        return parse_commands;
    }

    /**
     * Gathers information from user about the artist they wish to add and then
     * creates an array list of strings made up of commands for the parser.
     * 
     * @return ArrayList of Strings to be sent to the parser
     */
    public static ArrayList<String> add_artist(){

    	ArrayList<String> parse_commands = new ArrayList<String>();
    	String name = "";
    	Integer year = 0;

    	Scanner in = new Scanner(System.in);

    	System.out.println("\n-----Add an Artist---\n");
    	System.out.print("Name of the artist: ");
    	while (name.equals("")){
    		name = in.nextLine().trim();
    	}

    	System.out.print("Artists Debut Year: ");
    	while (year == 0){
    		if (in.hasNextInt()){
    			year = in.nextInt();
    		} else {
    			in.next();
    		}
    	}

    	parse_commands.add("INSERT INTO Artists VALUES FROM (\"" + name + "\", " + year + ");");

    	return parse_commands;
    }

    /**
     * Gathers information from user about the album they wish to add and then
     * creates an array list of strings made up of commands for the parser.
     * 
     * @return ArrayList of Strings to be sent to the parser
     */
    public static ArrayList<String> add_album(){

    	ArrayList<String> parse_commands = new ArrayList<String>();
    	String name = "";
    	Integer year = 0;
    	String artist = "";

    	Scanner in = new Scanner(System.in);

    	System.out.println("\n-----Add an Album----\n");
    	System.out.print("Name of the album: ");
    	while (name.equals("")){
    		name = in.nextLine().trim();
    	}

    	System.out.print("Year album released: ");
    	while (year == 0){
    		if (in.hasNextInt()){
    			year = in.nextInt();
    		} else {
    			in.next();
    		}
    	}

    	System.out.print("Name of the artist: ");
    	while (artist.equals("")){
    		artist = in.nextLine().trim();
    	}

    	parse_commands.add("INSERT INTO Albums VALUES FROM (\"" + name + "\", " + year + ");");
    	parse_commands.add("INSERT INTO Created VALUES FROM (\"" + artist + "\", \"" + name + "\");");


    	return parse_commands;
    }

    /**
     * Gathers information from user about the song they wish to delete and then
     * creates an array list of strings made up of commands for the parser.
     * 
     * @return ArrayList of Strings to be sent to the parser
     */
    public static ArrayList<String> delete_song() {

        ArrayList<String> parse_commands = new ArrayList<String>();
        String name = "";

        Scanner in = new Scanner(System.in);

        System.out.println("\n----Delete a Song----\n");
        System.out.print("Name of the song you would like to remove: ");
        while (name.equals("")) {
            name = in.nextLine().trim();
        }

        parse_commands.add("DELETE FROM Songs WHERE (Song Name == \"" + name + "\");");
        parse_commands.add("DELETE FROM Is On WHERE (Song Name == \"" + name + "\");");

        return parse_commands;
    }

    /**
     * Gathers information from user about the artist they wish to delete and then
     * creates an array list of strings made up of commands for the parser.
     * 
     * @return ArrayList of Strings to be sent to the parser
     */
    public static ArrayList<String> delete_artist() {

        ArrayList<String> parse_commands = new ArrayList<String>();
        String name = "";

        Scanner in = new Scanner(System.in);

        System.out.println("\n---Delete an Artist--\n");
        System.out.print("Name of the artist you would like to remove: ");
        while (name.equals("")) {
            name = in.nextLine().trim();
        }

        parse_commands.add("DELETE FROM Artists WHERE (Artist Name == \"" + name + "\");");

        return parse_commands;
    }

    /**
     * Gathers information from user about the album they wish to delete and then
     * creates an array list of strings made up of commands for the parser.
     * 
     * @return ArrayList of Strings to be sent to the parser
     */
    public static ArrayList<String> delete_album() {

        ArrayList<String> parse_commands = new ArrayList<String>();
        String name = "";

        Scanner in = new Scanner(System.in);

        System.out.println("\n----Delete an Album--\n");
        System.out.print("Name of the artist you would like to remove: ");
        while (name.equals("")) {
            name = in.nextLine().trim();
        }

        parse_commands.add("DELETE FROM Albums WHERE (Album Name == \"" + name + "\");");
        parse_commands.add("DELETE FROM Created WHERE (Album Name == \"" + name + "\");");

        return parse_commands;
    }

    /**
     * Asks the user which song they wish to search for and then
     * creates a String with a command for the parser.
     * 
     * @return String to be sent to the parser
     */
    public static String search_songs() {

        Scanner in = new Scanner(System.in);
        String song_name = "";
        String part_of_parse_command = "";

        System.out.println("\n-----Search Songs----\n");
        System.out.print("Which song are you searching for: ");

        while (song_name.equals("")) {
            song_name = in.nextLine().trim();
        }

        part_of_parse_command = "SHOW (select(Song Name ==\"" + song_name + "\")Songs);";
        return part_of_parse_command;
    }

    /**
     * Asks the user which artist they wish to search for and then
     * creates a String with a command for the parser.
     * 
     * @return String to be sent to the parser
     */
    public static String search_artists() {

        Scanner in = new Scanner(System.in);
        String artist_name = "";
        String part_of_parse_command = "";

        System.out.println("\n----Search Artists---\n");
        System.out.print("Which artist are you searching for: ");

        while (artist_name.equals("")) {
            artist_name = in.nextLine().trim();
        }

        part_of_parse_command = "SHOW (select(Artist Name ==\"" + artist_name + "\")Artists);";
        return part_of_parse_command;
    }

    /**
     * Asks the user which album they wish to search for and then
     * creates a String with a command for the parser.
     * 
     * @return String to be sent to the parser
     */
    public static String search_albums() {

        Scanner in = new Scanner(System.in);
        String album_name = "";
        String part_of_parse_command = "";

        System.out.println("\n----Search Albums----\n");
        System.out.print("Which album are you searching for: ");

        while (album_name.equals("")) {
            album_name = in.nextLine().trim();
        }

        part_of_parse_command = "SHOW (select(Album Name ==\"" + album_name + "\")Albums);";
        return part_of_parse_command;
    }

    /**
     * Creates a string to send to parser that will show all songs on the database.
     * 
     * @return String to be sent to the parser
     */
    public static String view_songs() {

        System.out.println("\n------View Songs-----\n");
        return "SHOW Songs;";
    }

    /**
     * Creates a string to send to parser that will show all artists on the database.
     * 
     * @return String to be sent to the parser
     */
    public static String view_artists() {

        System.out.println("\n----View Artists----\n");
        return "SHOW Artists;";
    }

    /**
     * Creates a string to send to parser that will show all albums on the database.
     * 
     * @return String to be sent to the parser
     */
    public static String view_albums() {

        System.out.println("\n-----View Albums----\n");
        return "SHOW Albums;";
    }

    /**
     * Asks the user which artist's albums they wish to search for and then
     * creates a String with a command for the parser.
     * 
     * @return String to be sent to the parser
     */
    public static String view_artists_albums() {

        Scanner in = new Scanner(System.in);
        String artist = "";
        String part_of_parse_command = "";

        System.out.println("\n-View Artist's Albums-\n");
        System.out.print("Which artist are you wanting to see: ");

        while (artist.equals("")) {
            artist = in.nextLine().trim();
        }

        part_of_parse_command = "SHOW ((select(Artist Name ==\"" + artist + "\")Artists) JOIN Created);";
        return part_of_parse_command;
    }

    /**
     * Asks the user which albums's trakcs they wish to search for and then
     * creates a String with a command for the parser.
     * 
     * @return String to be sent to the parser
     */
    public static String view_albums_tracklist() {

        Scanner in = new Scanner(System.in);
        String album = "";
        String part_of_parse_command = "";

        System.out.println("\n-View Album's Tracks-\n");
        System.out.print("Which album are you wanting to see: ");

        while (album.equals("")) {
            album = in.nextLine().trim();
        }

        part_of_parse_command = "SHOW ((select(Album Name ==\"" + album + "\")Albums) JOIN Is On);";
        return part_of_parse_command;
    }

    /**
     * Asks the user which artist's songs they wish to search for and then
     * creates a String with a command for the parser.
     * 
     * @return String to be sent to the parser
     */
    public static String view_artists_tracks() {

        Scanner in = new Scanner(System.in);
        String artist = "";
        String part_of_parse_command = "";

        System.out.println("\n-View Artist's Songs-\n");
        System.out.print("Which artist are you wanting to see: ");

        while (artist.equals("")) {
            artist = in.nextLine().trim();
        }

        part_of_parse_command = "SHOW ((select(Artist Name ==\"" + artist + "\")Artists) JOIN (Created JOIN Is On));";
        return part_of_parse_command;

    }

    /**
     * Creates an array list of strings with commands to create database schema.
     * 
     * @return ArrayList of Strings to be sent to the parser
     */
    public static ArrayList<String> create_new_database() {

        ArrayList<String> tables_to_create = new ArrayList<String>();

        tables_to_create.add("CREATE TABLE Songs (Song Name VARCHAR(40), Genre VARCHAR(15), Length VARCHAR(5)) PRIMARY KEY (Song Name);");
        tables_to_create.add("CREATE TABLE Artists (Artist Name VARCHAR(40), Debut Year INTEGER) PRIMARY KEY (Artist Name);");
        tables_to_create.add("CREATE TABLE Albums (Album Name VARCHAR(40), Year Released INTEGER) PRIMARY KEY (albumID);");
        tables_to_create.add("CREATE TABLE Created (Artist Name VARCHAR(40), Album Name VARCHAR(40)) PRIMARY KEY (Artist Name, Album Name);");
        tables_to_create.add("CREATE TABLE Is On (Song Name VARCHAR(40), Album Name VARCHAR(40)) PRIMARY KEY (Song Name, Album Name);");

        return tables_to_create;
    }

    /**
     * Asks user which database they wish to load and then creates a String with a command for the parser.
     * 
     * @return String to be sent to the parser
     */
    public static String load_database() {

        Scanner in = new Scanner(System.in);
        String database_name = "";
        String part_of_parse_command = "";

        System.out.print("What is the name of the library you wish to load: ");
        while (database_name.equals("")) {
            database_name = in.nextLine().trim();
        }

        part_of_parse_command = "OPEN " + database_name + ";";
        return part_of_parse_command;
    }

    /**
     * Asks user what name they want to call the database and then creates a String with a command for the parser.
     * 
     * @return String to be sent to the parser
     */
    public static String save_database() {

        Scanner in = new Scanner(System.in);
        String database_name = "";
        String part_of_parse_command = "";

        System.out.println("\n----Save Library----\n");
        System.out.print("What would you like to call the library: ");

        while (database_name.equals("")) {
            database_name = in.nextLine().trim();
        }

        part_of_parse_command = "WRITE " + database_name + ";";

        return part_of_parse_command;
    }

    /**
     * Creates a String with a command for the parser to quit.
     * 
     * @return String to be sent to the parser
     */
    public static String quit() {
        System.out.println("Quiting...");
        return "EXIT";
    }

}