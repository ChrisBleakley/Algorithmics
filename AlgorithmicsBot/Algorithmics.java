package AlgorithmicsBot;
// put your code here

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
		boolean flagI= checkI();
		boolean flagA = checkA();
		boolean flagC= checkC();
		boolean flagF=checkFullset();
		boolean flagIW= checkIW();
		boolean flagIWW= checkIWW();
		boolean flagAW=checkAW();
		boolean flagAWW= checkAWW();
		boolean flagCW=checkCW();
		boolean flagCWW= checkCWW();
		boolean flagCAW=checkCAW();
		boolean flagIAW= checkIAW();
		boolean flagICW= checkICW();
		String command = "";
		if (player.isOptionalExchange()){
				
			
			if (flagI){
				command="III";
			}
			if (flagA){
				command="AAA";
			}
			if (flagC){
				command="CCC";
			}
			if (flagF){
				command="IAC";
			}
			if (flagIW){
				command="IIW";
			}
			if (flagIWW){
				command="IWW";
			}
			if (flagAW){
				command="AAW";
			}
			if (flagAWW){
				command="AWW";
			}
			if (flagCW){
				command="CCW";
			}
			if (flagCWW){
				command="CWW";
			}
			if (flagCAW){
				command="CAW";
			}
			if (flagIAW){
				command="IAW";
			}
			if (flagICW){
				command="ICW";
			}
			
		}
	
		if (player.isForcedExchange ()){
				
			
			if (flagI){
				command="III";
			}
			if (flagA){
				command="AAA";
			}
			if (flagC){
				command="CCC";
			}
			if (flagF){
				command="IAC";
			}
			if (flagIW){
				command="IIW";
			}
			if (flagIWW){
				command="IWW";
			}
			if (flagAW){
				command="AAW";
			}
			if (flagAWW){
				command="AWW";
			}
			if (flagCW){
				command="CCW";
			}
			if (flagCWW){
				command="CWW";
			}
			if (flagCAW){
				command="CAW";
			}
			if (flagIAW){
				command="IAW";
			}
			if (flagICW){
				command="ICW";
			}
			
		}
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
		int fortifyToCountry=0;
		int fortifyFromCountry=0;
		int largestAdjArmy=0;
		int smallestAdjArmy = 0;
		int numberToMove=0;
		// Check adjacent countries for large opposing armies
		for (int i=0; i<player.getCards().size();i++){
			int checkId=player.getCards().get(i).getCountryId();
			for (int j=0;j<GameData.ADJACENT[checkId].length; j++){
				if (board.getNumUnits(GameData.ADJACENT[checkId][j])>largestAdjArmy){ // Add check that country is not ours
					largestAdjArmy=board.getNumUnits(GameData.ADJACENT[checkId][j]);
					fortifyToCountry=checkId;
				}
			}
		}

		// From country // Lowest surrounding armies? Compare to already stationed?
		for (int i=0; i<player.getCards().size(); i++){
			int checkId=player.getCards().get(i).getCountryId();
			for (int j=0;j<GameData.ADJACENT[checkId].length; j++){
				if ((board.getNumUnits(GameData.ADJACENT[checkId][j])-(board.getNumUnits(i))<smallestAdjArmy)){ // Add check that country is not ours
					smallestAdjArmy=board.getNumUnits(GameData.ADJACENT[checkId][j]);
					fortifyFromCountry=checkId;
				}
			}
			
		}
		numberToMove=board.getNumUnits(fortifyFromCountry)-1;
		
		command = GameData.COUNTRY_NAMES[fortifyToCountry] + GameData.COUNTRY_NAMES[fortifyFromCountry] + numberToMove;
		return(command);
	}
	private boolean checkI(){
		int[] infantry;
		infantry = new int[] {0,0,0};
		
		boolean flagI= player.isCardsAvailable(infantry);
		if (flagI ==false){
		return false;}
		else
			return flagI;
	}
		private boolean checkC(){
			int[] cavalry;
			cavalry = new int[] {1,1,1};
			
			boolean flagC= player.isCardsAvailable(cavalry);
			if (flagC ==false){
			return false;}
			else
				return flagC;
		}
		
		private boolean checkA(){
		int[] art;
		art = new int[] {2,2,2};
		
		boolean flagA= player.isCardsAvailable(art);
		if (flagA ==false){
		return false;}
		else
			return flagA;
	}
		private boolean checkFullset(){
			int[] full;
			full = new int[] {0,1,2};
			
			boolean flagF= player.isCardsAvailable(full);
			if (flagF ==false){
			return false;}
			else
				return flagF;
		}
		private boolean checkIW(){
			int[] infw1;
			infw1= new int[] {0,0,3};
			
			boolean flagIW= player.isCardsAvailable(infw1);
			if (flagIW ==false){
			return false;}
			else
				return flagIW;
		}
		private boolean checkIWW(){
			int[] infw1;
			infw1= new int[] {0,3,3};
			
			boolean flagIWW= player.isCardsAvailable(infw1);
			if (flagIWW ==false){
			return false;}
			else
				return flagIWW;
		}
		private boolean checkAW(){
			int[] infw1;
			infw1= new int[] {2,2,3};
			
			boolean flagAW= player.isCardsAvailable(infw1);
			if (flagAW ==false){
			return false;}
			else
				return flagAW;
		}
		private boolean checkAWW(){
			int[] infw1;
			infw1= new int[] {2,3,3};
			
			boolean flagAWW= player.isCardsAvailable(infw1);
			if (flagAWW ==false){
			return false;}
			else
				return flagAWW;
		}
		private boolean checkCW(){
			int[] infw1;
			infw1= new int[] {1,1,3};
			
			boolean flagCW= player.isCardsAvailable(infw1);
			if (flagCW ==false){
			return false;}
			else
				return flagCW;
		}
		private boolean checkCWW(){
			int[] infw1;
			infw1= new int[] {1,3,3};
			
			boolean flagCWW= player.isCardsAvailable(infw1);
			if (flagCWW ==false){
			return false;}
			else
				return flagCWW;
		}
		private boolean checkCAW(){
			int[] infw1;
			infw1= new int[] {1,2,3};
			
			boolean flagCAW= player.isCardsAvailable(infw1);
			if (flagCAW ==false){
			return false;}
			else
				return flagCAW;
		}
		private boolean checkIAW(){
			int[] infw1;
			infw1= new int[] {0,2,3};
			
			boolean flagIAW= player.isCardsAvailable(infw1);
			if (flagIAW ==false){
			return false;}
			else
				return flagIAW;
		}
		private boolean checkICW(){
			int[] infw1;
			infw1= new int[] {0,1,3};
			
			boolean flagICW= player.isCardsAvailable(infw1);
			if (flagICW ==false){
			return false;}
			else
				return flagICW;
		}

}
