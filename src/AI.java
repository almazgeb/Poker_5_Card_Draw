/**
 * AI Class
 * Extends the Player class to include decision making for AI.
 * @author Emily J Austin 2/5/2014
 */

public class AI extends Player {
/* For the computer player's turn, use the following AI algorithm.
1. First check if the computer player already has a hand of One Pair or better. If so, discard all other card.
2. If the hand evaluates to "High Card", determine if the user has 4 cards of the same suit. If so, discard the card of the different suit.
3. Next determine if the user has 4 cards in sequence. If so, discard the card that is out of sequence.
4. Next if the user has an Ace, discard the other four cards.
5. Otherwise, keep the two highest cards and discard the other 3. */
	static int MAX_HAND=5;
	
	Evaluate eval;	//Used to access evaluation methods.
	CardPile deck;	//Used to draw new Cards.
	
	public AI() {
		super();
		eval = new Evaluate(this);
	}//end AI
	
	/**
	 * Constructs an AI holding cards from test_hand. Used for testing.
	 * @param test_hand
	 * @param test_hand_count
	 */
	public AI(Card test_hand[], int test_hand_count) {
		super(test_hand, test_hand_count);
		eval = new Evaluate(this);
	}//end AI()
	
	/**
	 * Decides the cards to discard for AI.
	 * Note: Strength is from low-high 1-9.
	 * @return The index from which to discard cards. If return value is MAX_HAND, discard no cards.
	 */
	public int play() {
		sortHand();
		strength = eval.getEvaluation();
		
		//Case One Pair or better.
		if (strength >= 2) {
			switch (strength) {
				case 2: return 2;
				case 3: return 4;
				case 4: return 3;
				case 8: return 4;
				default: return 5;
			}//end switch strength
		}
		else {
			//Case 4 of the same suit.
			int suit_count=0;
			char suit;
			
			for (int i=0; i<MAX_HAND; i++) { 
				suit = hand[i].getSuit();
				suit_count=0;
				for (int j=0; j<5; j++) {
					if (hand[j].getSuit() == suit)
						suit_count++;
				}
				if (suit_count >= 4)
					return 4;
			}
			
			//Case 4 in sequence.
			int is_four_of_a_kind=0;
			
			is_four_of_a_kind = isFourOfAKind(0);
			if (is_four_of_a_kind == 4) { return 4; }
			is_four_of_a_kind = isFourOfAKind(1);
			if (is_four_of_a_kind == 4) { return 4; }
			
			//Case A only.
			for (int i=0; i<MAX_HAND; i++) {
				if (hand[i].getRank() == 'A') {
					hand[0] = hand[i];
					return 1;
				}
			}
			
			//Case keep top 2 cards.
			return 2;
		}
	}//end play()
	
	/**
	 * @return 4 if there is four of a kind and 0 otherwise.
	 */
	private int isFourOfAKind(int start) {
		int isFOAK = 0;
		
		for (int i=start; i<start+3; i++) {
			if (hand[i].getNumericRank() != (hand[i+1].getNumericRank()+1) ) {
				return 0;
			}
		}
		return 4;
	}//end isFourOfAKind()
}//end AI