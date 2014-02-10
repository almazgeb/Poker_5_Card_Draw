/**
 * Player Class
 * Keeps track of the player's hand, hand_count, and strength where strength refers to
 * "the strength of one player's hand compared with another player's hand."
 * @author Emily J. Austin February 2, 2014
 */

public class Player {
	static int MAX_HAND=5;
	
	protected Card hand[];
	private int hand_count;	//Number of cards in hand
	protected int strength;
	
	/**
	 * Constructs a Player holding no cards.
	 */
	public Player() {
		hand = new Card[MAX_HAND];
		hand_count = 0;
		for (int i=0; i<MAX_HAND; i++) {
			hand[i] = null;
		}
		strength = 0;	//0 is the lowest strength
	}//end Player()
	
	/**
	 * Constructs a player holding cards from test_hand. Used for testing.
	 * @param test_hand
	 * @param test_hand_count
	 */
	public Player(Card test_hand[], int test_hand_count) {
		hand = new Card[MAX_HAND];
		hand_count = test_hand_count;
		for (int i=0; i<test_hand_count; i++) {
			hand[i] = test_hand[i];
		}
		strength = 0;	//0 is the lowest strength
	}//end Player()
	
	/**
	 * Draws a card from CardPile deck.
	 * @param deck
	 */
	public void drawCard(CardPile deck) {
		if (hand_count >= MAX_HAND) {
			System.out.println("Invalid: You cannot draw another card.");
		}
		hand[hand_count++] = deck.drawCard();
	}//end drawCard()
	
	public int getStrength() {
		return strength;
	}//end getStrength()
	
	public int setStrength(int new_strength) {
		strength = new_strength;
		return strength;
	}//end setStrength()
	
	/**
	 * @return The hand in sorted order with respect to best strengths or best matches.
	 */
	public Card[] getHand() {
		sortHand();
		return hand;
	}//end getHand()
	
	/**
	 * Prints the hand with indexes in sorted order with respect to best strengths or best matches.
	 * @author Adam Socik
	 */
	public void printHand()
	{
		sortHand();
		for (int i=0; i<MAX_HAND; i++)
			System.out.print((i+1) + ") " + hand[i].getCard() + "   ");
		System.out.println();
	}//end printHand()
	
	/**
	 * Discards the Card at index and draws the next Card in CardPile deck.
	 * @param deck
	 * @param index
	 * @return The hand_count or -1 on error.
	 * @author Adam Socik
	 */
	public int drawTargetCard(CardPile deck, int index)
	{
		if (hand_count > MAX_HAND) 
		{
			System.out.println("Invalid: You cannot draw another card.");
			return -1;
		}
		hand[index] = deck.drawCard();
		return hand_count;
	}// end drawTargetCard()
	
	/**
	 * Sorts the hand with respect to best strengths or best matches.
	 */
	public void sortHand() {
		HandHash hand_hash = new HandHash();
		Card new_hand[] = new Card[MAX_HAND];
		char top_rank=0;				//Tracks the top rank for hand
		int top_rank_index=0;			//Tracks the index for top rank card in hand
		int new_hand_count=0;
		
		//Initializes new_hand to null.
		for (int i=0; i<MAX_HAND; i++) {
			new_hand[i] = null;
		}
		
		//Adds hand to hand_hash.
		for(int i=0; i<MAX_HAND && hand[i] != null; i++) {
			hand_hash.add(hand[i]);
		}
		
		//Gets cards with respect to best matches.
		top_rank = hand_hash.getTopRank();
		while (top_rank != 'E' && top_rank != 'S') {
			for (int i=0; i<hand_count; i++) {
				if (hand[i] != null && hand[i].getRank() == top_rank) {
					new_hand[new_hand_count++] = hand[i];
					hand[i] = new Card("0H");	//Temp place-holder card with lowest rank.
				}
			}
			top_rank = hand_hash.getTopRank();
		}
		
		//Gets cards with respect to best rank.
		top_rank_index=getTopRankIndex(hand);
		while (top_rank_index < hand_count && hand[top_rank_index] != null && new_hand_count < MAX_HAND) {
			new_hand[new_hand_count++] = hand[top_rank_index];
			hand[top_rank_index] = new Card("0H");	//Temp place-holder card with lowest rank.
			top_rank_index=getTopRankIndex(hand);
		}
		
		//This deals with special case, Straight 5 4 3 2 A
		if (new_hand[0] != null && new_hand[0].getRank()=='A' && new_hand[1].getRank()=='5' &&
			new_hand[2].getRank()=='4' && new_hand[3].getRank()=='3' && new_hand[4].getRank()=='2') {
			Card ace = new_hand[0];
			for (int i=0; i<4; i++) {
				new_hand[i] = new_hand[i+1];
			} new_hand[4] = ace;
		}
		
		for (int i=0; i<hand.length; i++) {
			hand[i] = new_hand[i];
		}
	}//end sortHand()
	
	/**
	 * @param hand
	 * @return The index of the top rank Card in hand or 5 if no cards are left in hand.
	 */
	private int getTopRankIndex(Card hand[]) {
		int top_rank_index=0;
		
		while (top_rank_index < MAX_HAND && hand[top_rank_index] == null) { top_rank_index++; }
		if ( !(top_rank_index < MAX_HAND) ) { return top_rank_index; }	//top_index=5
		
		for (int i=0; i<MAX_HAND; i++) {
			if (hand[i] != null && hand[i].getNumericRank() > hand[top_rank_index].getNumericRank()) {
				top_rank_index = i;
			}
		}
		
		return top_rank_index;
	}//end getTopIndex
}//end Player
