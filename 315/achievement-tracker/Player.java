import java.util.*;
import java.lang.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Player{
    public final int playerID;
    public final String name;
    public final ArrayList<Integer> friends; 
    public final HashMap<Integer,String> games;
    public final ArrayList<Integer> achievements;
    private int totalPoints;
    
    public Player(int playerID, String name){
        this.playerID = playerID;
        this.name = name;
        friends = new ArrayList<Integer>();
        games = new HashMap<Integer,String>();
        achievements = new ArrayList<Integer>();
        totalPoints = 0;
    }
    
    public void addPoints(int points){
        totalPoints += points;
    }
    
    /*public String toString(){
        return;
    }*/
}