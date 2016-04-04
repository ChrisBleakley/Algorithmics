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
	List<Integer> owned_territories_List = new ArrayList<Integer>();
	List<Integer> owned_cards_List = new ArrayList<Integer>();
	
	Player(int input_player_index, String input_name){
		player_index = input_player_index;
		name = input_name;
	}
	
	void setArmies(int army_input){
		army_units += army_input;
	}
	
	void addOwnedTerritory(int territory_input){
		owned_territories_List.add(territory_input);
	}
	
	void removeOwnedTerritory(int territory_input){
		for(int i=0; i<owned_territories_List.size(); i++){
			if(owned_territories_List.get(i) == territory_input){
				owned_territories_List.remove(i);
			}
		}
	}
	
	int getArmies(){
		return army_units;
	}
	
	int getOwnedTerritory(int i){
		return owned_territories_List.get(i);
	}
	
	int getPlayer(){
		return player_index;
	}
	
	String getName(){
		return name;
	}

	public int ownedTerritoriesSize() {
		return owned_territories_List.size();
		 
	}
	int getPlayer(int i){
		return player_index;
	}	
	void addOwnedCard(int card_input){
		owned_cards_List.add(card_input);
	}
	
	void removeOwnedCard(int card_input){
		for(int i=0; i<owned_cards_List.size(); i++){
			if(owned_cards_List.get(i) == card_input){
				owned_cards_List.remove(i);
			}
		}
	}
	int getOwnedCard(int i){
		return owned_cards_List.get(i);
	}
	public int ownedCardsSize() {
		return owned_cards_List.size();
		 
	}
}
