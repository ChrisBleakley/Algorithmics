
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* Algorithmics
 * 14708689 Orla Cullen
 * 05641349 Gavin Keaveney
 * 14343826 Jonathan Sweeney 
 */
public class PlayGame {

	//arraylist represents the random ownership of the cards of a shuffled deck.
	List<Integer> arrayList = deal();

	//Creates the territories, assigning them the owners as determined in arraylist.
	List<Territory> territory_list = buildTerritories();
	
	//Shuffles the deck of PlayingCards
	List<Integer> shuffled_deck = shuffleDeck();
	//Builds the deck of PlayingCards
	List<Territory> card_list = buildCards(shuffled_deck);

	


	MouseArea mouseArea = new MouseArea(null);


	//Creates the Game Board, to show all game developments.
	MapPanel mapPanel = new MapPanel(territory_list, mouseArea);

	//Creates the a Jframe split into 3 panels, one of which will hold the map.
	SplitFrameGUI interfaceFrame = new SplitFrameGUI(mapPanel, mouseArea);


	PlayGame() {
		int golden_horse = -1;

		interfaceFrame.pack();
		interfaceFrame.setVisible(true);
		
		//Gets player names from prompt
		String player_1 = getNames(interfaceFrame, 1);
		String player_2 = getNames(interfaceFrame, 2);

		//Creates list of players and syncs each one with their owned territories.
		List<Player> player_list = buildPlayers(territory_list, player_1, player_2);


		//Draws territory cards and displays them to users.
		draw(territory_list, player_list, arrayList);

		interfaceFrame.displayString("Enter or press 'roll' to decide who places armies first.");
		int winner = roll(player_list);
		interfaceFrame.displayString(player_list.get(winner).getName() +" will place armies first.");

		//Method to allow players to set up the board, each player placing 27 armies on their own territories and 9 on each neutral.
		placeArmies(winner, territory_list, player_list);

		interfaceFrame.displayString("Enter or press 'roll' to decide who goes first.");
		winner = roll(player_list);
		interfaceFrame.displayString(player_list.get(winner).getName() +" has the first turn.");

		while(true){
			int current_player = 0;
			for(int i=0;i<2;i++){

				current_player=0;

				if (i % 2 == 0){
					current_player = winner;
				}
				else{
					current_player = (winner + 1) % 2;
				}

				check_HumanWinner(player_list);
				golden_horse = turn(current_player, territory_list, player_list, card_list, golden_horse);
			}
		}
	}

	
	public int turn(int current_player, List<Territory> territory_list, List<Player> player_list, List<Territory> card_list, int golden_horse){
		int reinforcements = calc_TotalReinforcements(territory_list, player_list, current_player);
		reinforceTerritories(current_player, territory_list, player_list, reinforcements);
		
		boolean territory_captured = combat(current_player, territory_list, player_list);
		
		fortify(current_player, territory_list, player_list);
		
		if(territory_captured){
			assignCard(current_player, player_list, card_list);	
		}
		
		golden_horse = cardExchange(current_player, territory_list, player_list, card_list, golden_horse);
		
		interfaceFrame.displayString("End of " + player_list.get(current_player).getName() + "'s turn.");	
		interfaceFrame.displayString("Beginning " + player_list.get((current_player + 1) % 2).getName() + "'s turn.");
		
		return golden_horse;
	}

	
	public void assignCard(int current_player, List<Player> player_list, List<Territory> card_list){
		if(!card_list.isEmpty()){
			interfaceFrame.displayString(player_list.get(current_player).getName() + " recieves the " + card_list.get(0).getName() + " ("  
											+ card_list.get(0).getInsignia() + ") territory card for capturing a territory.");	
			player_list.get(current_player).addOwnedCard(card_list.remove(0));
		}
		else{
			interfaceFrame.displayString("There are no more territory cards to give out.");
		}
	}

	public int cardExchange(int current_player, List<Territory> territory_list, List<Player> player_list, List<Territory> card_list, int golden_horse) {
		boolean trade_cards = true;
		do{	
			int traded_armies=0, flagI=0, flagA=0, flagC=0, flagW=0;
	
			for (int i=0; i<player_list.get(current_player).ownedCardsSize(); i++){
				String check;
				check = player_list.get(current_player).getOwnedCard(i).getShortName();
				if (check.equalsIgnoreCase("I")){
					flagI++;
				}
				else if (check.equalsIgnoreCase("A")){
					flagA++;
				}
				else if (check.equalsIgnoreCase("C")){
					flagC++;
				}
				else if (check.equalsIgnoreCase("W")){
					flagW++;
				}
			}
			
			interfaceFrame.displayString(player_list.get(current_player).getName() + " has " + flagI +" Infantry, " + flagA +" Artillery, " 
												+ flagC +" Cavalry, and " + flagW +" Wild Cards.");
	
			interfaceFrame.displayString("Please enter the insignias of the card set you wish to swap (III, AAA, CCC, or IAC), or enter 'skip' to keep cards");
			boolean cards_traded = false;
			do{	
				String loop = interfaceFrame.getCommand();
	
				if(loop.equalsIgnoreCase("skip")){
					if(player_list.get(current_player).ownedCardsSize() < 5){
						trade_cards = false;
						break;
					}
					else{
						interfaceFrame.displayString("As you have 5 or more territories, you must trade in a set of cards (III, AAA, CCC, or IAC)");
						continue;
					}
				}
	
				if(loop.equalsIgnoreCase("III")){
					if(flagI + flagW >= 3){
						int removed_cards = 0;
						for(int i=0; i<=player_list.get(current_player).ownedCardsSize() && removed_cards<3; i++){
							if(player_list.get(current_player).getOwnedCard(i).getShortName().equalsIgnoreCase("I")){
								player_list.get(current_player).removeOwnedCard(i);
								removed_cards++;
							}
						}
	
						if(removed_cards<3){
							for(int i=0; i<=player_list.get(current_player).ownedCardsSize() && removed_cards<3; i++){
								if(player_list.get(current_player).getOwnedCard(i).getShortName().equalsIgnoreCase("W")){
									player_list.get(current_player).removeOwnedCard(i);
									removed_cards++;
								}
							}
						}
	
						cards_traded = true;
						break;
					}
					else{
						interfaceFrame.displayString("You do not have enough cards with this insignia");
						continue;
					}
				}
	
				if(loop.equalsIgnoreCase("AAA")){
					if(flagA + flagW >= 3){
						int removed_cards = 0;
						for(int i=0; i<=player_list.get(current_player).ownedCardsSize() && removed_cards<3; i++){
							if(player_list.get(current_player).getOwnedCard(i).getShortName().equalsIgnoreCase("A")){
								player_list.get(current_player).removeOwnedCard(i);
								removed_cards++;
							}
						}
	
						if(removed_cards<3){
							for(int i=0; i<=player_list.get(current_player).ownedCardsSize() && removed_cards<3; i++){
								if(player_list.get(current_player).getOwnedCard(i).getShortName().equalsIgnoreCase("W")){
									player_list.get(current_player).removeOwnedCard(i);
									removed_cards++;
								}
							}
						}
	
						cards_traded = true;
						break;
					}
					else{
						interfaceFrame.displayString("You do not have enough cards with this insignia");
						continue;
					}
				}
	
				if(loop.equalsIgnoreCase("CCC")){
					if(flagC + flagW >= 3){
						int removed_cards = 0;
						for(int i=0; i<=player_list.get(current_player).ownedCardsSize() && removed_cards<3; i++){
							if(player_list.get(current_player).getOwnedCard(i).getShortName().equalsIgnoreCase("C")){
								player_list.get(current_player).removeOwnedCard(i);
								removed_cards++;
							}
						}
	
						if(removed_cards<3){
							for(int i=0; i<=player_list.get(current_player).ownedCardsSize() && removed_cards<3; i++){
								if(player_list.get(current_player).getOwnedCard(i).getShortName().equalsIgnoreCase("W")){
									player_list.get(current_player).removeOwnedCard(i);
									removed_cards++;
								}
							}
						}
	
						cards_traded = true;
						break;
					}
					else{
						interfaceFrame.displayString("You do not have enough cards with this insignia");
						continue;
					}
				}		
	
				if(loop.equalsIgnoreCase("IAC")){
					int wilds_required = 0;
					if(flagI == 0) wilds_required++;
					if(flagA == 0) wilds_required++;
					if(flagC == 0) wilds_required++;
	
					if(wilds_required <= flagW){
						for(int i=0; i<player_list.get(current_player).ownedCardsSize(); i++){
							if(player_list.get(current_player).getOwnedCard(i).getShortName().equalsIgnoreCase("I")){
								player_list.get(current_player).removeOwnedCard(i);
								break;
							}
						}
	
						for(int i=0; i<player_list.get(current_player).ownedCardsSize(); i++){
							if(player_list.get(current_player).getOwnedCard(i).getShortName().equalsIgnoreCase("A")){
								player_list.get(current_player).removeOwnedCard(i);
								break;
							}
						}
	
						for(int i=0; i<player_list.get(current_player).ownedCardsSize(); i++){
							if(player_list.get(current_player).getOwnedCard(i).getShortName().equalsIgnoreCase("C")){
								player_list.get(current_player).removeOwnedCard(i);
								break;
							}
						}
	
						for(int i=0; i<player_list.get(current_player).ownedCardsSize() && wilds_required>0; i++){
							if(player_list.get(current_player).getOwnedCard(i).getShortName().equalsIgnoreCase("W")){
								player_list.get(current_player).removeOwnedCard(i);
								wilds_required--;
							}
						}
	
						cards_traded = true;
						break;
					}
					else{
						interfaceFrame.displayString("You do not have enough cards with these insignia");
						continue;
					}
				}		
	
				interfaceFrame.displayString("INPUT NOT RECOGNISED");
				interfaceFrame.displayString("Please enter a valid insignia shorthand (III, AAA, CCC, or IAC), or enter skip to keep cards");
	
			}while(true);
	
	
			if(cards_traded){
				golden_horse++;
				switch(golden_horse){
				case 0:
					traded_armies=4;
					break;
				case 1:
					traded_armies=6;
					break;
				case 2:
					traded_armies=8;
					break;
				case 3:
					traded_armies=10;
					break;
				case 4:
					traded_armies=15;
					break;
				case 5:
					traded_armies=20;
					break;
				case 6:
					traded_armies=25;
					break;
				case 7:
					traded_armies=30;
					break;
				case 8:
					traded_armies=35;
					break;
				case 9:
					traded_armies=40;
					break;
				case 10:
					traded_armies=45;
					break;
				case 11:
					traded_armies=50;
					break;
				case 12:
					traded_armies=55;
					break;
				case 13:
					traded_armies=60;
					break;
				default:
					traded_armies=60;	// Increment by MAX amount
				}
				
				interfaceFrame.displayString("Card set successfully traded!");
				reinforceTerritories(current_player, territory_list, player_list, traded_armies);
			}
		}while(trade_cards);
	return golden_horse;
	}

	
	public void reinforceTerritories(int current_player, List<Territory> territory_list, List<Player> player_list, int reinforcements){
		player_list.get(current_player).setArmies(reinforcements);

		interfaceFrame.displayString(player_list.get(current_player).getName() + " has " + player_list.get(current_player).getArmies() + " total reinforcements.");

		while(player_list.get(current_player).getArmies() > 0){
			interfaceFrame.displayString(player_list.get(current_player).getName() + ", please choose one of your territories to place armies on.");
			assignArmies(territory_list, player_list, current_player, 1);
		}	
	}

	
	public boolean combat(int current_player, List<Territory> territory_list, List<Player> player_list){
		boolean territory_captured = false;
		interfaceFrame.displayString(player_list.get(current_player).getName() + ", please choose one of your territories to launch an attack or enter 'skip' to end combat.");	
		do{	
			boolean valid_choice = false;
			int chosen_node = getCombatInput(territory_list);
			if(chosen_node == -2){
				break;
			}
			for (int j=0; j < player_list.get(current_player).ownedTerritoriesSize() ; j++){
				if(chosen_node == player_list.get(current_player).getOwnedTerritory(j)){
					if(territory_list.get(chosen_node).getArmies() == 1){
						interfaceFrame.displayString("You cannot attack from a territory with 1 army");	
						valid_choice = true;
						break;
					}
					territory_captured = battle(chosen_node, current_player, territory_list, player_list);
					mapPanel.refresh();
					valid_choice = true;
					break;
				}
			}

			if(valid_choice==true){
				interfaceFrame.displayString(player_list.get(current_player).getName() + ", please choose one of your territories to launch an attack or enter 'skip' to end combat.");
				continue;
			}

			interfaceFrame.displayString(player_list.get(current_player).getName() + " does not own " + GameData.COUNTRY_NAMES[chosen_node]);
			interfaceFrame.displayString("Please enter a territory owned by " + player_list.get(current_player).getName());
		}while(true);

		interfaceFrame.displayString(player_list.get(current_player).getName() + " has ended combat.");
		return territory_captured;
	}

	
	public int getCombatInput(List<Territory> territory_list){
		int chosen_node = -1;
		do{	
			String loop = interfaceFrame.getCommand();

			if(loop.equalsIgnoreCase("skip")){
				chosen_node = -2;
				break;
			}

			for(int i=0; i<42; i++){
				if (loop.equalsIgnoreCase(GameData.COUNTRY_NAMES[i]) || loop.equalsIgnoreCase(GameData.SHORT_COUNTRY_NAMES[i])){
					chosen_node = territory_list.get(i).getNode();
					break;
				}
			}

			if(chosen_node == -1){
				interfaceFrame.displayString("NAME NOT RECOGNISED");
				interfaceFrame.displayString("Please enter a valid name or shorthand. ");
			}

		}while(chosen_node == -1);

		return chosen_node;
	}

	
	public boolean battle(int chosen_node, int current_player, List<Territory> territory_list, List<Player> player_list){
		boolean territory_captured = false;
		boolean attack_again = false;
		interfaceFrame.displayString("Please choose a bordering opponant's territory to attack");	
		do{
			boolean valid_choice = true;
			boolean valid_target = false;
			int chosen_target = getCombatInput(territory_list);
			if(chosen_node == -2){
				break;
			}

			for (int j=0; j < player_list.get(current_player).ownedTerritoriesSize() ; j++){
				if(chosen_target == player_list.get(current_player).getOwnedTerritory(j)){
					interfaceFrame.displayString("You cannot attack your own territory");	
					valid_choice=false;
					break;
				}
			}

			if(valid_choice==false){
				interfaceFrame.displayString("Please choose a bordering opponant's territory to attack");
				continue;
			}

			for(int j=0;j<6;j++){	
				try{
					if(GameData.ADJACENT[chosen_node][j] == chosen_target){
						valid_target = true;
						break;
					}
				}
				catch(ArrayIndexOutOfBoundsException e){
					break;
				}
			}

			if(valid_target==false){
				interfaceFrame.displayString(GameData.COUNTRY_NAMES[chosen_target] + " is not adjacent to " + GameData.COUNTRY_NAMES[chosen_node]);
				interfaceFrame.displayString("Please choose a bordering opponant's territory to attack");
				continue;
			}


			do{
				interfaceFrame.displayString(player_list.get(current_player).getName() + ", with how many armies do you want to attack " + GameData.COUNTRY_NAMES[chosen_target] + "? (Max 3)");	
				int attack_number;
				int defend_number;
				do {	
					String attack_input = interfaceFrame.getCommand();

					try{
						attack_number = Integer.parseInt(attack_input);
					}
					catch(Exception e){
						interfaceFrame.displayString("You must enter an integer value.");
						continue;
					}

					if (attack_number >= territory_list.get(chosen_node).getArmies() || attack_number <= 0 || attack_number > 3){
						interfaceFrame.displayString("You cannot attack with that many armies.");
						continue;
					}

					break;
				} while(true);

				if(territory_list.get(chosen_target).getArmies()==1){
					defend_number=1;
					interfaceFrame.displayString(player_list.get((current_player + 1) % 2).getName() + " will defend " + GameData.COUNTRY_NAMES[chosen_target] + " with 1 army.");
				}
				else{
					interfaceFrame.displayString(player_list.get((current_player + 1) % 2).getName() + ", would you like to defend " + GameData.COUNTRY_NAMES[chosen_target] + " with 1 or 2 armies?");	
					do {	
						String loop = interfaceFrame.getCommand();

						try{
							defend_number = Integer.parseInt(loop);
						}
						catch(Exception e){
							interfaceFrame.displayString("You must enter an integer value.");
							continue;
						}

						if (defend_number > territory_list.get(chosen_target).getArmies() || defend_number <= 0 || defend_number > 2){
							interfaceFrame.displayString("You cannot attack with that many armies.");
							continue;
						}

						break;
					} while(true);
				}

				combatRoll(current_player, attack_number, defend_number, chosen_node, chosen_target, territory_list, player_list);
				if(territory_list.get(chosen_target).getArmies()==0){
					interfaceFrame.displayString(player_list.get(current_player).getName() + " has captured " + GameData.COUNTRY_NAMES[chosen_target]);	

					player_list.get(territory_list.get(chosen_target).getPlayer()).removeOwnedTerritory(chosen_target);
					player_list.get(current_player).addOwnedTerritory(chosen_target);
					territory_list.get(chosen_target).setPlayer(current_player);
					territory_list.get(chosen_target).setArmies(attack_number);
					territory_list.get(chosen_node).setArmies(-attack_number);
					mapPanel.refresh();
					removePlayer(player_list);
					check_HumanWinner(player_list);
					territory_captured = true;
					attack_again = false;
					break;
				}
				else{
					interfaceFrame.displayString(player_list.get(current_player).getName() + ", would you like to attack " + GameData.COUNTRY_NAMES[chosen_target] + " again? Y/N");	
					do {	
						String word = interfaceFrame.getCommand();
						if (word.equalsIgnoreCase("Y")){
							attack_again = true;
							break;
						}
						else if (word.equalsIgnoreCase("N")){
							attack_again = false;
							break;
						}
						else{
							interfaceFrame.displayString("COMMAND NOT RECOGNISED");
							interfaceFrame.displayString("Please enter Y or N. ");
						}
					} while(true);
				}	
			}while(attack_again);	
		}while(attack_again);
	return territory_captured;
	}

	
	public void combatRoll(int current_player, int attack_number, int defend_number, int chosen_node, int chosen_target, List<Territory> territory_list, List<Player> player_list){
		List<Integer> attacking_rolls = new ArrayList<Integer>();
		List<Integer> defending_rolls = new ArrayList<Integer>();

		interfaceFrame.displayString(player_list.get(current_player).getName() + " rolls:");
		for(int j=0; j<attack_number; j++){
			Die die = new Die();
			die.roll();
			attacking_rolls.add(die.value());
			interfaceFrame.displayString("" + die.value());
		}

		interfaceFrame.displayString(player_list.get((current_player + 1) % 2).getName() + " rolls:");
		for(int j=0; j<defend_number; j++){
			Die die = new Die();
			die.roll();
			defending_rolls.add(die.value());
			interfaceFrame.displayString("" + die.value());
		}

		Collections.sort(attacking_rolls);
		Collections.sort(defending_rolls);
		Collections.reverse(attacking_rolls);
		Collections.reverse(defending_rolls);

		if(attacking_rolls.get(0) > defending_rolls.get(0)){
			interfaceFrame.displayString(player_list.get(current_player).getName() + " wins a battle, defending player loses one army.");
			territory_list.get(chosen_target).setArmies(-1);
		}
		else{
			interfaceFrame.displayString(player_list.get(current_player).getName() + " loses a battle, attacking player loses one army.");	
			territory_list.get(chosen_node).setArmies(-1);
		}
		mapPanel.refresh();
		if(defend_number==2 && attack_number>=2){
			if(attacking_rolls.get(1) > defending_rolls.get(1)){
				interfaceFrame.displayString(player_list.get(current_player).getName() + " wins a battle, defending player loses one army.");
				territory_list.get(chosen_target).setArmies(-1);
			}
			else{
				interfaceFrame.displayString(player_list.get(current_player).getName() + " loses a battle, attacking player loses one army.");	
				territory_list.get(chosen_node).setArmies(-1);
			}
			mapPanel.refresh();
		}
	}

	
	public void fortify(int current_player, List<Territory> territory_list, List<Player> player_list){
		interfaceFrame.displayString(player_list.get(current_player).getName() + ", please choose one of your territories to move armies from or enter 'skip'.");	
		boolean valid_choice = false;
		do{
			boolean success = false;
			int chosen_target = 0;
			int chosen_node = getCombatInput(territory_list);
			if(chosen_node == -2){
				break;
			}
			for (int j=0; j < player_list.get(current_player).ownedTerritoriesSize() ; j++){
				if(chosen_node == player_list.get(current_player).getOwnedTerritory(j)){
					if(territory_list.get(chosen_node).getArmies() == 1){
						interfaceFrame.displayString("You cannot move armies from a territory with 1 army");	

						break;
					}
					interfaceFrame.displayString(player_list.get(current_player).getName() + ", please choose one of your territories to fortify or enter 'skip'.");	

					do{
						chosen_target = getCombatInput(territory_list);
						if(chosen_target == -2){
							break;
						}
						for (int i=0; i < player_list.get(current_player).ownedTerritoriesSize() ; i++){
							if(chosen_node == player_list.get(current_player).getOwnedTerritory(i)){
								success = moveArmies(chosen_node, chosen_target, current_player, territory_list, player_list);
								mapPanel.refresh();
								break;
							}
						}

						if(success==true){
							break;
						}

						interfaceFrame.displayString(player_list.get(current_player).getName() + " does not own " + GameData.COUNTRY_NAMES[chosen_target]);
						interfaceFrame.displayString("Please enter a territory owned by " + player_list.get(current_player).getName());	
					}while(true);
					mapPanel.refresh();
					valid_choice = true;
					break;
				}
			}

			if(chosen_target == -2){
				break;
			}

			if(valid_choice==true){
				break;
			}

			interfaceFrame.displayString(player_list.get(current_player).getName() + " does not own " + GameData.COUNTRY_NAMES[chosen_node]);
			interfaceFrame.displayString("Please enter a territory owned by " + player_list.get(current_player).getName());	
		}while(true);
	}

	
	public boolean moveArmies(int chosen_node, int chosen_target, int current_player, List<Territory> territory_list, List<Player> player_list){
		interfaceFrame.displayString(player_list.get(current_player).getName() + ", how many armies would you like to move from " 
				+ GameData.COUNTRY_NAMES[chosen_node] + " to " + GameData.COUNTRY_NAMES[chosen_target] + "?");
		int move_number;
		boolean success = false;
		do {	
			String loop = interfaceFrame.getCommand();

			try{
				move_number = Integer.parseInt(loop);
			}
			catch(Exception e){
				interfaceFrame.displayString("You must enter an integer value.");
				continue;
			}

			if (move_number >= territory_list.get(chosen_node).getArmies() || move_number <= 0){
				interfaceFrame.displayString("You cannot move that many armies.");
				continue;
			}

			break;
		}while(true);

		boolean valid_target = false;
		for(int j=0;j<6;j++){	
			try{
				if(GameData.ADJACENT[chosen_node][j] == chosen_target){
					valid_target = true;
					break;
				}
			}
			catch(ArrayIndexOutOfBoundsException e){
				break;
			}
		}

		if(valid_target==false){
			interfaceFrame.displayString(GameData.COUNTRY_NAMES[chosen_target] + " is not adjacent to " + GameData.COUNTRY_NAMES[chosen_node]);
			interfaceFrame.displayString("Please choose a bordering territory to fortify");
			success = false;
		}

		else{
			territory_list.get(chosen_target).setArmies(move_number);
			territory_list.get(chosen_node).setArmies(-move_number);
			mapPanel.refresh();
			success = true;
		}

		return success;
	}

	
	public void draw(List<Territory> territory_list, List<Player> player_list, List<Integer> arrayList){

		do {
			interfaceFrame.displayString("Enter or press 'draw' to draw territory cards");

			String loop = interfaceFrame.getCommand();
			if (loop.equalsIgnoreCase("draw")){
				break;
			}
			else if (!(loop.equalsIgnoreCase("draw"))){
				interfaceFrame.displayString("COMMAND NOT RECOGNISED");
				interfaceFrame.displayString("Would you like to draw territory cards? ");
				interfaceFrame.displayString("Enter Y for Yes or N for No ");
				String word = interfaceFrame.getCommand();

				if (word.equalsIgnoreCase("Y")){
					break;
				}
				else if (word.equalsIgnoreCase("N")){
					interfaceFrame.displayString("Cannot continue, thank you for playing. ");
					try {
						TimeUnit.SECONDS.sleep(2);
					} 
					catch (InterruptedException e){
					}
					System.exit(0);
				}
			}
		} while (true);
		assignTerritories(territory_list, player_list, arrayList);
		printNames(player_list);
	}

	
	public int roll(List<Player> player_list){
		int winner;
		do {
			String loop = interfaceFrame.getCommand();
			if (loop.equalsIgnoreCase("roll")){
				winner = rollDice(player_list);
				break;
			}
			else if (!(loop.equalsIgnoreCase("roll"))){
				interfaceFrame.displayString("COMMAND NOT RECOGNISED");
				interfaceFrame.displayString("Would you like to roll dice? ");
				interfaceFrame.displayString("Enter Y for Yes or N for No ");
				String word = interfaceFrame.getCommand();

				if (word.equalsIgnoreCase("Y")){
					winner = rollDice(player_list);
					break;
				}
				else if (word.equalsIgnoreCase("N")){
					interfaceFrame.displayString("Cannot continue, thank you for playing. ");
					try {
						TimeUnit.SECONDS.sleep(2);
					} 
					catch (InterruptedException e){
					}
					System.exit(0);
				}
			}
		} while (true);

		return winner;
	}

	
	public void placeArmies(int winner, List<Territory> territory_list, List<Player> player_list) {		
		for(int i=0;i<18;i++) {
			int current_player = 0;
			int armies_placed = 0;

			if (i % 2 == 0){
				current_player = winner;
			}
			else{
				current_player = (winner + 1) % 2;
			}
			while(armies_placed < 3){
				interfaceFrame.displayString(player_list.get(current_player).getName() + ", please choose one of your territories to place  armies on.");
				armies_placed += assignArmies(territory_list, player_list, current_player, 3-armies_placed);
			}

			for (int k=2; k < GameData.NUM_PLAYERS_PLUS_NEUTRALS; k++) { 
				interfaceFrame.displayString(player_list.get(current_player).getName() + ", please choose one of " + player_list.get(k).getName()
						+ " territories" + "(" + GameData.PLAYER_COLOURS[k] + ")" + "to place 1 army on.");
				assignArmies(territory_list, player_list, k, 1);
			}
		}
	}

	// Assigns armies to a chosen territory belonging to a given player.

	public int assignArmies(List<Territory> territory_list, List<Player> player_list, int player, int armies){
		int chosen_armies = 0;
		do{	
			boolean valid_choice = false;
			int chosen_node = getTerritoryInput(territory_list);
			for (int j=0; j < player_list.get(player).ownedTerritoriesSize() ; j++){
				if(chosen_node == player_list.get(player).getOwnedTerritory(j)){
					if(armies > 1){
						interfaceFrame.displayString(player_list.get(player).getName() + " has " + armies + " armies available");
						interfaceFrame.displayString("How many would you like to place on " + GameData.COUNTRY_NAMES[chosen_node] + "?");
						do {	
							String armies_input = interfaceFrame.getCommand();

							try{
								chosen_armies = Integer.parseInt(armies_input);
							}
							catch(Exception e){
								interfaceFrame.displayString("You must enter an integer value.");
								continue;
							}

							if (chosen_armies > armies || chosen_armies < 0){
								interfaceFrame.displayString("You cannot place that many armies.");
								continue;
							}

							break;
						} while(true);
					}
					else{
						chosen_armies = armies;
					}
					player_list.get(player).setArmies(-chosen_armies);
					territory_list.get(chosen_node).setArmies(chosen_armies);
					mapPanel.refresh();
					valid_choice = true;
					break;
				}
			}
			if(valid_choice==true){
				break;
			}
			interfaceFrame.displayString(player_list.get(player).getName() + " does not own " + GameData.COUNTRY_NAMES[chosen_node]);
			interfaceFrame.displayString("Please enter a territory owned by " + player_list.get(player).getName());
		}while(true);

		return chosen_armies;
	}

	//Reads the entered territory name from the prompt.

	public int getTerritoryInput(List<Territory> territory_list){
		int chosen_node = -1;
		do {	
			String loop = interfaceFrame.getCommand();
			for(int i=0; i<42; i++){
				if (loop.equalsIgnoreCase(GameData.COUNTRY_NAMES[i]) || loop.equalsIgnoreCase(GameData.SHORT_COUNTRY_NAMES[i])){
					chosen_node = territory_list.get(i).getNode();
					break;
				}
			}
			if(chosen_node == -1){
				interfaceFrame.displayString("NAME NOT RECOGNISED");
				interfaceFrame.displayString("Please enter a valid name or shorthand. ");
			}

		} while (chosen_node == -1);

		return chosen_node;
	}


	//Rolls the dice and returns the winner when called.
	public int rollDice(List<Player> player_list) {
		Die die = new Die();
		die.roll();
		int die1 = die.value();
		interfaceFrame.displayString(player_list.get(0).getName() +" (Player 1) rolled: " + die.getDie());
		die.roll();
		int die2 = die.value();
		interfaceFrame.displayString(player_list.get(1).getName() +" (Player 2) rolled: " + die.getDie());

		int winner = 0;
		if (die1 > die2) {
			winner = 0;
			interfaceFrame.displayString(player_list.get(0).getName() +" wins");
		} else if (die1 < die2) {
			winner = 1;

			interfaceFrame.displayString(player_list.get(1).getName() +" wins");
		} else{
			interfaceFrame.displayString(" Draw, Re-Rolling");
			try {
				TimeUnit.SECONDS.sleep(1);
			} 
			catch (InterruptedException e){
			}
			winner = rollDice(player_list);
		}
		return winner;
	}

	//Creates and initializes the list of territories.
	public List<Territory> buildTerritories(){
		List<Territory> territory_list= new ArrayList<Territory>();

		for(int i=0;i<42;i++){
			Territory current_territory = new Territory(i, GameData.COUNTRY_NAMES[i], GameData.SHORT_COUNTRY_NAMES[i]);
			current_territory.setArmies(1);

			current_territory.setPlayer(-1);

			territory_list.add(current_territory);
		}
		return territory_list;
	}

	//Creates and initializes the list of 6 players.
	public List<Player> buildPlayers(List<Territory> territory_list,  String player_1, String player_2){	
		List<Player> player_list= new ArrayList<Player>();
		String player_name = null;
		int armies = 0;
		for(int i=0;i<6;i++){
			switch (i) {
			case 0:  player_name = player_1;
			armies = 27; // **** Keep at 27 (Change to 3 for quick start) ****
			break;
			case 1:  player_name = player_2;
			break;
			case 2:  player_name = "Neutral Player 1";
			armies = 18;
			break;
			case 3:  player_name = "Neutral Player 2";
			break;
			case 4:  player_name = "Neutral Player 3";
			break;
			case 5:  player_name = "Neutral Player 4";
			break;
			}	
			Player current_player = new Player(i, player_name);
			current_player.setArmies(armies);



			player_list.add(current_player);
		}
		return player_list;
	}

	
	public void assignTerritories(List<Territory> territory_list, List<Player> player_list, List<Integer> arrayList){
		for(int i=0;i<42;i++){
			territory_list.get(i).setPlayer(arrayList.get(i));
		}
		for(int i=0;i<6;i++){
			for(int j=0 ;j<42 ;j++){
				if(i==territory_list.get(j).getPlayer()){
					player_list.get(i).addOwnedTerritory(territory_list.get(j).getNode());
				}
			}
		}
		mapPanel.refresh();
	}

	//Creates a length 42 integer list of numbers 0-5 and randomizes it.
	public List<Integer> deal() {
		int i=0;
		int current_player = 0;
		List<Integer> arrayList = new ArrayList<Integer>();

		for (i=0;i<	42 ;i++){
			switch (i) {
			case 0:  current_player = 0;
			break;
			case 9:  current_player = 1;
			break;
			case 18: current_player = 2;
			break;
			case 24: current_player = 3;
			break;
			case 30: current_player = 4;
			break;
			case 36: current_player = 5; 
			break;
			}
			arrayList.add(current_player);
		}

		Collections.shuffle(arrayList);

		return arrayList;
	}

	//Get names from prompt.
	public  String getNames(SplitFrameGUI interfaceFrame, int player_number){
		interfaceFrame.displayString("Enter the name of Player " + player_number);
		String name = interfaceFrame.getCommand();
		interfaceFrame.displayString("Welcome to risk " + name);

		return name;
	}

	//Print each player's names and owned territories.
	public void printNames(List<Player> player_list){
		for(int j=0; j<6;j++){
			String nameList = "";
			for (int i=0; i < player_list.get(j).ownedTerritoriesSize()-1 ; i++){
				nameList += (GameData.COUNTRY_NAMES[player_list.get(j).getOwnedTerritory(i)] + ", ");
			}
			nameList += (GameData.COUNTRY_NAMES[player_list.get(j).getOwnedTerritory(player_list.get(j).ownedTerritoriesSize()-1)] + ".");
			interfaceFrame.displayString(player_list.get(j).getName() + " (" + GameData.PLAYER_COLOURS[j] + ")" +" has received " + nameList + "\n");
		}
	}

	
	public int  calc_TotalReinforcements(List<Territory> territory_list, List<Player> player_list, int current_player) {
		int Namerica_size = 0;
		int Euro_size = 0;
		int Asia_size = 0;
		int Aus_size = 0;
		int Samerica_size = 0;
		int Af_size = 0;
		int nam_reinforce = 0;
		int eu_reinforce = 0;
		int as_reinforce = 0;
		int aus_reinforce = 0;
		int sam_reinforce = 0;
		int af_reinforce=0;
		int country_reinforce = 0;
		if (player_list.get(current_player).ownedTerritoriesSize() / 3 <= 3) {
			country_reinforce = 3;
		} 
		else if (player_list.get(current_player).ownedTerritoriesSize() / 3 > 3) {
			country_reinforce = player_list.get(current_player).ownedTerritoriesSize() / 3;
		}
		for (int j = 0; j < 42; j++) {

			if (j < 9 && (current_player == territory_list.get(j).getPlayer())) {
				Namerica_size++;
				if (Namerica_size==9){
					nam_reinforce = GameData.north_america;
				}
				else{
					nam_reinforce = 0;
				}
			}
			if (j > 8 && j < 16 && (current_player == territory_list.get(j).getPlayer())) {
				Euro_size++;
				if (Euro_size==7){
					eu_reinforce = GameData.europe;
				}
				else{
					eu_reinforce = 0;
				}


			}
			if (j > 15 && j < 28 && (current_player == territory_list.get(j).getPlayer())) {
				Asia_size++;
				if (Asia_size==12){
					as_reinforce = GameData.asia;
				}
				else{
					as_reinforce = 0;
				}

			}
			if (j > 27 && j < 32 && (current_player == territory_list.get(j).getPlayer())) {
				Aus_size++;
				if (Aus_size==4){
					aus_reinforce =  GameData.australia;
				}
				else{
					aus_reinforce = 0;
				}

			}
			if (j > 31 && j < 36 && (current_player == territory_list.get(j).getPlayer())) {
				Samerica_size++;
				if (Samerica_size==4){
					sam_reinforce =  GameData.south_america;
				}
				else{
					sam_reinforce = 0;
				}


			}
			if (j > 35 && j < 42 && (current_player == territory_list.get(j).getPlayer())) {
				Af_size++;
				if (Af_size==6){
					af_reinforce =  GameData.africa;
				}
				else{
					af_reinforce = 0;
				}

			}

		}

		int continent_reinforce= nam_reinforce+eu_reinforce + as_reinforce + aus_reinforce +sam_reinforce+ af_reinforce;

		int total_reinforcements=continent_reinforce+ country_reinforce;
		//interfaceFrame.displayString(player_list.get(i).getName() + ", has " + Namerica_size + " size of america");
		//interfaceFrame.displayString(player_list.get(i).getName() + ", has " + Euro_size + " size of europe");
		//interfaceFrame.displayString(player_list.get(i).getName() + ", has " + Asia_size + " size of asia");
		//interfaceFrame.displayString(player_list.get(i).getName() + ", has " + Aus_size + " size of austalia");
		//interfaceFrame.displayString(player_list.get(i).getName() + ", has " + Samerica_size + " size of Southamerica");
		//interfaceFrame.displayString(player_list.get(i).getName() + ", has " + Af_size + " size of Africa");

		interfaceFrame.displayString(player_list.get(current_player).getName() + ", has " + continent_reinforce + " bonus reinforcements.");
		return total_reinforcements;
	}

	
	public  void check_HumanWinner(List<Player> player_list){
		for (int i=0;i<GameData.NUM_PLAYERS;i++){
			if (i==0 && player_list.get(i).ownedTerritoriesSize()==0){
				interfaceFrame.displayString(player_list.get(i+1).getName() + " is the winner!");
				interfaceFrame.displayString( "GAME OVER");
				try {
					TimeUnit.SECONDS.sleep(5);
				} 
				catch (InterruptedException e){
				}
				System.exit(0);

			}
			if (i==1 && player_list.get(i).ownedTerritoriesSize()==0){
				interfaceFrame.displayString(player_list.get(i-1).getName() + " is the winner!");
				interfaceFrame.displayString( "GAME OVER");
				try {
					TimeUnit.SECONDS.sleep(5);
				} 
				catch (InterruptedException e){
				}
				System.exit(0);
			}
		}
	}

	
	public void removePlayer(List<Player> player_list){
		try{		
			for(int i=2;i<GameData.NUM_PLAYERS_PLUS_NEUTRALS;i++){
				if(player_list.get(i).ownedTerritoriesSize()==0){
					interfaceFrame.displayString(player_list.get(i).getName() + " has been eliminated"); 
					break;
				}				
			}
		}
		catch(ArrayIndexOutOfBoundsException e){
		} 			
	}

	public static List<Territory> buildCards(List<Integer> shuffled_deck) {
		List<Territory> card_list = new ArrayList<Territory>();
		Territory current_card = null;
		
		for(int i=0; i<44; i++) {
			current_card = new Territory(shuffled_deck.get(i), GameData.CARD_NAMES[shuffled_deck.get(i)], GameData.INSIGNIA_TYPE[shuffled_deck.get(i)].substring(0, 1));
			
			current_card.setInsignia(GameData.INSIGNIA_TYPE[shuffled_deck.get(i)]);
			
			card_list.add(current_card);
			
		}
		return card_list;
	}

	
	public static List<Integer> shuffleDeck() {
		List<Integer> deck = new ArrayList<Integer>();

		for (int i=0; i<44; i++) {
			deck.add(i);
		}
		
		Collections.shuffle(deck);
		return deck;
	}

}
