import java.util.Scanner;

public class Game 
{
	public static void main(String[] args) 
	{
		/*---------------------------------------------------------------------
		 * This section sets up the deck and prompts user for number of computer
		 * players in the game
		 * -------------------------------------------------------------------*/
		// Get input for how many computer opponents will play
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to Poker 5 Card Draw\n");
		System.out.print("How many computer opponents do you want to play against (max 3) -> ");
		
		//int numberOfOpponents= input.nextInt();
		int numberOfOpponents = 1;	// for debugging
		while (numberOfOpponents > 3 || numberOfOpponents < 0)	// While not between 0 and 3
		{
			System.out.println("You can only play against a range of 0-3 opponents");
			System.out.print("Enter number of opponents -> ");
			numberOfOpponents= input.nextInt();
		}
		System.out.println("You will play aginst  " + numberOfOpponents + " computer oponent(s)");
		
		CardPile deck = new CardPile();
		System.out.println("The deck is being shuffled and distributed\n");
		
		/*---------------------------------------------------------------------
		 * This section handles user input for discarding cards as well as
		 * executing the computer opponent's actions 
		 * -------------------------------------------------------------------*/
		Player user = new Player();		// Create the user 
		user.drawCard(deck, 5);			// Give the user the first 5 cards in the deck
		
		Player computer[] = new Player[numberOfOpponents];		// Array for computer opponents
		
		// Initialize each computer opponent and draw cards for them
		for (int i = 0; i < computer.length; i++)
		{
			computer[i]= new Player();
			computer[i].drawCard(deck, 5);
		}
		
		// Check to see if the user has an ace in their hand
		boolean containsAce = false;
		
		for (int i=0; i<5; i++)
		{
			if (user.getHand()[i].getRank() == 'A')
				containsAce = true;
		}
		
		System.out.print("The cards in your hand are: ");
		user.printHand();
		System.out.println();
			
		/* 
		 * Get the input for which cards to discard - if the user has an ace they
		 * can discard all 4 cards except for the ace, otherwise they can only
		 * discard a maximum of 3 cards
		 */

		String discards = null;
		boolean validDiscards = false;
		if (containsAce)
		{
			System.out.println("Since you have an Ace you can keep the Ace and discard the other four cards\n"
							 + "or you can discard any three cards");
			
			while (!validDiscards)
			{
				System.out.print("List the card numbers that you wish to discard (ex: \"132\") -> ");
				discards = input.nextLine();
				
				// Check if the input was valid
				if (!validInput(discards))
					continue;
				
				// If 4 cards were specified check if ace is the only card left
				if (discards.length() == 4)
				{
					boolean keepAce = true;
					for (int i = 0; i < discards.length(); i++)
					{
						if (user.getHand()[Character.getNumericValue(discards.charAt(i))-1].getRank() == 'A')
						{
							System.out.println("Error: If you want to discard 4 cards you must keep the ace");
							keepAce = false;
							break;
						}
					}
					
					if (!keepAce) 	// If ace was not kept then loop again in while loop
						continue;
					else 
						break;		// Input is fine so break out of the while loop
				}
				
				// Make sure there are less than 3 characters 
				if (discards.length() > 3)
				{
					System.out.println("Error: You are only allowed to discard a maximum of 3 cards");
					continue;
				}
				validDiscards = true;
			} // End while
		}
		
		else
		{
			System.out.println("You can discard a max of three cards");
			
			while (!validDiscards)
			{
				System.out.print("List the card numbers that you wish to discard (ex: \"132\") -> ");
				discards = input.nextLine();
				
				// Make sure there are less than 3 characters 
				if (discards.length() > 3)
				{
					System.out.println("Error: You are only allowed to discard a maximum of 3 cards");
					continue;
				}
				
				if (!validInput(discards))
					continue;
				
				validDiscards = true;
			} // End while
		} // End else
		
		// Get new cards
		for (int i=0; i<discards.length(); i++)
		{
			int index = Character.getNumericValue(discards.charAt(i)-1);
			user.drawTargetCard(deck, index);
		}
		
		input.close();
		
		System.out.print("The cards in your hand are: ");
		user.printHand();
		System.out.println();
		
		// Sort computer opponent hands
		for (int i = 0; i < computer.length; i++) 
			computer[i].sortHand();
		
		
		
		
		//*******************************************************************
		// Call AI methods to perform computer actions
		for (int i = 0; i < computer.length; i++) 
		{
			
		}
		//*******************************************************************

		
		
		
		/*---------------------------------------------------------------------
		 * This section evaluates each player's hand and determines a winner
		 * for the game. 
		 * -------------------------------------------------------------------*/
		// Sort all player hands before evaluation
		user.sortHand();
		for (int i = 0; i < computer.length; i++) 
			computer[i].sortHand();
		
		Evaluate uEval = new Evaluate(user);
		Evaluate cEval[] = new Evaluate[computer.length]; 
		
		for (int i = 0; i < cEval.length; i++) 
			cEval[i] = new Evaluate(computer[i]); 

		// Get the evaluation for each hand and store it
		int strengths[] = new int[computer.length + 1];
		
		// Store user's evaluation in last index of the array
		strengths[strengths.length-1] = uEval.getEvaluation(); 
		user.setStrength(uEval.getEvaluation());
		
		// Store computer's strength in the same index as in computer array
		for (int i = 0; i < cEval.length; i++) 
		{
			strengths[i] = cEval[i].getEvaluation();  
			computer[i].setStrength(strengths[i]);
		}
		
		// Determine the winner with the highest ranked hand 
		int strongest = strengths[0];
		int strongestIndex = 0;
		
		// Find the max
		for (int i = 1; i < strengths.length; i++) 
		{
			if (strengths[i] > strongest)
			{
				strongest = strengths[i];
				strongestIndex = i;
			}
		}
		
		// Check for ties
		for (int i = 0; i < strengths.length; i++) 
		{
			if (strengths[i] == strongest && strongestIndex != i)
			{
				int result;
				// If one of the players is a user
				if ((strengths.length-1) == i || (strengths.length-1) == strongestIndex)
				{
					result = uEval.tieBreaker(user, computer[i]);
					
					// Game ends in a tie
					if (result == 0)
					{
						System.out.println("Tie game, both user an computer " + (i+1) + " have equal strength hands");
						break;
					}
				}
				// Two computer players
				else 
				{
					result = uEval.tieBreaker(computer[strongestIndex], computer[i]);
					
					// Game ends in a tie
					if (result == 0)
					{
						System.out.println("Tie game, computer "+ (i+1) + " and computer "
											+ (strongestIndex + 1) + "have equal strength hands");
						break;
					}
				}
			}
		}
		
		// Print all of the computer hands and show winner
		if (strongestIndex == strengths.length-1)	// User wins
			System.out.println("You win!");
		else 
			System.out.println("Computer " + (strongestIndex + 1) + " wins!");
		
		System.out.println("----------------------------------------\nYour hand: ");
		printHandType(user.getStrength());
		user.printHand();

		// Print put computer opponent hands
		for (int i = 0; i < computer.length; i++) 
		{
			System.out.println("\n----------------------------------------");
			System.out.println("Computer " + (i+1) + "'s hand: ");
			printHandType(computer[i].getStrength());
			computer[i].printHand();
		}
		
		System.out.println("\n\nThank you for playing Five Card Draw Poker");
		
	} // End main()
	
	/**------------------------------------------------------------------------
	 * Checks to see if the input string for discards by the user is valid. 
	 * The string is valid if it only contains integer values with no spaces 
	 * (EX: "123" or "23") and each value is less than or equal to 5
	 * 
	 * @param s		Input string
	 * @return		true for valid string or false otherwise
	 ------------------------------------------------------------------------*/
	public static boolean validInput(String s)
	{
		for (int i=0; i<s.length(); i++)
		{
			// Make sure each character is a digit / number
			if (!Character.isDigit(s.charAt(i)))
			{
				System.out.println("Error: Input contained non-numeric values");
				return false;
			}
			
			// Make sure the number is between 1 and 5
			int temp = Character.getNumericValue(s.charAt(i));
			if (temp > 5 || temp< 1)
			{
				System.out.println("Error: Input contained numeric values not in range of 1 to 5");
				return false;
			}
		}
		return true;
	}
	
	/**------------------------------------------------------------------------
	 * Method prints out the strength of hand based on the parameter
	 * 
	 * @param type	strength of hand
	 ------------------------------------------------------------------------*/
	public static void printHandType(int type) 
	{
		switch (type)
		{
			case 9: System.out.print("Straight Flush: "); break;
			case 8: System.out.print("Four of a kind: "); break;
			case 7: System.out.print("Full house: "); break;
			case 6: System.out.print("Flush: "); break;
			case 5: System.out.print("Straight : "); break;
			case 4: System.out.print("Three of a kind: "); break;
			case 3: System.out.print("Two pair: "); break;
			case 2: System.out.print("One pair: "); break;
			case 1: System.out.print("High card: "); break;
			default: break;
		}
	}
	
} // End class Game 
