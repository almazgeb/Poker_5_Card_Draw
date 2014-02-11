/**
 * HandHash
 * Functions like a Hash Table to determine the best matches from a hand.
 * @author Emily J. Austin February 5, 2014
 */

public class HandHash {
	static int MAX_HAND=5;
	
	private int hand_hash[];	//The Hash Table for Card ranks 2-A
	int top_dup;				//Tracks the rank with the most duplicates
	
	public HandHash(){
		top_dup = 0;
		hand_hash = new int[13];
		
		for (int i=0; i<13; i++) {
			hand_hash[i] = 0;
		}
	}// end HashHash()
	
	public void add(Card c) {
		hand_hash[c.getNumericRank()-2]++;
		getTopDup();
	}//end add()
	
	private void getTopDup() {
		for (int i=0; i<hand_hash.length; i++) {
			if (hand_hash[i] > top_dup) {
				top_dup = hand_hash[i];
			}
		}
	}//end getTopDup()
	
	/**
	 * @return Top rank based on duplicated in hand_hash.
	 */
	public char getTopRank() {
		char top_rank=0;
		
		getTopDup();
		if (top_dup <= 1) {
			return 'E';	//No distinguishing duplicates.
		}
	
		for (int i=hand_hash.length-1; i>=0; i--) {
			if (hand_hash[i] >= top_dup) {
				switch (i) {
					case 12:
						top_rank = 'A'; break;
					case 11:
						top_rank = 'K';	break;
					case 10:
						top_rank = 'Q';	break;
					case 9:
						top_rank = 'J';	break;
					case 8:
						top_rank = 'T'; break;
					default:
						top_rank = (Integer.toString(i+2)).charAt(0);
						break;
				}//end switch
				hand_hash[i]=0;
				return top_rank;
			}
		}
		return 'S'; //The sanity check. Code should never reach this point.
	}//end getTopRank()
}//end HandHash
