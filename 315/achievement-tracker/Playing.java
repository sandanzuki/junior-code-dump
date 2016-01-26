import java.util.*;
import java.lang.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Playing{
    public final int gameID;
    public final String ign;
    public final ArrayList<Integer> achievements;
    private int totalPoints;
    
    public Playing(int gameID, String ign){
        this.gameID = gameID;
        this.ign = ign;
        achievements = new ArrayList<Integer>();
        totalPoints = 0;
    }
    
    public void addPoints(int points){
        totalPoints += points;
    }
    
    public int getPoints(){
        return totalPoints;
    }
}