import java.util.*;
import java.lang.*;
import java.util.regex.*;
import java.util.ArrayList;

public class Main{
    public static void main(String[] args){
        Database db = new Database();
        Scanner cin = new Scanner(System.in);
        while(cin.hasNextLine()){
            String input = cin.nextLine();
            if(input.equals("quit") || input.equals("q")) break;
            matching(input,db);
        }
    }
    
    private static final ArrayList<Pattern> rxs = new ArrayList<Pattern>(
        Arrays.asList(Pattern.compile("\\bAddPlayer\\b( +)(\\d+)( +)\"([^\"]*)\""),
                      Pattern.compile("\\bAddGame\\b( +)(\\d+)( +)\"([^\"]*)\""),
                      Pattern.compile("\\bAddAchievement\\b( +)(\\d+)( +)(\\d+)( +)\"([^\"]*)\"( +)(\\d+)"),
                      Pattern.compile("\\bPlays\\b( +)(\\d+)( +)(\\d+)( +)\"([^\"]*)\""),
                      Pattern.compile("\\bAddFriends\\b( +)(\\d+)( +)(\\d+)"),
                      Pattern.compile("\\bAchieve\\b( +)(\\d+)( +)(\\d+)( +)(\\d+)"),
                      Pattern.compile("\\bFriendsWhoPlay\\b( +)(\\d+)( +)(\\d+)"),
                      Pattern.compile("\\bComparePlayers\\b( +)(\\d+)( +)(\\d+)( +)(\\d+)"),
                      Pattern.compile("\\bSummarizePlayer\\b( +)(\\d+)"),
                      Pattern.compile("\\bSummarizeGame\\b( +)(\\d+)"),
                      Pattern.compile("\\bSummarizeAchievement\\b( +)(\\d+)( +)(\\d+)"),
                      Pattern.compile("\\bAchievementRanking\\b")
        )
    );
        
    public static void matching(String s, Database db){
        int casenum = -1;
        for(int i = 0; i < rxs.size(); i++){
            if(rxs.get(i).matcher(s).matches()) casenum = i;
        }
        
        String[] first = s.split("\"");
        String[] second = first[0].split(" ");
        int playerID, player1ID, player2ID, gameID, achievementID;
        String playerName, gameName, achievementName;
        
        
        switch(casenum){
            case 0:     //add player
                        playerID = Integer.parseInt(second[1]);
                        playerName = first[1].trim();
                        db.addPlayer(playerID, playerName);
                        break;
            case 1:     //add game
                        gameID = Integer.parseInt(second[1]);
                        gameName = first[1].trim();
                        db.addGame(gameID, gameName);
                        break;
            case 2:     //add achievement
                        gameID = Integer.parseInt(second[1]);
                        achievementID = Integer.parseInt(second[2]);
                        achievementName = first[1].trim();
                        int achievementPoints = Integer.parseInt(first[2].trim());
                        db.addAchievement(gameID, achievementID, achievementName, achievementPoints);
                        break;
            case 3:     //plays
                        playerID = Integer.parseInt(second[1]);
                        gameID = Integer.parseInt(second[2]);
                        String playerIGN = first[1].trim();
                        db.plays(playerID, gameID, playerIGN);
                        break;
            case 4:     //add friends
                        player1ID = Integer.parseInt(second[1]);
                        player2ID = Integer.parseInt(second[2]);
                        db.addFriends(player1ID, player2ID);
                        break;
            case 5:     //achieve
                        playerID = Integer.parseInt(second[1]);
                        gameID = Integer.parseInt(second[2]);
                        achievementID = Integer.parseInt(second[3]);
                        db.achieve(playerID, gameID, achievementID);
                        break;
            case 6:     //friends who play
                        playerID = Integer.parseInt(second[1]);
                        gameID = Integer.parseInt(second[2]);
                        db.friendsWhoPlay(playerID, gameID);
                        break;
            case 7:     //compare players
                        player1ID = Integer.parseInt(second[1]);
                        player2ID = Integer.parseInt(second[2]);
                        gameID = Integer.parseInt(second[3]);
                        db.achieve(player1ID, player2ID, gameID);
                        break;
            case 8:     //summarizeplayer
                        playerID = Integer.parseInt(second[1]);
                        db.summarizePlayer(playerID);
                        break;
            case 9:     //summarizegame
                        gameID = Integer.parseInt(second[1]);
                        db.summarizeGame(gameID);
                        break;
            case 10:    //summarizeachievement
                        gameID = Integer.parseInt(second[1]);
                        achievementID = Integer.parseInt(second[2]);
                        db.summarizeAchievement(gameID, achievementID);
                        break;
            case 11:    //achievement ranking
                        db.achievementRank();
                        break;
            default:    //invalid input
                        System.out.println("invalid input");
                        break;
        }
    }
}