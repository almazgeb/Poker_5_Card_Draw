
public class TestGame {

	public static void main(String[] args) {
		Card test_hand[] = new Card[5];
		int test_hand_count=0;
		
		test_hand[0] = new Card("5C");
		test_hand[1] = new Card("9S");
		test_hand[2] = new Card("5H");
		test_hand[3] = new Card("3H");
		test_hand[4] = new Card("5D");
		test_hand_count=5;
	
		/* High Card */
		test_hand[0] = new Card("5C");
		test_hand[1] = new Card("9S");
		test_hand[2] = new Card("2H");
		test_hand[3] = new Card("3H");
		test_hand[4] = new Card("TD");
		callSort(test_hand, test_hand_count, "High Card");
		
		/* One Pair */
		test_hand[0] = new Card("5C");
		test_hand[1] = new Card("TS");
		test_hand[2] = new Card("5H");
		test_hand[3] = new Card("AH");
		test_hand[4] = new Card("QD");
		callSort(test_hand, test_hand_count, "One Pair");
		
		/* Two Pair */
		test_hand[0] = new Card("5C");
		test_hand[1] = new Card("9S");
		test_hand[2] = new Card("5H");
		test_hand[3] = new Card("AH");
		test_hand[4] = new Card("9D");
		callSort(test_hand, test_hand_count, "Two Pair");
		
		/* Three of a Kind */
		test_hand[0] = new Card("5C");
		test_hand[1] = new Card("AS");
		test_hand[2] = new Card("5H");
		test_hand[3] = new Card("QH");
		test_hand[4] = new Card("5D");
		callSort(test_hand, test_hand_count, "Three of a Kind");
		
		/* Straight, special case: A2345 */
		test_hand[0] = new Card("2C");
		test_hand[1] = new Card("AS");
		test_hand[2] = new Card("5H");
		test_hand[3] = new Card("3H");
		test_hand[4] = new Card("4D");
		callSort(test_hand, test_hand_count, "Straight");
		
		/* Flush, same as High Card*/
		
		/* Full House */
		test_hand[0] = new Card("TC");
		test_hand[1] = new Card("TS");
		test_hand[2] = new Card("JH");
		test_hand[3] = new Card("TH");
		test_hand[4] = new Card("JD");
		callSort(test_hand, test_hand_count, "Full House");
		
		/* Four of a Kind */
		test_hand[0] = new Card("2C");
		test_hand[1] = new Card("2S");
		test_hand[2] = new Card("5H");
		test_hand[3] = new Card("2H");
		test_hand[4] = new Card("2D");
		callSort(test_hand, test_hand_count, "Four of a Kind");
		
		/* Straight Flush, same as High Card*/
		
	}//end main
	
	private static void callSort(Card hand[], int count, String test) {
		Player player = new Player(hand, count);
		
		System.out.println(test + ":");
		player.printHand();
		player.sortHand();
		player.printHand();
		
	}//end callSort()
}//end TestGame
