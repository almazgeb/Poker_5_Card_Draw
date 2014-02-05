/* Player Class
 * Keep's track of the number of cards in a player's hand and what cards the player has.
 * The hand is an array of 5 cards initialized to NULL representing no cards.
 * Functions:
 * 		drawCard(CardPile deck), drawCard(CardPile deck, int card_count)
 * 		discardCard(int card)
 * 		getHand()
 * 		printHand()
 * 		sortHand()
 * Emily J. Austin, 2.2.2014 @ 24:12
 */

public class Player {
	private Card hand[];	//The player's hand
	private int hand_count;	//The player's hand count
	private int strength;	//The player's hand strength based on evaluation, 0 is lowest
	
	/* Constructs a player holding no cards. 
	 */
	public Player() {
		hand = new Card[5];
		hand_count = 0;
		for (int i=0; i<5; i++) {
			hand[i] = null;
		}
		strength = 0;	//0 is the lowest strength
	}//end Player()
	
	/* Objective: Draws a card from the deck and puts the card in the player's hand.
	 * Parameters: A CardPile deck.
	 * Returns: The number of cards in the player's hand OR -1 on error.
	 */
	public int drawCard(CardPile deck) {
		if (hand_count >= 5) {
			System.out.println("Invalid: You cannot draw another card.");
			return -1;
		}
		hand[hand_count++] = deck.drawCard();
		return hand_count;
	}//end drawCard()
	
	/* Objective: Draws card_count number of cards from the deck and puts the card in the player's hand.
	 * Parameters: A CardPile deck, an int card_count for the number of cards to draw.
	 * Returns: The number of cards in the player's hand OR -1 on error.
	 */
	public int drawCard(CardPile deck, int card_count) {
		if (hand_count+card_count > 5) {
			System.out.println("Invalid: You cannot draw" + card_count + " more cards.");
			return -1;
		}
		for (int i=0; i<card_count; i++)
		hand[hand_count++] = deck.drawCard();
		return hand_count;
	}//end drawCard()
	
	/* Objective: Discard card at index card.
	 * Parameter: Int card for the index of the card to discard.
	 * Returns: Card discarded.
	 */
	public Card discardCard(int card) {
		Card discard = hand[card];
		hand[card] = null;
		hand_count--;
		hand[card] = hand[hand_count-1];
		return discard;
	}//end discardCard
	
	/* Returns: Player's hand strength.*/
	public int getStrength() {
		return strength;
	}//end getStrength()
	
	/* Objective: Sets player's hand strength.
	 * Returns: New hand strength.
	 */
	public int setStrength(int new_strength) {
		strength = new_strength;
		return strength;
	}//end setStrength()
	
	/* Objective: Return the player's hand.
	 * Returns: Player's hand.
	 */
	public Card[] getHand() {
		return hand;
	}//end getHand()
	
	/* Returns: A String of the player's hand.*/
	public String toString(){
		String players_hand = "";
		for (int i=0; i<hand.length && hand[i] != null; i++) {
			players_hand.concat(hand[i].getCard() + " ");
		}
		players_hand.concat("\n");
		return players_hand;
	}//end printHand()
	
	//------------------------------------------------------------------------------
	public void printHand()
	{
		for (int i=0; i<5; i++)
			System.out.print((i+1) + ") " + hand[i].getCard() + "   ");
	}
	
	public int drawTargetCard(CardPile deck, int index)
	{
		if (hand_count > 5) 
		{
			System.out.println("Invalid: You cannot draw another card.");
			return -1;
		}
		hand[index] = deck.drawCard();
		return hand_count;
	}
	
	//------------------------------------------------------------------------------
	
	/* Sorts the player's hand. */
	private void sortHand() {
		HandHash hand_hash = new HandHash();
		char top_rank=0;
		Card new_hand[] = new Card[5];
		int new_hand_count=0;
		int top_index=0;
		
		for (int i=0; i<5; i++) {
			new_hand[i] = null;
		}
		
		for(int i=0; i<5 && hand[i] != null; i++) {
			hand_hash.add(hand[i]);
		}
		
		top_rank = hand_hash.getTopRank();
		while (top_rank != 'E' && top_rank != 'S') {
			for (int i=0; i<hand.length; i++) {
				if (hand[i] != null && hand[i].getRank() == top_rank) {
					new_hand[new_hand_count++] = hand[i];
					hand[i] = null;
				}
			}
			top_rank = hand_hash.getTopRank();
		}
		
		top_index=getTopIndex(hand);
		while (top_index < hand.length) {
			new_hand[new_hand_count++] = hand[top_index];
		}
		
		for (int i=0; i<hand.length; i++) {
			hand[i] = new_hand[i];
		}
	}//end sortHand()
	
	private int getTopIndex(Card hand[]) {
		int top_index=0;
		
		while (top_index < hand.length && hand[top_index] == null) { top_index++; }
		
		if (top_index >= hand.length) { return top_index; }
		
		for (int i=0; i<hand.length; i++) {
			if (hand[i] != null && hand[i].getNumericRank() > hand[top_index].getNumericRank()) {
				top_index = i;
			}
		}
		
		return top_index;
	}//end getTopIndex
}//end Player
