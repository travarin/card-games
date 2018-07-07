package com.casestudy.cards;
/**
 * Travis Langston
 * 
 * CS 3 Per 2
 * 9/26/2016 
 * This program will run the card game BlackJack. 
 */
import static java.lang.System.out;
import java.util.Scanner;
public class BlackJack {

	public static void main(String[] args) {
		Scanner  	   rdIn		   = new Scanner(System.in);
		BlackJackRules game 	   = new BlackJackRules();
		boolean        keepPlaying = true;
		while (  keepPlaying )
		{
			game.move();
			game.getStats();
			out.print("\n    Play another game? Enter y for yes, or n for no. ");
			String x = rdIn.nextLine();
			if ( x.charAt(0) == 'n' )
			{
				keepPlaying = false;
			}
			else
			{
				game.reset();
			}
		}
		out.println("\n    Final Stats: ");
		game.getStats();
		rdIn.close();
	}

}
