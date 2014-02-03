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
	
	/* Constructs a player holding no cards. 
	 */
	public Player() {
		hand = new Card[5];
		hand_count = 0;
		for (int i=0; i<5; i++) {
			hand[i] = null;
		}
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
		return discard;
	}//end discardCard
	
	/* Objective: Return the player's hand.
	 * Returns: Player's hand.
	 */
	public Card[] getHand() {
		return hand;
	}//end getHand()
	
	/* Objective: Return a String of the player's hand.
	 * Returns: A String of the player's hand.
	 */
	public String toString(){
		String players_hand = "";
		for (int i=0; i<hand_count; i++) {
		}
		players_hand.concat("\n");
		return players_hand;
	}//end printHand()
	
	/* Sorts the player's hand. */
	private void sortHand() {
		//Note: Todo later.
	}//end sortHand()
}//end Player
