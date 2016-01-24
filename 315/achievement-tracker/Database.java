import java.util.*;
import java.lang.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Database{
    public final HashMap<Integer,Player> players; //integer is playerID
    public final HashMap<Integer,Game> games; //integer is gameID
    public final HashMap<Integer,Achievement> achievements; //integer is achievementID
    
    public Database(){
        players = new HashMap<Integer,Player>();
        games = new HashMap<Integer,Game>();
        achievements = new HashMap<Integer,Achievement>();
    }
    
    public void addPlayer(int playerID, String name){
        players.put(playerID, new Player(playerID, name));
    }
    
    public void addGame(int gameID, String name){
        games.put(gameID, new Game(gameID, name));
    }
    
    public void addAchievement(int gameID, int achievementID, String name, int points){
        if(games.containsKey(gameID)){
            achievements.put(achievementID, new Achievement(gameID, achievementID, name, points));
            games.get(gameID).addAchieve();
        }else{
            System.out.println("game does not exist");
        }
    }
    
    public void plays(int playerID, int gameID, String ign){
        if(players.containsKey(playerID) && games.containsKey(gameID)){
            players.get(playerID).games.put(gameID, ign);
            games.get(gameID).players.add(playerID);
        }else{
            System.out.println("player or game does not exist");
        }
    }
    
    public void addFriends(int player1ID, int player2ID){        
        if(players.containsKey(player1ID) && players.containsKey(player2ID)){
            players.get(player1ID).friends.add(player2ID);
            players.get(player2ID).friends.add(player1ID);
        }
    }
    
    public void achieve(int playerID, int gameID, int achievementID){
        if(players.containsKey(playerID) && achievements.containsKey(achievementID)){
            Player p = players.get(playerID);
            Achievement a = achievements.get(achievementID);
            p.achievements.add(achievementID);
            p.addPoints(a.points);
            a.players.add(playerID);
            a.achieved();
        }
    }
    
    public ArrayList<Integer> friendsWhoPlay(int playerID, int gameID){
        ArrayList<Integer> p = new ArrayList<Integer>();
        if(players.containsKey(playerID) && games.containsKey(gameID)){
            for(int friend : players.get(playerID).friends){
                if(games.get(gameID).players.contains(friend)) p.add(friend);
            }
        }
        return p;
    }
    
    public void comparePlayers(int player1ID, int player2ID, int gameID){
        //Print report comparing player 1 and player 2's achievement records.
        
    }
    
    public void summarizePlayer(int playerID){
        //Print record of all of the player's friends, games, and gamer point totals.
        
        
    }
    
    public void summarizeGame(int gameID){
        //Print a record of all players who play the specified game and the number of times each of its achievements have been accomplished.
    }
    
    public void summarizeAchievement(int gameID, int achievementID){
        //Print a list of all players who have achieved an achievement, and the percentage of players who play that game who have the achievement.
    }
    
    public void achievementRank(){
        //Print a summary ranking all players by their total number of gamer points.
        
    }
}