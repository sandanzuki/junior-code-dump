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
    private int gamerScore;
    
    public Player(int playerID, String name){
        this.playerID = playerID;
        this.name = name;
        friends = new ArrayList<Integer>();
        games = new HashMap<Integer,String>();
        achievements = new ArrayList<Integer>();
        gamerScore = 0;
    }
    
    public void addPoints(int points){
        gamerScore += points;
    }
}