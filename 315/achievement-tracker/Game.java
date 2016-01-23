import java.util.*;
import java.lang.*;
import java.util.ArrayList;

public class Game{
    public final int gameID;
    public final String name;
    public final ArrayList<Integer> players;
    public final ArrayList<Integer> achievements;
    private int totalachieve;
    
    public Game(int gameID, String name){
        this.gameID = gameID;
        this.name = name;
        players = new ArrayList<Integer>();
        achievements = new ArrayList<Integer>();
        totalachieve = 0;
    }
    
    public void addachieve(){
        totalachieve++;
    }
    
    /*public String toString(){
        return;
    }*/
}