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
            games.get(gameID).achievements.add(achievementID);
        }else{
            //System.out.println("game does not exist");
        }
    }
    
    public void plays(int playerID, int gameID, String ign){
        if(players.containsKey(playerID) && games.containsKey(gameID)){
            players.get(playerID).games.put(gameID, new Playing(gameID, ign));
            games.get(gameID).players.add(playerID);
        }else{
            //System.out.println("player or game does not exist");
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
            if(p.games.containsKey(gameID) && !p.games.get(gameID).achievements.contains(achievementID)){
                Playing playerInfo = p.games.get(gameID);
                int pointsEarned = a.points;
                playerInfo.achievements.add(achievementID);
                playerInfo.addPoints(pointsEarned);
                p.addPoints(pointsEarned);
                a.players.add(playerID);
                a.achieved();
            }else{
                //System.out.println("player does not play this game or already has this achievement");
            }
        }else{
            //System.out.println("player or achievement does not exist");
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
        Player p = players.get(playerID);
        Map<Integer, Playing> playersGames = p.games;
        Set<Integer> gameskeys = playersGames.keySet();
        Integer[] gameIDs = gameskeys.toArray(new Integer[gameskeys.size()]);
        
        System.out.println("Player: " + p.name);
        System.out.println("Total Gamerscore: " + p.getGamerScore());
        System.out.printf("%4s %-20s %-16s %-16s %-16s \n", " ", "Game", "Achievements", "Gamerscore", "IGN");
        System.out.println("--------------------------------------------------------------------------------");
        for(int i = 0; i < gameIDs.length; i++){
            Game g = games.get(gameIDs[i]);
            Playing playerInfo = p.games.get(gameIDs[i]);
            int nPlayerAchieve = playerInfo.achievements.size();
            int nTotalAchieve = g.achievements.size();
            System.out.printf("%4s %-20s %-16s %-16s %-16s \n", (i+1 +"."), g.name, (nPlayerAchieve + "/" + nTotalAchieve), (playerInfo.getPoints() + " pts"), playerInfo.ign);
        }
        
        ArrayList<Player> friends = new ArrayList<Player>();
        for(int friend : p.friends){
            friends.add(players.get(friend));
        }
        Collections.sort(friends, new Player.PlayerComparator());
        
        System.out.printf("\n%4s %-16s %-16s \n", " ", "Friend", "Gamerscore");
        System.out.println("--------------------------------------------------------------------------------");
        int nFriends = friends.size();
        for(int i = 0; i < nFriends; i++){
            Player friend = friends.get(nFriends - i - 1);
            System.out.printf("%4s %-16s %-16s \n", (i+1 +"."), friend.name, friend.getGamerScore());
        }        
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