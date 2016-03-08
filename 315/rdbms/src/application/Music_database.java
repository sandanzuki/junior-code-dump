package application;

import java.util.*;
import java.io.*;
import parser.*;

/**
 * 
 * @author StaticLightningDragons
 *
 */
public class Music_database {

    /**
     * Runs Music Database application by first asking the user if they want to setup or load a database.
     * Then allows the user to navigate through menus and interact with database.
     *
     * @param args Standard argument pipes
     * @throws FileNotFoundException In case OPEN argument is not found
     */
    public static void main(String[] args) throws FileNotFoundException {
        Parser parser = new Parser();

        ArrayList<String> commands_to_parse = new ArrayList<String>(); // What will be sent to parser
        String current_command = "";

        System.out.println("\n----------------------Welcome to your music database---------------------\n");
        System.out.println("         Use the menus to navigate through what you wish to do.         ");
        System.out.println("You can input \"Main Menu\" at any of the menus to return to the main menu.\n");
        current_command = Application.load_or_new();

        if (current_command.equals("load")) {

            Boolean load_successfull = false;

            while (!load_successfull) { 
                try {
                    current_command = Application.load_database();
                    parser.parse(current_command);
                    load_successfull = true;
                } catch (FileNotFoundException e){
                    load_successfull = false;
                }
            }

        } else if (current_command == "new") {

            ArrayList<String> tables_to_create = Application.create_new_database();

            for (String s : tables_to_create) {
                parser.parse(s);
            }

        }
        
        while (current_command != "quit") {
            current_command = Application.main_menu();
            
            switch (current_command) {
                case "manage":
                    current_command = Application.manage_music();
                    break;

                case "view":
                    current_command = Application.view_music_library();
                    break;

                case "search":
                    current_command = Application.search_music_library();
                    break;

                case "save":
                    commands_to_parse.add(Application.save_database());
                    break;

                case "quit":
                    current_command = Application.quit();
                    break;
            }

            if (current_command != "save") {
                commands_to_parse = select_current_command(current_command);
            }

            if (commands_to_parse.get(0) != "menu") {
                for (String s : commands_to_parse) {
                    parser.parse(s);
                }
                commands_to_parse.clear();
            }
		}
        
    }  


    /**
     * Selects and executes function that user has chosen from menus
     *
     * @param input String that will route to which function user called
     * @return Returns an arraylist of Strings to be parsed
     */
    public static ArrayList<String> select_current_command(String input) {

        String parse_command = "";
        ArrayList<String> parse_commands = new ArrayList<String>();

        switch (input) {
            case "add song":
                parse_commands = Application.add_song();
                break;

            case "add artist":
                parse_commands = Application.add_artist();
                break;

            case "add album":
                parse_commands = Application.add_album();
                break;

            case "delete song":
                parse_commands = Application.delete_song();
                break;

            case "delete artist":
                parse_commands = Application.delete_artist();
                break;

            case "delete album":
                parse_commands = Application.delete_album();
                break;

            case "view songs":
                parse_commands.add(Application.view_songs());
                break;

            case "view artists":
                parse_commands.add(Application.view_artists());
                break;

            case "view albums":
                parse_commands.add(Application.view_albums());
                break;

            case "view artists albums":
                parse_commands.add(Application.view_artists_albums());
                break;

            case "view album tracklist":
                parse_commands.add(Application.view_albums_tracklist());
                break;

            case "view artists tracks":
                parse_commands.add(Application.view_artists_tracks());
                break;

            case "search songs":
                parse_commands.add(Application.search_songs());
                break;

            case "search artists":
                parse_commands.add(Application.search_artists());
                break;

            case "search albums":
                parse_commands.add(Application.search_albums());
                break;

            case "menu":
                parse_commands.add("menu");
                break;

            case "EXIT":
                parse_commands.add("EXIT");
                break;

            default:
                break;
        }
        return parse_commands;
    } 
}