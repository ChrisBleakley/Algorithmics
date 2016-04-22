package AlgorithmicsBot;
// put your code here

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
		int myId = 0;

		for (int i = 0; i < 42; i++) {
			myId = player.getId();
			if ((board.getOccupier(i) == 0 || board.getOccupier(i) == 1) && (i > 27)) {

				if (GameData.CONTINENT_IDS[i] > 2 && board.getNumUnits(i) < 7) {
					command = GameData.COUNTRY_NAMES[(int) (Math.random() * 41 - 27) + 27];
					command = command.replaceAll("\\s", "");
					command += " 3";

				}

				else if (GameData.CONTINENT_IDS[i] < 2 && board.getNumUnits(i) >= 1 && board.getNumUnits(i) < 7) {
					command = command.replaceAll("\\s", "");
					command += " 1";

				} else {
					command = "";
					command = GameData.COUNTRY_NAMES[(int) (Math.random() * GameData.NUM_COUNTRIES)];
					command = command.replaceAll("\\s", "");
					command += " 1";
				}
			}
			if ((board.getOccupier(i) == 0 || board.getOccupier(i) == 1) && (i < 27)) {

				if (GameData.CONTINENT_IDS[i] < 7 && board.getNumUnits(i) > 3) {
					command = GameData.COUNTRY_NAMES[i];
					command = command.replaceAll("\\s", "");
					command += " 1";

				} else if (GameData.CONTINENT_IDS[i] < 7 && board.getNumUnits(i) <= 3) {
					command = GameData.COUNTRY_NAMES[i];
					command = command.replaceAll("\\s", "");
					command += " 1";
				} else {
					command = "";
					command = GameData.COUNTRY_NAMES[(int) (Math.random() * GameData.NUM_COUNTRIES)];
					command = command.replaceAll("\\s", "");
					command += " 1";
				}
			} else if ((board.getOccupier(i) != myId) && (i < 42)) {
				command = "";
				command = GameData.COUNTRY_NAMES[(int) (Math.random() * GameData.NUM_COUNTRIES)];
				command = command.replaceAll("\\s", "");
				command += " 1";

			}

		}

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
		// put your code here
		command = "0";
		return(command);
	}
// Gavin
	public String getFortify () {
		String command = "";
		// put code here
		command = "skip";
		return(command);
	}

}
