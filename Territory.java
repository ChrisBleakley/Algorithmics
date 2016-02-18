public class Territory {

	public int player;
	public int node;
	public int army_units;
	
	Territory(int input_node){
		node = input_node;
	}
	
	void setArmies(int army_input){
		army_units = army_input;
	}
	
	void setPlayer(int player_input){
		player = player_input;
	}
	
	int getArmies(){
		return army_units;
	}
	
	int getPlayer(){
		return player;
	}
	
	int getNode(){
		return node;
	}
}
