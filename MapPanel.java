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
	Graphics2D g2d;
	public List<Territory> territories_list;
	private static final long serialVersionUID = 1L;
		BufferedImage image;
		 MapPanel (List<Territory> territory_list) {
			 territories_list = territory_list;
	
		}
	
public void paintComponent(Graphics grphcs) {
			
			super.paintComponent(grphcs);
				g2d = (Graphics2D) grphcs;
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
				showArmyNumber(g2d, territories_list);
				showCountryOwnership(g2d, territories_list);
		
		}
		
		public void paintNodes(Graphics2D g2d){
			for(int i=0;i<GameData.NUM_COUNTRIES;i++){	
				switch (GameData.CONTINENT_IDS[i]) {
				case 0:  g2d.setColor(Color.yellow);
					break;
				case 1:  g2d.setColor(Color.blue);
               		break;
				case 2:  g2d.setColor(Color.green);
                	break;
				case 3:  g2d.setColor(Color.orange);
					break;
				case 4:  g2d.setColor(Color.red);
					break;
				case 5:  g2d.setColor(Color.MAGENTA);
					break;
				}	
					g2d.fillOval(GameData.COUNTRY_COORD[i][0] - 10,GameData.COUNTRY_COORD[i][1] - 10,20,20);
			}
		}
		
		
		public void paintConnectingLines(Graphics2D g2d){
			g2d.setColor(Color.black);
			for(int i=0;i<GameData.NUM_COUNTRIES;i++){	
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
			for(int i=0;i<GameData.NUM_COUNTRIES;i++){
				String country_tag = GameData.COUNTRY_NAMES[i] + " (" + GameData.SHORT_COUNTRY_NAMES[i] + ") ";
				 g2d.drawString(country_tag, GameData.COUNTRY_COORD[i][0] - (2*country_tag.length() +10),GameData.COUNTRY_COORD[i][1] - 15 );
			}
		}
		
		
		
		public void showArmyNumber(Graphics2D g2d, List<Territory> territories_list){
			Font font = new Font("Serif", Font.BOLD, 16);
			g2d.setColor(Color.BLACK);
			g2d.setFont(font);
			for(int i=0;i<GameData.NUM_COUNTRIES;i++){
				String current_armies = Integer.toString(territories_list.get(i).getArmies());
				g2d.drawString(current_armies ,GameData.COUNTRY_COORD[territories_list.get(i).getNode()][0] -3 ,GameData.COUNTRY_COORD[territories_list.get(i).getNode()][1] + 25 );	
			}
		}
		
		public void	showCountryOwnership(Graphics2D g2d, List<Territory> territories_list){
			for(int i=0;i<GameData.NUM_COUNTRIES;i++){	
				switch (territories_list.get(i).getPlayer()) {
				case -1:  g2d.setColor(Color.WHITE);
						 break;
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
					g2d.fillOval(GameData.COUNTRY_COORD[i][0] - 6,GameData.COUNTRY_COORD[i][1] - 6,12,12);
			}
			
		}

		public void refresh() {
			revalidate();
			repaint();
			return;
		}
	}
	

	
		
