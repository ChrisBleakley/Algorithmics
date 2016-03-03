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
	List<Integer> owned_territories_List = new ArrayList<Integer>();;
	
	
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
		owned_territories_List.remove(territory_input);
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
}
