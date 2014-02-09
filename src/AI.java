/**
 * 
 * @author Emily J Austin 2/5/2014
 */

public class AI extends Player {
/* For the computer player's turn, use the following AI algorithm.
1. First check if the computer player already has a hand of One Pair or better. If so, discard all other card.
2. If the hand evaluates to "High Card", determine if the user has 4 cards of the same suit. If so, discard the card of the different suit.
3. Next determine if the user has 4 cards in sequence. If so, discard the card that is out of sequence.
4. Next if the user has an Ace, discard the other four cards.
5. Otherwise, keep the two highest cards and discard the other 3. */
	Evaluate eval;
	CardPile deck;
	
	public AI() {
		super();
		eval = new Evaluate(this);
	}//end AI
	
	/* Objective: Simulate AI turn.
	 * Returns: Index from which to discard hand.
	 * Note: high card = 1 strength
	 */
	public int play() {
		strength = eval.getEvaluation();
		
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
			//4 same suit
			int suit_count;
			char suit;
			
			for (int i=0; i<5; i++) { 
				suit = hand[i].getSuit();
				suit_count=0;
				for (int j=0; j<5; j++) {
					if (hand[j].getSuit() == suit)
						suit_count++;
				}
				if (suit_count >= 4)
					return 4;
			}
			
			//4 in sequence
			int isfourofakind=1;
			for (int i=0; i<3; i++) { //1234
				if (hand[i].getNumericRank() != (hand[i+1].getNumericRank()+1) ) {
					isfourofakind=0;
					break;
				}
			}
			if (isfourofakind==1) { return 4; }
			
			isfourofakind = 1;
			for (int i=0; i<3; i++) { //4321
				if (hand[i].getNumericRank() != (hand[i+1].getNumericRank()+1) ) {
					isfourofakind=0;
					break;
				}
			}
			if (isfourofakind==1) { return 4; }
			
			isfourofakind=1;
			for (int i=1; i<4; i++) { //1234
				if (hand[i].getNumericRank() != (hand[i-1].getNumericRank()+1) ) {
					isfourofakind=0;
					Card tmp = hand[0];
					hand[0] = hand[4];
					hand[4]= tmp;
					break;
				}
			}
			if (isfourofakind==1) { return 4; }
			
			isfourofakind = 1;
			for (int i=1; i<4; i++) { //4321
				if (hand[i].getNumericRank() != (hand[i-1].getNumericRank()+1) ) {
					isfourofakind=0;
					Card tmp = hand[0];
					hand[0] = hand[4];
					hand[4]= tmp;
					break;
				}
			}
			if (isfourofakind==1) { return 4; }
			
			//A only
			for (int i=0; i<5; i++) {
				if (hand[i].getRank() == 'A') {
					Card tmp = hand[0];
					hand[0] = hand[i];
					hand[i]= tmp;
					return 1;
				}
			}
			
			//keep top 2 cards
			return 2;
		}
	}//end play()
}//end AI
