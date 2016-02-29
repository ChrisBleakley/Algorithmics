/* Algorithmics
 * 14708689 Orla Cullen
 * 05641349 Gavin Keaveney
 * 14343826 Jonathan Sweeney 
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class MapPanel extends JPanel{

	private static final long serialVersionUID = 1L;
		BufferedImage image;
		MapPanel () {
			
		}
	
public void paintComponent(Graphics grphcs) {
			
			super.paintComponent(grphcs);
				Graphics2D g2d = (Graphics2D) grphcs;
				try {
					image = ImageIO.read(new File("risk.jpg"));
					
				}
				catch (IOException ioe) {
					System.out.println("Could not read in the pic");
				}
				g2d.drawImage(image,0,0,this);
				paintNodes(g2d);
				paintConnectingLines(g2d);
				showCountryNames(g2d);
				buildTerritories(g2d);
		}
		
		public void paintNodes(Graphics2D g2d){
			for(int i=0;i<42;i++){	
				switch (GameData.CONTINENT_IDS[i]) {
				case 0:  g2d.setColor(GameData.MY_ORANGE);
                		 break;
	            case 1:  g2d.setColor(GameData.MY_BLUE);
	                     break;
	            case 2:  g2d.setColor(GameData.MY_GREEN);
	                     break;
	            case 3:  g2d.setColor(GameData.MY_PURPLE);
	                     break;
	            case 4:  g2d.setColor(GameData.MY_RED);
	                     break;
	            case 5:  g2d.setColor(GameData.MY_BROWN);
	                     break;
				}
					g2d.fillOval(GameData.COUNTRY_COORD[i][0] - 10,GameData.COUNTRY_COORD[i][1] - 10,20,20);
			}	
		}
		
		
		public void paintConnectingLines(Graphics2D g2d){
			g2d.setColor(Color.black);
			for(int i=0;i<42;i++){	
				for(int j=0;j<6;j++){	
					if((i!=8 || j!= 2)&& (i!=22 || j!= 0)){
						try{
							g2d.drawLine(GameData.COUNTRY_COORD[i][0], GameData.COUNTRY_COORD[i][1],
									GameData.COUNTRY_COORD[GameData.ADJACENT[i][j]][0],
									GameData.COUNTRY_COORD[GameData.ADJACENT[i][j]][1]);
						}
						catch(ArrayIndexOutOfBoundsException e){
							break;
						}
					}
					else{
						g2d.drawLine(GameData.COUNTRY_COORD[8][0], GameData.COUNTRY_COORD[8][1], -50,  GameData.COUNTRY_COORD[22][1]);
						g2d.drawLine(GameData.COUNTRY_COORD[22][0],GameData.COUNTRY_COORD[22][1], 1050, GameData.COUNTRY_COORD[8][1]);
					}
				}
			}
		}
		
		public void showCountryNames(Graphics2D g2d){
			Font font = new Font("Serif", Font.BOLD, 16);
		    g2d.setFont(font);
			for(int i=0;i<42;i++){
				 g2d.drawString(GameData.COUNTRY_NAMES[i],GameData.COUNTRY_COORD[i][0] - (2*GameData.COUNTRY_NAMES[i].length() +10),GameData.COUNTRY_COORD[i][1] - 15 );
			}
		}
		
		public List<Territory> buildTerritories(Graphics2D g2d){
			List<Territory> territory_list= new ArrayList<Territory>();
			int current_player = 0;
			for(int i=0;i<42;i++){
				Territory current_territory = new Territory(i);
				current_territory.setArmies(1);
				
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
				current_territory.setPlayer(current_player);
				showArmyNumber(g2d, current_territory);
					
		        territory_list.add(current_territory);
			}
			 return territory_list;
		}
	        
		
		
		public void showArmyNumber(Graphics2D g2d, Territory input_territory){
			Font font = new Font("Serif", Font.BOLD, 16);
			g2d.setFont(font);
			switch (input_territory.getPlayer()) {
			case 0:  g2d.setColor(Color.BLUE);
            		 break;
            case 1:  g2d.setColor(Color.RED);
                     break;
            case 2:  g2d.setColor(Color.YELLOW);
                     break;
            case 3:  g2d.setColor(Color.CYAN);
                     break;
            case 4:  g2d.setColor(Color.GREEN);
                     break;
            case 5:  g2d.setColor(Color.MAGENTA);
                     break;
			}
			String current_armies = Integer.toString(input_territory.getArmies());
			g2d.drawString(current_armies ,GameData.COUNTRY_COORD[input_territory.getNode()][0] -3 ,GameData.COUNTRY_COORD[input_territory.getNode()][1] + 25 );	
		}

		public void refresh() {
			revalidate();
			repaint();
			return;
		}
		}
	

	
		
