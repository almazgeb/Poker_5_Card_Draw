/*----------------------------------------------------------------------------
 * 5 Card Draw Poker
 *
 * Class: CS 342 Computer System
 *
 * Created by Adam Socik
 * January-February 2013
 ----------------------------------------------------------------------------*/
/*
 * A card is a 2 character string with index 0 representing the rank of the
 * card and index 0 representing the suit of the card.
 * 
 * This class' methods are all setters and getters that initialize the card,
 * and can return the card itself, the suit, or the rank.
 */
public class Card 
{
	private String card;
	
	public Card() 
	{
		card = null;
	}
	
	/**
	 * Constructs a card with value test_card
	 * @param test_card
	 */
	public Card(String test_card)
	{
		card = test_card;
	}
	
	public void setCard(String s)
	{
		card = s;
	}
	
	public String getCard()
	{
		return card;
	}
	
	public char getRank()
	{
		return card.charAt(0);
	}
	
	public char getSuit()
	{
		return card.charAt(1);
	}
	
	public int getNumericRank() {
		char rank = getRank();
		switch (rank) {
			case 'A': return 14;
			case 'K': return 13;
			case 'Q': return 12;
			case 'J': return 11;
			case 'T': return 10;
			default: return Character.getNumericValue(rank);
		}
	}
}
