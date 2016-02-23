
/*
 * Algorithmics
 * 14708689 Orla Cullen
 * 05641349 Gavin Keaveney
 * 14343826 Jonathan Sweeney
 * 
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class SplitFrameGUI extends JFrame  {

	private static final long serialVersionUID = 1L;
	private JSplitPane verticalSplit;
	private JSplitPane horizontalSplit;
	private JPanel textPanel= new OutputPanel();
	private JPanel graphPanel= new MapPanel();
	private JPanel userInputPanel= new InputPanel(); 

	

	public SplitFrameGUI() {

		setTitle("WELCOME TO RISK");
		JPanel upperPanel = new JPanel();
		graphPanel.setPreferredSize(new Dimension(GameData.FRAME_WIDTH, GameData.FRAME_HEIGHT));
		upperPanel.setLayout(new BorderLayout());
		getContentPane().add(upperPanel);
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
		verticalSplit.setRightComponent(userInputPanel);
		/*adds menu to Frame
		 * 
		 */
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
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		}

		);
		verticalSplit.setRightComponent(userInputPanel);
	}


	public String getCommand() {
		return  ((InputPanel) userInputPanel).getCommand();
	}

	public void displayMap() {
		((MapPanel) graphPanel).refresh();
		return;
	}

	public void displayString(String string) {
		 ((OutputPanel) textPanel).addText(string);
		return;
	}


}
