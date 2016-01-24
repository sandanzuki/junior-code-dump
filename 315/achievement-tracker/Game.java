import java.util.*;
import java.lang.*;
import java.util.ArrayList;

public class Game{
    public final int gameID;
    public final String name;
    public final ArrayList<Integer> players;
    public final ArrayList<Integer> achievements;
    private int totalAchieve;
    
    public Game(int gameID, String name){
        this.gameID = gameID;
        this.name = name;
        players = new ArrayList<Integer>();
        achievements = new ArrayList<Integer>();
        totalAchieve = 0;
    }
    
    public void addAchieve(){
        totalAchieve++;
    }
}