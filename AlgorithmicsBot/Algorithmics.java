package AlgorithmicsBot;
// put your code here

import java.util.ArrayList;
import java.util.Random;

public class Algorithmics implements Bot {
	// The public API of YourTeamName must not change
	// You cannot change any other classes
	// YourTeamName may not alter the state of the board or the player objects
	// It may only inspect the state of the board and the player objects
	// So you can use player.getNumUnits() but you can't use player.addUnits(10000), for example

	private BoardAPI board;
	private PlayerAPI player;

	Algorithmics (BoardAPI inBoard, PlayerAPI inPlayer) {
		board = inBoard;	
		player = inPlayer;
		// put your code here
		return;
	}

	public String getName () {
		String command = "";
		command = "Algorthmics";
		return(command);
	}
	// Orla
	public String getReinforcement() {
		String command = "";
		Random rn = new Random();
		int myId = rn.nextInt(6 - 1 + 1) + 0;

		if (board.getNumUnits(myId)<8){

			if (myId == 3 && board.getNumUnits(myId)<7&& player.getNumUnits()==3) {
				command = GameData.COUNTRY_NAMES[(int) (rn.nextInt(31-28+1)) + 28];
				command = command.replaceAll("\\s", "");
				command += " 3";}
			if (myId == 3 && board.getNumUnits(myId)<7&& player.getNumUnits()==2) {
				command = GameData.COUNTRY_NAMES[(int) (rn.nextInt(31-28+1)) + 28];
				command = command.replaceAll("\\s", "");
				command += " 2";}
			if (myId == 3 && board.getNumUnits(myId)<7&& player.getNumUnits()==1) {
				command = GameData.COUNTRY_NAMES[(int) (rn.nextInt(31-28+1)) + 28];
				command = command.replaceAll("\\s", "");
				command += " 1";}
			if (myId == 4 && board.getNumUnits(myId)<7&& player.getNumUnits()==3) {
				command = GameData.COUNTRY_NAMES[(int) (rn.nextInt( 35 - 32+1) )+ 32];
				command = command.replaceAll("\\s", "");
				command += " 3";}
			if (myId == 4 && board.getNumUnits(myId)<7&& player.getNumUnits()==2) {
				command = GameData.COUNTRY_NAMES[(int) (rn.nextInt( 35 - 32+1) )+ 32];
				command = command.replaceAll("\\s", "");
				command += " 2";}
			if (myId == 4 && board.getNumUnits(myId)<7&& player.getNumUnits()==1) {
				command = GameData.COUNTRY_NAMES[(int) (rn.nextInt( 35 - 32+1) )+ 32];
				command = command.replaceAll("\\s", "");
				command += " 1";}
			if (myId == 5 && board.getNumUnits(myId)<7&& player.getNumUnits()==3) {
				command = GameData.COUNTRY_NAMES[(int) (rn.nextInt( 41 - 36) )+ 36];
				command = command.replaceAll("\\s", "");
				command += " 3";}
			if (myId == 5 && board.getNumUnits(myId)<7&& player.getNumUnits()==2) {
				command = GameData.COUNTRY_NAMES[(int) (rn.nextInt( 41 - 36) )+ 36];
				command = command.replaceAll("\\s", "");
				command += " 2";}
			if (myId == 5 && board.getNumUnits(myId)<7&& player.getNumUnits()==1) {
				command = GameData.COUNTRY_NAMES[(int) (rn.nextInt( 41 - 36) )+ 36];
				command = command.replaceAll("\\s", "");
				command += " 1";}

			if (myId< 3 ) {
				command = GameData.COUNTRY_NAMES[(int) (rn.nextInt( 41 - 0)) + 0];
				command = command.replaceAll("\\s", "");
				command += " 1";

			}

		}
		else
			getReinforcement();

		return (command);
	}
	// Orla
	public String getPlacement(int forPlayer) {
		String command = "";
		int myId = 0;

		for (int i = 0; i < 42; i++) {
			if (forPlayer > 1) {
				if ((board.getOccupier(i) == forPlayer) && (i < 42)) {

					if (GameData.CONTINENT_IDS[i] < 6 && board.getNumUnits(i) < 5) {
						command = GameData.COUNTRY_NAMES[(int) (Math.random() * GameData.NUM_COUNTRIES)];
						command = command.replaceAll("\\s", "");
						command += " 1";

					} else if (GameData.CONTINENT_IDS[i] < 6 && board.getNumUnits(i) > 5) {
						command = GameData.COUNTRY_NAMES[(int) (Math.random() * GameData.NUM_COUNTRIES)];
						command = command.replaceAll("\\s", "");
						command += " 0";

					}

				}
			}

		}
		return (command);

	}

	public String getCardExchange () {
		String command = "";
		// put your code here
		command = "skip";
		return(command);
	}
	// Jon
	public String getBattle () {
		String command = "";
		// put your code here
		command = "skip";
		return(command);
	}
	// Jon
	public String getDefence (int countryId) {
		String command = "";
		// put your code here
		command = "1";
		return(command);
	}
	// Gavin 
	public String getMoveIn (int attackCountryId) {
		String command = "";
		command = Integer.toString((board.getNumUnits(attackCountryId)/2)+1);
		return(command);
	}
	// Gavin
	public String getFortify () {
		String command = "";
		int fortifyToCountry=-1;
		int fortifyFromCountry=-1;
		int largestAdjArmy=0;
		int smallestAdjArmy = 0;
		int numberToMove=0;
		// Check adjacent countries for large opposing armies		
		for (int i=0; i<42;i++){
			if (player.getId() ==  board.getOccupier(i)){
				for (int j=0;j<GameData.ADJACENT[i].length; j++){
					if (board.getNumUnits(GameData.ADJACENT[i][j])>largestAdjArmy){ // Add check that country is not ours
						largestAdjArmy=board.getNumUnits(GameData.ADJACENT[i][j]);
						fortifyToCountry=i;
					}
				}
			}
		}

		// From country
		for (int i=0; i<42;i++){
			if (player.getId() ==  board.getOccupier(i)){
				for (int j=0;j<GameData.ADJACENT[i].length; j++){
					if ((board.getNumUnits(GameData.ADJACENT[i][j])-(board.getNumUnits(i))<smallestAdjArmy)  && board.getNumUnits(GameData.ADJACENT[i][j])!=1){ // Add check that country is not ours
						smallestAdjArmy=board.getNumUnits(GameData.ADJACENT[i][j]);
						fortifyFromCountry=i;
					}

				}

			}
		}

		numberToMove=board.getNumUnits(fortifyFromCountry)-1;
		
		System.out.println("from " + fortifyFromCountry);
		System.out.println("to " + fortifyToCountry);

		if (fortifyToCountry == fortifyFromCountry || !board.isConnected(fortifyFromCountry, fortifyToCountry)){
			System.out.println("skip 1");
			command = "skip";
			return command;
		}
		else {
			command = GameData.COUNTRY_NAMES[fortifyFromCountry].replaceAll("\\s", "") + " " + GameData.COUNTRY_NAMES[fortifyToCountry].replaceAll("\\s", "") + " " + numberToMove;
			return(command);
		}
	}

}
