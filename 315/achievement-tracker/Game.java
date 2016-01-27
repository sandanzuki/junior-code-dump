import java.util.*;
import java.lang.*;
import java.util.ArrayList;

public class Game{
    public final int gameID;
    public final String name;
    public final ArrayList<Integer> players;
    public final ArrayList<Integer> achievements;
    
    public Game(int gameID, String name){
        this.gameID = gameID;
        this.name = name;
        players = new ArrayList<Integer>();
        achievements = new ArrayList<Integer>();
    }
    
    @Override
    public String toString(){
        return name;
    }
}