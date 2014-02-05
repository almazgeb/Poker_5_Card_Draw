/*----------------------------------------------------------------------------
 * 5 Card Draw Poker
 *
 * Class: CS 342 Computer System
 *
 * Created by Adam Socik
 * January-February 2013
 ----------------------------------------------------------------------------*/
/*
 * A card pile is a class to hold the data structure of the 52 card deck which 
 * is an array of Card objects. There is also an integer that is used as an 
 * array index to signify the top of the deck. 
 * 
 * The class' constructor creates and initializes a 52 card deck and the shuffles it.
 * The class' methods include a getter for the top of the deck, a drawCard method,
 * and a method to print the deck.
 *  
 */

import java.util.*;

public class CardPile 
{
	private int topOfDeck = 0;
	private Card deck[];
	
	// Constructs a shuffled deck of cards
	CardPile()
	{
		deck = new Card[52];
		
		for (int i=0; i<deck.length; i++)
			deck[i] = new Card();
		
		/*
		 * Initialize each card
		 * Unshuffled deck by array index: 
		 * 			2  3  4  5  6  7  8  9  T  J  Q  K  A
		 * Clubs:	0  1  2  3  4  5  6  7  8  9  10 11 12
		 * Diamonds:13 14 15 16 17 18 19 20 21 22 23 24 25
		 * Hearts:	26 27 28 29 30 31 32 33 34 35 36 37 38 
		 * Spades:	39 40 41 42 43 44 45 46 47 48 49 50 51
		 */
		for (int i=0, j=2; j<=14; i++, j++)
		{
			if (j <= 9) // Suits 2 through 9
			{
				deck[i].setCard(Integer.toString(j) + "C");
				deck[i+13].setCard(Integer.toString(j) + "D");
				deck[i+26] .setCard(Integer.toString(j) + "H");
				deck[i+39] .setCard(Integer.toString(j) + "S");
			}
			else if (j == 10)
			{
				deck[i].setCard("TC");
				deck[i+13].setCard("TD");
				deck[i+26].setCard("TH");
				deck[i+39].setCard("TS");
			}
			else if (j == 11) // Jack
			{
				deck[i].setCard("JC");
				deck[i+13].setCard("JD");
				deck[i+26].setCard("JH");
				deck[i+39].setCard("JS");
			}
			else if (j == 12) // Queen
			{
				deck[i].setCard("QC");
				deck[i+13].setCard("QD");
				deck[i+26].setCard("QH");
				deck[i+39].setCard("QS");
			}
			else if (j == 13) // King
			{
				deck[i].setCard("TC");
				deck[i+13].setCard("KD");
				deck[i+26].setCard("KH");
				deck[i+39].setCard("KS");
			}
			else // Ace
			{
				deck[i].setCard("AC");
				deck[i+13].setCard("AD");
				deck[i+26].setCard("AH");
				deck[i+39].setCard("AS");
			}
		}
		
		// Left this here for debugging
		System.out.print("Unshuffled -> ");
		printDeck();	
		System.out.println();
		
		Collections.shuffle(Arrays.asList(deck));	// Shuffle the deck
		
		// Left this here for debugging
		System.out.print("\nShuffled   -> ");
		printDeck();
		System.out.println();
	}
		
	// Returns the current position of the deck array
	public int getTopOfDeck()
	{
		return topOfDeck;
	}
	
	// Returns a card object (to place in hand)
	public Card drawCard()
	{
		Card c = deck[topOfDeck];
		topOfDeck++;
		return c;
	}
	 
	public void printDeck()
	{
		for (int i=0; i<52; i++)
			System.out.print(deck[i].getCard() + " ");
	}
}
