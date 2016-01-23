import java.util.*;
import java.lang.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Database{
    public final HashMap<Integer,Player> players;
    public final HashMap<Integer,Game> games;
    public final HashMap<Integer,Achievement> achievements;
    
    public Database(){
        players = new HashMap<Integer,Player>();
        games = new HashMap<Integer,Game>();
        achievements = new HashMap<Integer,Achievement>();
    }
    
    public void addPlayer(int id, String name){
        players.put(id, new Player(id, name));
    }
    
    public void addGame(int id, String name){
        games.put(id, new Game(id, name));
    }
    
    public void addAchievement(int gid, int aid, String name, int points){
        if(games.containsKey(gid)){
            achievements.put(aid, new Achievement(gid, aid, name, points));
            games.get(gid).addachieve();
        }else{
            System.out.println("game does not exist");
        }
    }
    
    public void plays(int pid, int gid, String ign){
        if(players.containsKey(pid) && games.containsKey(gid)){
            players.get(pid).games.put(gid, ign);
            games.get(gid).players.add(pid);
        }else{
            System.out.println("player or game does not exist");
        }
    }
    
    public void addFriends(int id1, int id2){        
        if(players.containsKey(id1) && players.containsKey(id2)){
            players.get(id1).friends.add(id2);
            players.get(id2).friends.add(id1);
        }
    }
    
    public void achieve(int pid, int gid, int aid){
        if(players.containsKey(pid) && achievements.containsKey(aid)){
            Player tmp = players.get(pid);
            Achievement temp = achievements.get(aid);
            tmp.achievements.add(aid);
            tmp.addPoints(temp.points);
            temp.players.add(pid);
            temp.achieved();
        }
    }
    
    public ArrayList<Integer> friendsWhoPlay(int pid, int gid){
        ArrayList<Integer> tmp = new ArrayList<Integer>();
        if(players.containsKey(pid) && games.containsKey(gid)){
            for(int friend : players.get(pid).friends){
                if(games.get(gid).players.contains(friend)) tmp.add(friend);
            }
        }
        return tmp;
    }
    
    public void comparePlayers(int pid1, int pid2, int gid){
        //Print report comparing player 1 and player 2's achievement records.
        
    }
    
    public void summarizePlayer(int id){
        //Print record of all of the player's friends, games, and gamer point totals.
        
        
    }
    
    public void summarizeGame(int id){
        //Print a record of all players who play the specified game and the number of times each of its achievements have been accomplished.
    }
    
    public void summarizeAchievement(int gid, int aid){
        //Print a list of all players who have achieved an achievement, and the percentage of players who play that game who have the achievement.
    }
    
    public void achievementRank(){
        //Print a summary ranking all players by their total number of gamer points.
        
    }
}