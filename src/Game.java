public class Game 
{
	public static void main(String[] args) 
	{
		CardPile deck = new CardPile();
		
		Card c = deck.drawCard();	// Take card on top of deck
		System.out.println("\nTop: " + c.getCard() + " " + c.getRank() + " " + c.getSuit());
		System.out.println("Top of deck array position: " + deck.getTopOfDeck());
		
		c = deck.drawCard();	// Take another card from the top
		System.out.println("Top: " + c.getCard() + " " + c.getRank() + " " + c.getSuit());
		System.out.println("Top of deck array position: " + deck.getTopOfDeck());
	}
}
