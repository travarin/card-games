package com.casestudy.cards;
/**
 * Travis Langston
 * 
 * CS 3 Per 2
 * 9/20/2016 
 * This program will create player objects to be used for card games. 
 */
import java.util.*;
public class Player
{
    private Scanner rdIn           = new Scanner(System.in);
    private String  name           = "";
    private int     gamesWon       = 0;
    private int     gamesAttempted = 0;
        
    /**
     * Default constructor to initialize class Player
     */
    public Player()
    {
        System.out.print("\n    Enter player name : ");
        name = rdIn.nextLine();
    }
    
    /*
     * One argument constructor to initialize class Player.
     */
    public Player(String nm)
    {
    	name = nm;
    }
    
    /**
     * Accessor method to return the player's name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Accessor method to return the number of games the player has won
     */
    public int getGamesWon()
    {
        return gamesWon;
    }
    
    /**
     * Accessor method to return the number of games the player has played
     */
    public int getGamesAttempted()
    {
        return gamesAttempted;
    }
    
    /**
     * Mutator method to set the player's name
     */
    public void setName()
    {
        System.out.print("                                                                  Enter player name : ");
        name = rdIn.nextLine();
    }
    
    /**
     * Mutator method to set the number of games the player has won
     */
    public void setGamesWon()
    {
        gamesWon++;
    }
    
    /**
     * Mutator method to set the number of games the player has attempted
     */
    public void setGamesAttempted()
    {
        gamesAttempted++;
    }
        
    /**
     * Tells the computer how to print out an object of this class
     */
    public String toString()
    {
        return ("\n                                                                                           Player Stats : " +
                "\n                                                                                           -------------- " +
                "\n                                                                                           Name : " + name +
                "\n                                                                                       GamesWon : " + gamesWon + 
                "\n                                                                                 GamesAttempted : " + gamesAttempted );     
    }
}
