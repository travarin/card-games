package com.casestudy.cards;
/*
 * Travis Langston
 * 
 * 9/21/2016
 * CS 3 Period 2
 * This program will create an object of the rules for the card game war.  
 */
import java.util.*;

import static java.lang.System.*;
public class WarRules {
	
	private Player 		p1    = new Player();
	private Player 		p2    = new Player();
	private Queue<Card> deck1 = new LinkedList<Card>();
	private Queue<Card> deck2 = new LinkedList<Card>();
	private Scanner     rdIn  = new Scanner(System.in);
	private Deck 		deck;
	private int			moves = 0;
	
	/*
	 * Creates the object and calls the reset method.
	 */
	public WarRules()
	{
		reset();
	}
	
	/*
	 * Resets and shuffles the deck, then splits it between the two players. 
	 */
	public void reset()
	{
		deck = new Deck();
		deck.shuffle();
		deck.shuffle();
		deck1.clear();
		deck2.clear();
		p1.setGamesAttempted();
        p2.setGamesAttempted();
        for ( int i = 0; i < 52; i += 2 )
        {
        	deck1.offer(deck.deal());
        	deck2.offer(deck.deal());
        }
	}
	
	/**
     * Accessor method to get the stats for the players
     */
    public void getStats()
    {
        out.println(p1);
        out.println(p2);
    }
    
    /*
     * Has the player draw a card from their deck. 
     */
    public Card draw(Player a, Queue<Card> deck)
    {
    	out.print("\n    " + a.getName() + " draw a card: ");
    	// rdIn.nextLine();
    	return deck.poll();
    }
    
    /*
     * Allows the players to each draw a card and see who wins the round. 
     */
    public void move()
    {
    	moves++;
    	if ( moves >= 100 )
    	{
    		// handShuffle(deck1);
    		// handShuffle(deck2);
    	}
    	Card x = draw(p1, deck1);
    	out.println("    " + x);
    	
    	Card y = draw(p2, deck2);
    	out.println("    " + y);
    	
    	if ( x.getRank() > y.getRank() )
    	{
    		out.println("\n    " + p1.getName() + " wins this round. ");
    		deck1.offer(x);
    		deck1.offer(y);
    		printSize();
    	}
    	else if ( y.getRank() > x.getRank() )
    	{
    		out.println("\n    " + p2.getName() + " wins this round. ");
    		deck2.offer(x);
    		deck2.offer(y);
    		printSize();
    	}
    	else if ( x.getRank() == y.getRank() )
    	{
    		out.println("\n    It's a tie. Time for war.");
    		war(x, y);
    	}
    }
    
    /*
     * Settles a tie by having the players go to war. 
     */
    public void war(Card a, Card b)
    {
    	Queue<Card> drawn = new LinkedList<Card>();
    	int 		indx  = 0;
    	boolean 	tie   = true;
    	drawn.offer(a);
    	drawn.offer(b);
    	
    	while ( indx < 3 && deck1.size() > 0 && deck2.size() > 0 )
    	{
    		drawn.offer(deck1.remove());
    		drawn.offer(deck2.remove());
    		indx++;
    	}
    	
    	if ( deck1.isEmpty() )
    	{
    		tie = false;
    		out.println("\n    " + p1.getName() + " ran out of cards and loses the war. ");
    	}
    	else if ( deck2.isEmpty() )
    	{
    		tie = false;
    		out.println("\n    " + p2.getName() + " ran out of cards and loses the war. ");
    	}
    	
    	while ( tie )
    	{
    		Card x = draw(p1, deck1);
        	out.println("    " + x);
        	
        	Card y = draw(p2, deck2);
        	out.println("    " + y);
        	
        	if ( x.getRank() > y.getRank() )
        	{
        		out.println("\n    " + p1.getName() + " wins the war. ");
        		deck1.offer(x);
        		deck1.offer(y);
        		while ( !drawn.isEmpty() )
        		{
        			deck1.offer(drawn.remove());
        		}
        		tie = false;
        		printSize();
        	}
        	else if ( y.getRank() > x.getRank() )
        	{
        		out.println("\n    " + p2.getName() + " wins the war. ");
        		deck2.offer(x);
        		deck2.offer(y);
        		while ( !drawn.isEmpty() )
        		{
        			deck2.offer(drawn.remove());
        		}
        		tie = false;
        		printSize();
        	}
        	else if ( x.getRank() == y.getRank() )
        	{
        		out.println("\n    Tie. Draw again. ");
        		drawn.offer(x);
        		drawn.offer(y);
        	}
    	}
    }
    
    /*
     * Checks to see if someone has won by checking if a deck is empty. 
     */
    public boolean checkWin()
    {
    	if ( deck1.isEmpty() )
    	{
    		printWin(p2);
    		return true;
    	}
    	else if ( deck2.isEmpty() )
    	{
    		printWin(p1);
    		return true;
    	}
    	else
    	{
    		return false;
    	}
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
     * Prints out how many cards each player has left. 
     */
    public void printSize()
    {
    	out.println("\n    " + p1.getName() + " has " + deck1.size() + " cards. " + p2.getName() + " has " + deck2.size() + " cards.");
    }
    
    /*
    public void handShuffle(Queue<Card> hand )
	{
    	out.println("Entering method handShuffle...");
		Random gen = new Random();
		// Remove cards from the list and place at random positions
		// in an array
		Card[] array = new Card[hand.size()];
		while ( hand.size() > 0 )
		{
			Card card = hand.remove();
			int i = gen.nextInt(hand.size());
			out.println(hand.size());
			// out.println(Arrays.toString(array));
			// out.println(hand);
			while ( array[i] != null )
			{
				i = gen.nextInt(hand.size());
			}
			array[i] = card;
		}
		// Transfer the shuffled cards back to the list
		for ( Card card : array )
		{
			hand.add(card);
		}
	}
   	*/
}
