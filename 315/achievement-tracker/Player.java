import java.util.*;
import java.lang.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Player implements Comparable<Player>{
    public final int playerID;
    public final String name;
    public final ArrayList<Integer> friends; 
    public final HashMap<Integer,Playing> games;
    private int gamerScore;
    
    public Player(int playerID, String name){
        this.playerID = playerID;
        this.name = name;
        friends = new ArrayList<Integer>();
        games = new HashMap<Integer,Playing>();
        gamerScore = 0;
    }
    
    public void addPoints(int points){
        gamerScore += points;
    }
    
    public int getGamerScore(){
        return gamerScore;
    }
    
    @Override
    public String toString(){
        return name;
    }
    
    @Override
    public int compareTo(Player p){
        if(gamerScore == p.gamerScore) return 0;
        else if(gamerScore < p.gamerScore) return -1;
        else return 1;
    }
    
    static class PlayerComparator implements Comparator<Player>{
        @Override
        public int compare(Player p1, Player p2) {
            if(p1.gamerScore == p2.gamerScore) return 0;
            else if(p1.gamerScore < p2.gamerScore) return -1;
            else return 1;
        }
    }
}