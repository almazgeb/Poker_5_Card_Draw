import java.util.Scanner;
import java.lang.*;

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
		
		int numberOfOpponents = -1;
		while (numberOfOpponents > 3 || numberOfOpponents < 0)	// While not between 0 and 3
		{
			System.out.println("You can only play against a range of 0-3 opponents");
			System.out.print("Enter number of opponents -> ");
			numberOfOpponents= input.nextInt();
			input.nextLine();
		}
		System.out.println("You will play aginst  " + numberOfOpponents + " computer oponent(s)");
		
		Player ai[] = new AI[numberOfOpponents];
		for (int i=0; i<numberOfOpponents; i++) {
			ai[i] = new AI();
		}
		
		Player user = new Player();		// Create the user
		
		/*---------------------------------------------------------------------
		 * This section handles the dealing phase
		 *-------------------------------------------------------------------*/
		CardPile deck = new CardPile();
		System.out.println("The deck is being shuffled and distributed\n");
		
		//Draws cards alternating among AI then user until each player has 5 cards in hand
		for (int i=0; i<5; i++){
			for (int j=0; j<numberOfOpponents; j++)
			{
				ai[j].drawCard(deck);
			}
			user.drawCard(deck);
		}//end for 5 card draw
		
		/*---------------------------------------------------------------------
		 * This section handles user input for discarding cards
		 * -------------------------------------------------------------------*/
		// Check to see if the user has an ace in their hand
		boolean containsAce = false;
		
		for (int i=0; i<5; i++)
		{
			if ((user.getHand())[i].getRank() == 'A')
				containsAce = true;
		}
		
		System.out.print("The cards in your hand are: ");
		user.printHand();
		System.out.println();
		
		/*---------------------------------------------------------------------
		 * Get the input for which cards to discard - if the user has an ace they
		 * can discard all 4 cards except for the ace, otherwise they can only
		 * discard a maximum of 3 cards
		 *  -------------------------------------------------------------------*/
		String discards = null;
		boolean validDiscards = false;
		if (containsAce)
		{
			System.out.println("Since you have an Ace you can keep the Ace and discard the other four cards\n"
							 + "or you can discard up to three cards");
			
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
				
				// Make sure there are less than 4 characters
				if (discards.length() > 4)
				{
					System.out.println("Error: You are only allowed to discard a maximum of 4 cards");
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
		
		System.out.print("The cards in your hand are: ");
		user.printHand();
		System.out.println();
		
	} // End main()
	
	/*---------------------------------------------------------------------
	 * Methods for the Game class
	 * -------------------------------------------------------------------*/
	
	/**
	 * Checks to see if the input string for discards by the user is valid. The string
	 * is valid if it only contains integer values with no spaces (EX: "123" or "23") and 
	 * each value is less than or equal to 5
	 * 
	 * @param s		Input string
	 * @return		true for valid string or false otherwise
	 */
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
	
} // End class Game 





/*---------------------------------------------------------------------
 * 
 * -------------------------------------------------------------------*/

/*
		Card c = deck.drawCard();	// Take card on top of deck
		System.out.println("\nTop: " + c.getCard() + " " + c.getRank() + " " + c.getSuit());
		System.out.println("Top of deck array position: " + deck.getTopOfDeck());
		
		c = deck.drawCard();	// Take another card from the top
		System.out.println("Top: " + c.getCard() + " " + c.getRank() + " " + c.getSuit());
		System.out.println("Top of deck array position: " + deck.getTopOfDeck());
*/