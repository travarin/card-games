package com.casestudy.cards;
/*
 * Travis Langston
 * 
 * 9/26/2016
 * CS 3 Period 2
 * This program will create an object of the rules for the card game BlackJack.
 */
import java.util.*;
import static java.lang.System.out;
public class BlackJackRules {

	private Player 		    p1          = new Player();
	private Player 		    p2          = new Player("dealer");
	private ArrayList<Card> hand1       = new ArrayList<Card>();
	private ArrayList<Card> hand2       = new ArrayList<Card>();
	private Scanner     	rdIn        = new Scanner(System.in);
	private Deck 		    deck;
	private int             gamesTied   = 0;
	private boolean         p1BlackJack = false;
	private boolean         p2BlackJack = false;
	
	/*
	 * Creates the object and calls the reset method.
	 */
	public BlackJackRules()
	{
		reset();
	}
	
	/*
	 * Resets and shuffles the deck. 
	 */
	public void reset()
	{
		deck = new Deck();
		deck.shuffle();
		deck.shuffle();
		hand1.clear();
		hand2.clear();
		p1.setGamesAttempted();
        p2.setGamesAttempted();
        p1BlackJack = false;
        p2BlackJack = false;
	}
	

	/**
     * Accessor method to get the stats for the players
     */
    public void getStats()
    {
        out.println(p1);
        out.println(p2);
        out.println("\n                                                                                      GamesTied : " + gamesTied );
    }
    
    /*
     * Prints out the player's cards and one of the dealer's cards, 
     * and then all of the dealer's cards at the end of the game.
     */
    public void getCards(int deal)
    {
    	out.println("\n    You have: ");
    	for ( Card x : hand1 )
    	{
    		out.println("    " + x);
    	}
    	out.println("    Total: " + getTotal(hand1) );
    	out.println("\n    The dealer has: ");
    	for ( int i = 0; i < deal; i++)
    	{
        	out.println("    " + hand2.get(i));
    	}
    	if ( deal == hand2.size() )
    	{
    		out.println("    Total: " + getTotal(hand2) );
    	}
    }
    
    /*
     * Deals two cards for each player, and then allows them to hit or stay.
     * Then determines who wins. 
     */
    public void move()
    {
    	out.println("\n    Dealing two cards for each player. ");
    	for ( int i = 0; i < 2; i++ )
    	{
    		hand1.add(deck.deal());
    		hand2.add(deck.deal());
    	}
    	getCards(1);
    	boolean keepLooping = true;
    	if ( checkBlackJack(hand1) )
    	{
    		out.println("\n    You got BlackJack! ");
    		keepLooping = false;
    		p1BlackJack = true;
    	}
    	while ( keepLooping && checkBounds(hand1) )
    	{
    		out.print("\n\n    Enter 0 for hit, or 1 for stay.");
    		int x = rdIn.nextInt();
    		if ( x == 0 )
    		{
    			hit(hand1);
    			getCards(1);
    		}
    		else
    		{
    			keepLooping = false;
    		}
    	}
    	if ( !checkBounds(hand1) )
    	{
    		out.println("\n    You went out of bounds! ");
    	}
    	else
    	{
    		keepLooping = true;
    		if ( checkBlackJack(hand2))
    		{
    			keepLooping = false;
    			p2BlackJack = true;
    		}
    		while ( keepLooping )
    		{
    			int x = getTotal(hand2);
    			if ( x < 17 )
    			{
    				hit(hand2);
    			}
    			else 
    			{
    				keepLooping = false;
    			}
    		}	
    	}
    	checkWin();
    }
    
    /*
     * Draws a card from the deck and adds it to the player's hand. 
     */
    public void hit(ArrayList<Card> a)
    {
    	a.add(deck.deal());
    }
    
    /*
     * Determines who won the game, or if it was a tie. 
     */
    public void checkWin()
    {
    	if ( p1BlackJack && p2BlackJack )
    	{
    		out.println("\n    It's a tie. ");
    		gamesTied++;
    	}
    	else if ( p1BlackJack )
    	{
    		printWin(p1);
    	}
    	else if ( p2BlackJack )
    	{
    		printWin(p2);
    	}
    	else if ( (!checkBounds(hand1) && !checkBounds(hand2)) || (getTotal(hand1) == getTotal(hand2)) )
    	{
    		out.println("\n    It's a tie. ");
    		gamesTied++;
    	}
    	else if ( !checkBounds(hand1) )
    	{
    		printWin(p2);
    	}
    	else if ( !checkBounds(hand2))
    	{
    		printWin(p1);
    	}
    	else if ( getTotal(hand1) < getTotal(hand2) )
    	{
    		printWin(p2);
    	}
    	else if ( getTotal(hand2) < getTotal(hand1) )
    	{
    		printWin(p1);
    	}
		getCards(hand2.size());
    }
    
    /*
     * Prints out the player who won, and adds 1 to their games won. 
     */
    public void printWin(Player a)
    {
    	out.println("\n    Player: " + a.getName() + " wins. ");
    	a.setGamesWon();
    }
    
    /*
     * Gets the total value of the hand. 
     */
    public int getTotal(ArrayList<Card> a)
    {
    	int total = 0;
    	for ( Card x : a )
    	{
    		total += getBlackJackRank(x);
    	}
    	return total;
    }
    
    /*
     * Checks to see if the player went out of bounds. 
     */
    public boolean checkBounds(ArrayList<Card> a)
    {
    	int total = getTotal(a);
    	if ( total > 21 )
    	{
    		return false;
    	}
    	else
    	{
    		return true;
    	}
    }
    
    /*
     * Checks to see if the player got blackjack. 
     */
    public boolean checkBlackJack(ArrayList<Card> a)
    {
    	int total = getTotal(a);
    	if ( total == 21 )
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
    
    /*
     * Determines the rank of the card for BlackJack.
     */
    public int getBlackJackRank(Card a)
	{
		if ( a.getRank() == 14 )
		{
			return 11;
		}
		else if ( a.getRank() > 10 )
		{
			return 10;
		}
		else
		{
			return a.getRank();
		}
	}
    
}
