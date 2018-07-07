package com.casestudy.cards;
/*
 * Travis Langston
 * 
 * 9/21/2016
 * CS 3 Period 2
 * This program will run the card game war. 
 */
import static java.lang.System.*;
import java.util.*;
public class War {

	public static void main(String[] args) {
		
		Scanner  rdIn		 = new Scanner(System.in);
		WarRules game 		 = new WarRules();
		boolean  keepPlaying = true;
		while (  keepPlaying )
		{
			while ( !game.checkWin() )
			{
				game.move();
			}
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
