package com.casestudy.cards;
import static java.lang.System.*;
public class TestDeck {

	public static void main(String[] args) {
		Deck deck = new Deck();
		deck.shuffle();
		int count = 0;
		while ( !deck.isEmpty() )
		{
			count++;
			Card card = deck.deal();
			out.println(count + ": " + card);
		}

	}

}
