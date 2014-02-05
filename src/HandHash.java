public class HandHash {
	private int hand_hash[];   //Tracks duplicates based on rank, rank=index-2
	int top_dup;
	
	public HandHash(){
		top_dup = 0;
		hand_hash = new int[13];
		nullifyHandHash();
	}// end HashHash()
	
	/* Objective: Sets all indexes of hand_hash to 0.
	 * Returns: Nothing
	 */
	private void nullifyHandHash(){
		for (int i=0; i<13; i++) {
			hand_hash[i] = 0;
		}
	}//end nullifyHandHash
	
	/* Objective: Add the card to hand_hash.
	 * Returns: Nothing
	 */
	public void add(Card c) {
		hand_hash[c.getNumericRank()-2]++;
		getTopDup();
	}//end add
	
	/* Sets top_dup based on highest duplicate number.*/
	private void getTopDup() {
		for (int i=0; i<hand_hash.length; i++) {
			if (hand_hash[i] > top_dup) {
				top_dup = hand_hash[i];
			}
		}
	}//end getTopRank()
	
	/* Returns the top rank based on duplicates from hand_hash. */
	public char getTopRank() {
		//Todo: Accounting for end of cards? if top_dup==0; sort in Player.
		char top_rank=0;
		
		if (top_dup <= 1) {
			return 'E';	//Error, no distinguishing dups
		}
	
		for (int i=hand_hash.length-1; i>=0; i--) {
			if (hand_hash[i] >= top_dup) {
				switch (i) {
					case 12:
						top_rank = 'A';
						break;
					case 11:
						top_rank = 'K';
						break;
					case 10:
						top_rank = 'Q';
						break;
					case 9:
						top_rank = 'J';
						break;
					case 8:
						top_rank = 'T';
						break;
					default:
						top_rank = (char)( (Integer.toString(i)).charAt(0) );
						break;
				}//end switch
				hand_hash[i]=0;
				return top_rank;
			}
		}
		return 'S'; //Sanity check, should never execute
	}//end getTop()

}//end HandHash
