/*----------------------------------------------------------------------------
 * 5 Card Draw Poker
 *
 * Class: CS 342 Computer System
 *
 * Created by Adam Socik
 * January-February 2013
 ----------------------------------------------------------------------------*/
/*
 * The evaluate class parses a player's hand and determines the relative
 * strength of it; a numeric value is returned. This class also contains
 * a method to determine the outcome of a game if two players have hands
 * of equal strength.
 */
public class Evaluate 
{
	private Card hand[];
	
	public Evaluate(Player p) 
	{
		//Note: gethand() sorts the player's hand before returning hand.
		hand = p.getHand();
	}
	
	/**------------------------------------------------------------------------
	 * Returns the strength of the hand. 9 is the highest strength and 1 
	 * is the lowest
	 * 
	 * @return	integer value of the strength
	 ------------------------------------------------------------------------*/
	public int getEvaluation() 
	{
		if (this.isStraightFlush() == true)
			return 9;
		else if (this.ofaKind(4) == true)
			return 8;
		else if (this.isFullHouse() == true)
			return 7;
		else if (this.isFlush() == true)
			return 6;
		else if (this.isStraight() == true)
			return 5;
		else if (this.ofaKind(3) == true)
			return 4;
		else if (this.isTwoPair() == true)
			return 3;
		else if (this.ofaKind(2) == true)
			return 2;
		else 
			return 1;	
	}// end getEvaluation()
	
	/**------------------------------------------------------------------------
	 * Checks to see if hand is a straight flush. A straight flush occurs when
	 * a hand contains five cards that are in a sequence and of the same suit.
	 * Note ace can be used as high or low cards.
	 * ex: AS KS QS JS TS or 5H 4H 3H 2H AH
	 * 
	 * @return	true / false
	 ------------------------------------------------------------------------*/
	private boolean isStraightFlush()
	{
		if (this.isStraight() == true && this.isFlush() == true)
			return true;
		else 
			return false;
	}// end isStraightFlush()

	/**------------------------------------------------------------------------
	 * Checks to see if a hand contains a full house. A full house occurs when 
	 * a hand contains three cards with the same rank and two cards that have 
	 * a different same rank (ex: AS AH AD 2S 2D)
	 * 
	 * @return	true / false
	 ------------------------------------------------------------------------*/
	private boolean isFullHouse()
	{
		if (this.ofaKind(3) == true && this.ofaKind(2) == true)
			return true;
		else 
			return false;
	}//end isFullHouse()
	
	/**------------------------------------------------------------------------
	 * Checks to see if the hand contains cards of all the same suit
	 * 
	 * @return true / false
	 ------------------------------------------------------------------------*/
	private boolean isFlush()
	{
		char suit = hand[0].getSuit();		// Use first card as default
		
		for (int i = 1; i <	 hand.length; i++) 
			if (hand[i].getSuit() != suit)
				return false;
	
		return true;	
	}//end isFlush()
	
	/**------------------------------------------------------------------------
	 * Checks to see if hand is a straight. A straight is when all five cards
	 * are in sequence, ace can be either high or low cards.
	 * ex: AS KD QS JH TC or 5S 4D 3H 2C AS
	 * 
	 * @return	true / false
	 ------------------------------------------------------------------------*/
	private boolean isStraight()
	{
		// Array to keep the count of the occurrence of each rank. Each index of
		// the array applies to the rank of a card (index 2 applies to rank of 2) 
		// Index 0 is placeholder, ace applies to index 1 and 14.
		int rankCount[] = new int[15];
		
		// Initialize all values to 0
		for (int i = 0; i < rankCount.length; i++)
			rankCount[i] = 0;
		
		// Count the occurrence of each card
		for (int i = 0; i < hand.length; i++) 
			rankCount[hand[i].getNumericRank()]++;
		
		rankCount[1] = rankCount[14];	// Ace can be low or high rank
		
		// Look for a straight		
		for (int i = 1; i < 11; i++) 
		{
			if (rankCount[i] > 1)	// More than one card with the same rank
				return false;
			
			if (rankCount[i] == 1)
			{
				// If hand has 1 of the next higher ranked cards
				if (rankCount[i+1] == 1 && rankCount[i+2] == 1 && rankCount[i+3] == 1 && rankCount[i+4] == 1)
					return true;
				// If hand has ace and low ace is not straight check if high ace causes straight
				else if (i == 1 && rankCount[i+9] == 1 && rankCount[i+10] == 1 
						 && rankCount[i+11] == 1 && rankCount[i+12] == 1) 
					return true;
				else 
					return false;
			}
		}
		
		return false;
	}// end isStraight()
	 	
	/**------------------------------------------------------------------------
	 * Method checks if there are 2 pairs of cards with the same rank in the 
	 * hand.
	 * 
	 * @return	true / false
	 ------------------------------------------------------------------------*/
	private boolean isTwoPair()
	{
		int rankCount[] = new int[15];
		
		// Initialize all values to 0
		for (int i = 0; i < rankCount.length; i++)
			rankCount[i] = 0;
		
		// Count the occurrence of each card
		for (int i = 0; i < hand.length; i++) 
			rankCount[hand[i].getNumericRank()]++;
		
		int twoPairCount = 0;
		// Check for pairs (count of 2)
		for (int i = 0; i < rankCount.length; i++)
			if (rankCount[i] == 2)
				twoPairCount++;
		
		// If there is more than one two pair
		if (twoPairCount > 1)
			return true;
		
		return false;
	}//end isTwoPair()
	
	/**------------------------------------------------------------------------
	 * This method checks if a hand has two of a kind (1 pair of cars with
	 * the same rank), three of a kind, and four of a kind
	 * 
	 * @param n		specifies whether to look for two, three, or four of a kind
	 * @return		true / false
	 ------------------------------------------------------------------------*/
	private boolean ofaKind(int n)
	{
		// Array to keep the count of the occurrence of each rank. Each index of
		// the array applies to the rank of a card (index 2 applies to rank of 2) 
		// Index 0 and 1 are placeholders, ace applies to index 14.
		int rankCount[] = new int[15];
		
		// Initialize all values to 0
		for (int i = 0; i < rankCount.length; i++)
			rankCount[i] = 0;
		
		// Count the occurrence of each card
		for (int i = 0; i < hand.length; i++) 
			rankCount[hand[i].getNumericRank()]++;
			
		// Check for two, three, or four of a kind depending on n
		for (int i = 0; i < rankCount.length; i++)
			if (rankCount[i] == n)
				return true;
				
		return false;
	}//end ofaKind()
	
	/**------------------------------------------------------------------------
	 * This method searches a hand for the highest ranked card and returns
	 * the numerical rank of the card
	 * 
	 * @return Numeric rank of the highest ranked card.
	 ------------------------------------------------------------------------*/
	public int HighCard()
	{			
		int highCard = hand[0].getNumericRank();		// Set first card as high card	
		
		for (int i = 1; i < hand.length; i++)			// Compare to each other card
			if (hand[i].getNumericRank() > highCard)	// If higher rank set as new high card
				highCard = hand[i].getNumericRank();
	
		return highCard; 
	}// end HighCard();
		
	/**------------------------------------------------------------------------
	 * Checks both hands and determines who wins a tie 
	 * 
	 * @return	1 if p1 wins, 2 if p2 wins, 0 if game ends in a tie
	 ------------------------------------------------------------------------*/
	public int tieBreaker(Card p1[], Card p2[])
	{			
		// Cards are sorted so compare each card until one with a higher rank (winner) is found
		for (int i = 0; i < 5; i++)
		{
			if (p1[i].getNumericRank() > p2[i].getNumericRank())
				return 1;
			else if (p1[i].getNumericRank() < p2[i].getNumericRank())
				return 2;
		}
		return 0;	// Both hands had cards of the same ranks so game ends in a tie	
	}//end tieBreaker()
}//end Evaluate


