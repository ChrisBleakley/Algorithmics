import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javax.swing.JPanel;

public class Map extends JPanel{
	Map(){
		
	}

	public static final int[] CONTINENT_IDS = {0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,2,2,2,2,3,3,3,3,4,4,4,4,5,5,5,5,5,5};
	
	public static final String[] COUNTRY_NAMES = {
			"Ontario","Quebec","NW Territory","Alberta","Greenland","E United States","W United States","Central America","Alaska",
			"Great Britain","W Europe","S Europe","Ukraine","N Europe","Iceland","Scandinavia",
			"Afghanistan","India","Middle East","Japan","Ural","Yakutsk","Kamchatka","Siam","Irkutsk","Siberia","Mongolia","China",
			"E Australia","New Guinea","W Australia","Indonesia",
			"Venezuela","Peru","Brazil","Argentina",
			"Congo","N Africa","S Africa","Egypt","E Africa","Madagascar"};  // for reference
	
		public static final int[][] ADJACENT = { 
			{4,1,5,6,3,2},    // 0
			{4,5,0},
			{4,0,3,8},
			{2,0,6,8},
			{14,1,0,2},
			{0,1,7,6}, 
			{3,0,5,7},
			{6,5,32},
			{2,3,22},
			{14,15,13,10},    
			{9,13,11,37},     // 10
			{13,12,18,39,10},
			{20,16,18,11,13,15},
			{15,12,11,10,9},
			{15,9,4},
			{12,13,14},
			{20,27,17,18,12}, 
			{16,27,23,18},
			{12,16,17,40,39,11},
			{26,22},
			{25,27,16,12},    // 20
			{22,24,25},        
			{8,19,26,24,21},
			{27,31,17},
			{21,22,26,25},
			{21,24,26,27,20},
			{24,22,19,27,25},
			{26,23,17,16,20,25},
			{29,30},          
			{28,30,31},
			{29,28,31},      // 30
			{23,29,30},
			{7,34,33},       
			{32,34,35},
			{32,37,35,33},
			{33,34},
			{37,40,38},      
			{10,11,39,40,36,34},
			{36,40,41},
			{11,18,40,37},
			{39,18,41,38,36,37},  //40
			{38,40}
		};
		
		private static final int[][] COUNTRY_COORD = {
				{191,150},     // 0
				{255,161},
				{146,86},
				{123,144},
				{314,61},
				{205,235},
				{135,219},
				{140,299},
				{45,89},
				{370,199},
				{398,280},      // 10
				{465,270},
				{547,180},
				{460,200},
				{393,127},
				{463,122},
				{628,227},
				{679,332},
				{572,338},
				{861,213},
				{645,152},      // 20
				{763,70},
				{827,94},
				{751,360},
				{750,140},
				{695,108},
				{760,216},
				{735,277},
				{889,537},
				{850,429},
				{813,526},       // 30
				{771,454},
				{213,352},
				{221,426},
				{289,415},
				{233,523},
				{496,462},
				{440,393},
				{510,532},
				{499,354},
				{547,432},        // 40
				{586,545}
			};
		private BufferedImage image;
		public void paintComponent(Graphics grphcs) {
			
			super.paintComponent(grphcs);
				Graphics2D g2d = (Graphics2D) grphcs;
				 try {
			            image = ImageIO.read(new File("src\\risk.jpg"));
			            

			        } catch (IOException ioe) {
			            System.out.println("Could not read in the pic");
			            //System.exit(0);
			        }
				g2d.drawImage(image,0,0,this);
				paintNodes(g2d);
				paintConnectingLines(g2d);
				showCountryNames(g2d);
		}
		
		public void paintNodes(Graphics2D g2d){
			for(int i=0;i<42;i++){	
				switch (CONTINENT_IDS[i]) {
				case 0:  g2d.setColor(Color.yellow);
                		 break;
	            case 1:  g2d.setColor(Color.blue);
	                     break;
	            case 2:  g2d.setColor(Color.green);
	                     break;
	            case 3:  g2d.setColor(Color.magenta);
	                     break;
	            case 4:  g2d.setColor(Color.red);
	                     break;
	            case 5:  g2d.setColor(Color.cyan);
	                     break;
			}
					g2d.fillOval(COUNTRY_COORD[i][0] - 10,COUNTRY_COORD[i][1] - 10,20,20);
				}	
		}
		
		
		public void paintConnectingLines(Graphics2D g2d){
			g2d.setColor(Color.black);
			for(int i=0;i<42;i++){	
				for(int j=0;j<6;j++){	
					if((i!=8 || j!= 2)&& (i!=22 || j!= 0)){
						try{
							g2d.drawLine(COUNTRY_COORD[i][0], COUNTRY_COORD[i][1], COUNTRY_COORD[ADJACENT[i][j]][0], COUNTRY_COORD[ADJACENT[i][j]][1]);
						}
						catch(ArrayIndexOutOfBoundsException e){
							break;
						}
					}
					else{
						g2d.drawLine(COUNTRY_COORD[8][0], COUNTRY_COORD[8][1], -50,  COUNTRY_COORD[22][1]);
						g2d.drawLine(COUNTRY_COORD[22][0], COUNTRY_COORD[22][1], 1050,  COUNTRY_COORD[8][1]);
					}
				}
			}
		}
		
		public void showCountryNames(Graphics2D g2d){
			Font font = new Font("Serif", Font.BOLD, 16);
		    g2d.setFont(font);
			for(int i=0;i<42;i++){
			
				 g2d.drawString(COUNTRY_NAMES[i],COUNTRY_COORD[i][0] - (2*COUNTRY_NAMES[i].length() +10) ,COUNTRY_COORD[i][1] - 15 );	
			

			}
		}
		
		public void showArmyNumber(Graphics2D g2d){
			for(int i=0;i<42;i++){
				
			}
			
		}
		

}

	
		
