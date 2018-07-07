package com.casestudy.cards;
import static java.lang.System.*;
public class TestCard {
	public static void main(String[] args)
	{
		Card queenSpades = new Card(Suit.spade, 12);
		Card jackClubs = new Card(Suit.club, 11);
		Card twoHearts = new Card(Suit.heart, 2);
		Card twoDiamonds = new Card(Suit.diamond, 2);
		
		// Display "Queen of Spades"
		out.println(queenSpades);
		// Display true
		out.println(twoDiamonds.equals(twoHearts));
		// Display 1
		out.println(queenSpades.compareTo(jackClubs));
	}
}
