
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class CreateSplitFrameGUI extends JFrame implements UserInterface {

	private JSplitPane verticalSplit;
	private JSplitPane horizontalSplit;
	private JPanel textPanel;
	private JPanel graphPanel;
	private JPanel userInputPanel;

	private static final int FRAME_WIDTH = 1000; // must be even
	private static final int FRAME_HEIGHT = 600;

	public CreateSplitFrameGUI() {// constructor
		setTitle("WELCOME TO RISK");
		setBackground(Color.yellow);

		JPanel upperPanel = new JPanel();// create instance of JPanel
		upperPanel.setLayout(new BorderLayout());// setting the layout to Border
		/* gets the container for the content and add the JPanel */
		getContentPane().add(upperPanel);

		createTextDisplay();// Calling the function to create Text Display Panel
		createGraphPanel();// Calling the function that creates the Graph Panel
		createUserInputDialog();// Calling the function that creates the
								// UserInput Panel
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
		//
		horizontalSplit.setLeftComponent(textPanel);// sets the vertical split
													// while passing the
		/*
		 * sets the vertical split while passing the graphpanel into the right
		 * most panel of frame
		 */
		horizontalSplit.setRightComponent(graphPanel);
		verticalSplit.setLeftComponent(horizontalSplit);
		verticalSplit.setRightComponent(userInputPanel);// sets the userInput panel as the horizontal split
	}

	public void createTextDisplay() {

	}

	public void createGraphPanel() {

	}

	public void createUserInputDialog() {

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
