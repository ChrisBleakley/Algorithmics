/* Algorithmics
 * 14708689 Orla Cullen
 * 05641349 Gavin Keaveney
 * 14343826 Jonathan Sweeney 
 */

public class Main {

	public static void main(String args[]) {
		Territory territory = new Territory(1);
		SplitFrameGUI interfaceFrame = new SplitFrameGUI();
		interfaceFrame.pack();
		interfaceFrame.setVisible(true);
		int playerId, countryId;
		String name;

		// display blank territory
		interfaceFrame.displayMap();

		// get player names
		for (playerId = 0; playerId < GameData.NUM_PLAYERS; playerId++) {
			interfaceFrame.displayString("Enter the name of player " + (playerId + 1));
			name = interfaceFrame.getCommand();
			interfaceFrame.displayString("Welcome to Risk " + name);
		}

		// add units
		countryId = 0;
		for (playerId = 0; playerId < GameData.NUM_PLAYERS; playerId++) {
			for (int i = 0; i < GameData.INIT_COUNTRIES_PLAYER; i++) {
				territory.setArmies(countryId);
				countryId++;
			}
		}
		for (; playerId < GameData.NUM_PLAYERS_PLUS_NEUTRALS; playerId++) {
			for (int i = 0; i < GameData.INIT_COUNTRIES_NEUTRAL; i++) {
				territory.setArmies(countryId);
				countryId++;
			}
		}

		// display map
		interfaceFrame.displayMap();
		Die die  = new Die();  
		  
        String word = null;
    int die1;
    int die2;
        	
   
     interfaceFrame.displayString("To roll dice enter roll ");
 		word = interfaceFrame.getCommand();
        //
    while (word.equalsIgnoreCase("roll")){
        	
        	die.roll();
        	interfaceFrame.displayString(" Player 1" + die.getDie());
        	die1 = die.getDie();
        	die.roll();
        	interfaceFrame.displayString(" Player 2 " + die.getDie());
        	die2 = die.getDie();
        	
    }
		return;
	}

	}
	
