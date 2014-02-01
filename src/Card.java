/*----------------------------------------------------------------------------
 * 5 Card Draw Poker
 *
 * Class: CS 342 Computer System
 *
 * Created by Adam Socik
 * January 2013
 ----------------------------------------------------------------------------*/

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