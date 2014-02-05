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
	
	Card() 
	{
		card = null;
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
}
