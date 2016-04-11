import java.util.ArrayList;
import java.util.List;

/* Algorithmics
 * 14708689 Orla Cullen
 * 05641349 Gavin Keaveney
 * 14343826 Jonathan Sweeney 
 */

public class Player {
	public int player_index;
	public int army_units;
	public String name;
	List<Integer> owned_territories_list = new ArrayList<Integer>();
	List<Territory> owned_cards_list = new ArrayList<Territory>();
	
	Player(int input_player_index, String input_name){
		player_index = input_player_index;
		name = input_name;
	}
	
	void setArmies(int army_input){
		army_units += army_input;
	}
	
	void addOwnedTerritory(int territory_input){
		owned_territories_list.add(territory_input);
	}
	
	void removeOwnedTerritory(int territory_input){
		for(int i=0; i<owned_territories_list.size(); i++){
			if(owned_territories_list.get(i) == territory_input){
				owned_territories_list.remove(i);
			}
		}
	}
	
	int getArmies(){
		return army_units;
	}
	
	int getOwnedTerritory(int i){
		return owned_territories_list.get(i);
	}
	
	int getPlayer(){
		return player_index;
	}
	
	String getName(){
		return name;
	}

	public int ownedTerritoriesSize() {
		return owned_territories_list.size();
	}	
	
	void addOwnedCard(Territory card_input){
		owned_cards_list.add(card_input);
	}
	
	void removeOwnedCard(int card_input){
		owned_cards_list.remove(card_input);
	}
	
	Territory getOwnedCard(int i){
		return owned_cards_list.get(i);
	}
	
	public int ownedCardsSize() {
		return owned_cards_list.size();
		 
	}
}
