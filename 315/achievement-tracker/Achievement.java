import java.util.*;
import java.lang.*;
import java.util.ArrayList;

public class Achievement{
    public final int achievementID;
    public final int gameID;
    public final String name;
    public final int points;
    public final ArrayList<Integer> players;
    
    public Achievement(int gameID, int achievementID, String name, int points){
        this.gameID = gameID;
        this.achievementID = achievementID;
        this.name = name;
        this.points = points;
        players = new ArrayList<Integer>();
    }
    
    @Override
    public String toString(){
        return name;
    }
}