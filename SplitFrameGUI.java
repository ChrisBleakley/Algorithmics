
/*
 * Algorithmics
 * 14708689 Orla Cullen
 * 05641349 Gavin Keaveney
 * 14343826 Jonathan Sweeney
 * 
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

@SuppressWarnings("serial")
public class SplitFrameGUI extends JFrame implements UserInterface {

	private JSplitPane verticalSplit;
	private JSplitPane horizontalSplit;
	private JPanel textPanel;
	private JPanel graphPanel;
	private JPanel userInputPanel;

	private static final int FRAME_WIDTH = 1000; // must be even
	private static final int FRAME_HEIGHT = 600;

	public SplitFrameGUI() {

		setTitle("WELCOME TO RISK");
		JPanel upperPanel = new JPanel();
		upperPanel.setLayout(new BorderLayout());
		getContentPane().add(upperPanel);

		createTextDisplay();
		createGraphPanel();
		createUserInputDialog();

		verticalSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		horizontalSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

		/*
		 * adds the split to the container and sets the layout to border and
		 * center
		 */

		upperPanel.add(verticalSplit, BorderLayout.CENTER);
		horizontalSplit.setLeftComponent(textPanel);
		horizontalSplit.setRightComponent(graphPanel);
		verticalSplit.setLeftComponent(horizontalSplit);
		verticalSplit.setRightComponent(userInputPanel);// sets the userInput
														// panel as the
														// horizontal split

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		}

		);
		verticalSplit.setRightComponent(userInputPanel);
	}

	public void createTextDisplay() {
		JMenuBar menuBar = new JMenuBar();
		JMenu gameMenu = new JMenu("Game");

		menuBar.add(gameMenu);
		setJMenuBar(menuBar);

		JMenu gameMenu1 = new JMenu("Play Game");
		gameMenu.add(gameMenu1);
		JMenu gameMenu2 = new JMenu("Help");
		gameMenu.add(gameMenu2);
		JMenu gameMenu3 = new JMenu("ScoreBoard");
		gameMenu.add(gameMenu3);
		JMenu gameMenu4 = new JMenu("Exit Game");
		gameMenu.add(gameMenu4);
		textPanel = new OutputPanel();
	}

	public void createGraphPanel() {

		graphPanel = new Map();
		graphPanel.setLayout(new FlowLayout());
		graphPanel.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));

	}

	public void createUserInputDialog() {
		userInputPanel = new InputPanel();
	}

	public String getCommand() {
		return ((InputPanel) userInputPanel).getCommand();
	}

	public void displayMap() {
		((Map) graphPanel).refresh();
		return;
	}

	public void displayString(String string) {
		((OutputPanel) textPanel).addText(string);
		return;
	}

	public static int getFrameHeight() {// getter for the frame height
		return FRAME_HEIGHT;
	}

	public static int getFrameWidth() {// getter for the frame width
		return FRAME_WIDTH;
	}

}
