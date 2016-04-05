
/*
 * Algorithmics
 * 14708689 Orla Cullen
 * 05641349 Gavin Keaveney
 * 14343826 Jonathan Sweeney
 * 
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class SplitFrameGUI extends JFrame  {

	private static final long serialVersionUID = 1L;
	private JSplitPane verticalSplit;
	private JSplitPane horizontalSplit;
	private JPanel outputPanel;
	private JPanel mapPanel;
	private JPanel inputPanel; 


	public SplitFrameGUI(MapPanel mapPanel, MouseArea mouseArea) {

		setTitle("WELCOME TO RISK");
		JPanel upperPanel = new JPanel();
		
		outputPanel= new OutputPanel();
		inputPanel= new InputPanel(mouseArea);
		mapPanel.setPreferredSize(new Dimension(GameData.FRAME_WIDTH, GameData.FRAME_HEIGHT));
		inputPanel.setPreferredSize(new Dimension(GameData.FRAME_WIDTH, 50));
		upperPanel.setLayout(new BorderLayout());
		getContentPane().add(upperPanel);
		
		verticalSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		horizontalSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

		/*
		 * adds the split to the container and sets the layout to border and
		 * center
		 */

		upperPanel.add(verticalSplit, BorderLayout.EAST);
		horizontalSplit.setLeftComponent(outputPanel);
		horizontalSplit.setRightComponent(mapPanel);
		verticalSplit.setLeftComponent(horizontalSplit);
		verticalSplit.setRightComponent(inputPanel);
		/*adds menu to Frame
		 * 
		 */
		JMenuBar menuBar = new JMenuBar();
		JMenu gameMenu = new JMenu("Game");
		menuBar.add(gameMenu);
		setJMenuBar(menuBar);
		JMenu about = new JMenu("About");
        gameMenu.add(about);
		JMenuItem help = new JMenu("Help");
        gameMenu.add(help);
		JMenuItem score = new JMenu("ScoreBoard");
		gameMenu.add(score);
        // Add an JMenuItem
        JMenuItem exit = new JMenu("Exit");
        gameMenu.add(exit);
            
        JButton aboutButton= new JButton("About");
        about.add(aboutButton);
        aboutButton.setSize(60,60);
        aboutButton.addActionListener(new ActionListener() {
        	 
            public void actionPerformed(ActionEvent e)
            {
            	String message="This code was wriiten by the Algorithmics";
               JOptionPane.showMessageDialog(null, message);
            }
        }); 
        JButton helpButton= new JButton("Help");
        help.add(helpButton);
        helpButton.setSize(60,60);
        helpButton.addActionListener(new ActionListener() {
        	 
            public void actionPerformed(ActionEvent e)
            {
            	String message="For ease of use the map can be clicked on\n "
            			+ "the nodes to input the select the countrys also \n"
            			+ "incorporated is the roll and draw button when\n "
            			+ "asked to enter these commands you can click \n"
            			+ "the required button as an alternative\n";
               JOptionPane.showMessageDialog(mapPanel, message);
            }
        });  
        
        JButton exitButton= new JButton("Close");
        exit.add(exitButton);
        exitButton.setSize(40,40);
        exitButton.addActionListener(new ActionListener() {
        	 
            public void actionPerformed(ActionEvent e)
            {
                
                System.exit(0);
            }
        });    
		
		setResizable(false);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		}

		);
		verticalSplit.setRightComponent(inputPanel);
	}


	public String getCommand() {
		String last_input =((InputPanel) inputPanel).getCommand();
		displayString("> " + last_input);
		return  last_input;
	}

	public void displayMap() {
		((MapPanel) mapPanel).refresh();
		return;
	}

	public void displayString(String string) {
		 ((OutputPanel) outputPanel).addText(string);
		return;
	}


}
