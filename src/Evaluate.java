/* comparison methods (e.g. isStraightFlush)
 * tie braking methods
 * call evaluate(player p1, player p2)
 * every other one, StarightFlush = 9 = Adam, FourOfAKind = 8 = Emily (evens)
 */

public class Evaluate 
{
	private Card hand[];
	
	// Need a constructor for the hand
	public Evaluate(Player p) {
		hand = p.getHand();
	}//end Evaluate
	
	/* Returns strength of hand.
	 * 
	 */
	public int getEvaluation() {
		
	}//end getEvaluation()
	
	/**
	 * Checks to see if hand is a straight flush. A straight flush occurs when
	 * a hand contains five cards that are in a sequence and of the same suit.
	 * Note ace can be used as high or low cards.
	 * EX: AS KS QS JS TS or 5H 4H 3H 2H AH
	 * 
	 * @return	True if hand is a straight flush, false otherwise
	 */
	public boolean isStraightFlush()
	{
		// First check to see if hand is a straight 
		if (!this.isStraight())
			return false;
		
		// Check to see if suit of each card is the same
		char suit = hand[0].getSuit();		// Use first card as default
		
		for (int i = 1; i <	 hand.length; i++) 
		{
			if (hand[i].getSuit() != suit)
				return false;
		}
		
		return true;
	} // End isStraightFlush()
	
	/**
	 * Checks to see if a hand contains a full house. A full house occurs when a hand contains
	 * three cards with the same rank and two cards that have a different same rank.
	 * EX: AS AH AD 2S 2D
	 * 
	 * @return	True if hand is a full house, false otherwise
	 */
	public boolean isFullHouse()
	{
		// I'm waiting for isOnePair and isThreeOfaKind
		
		
		
		
		
		
		
		return true;
	} // End isFullHouse()
		
	/**
	 * Checks to see if hand is a straight. A straight is when all five cards
	 * are in sequence, ace can be either high or low cards
	 * EX: AS KD QS JH TC or 5S 4D 3H 2C AS
	 * 
	 * @return	True if hand is a straight, false otherwise
	 */
	public boolean isStraight()
	{
		/*
		 * Array to keep the count of the occurrence of each rank. Each index of the array
		 * applies to the rank i.e. index 2 applies to index 2. Index 0 is meaningless,
		 * ace applies to both index 1 and 14.
		 */
		int rankCount[] = new int[15];
		
		// Initialize all values to 0
		for (int i = 0; i < rankCount.length; i++)
			rankCount[i] = 0;
		
		// Count the occurrence of each card
		for (int i = 0; i < hand.length; i++) 
		{
			char rank = hand[i].getRank();
			rankCount[getNumericalRank(rank)]++;
		}
		
		rankCount[1] = rankCount[14];	// Ace can be low or high rank
		
		// Look for a straight
		int count = 0;
		
		for (int i = 1; i < rankCount.length; i++) 
		{
			if (rankCount[i] > 1)	// More than one can with the same rank
				return false;
			
			if (rankCount[i] == 1)
			{
				rankCount[i] = -1; 
				if (count != 0 && rankCount[i-1] != -1)		// Make sure rank behind is in hand
					return false;
				count++;
			}
		}
		
		return true;
	} // End isStraight()
	
	/**
	 * Method checks if there are 2 pairs in the hand
	 * 
	 * @return	True if there exists a pair, false otherwise
	 */
	public boolean isTwoPair()
	{
		/*
		 * Array to keep the count of the occurrence of each rank. Each index of the array
		 * applies to the rank i.e. index 2 applies to index 2. Index 0 and 1 are meaningless,
		 * ace applies to index 14.
		 */
		int rankCount[] = new int[15];
		
		// Initialize all values to 0
		for (int i = 0; i < rankCount.length; i++)
			rankCount[i] = 0;
		
		// Count the occurrence of each card
		for (int i = 0; i < hand.length; i++) 
		{
			char rank = hand[i].getRank();
			rankCount[getNumericalRank(rank)]++;
		}
		
		int twoPairCount = 0;
		// Check for pairs (count of 2)
		for (int i = 0; i < rankCount.length; i++)
			if (rankCount[i] == 2)
				twoPairCount++;
		
		// If there is more than one two pair
		if (twoPairCount > 1)
			return true;
		
		return false;
	} // End isTwoPair()

	/**
	 * This method searches a hand for the highest ranked card
	 * 
	 * @return	The rank of the highest card
	 */
	public char HighCard()
	{
		// Set first card as high card
		char highCard = hand[0].getRank();
		int max = getNumericalRank(highCard);
				
		for (int i = 1; i < 5; i++)
		{
			char temp1 = hand[i].getRank();
			int temp2 = getNumericalRank(temp1);
			if (temp2 > max)	// If higher rank then set new max
			{
				max = temp2;
				highCard = temp1;
			}
		}
		return highCard; 
	} // End HighCard()
	
	/**
	 * Returns the numerical value of the rank of a card. 
	 * Note ace is highest rank so it has a numerical value of 14
	 * 
	 * @param rank
	 * @return
	 */
	public int getNumericalRank(char rank)
	{
		switch (rank)
		{
			case '2': return 2;
			case '3': return 3;
			case '4': return 4;
			case '5': return 5;
			case '6': return 6;
			case '7': return 7;
			case '8': return 8;
			case '9': return 9;
			case 'T': return 10;
			case 'J': return 11;
			case 'Q': return 12;
			case 'K': return 13;
			case 'A': return 14;
			default:  return 0;		// Should never get here
		}
	} // End getNumericalRank(char rank)
}


