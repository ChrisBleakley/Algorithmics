/*
 * Algorithmics
 * 14708689 Orla Cullen
 * 05641349 Gavin Keaveney
 * 14343826 Jonathan Sweeney
 * 
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JSplitPane;

import javax.swing.JTextField;


@SuppressWarnings("serial")
public class CreateSplitFrameGUI extends JFrame  implements UserInterface {

	private JSplitPane verticalSplit;
	private JSplitPane horizontalSplit;
	private JPanel textPanel;
	private JPanel graphPanel;
	private JPanel userInputPanel;

	private static final int FRAME_WIDTH = 1000; // must be even
	private static final int FRAME_HEIGHT = 600;

	private String play1 = "Player1";
	private String play2 = "Player2";

	public CreateSplitFrameGUI() {// constructor
		setTitle("WELCOME TO RISK");
		setBackground(Color.yellow);

		JPanel upperPanel = new JPanel();// create instance of JPanel
		upperPanel.setLayout(new BorderLayout());// setting the layout to Border
		/* gets the container for the content and add the JPanel */
		getContentPane().add(upperPanel);

		createTextDisplay();// Calling the function to create Text Display Panel
		createGraphPanel();// Calling the function that creates the Graph Panel
		createUserInputDialog();// Calling the function that creates UserInput Panel

		/*
		 * The following code creates the split in the frame creates the
		 * horizontal and the vertical split
		 */

		verticalSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		horizontalSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

		/*
		 * adds the split to the container and sets the layout to border and
		 * center
		 */

		upperPanel.add(verticalSplit, BorderLayout.CENTER);
		/*
		 * sets the vertical split while passing the textpanel into the left
		 * most panel of frame
		 */

		horizontalSplit.setLeftComponent(textPanel);
		/*
		 * sets the vertical split while passing the graphpanel into the right
		 * most panel of frame
		 */
		horizontalSplit.setRightComponent(graphPanel);
		verticalSplit.setLeftComponent(horizontalSplit);
		verticalSplit.setRightComponent(userInputPanel);// sets the userInput panel as the horizontal split
	}

	public void createTextDisplay() {
		textPanel = new JPanel();

		JScrollBar menuBar = new JScrollBar();
		menuBar.setBounds(0, 0, 30, 30);
		//menuBar.add(new JLabel("Text Display Area"));
		textPanel.add(menuBar, BorderLayout.EAST);//menubar added

		textPanel.setLayout(new BorderLayout());
		textPanel.setPreferredSize(new Dimension(200, 100));

		textPanel.setBackground(Color.white);
	}

	public void createGraphPanel() {
		graphPanel = new JPanel();
		graphPanel.setLayout(new FlowLayout());
		graphPanel.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));// size of graph area

		graphPanel.add(new JLabel("Insert Graph Here:"), BorderLayout.NORTH);
		graphPanel.setBackground(Color.white);

	}

	public void assignName(String name, int i) {

		if (i==0){
			play1=name;
		}
		else if (i==1){
			play2=name;
		}
	}


	public void createUserInputDialog() {

		userInputPanel = new JPanel();
		userInputPanel.setLayout(new BorderLayout());
		userInputPanel.setPreferredSize(new Dimension(100, 50));
		userInputPanel.setMinimumSize(new Dimension(100, 50));

		JLabel label = new JLabel("Welcome to Risk");
		userInputPanel.add(label, BorderLayout.NORTH);
		userInputPanel.setBackground(Color.yellow);

		JTextField textArea = new JTextField();
		userInputPanel.add(textArea, BorderLayout.CENTER);
		JButton button = new JButton("Enter Player Names");
		userInputPanel.add(button, BorderLayout.WEST);

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				play1 = JOptionPane.showInputDialog(null, "Enter Player 1 Name", "Risk", JOptionPane.INFORMATION_MESSAGE);
				play2 = JOptionPane.showInputDialog(null, "Enter Player 2 Name", "Risk", JOptionPane.INFORMATION_MESSAGE);
				button.setText("Submit");
				label.setText("Welcome to Risk " + play1 + " & " + play2);
			}
		});      
	}

	public static void main(String[] args) {
		CreateSplitFrameGUI interfaceFrame = new CreateSplitFrameGUI();
		interfaceFrame.pack();
		interfaceFrame.setVisible(true);

	}

	public static int getFrameHeight() {//getter for the frame height
		return FRAME_HEIGHT;
	}

	public static int getFrameWidth() {//getter for the frame width
		return FRAME_WIDTH;
	}
}
