import java.util.*;
import java.lang.*;
import java.util.ArrayList;

public class Main{
    public static void main(String[] args){
        Database db = new Database();
        if(args.length == 5){
            if(args[0].equals("AddAchievement")){
                try{
                    int gameID = Integer.parseInt(args[1]);
                    int achievementID = Integer.parseInt(args[2]);
                    String achievementName = args[3];
                    int achievementPoints = Integer.parseInt(args[4]);
                    db.addAchievement(gameID, achievementID, achievementName, achievementPoints);
                }catch(NumberFormatException e){
                    System.out.println("please use the format AddAchievement <int> <int> <string> <int>");
                }
            }else{
                System.out.println("invalid argument : " + args[0]);
            }
        }else if(args.length == 4){
            if(args[0].equals("Plays")){
                try{
                    int playerID = Integer.parseInt(args[1]);
                    int gameID = Integer.parseInt(args[2]);
                    String playerIGN = args[3];
                    db.plays(playerID, gameID, playerIGN);
                }catch(NumberFormatException e){
                    System.out.println("please use the format Plays <int> <int> <string>");
                }
            }else if(args[0].equals("Achieve")){
                try{
                    int playerID = Integer.parseInt(args[1]);
                    int gameID = Integer.parseInt(args[2]);
                    int achievementID = Integer.parseInt(args[3]);
                    db.achieve(playerID, gameID, achievementID);
                }catch(NumberFormatException e){
                    System.out.println("please use the format Achieve <int> <int> <int>");
                }
            }else if(args[0].equals("ComparePlayers")){
                try{
                    int player1ID = Integer.parseInt(args[1]);
                    int player2ID = Integer.parseInt(args[2]);
                    int gameID = Integer.parseInt(args[3]);
                    db.achieve(player1ID, player2ID, gameID);
                }catch(NumberFormatException e){
                    System.out.println("please use the format ComparePlayers <int> <int> <int>");
                }
            }else{
                System.out.println("invalid argument : " + args[0]);
            }
        }else if(args.length == 3){
            if(args[0].equals("AddPlayer")){
                try{
                    int playerID = Integer.parseInt(args[1]);
                    String playerName = args[2];
                    db.addPlayer(playerID, playerName);
                }catch(NumberFormatException e){
                    System.out.println("please use the format AddPlayer <int> <string>");
                }
            }else if(args[0].equals("AddGame")){
                try{
                    int gameID = Integer.parseInt(args[1]);
                    String gameName = args[2];
                    db.addGame(gameID, gameName);
                }catch(NumberFormatException e){
                    System.out.println("please use the format AddGame <int> <string>");
                }
            }else if(args[0].equals("AddFriends")){
                try{
                    int player1ID = Integer.parseInt(args[1]);
                    int player2ID = Integer.parseInt(args[2]);
                    db.addFriends(player1ID, player2ID);
                }catch(NumberFormatException e){
                    System.out.println("please use the format AddFriends <int> <int>");
                }
            }else if(args[0].equals("FriendsWhoPlay")){
                try{
                    int playerID = Integer.parseInt(args[1]);
                    int gameID = Integer.parseInt(args[2]);
                    db.friendsWhoPlay(playerID, gameID);
                }catch(NumberFormatException e){
                    System.out.println("please use the format FriendsWhoPlay <int> <int>");
                }
            }else if(args[0].equals("SummarizeAchievement")){
                try{
                    int gameID = Integer.parseInt(args[1]);
                    int achievementID = Integer.parseInt(args[2]);
                    db.summarizeAchievement(gameID, achievementID);
                }catch(NumberFormatException e){
                    System.out.println("please use the format SummarizeAchievement <int> <int>");
                }
            }else{
                System.out.println("invalid argument : " + args[0]);
            }
        }else if(args.length == 2){
            if(args[0].equals("SummarizePlayer")){
                try{
                    int playerID = Integer.parseInt(args[1]);
                    db.summarizePlayer(playerID);
                }catch(NumberFormatException e){
                    System.out.println("please use the format SummarizePlayer <int>");
                }
            }else if(args[0].equals("SummarizeGame")){
                try{
                    int gameID = Integer.parseInt(args[1]);
                    db.summarizeGame(gameID);
                }catch(NumberFormatException e){
                    System.out.println("please use the format SummarizeGame <int>");
                }
            }else{
                System.out.println("invalid argument : " + args[0]);
            }
        }else if(args.length == 1){
            if(args[0].equals("AchievementRanking")){
                db.achievementRank();
            }else{
                System.out.println("invalid argument : " + args[0]);
            }
        }else{
            System.out.println("invalid number of arguments");
        }
    }
}