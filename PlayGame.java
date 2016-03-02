import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
//		int playerId;
		String name1;
		String name2;
		List<Integer> arrayList = new ArrayList<Integer>();

		// get player names
		interfaceFrame.displayString("Enter the name of player 1");
		name1 = interfaceFrame.getCommand();
		interfaceFrame.displayString("Welcome --> " + name1);
		interfaceFrame.displayString("Enter the name of player 2");
		name2 = interfaceFrame.getCommand();
		interfaceFrame.displayString("Welcome --> " + name2);

		// allocates territories
		arrayList = deal();
		printNames(0,9,arrayList,name1);
		printNames(9,18,arrayList,name2);
		printNames(18,24,arrayList,"Neutral 1");
		printNames(24,30,arrayList,"Neutral 2");
		printNames(30,36,arrayList,"Nuetral 3");
		printNames(36,42,arrayList,"Neutral 4");
		
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

	// creates number list 0-41 and randomises  
	public List<Integer> deal() {
		int i=0;
		List<Integer> arrayList = new ArrayList<Integer>();

		for (i=0;i<	42 ;i++){
			arrayList.add(i);
		}

		Collections.shuffle(arrayList);
		
		return arrayList;
	}
	
	public void printNames(int j, int k, List<Integer> arrayList, String name){
		String nameList = "";
		for (int i=j;i<k;i++){
			nameList += (GameData.COUNTRY_NAMES[arrayList.get(i)] + ", ");
		}
		interfaceFrame.displayString(name +" has received " + nameList + "\n");

	}
}

