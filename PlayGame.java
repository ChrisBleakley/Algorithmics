/* Algorithmics
 * 14708689 Orla Cullen
 * 05641349 Gavin Keaveney
 * 14343826 Jonathan Sweeney 
 */
public class PlayGame {
	SplitFrameGUI interfaceFrame = new SplitFrameGUI();
	PlayGame(){
	
		
		interfaceFrame.pack();
		interfaceFrame.setVisible(true);
		int playerId;
		String name;

		// get player names
		for (playerId = 0; playerId < GameData.NUM_PLAYERS; playerId++) {
			interfaceFrame.displayString("Enter the name of player " + (playerId + 1));
			name = interfaceFrame.getCommand();
			interfaceFrame.displayString("Welcome --> " + name);
		}
	
		interfaceFrame.displayString("Enter roll ");
		rolling();
	
		
	}
	public  void rolling(){
		do {
			String loop = interfaceFrame.getCommand();
			if (loop.equalsIgnoreCase("roll"))
				rollDice();
			else if (!(loop.equalsIgnoreCase("roll")))
				interfaceFrame.displayString("WRONG INPUT ROLL AGAIN ");
			interfaceFrame.displayString("WANT TO ROLL AGAIN ");
			interfaceFrame.displayString("Enter Y for Yes or N for No ");
			String word = interfaceFrame.getCommand();
			if (word.equalsIgnoreCase("Y")) {
				interfaceFrame.displayString("Enter: roll ");
			} else if (word.equalsIgnoreCase("N")) {
				interfaceFrame.displayString("Finished Roll ");
				break;
			}
		} while (true);
		
	}
	
	public int rollDice() {
		Die die = new Die();
		die.roll();
		int die1 = die.value();
		interfaceFrame.displayString(" Player 1 rolled: " + die.getDie());
		die.roll();
		int die2 = die.value();
		interfaceFrame.displayString(" Player 2 rolled: " + die.getDie());

		int winner = 0;
		if (die1 > die2) {
			winner = 1;
			interfaceFrame.displayString(" Player 1  wins");
		} else if (die1 < die2) {
			winner = 0;

			interfaceFrame.displayString(" Player 2  wins");
		} else{
			interfaceFrame.displayString(" DRAW RE Rolling");
			winner = rollDice();
		}
		return winner;

	}
	
	
	}

	