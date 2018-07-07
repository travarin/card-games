package com.casestudy.cards;
import static java.lang.System.*;
public class TestSuit {

	public static void main(String[] args) {
		Suit s = Suit.spade;
		Suit h = Suit.heart;
		Suit d = Suit.diamond;
		Suit c = Suit.club;
		
		// Display "spades hearts diamonds clubs"
		out.println(s + " " + h + " " + d + " " + c);
		
		out.println(s.equals(s)); // Display true
		out.println(s.equals(h)); // Display false0
		out.println(s.compareTo(s)); // Display 0
		out.println(s.compareTo(d)); // Display 2
		out.println(d.compareTo(s)); // Display -2
	}

}
