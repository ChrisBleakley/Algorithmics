import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

/* Algorithmics
 * 14708689 Orla Cullen
 * 05641349 Gavin Keaveney
 * 14343826 Jonathan Sweeney 
 */
public class OutputPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private static final int TEXT_AREA_HEIGHT =5;
	private static final int CHARACTER_WIDTH =15;
	private static final int FONT_SIZE = 14;
	JTextArea textArea = new JTextArea(TEXT_AREA_HEIGHT, CHARACTER_WIDTH);
	JScrollPane scrollPane = new JScrollPane(textArea);
	DefaultCaret caret = (DefaultCaret)textArea.getCaret();
	
	OutputPanel () {
		/*	JScrollBar scrollBar = new JScrollBar();
		JTextArea displayArea = new JTextArea();
		JMenuBar menuBar= new JMenuBar();
		JMenu gameMenu= new JMenu("Game");
		
		menuBar.add(gameMenu);
		setJMenuBar(menuBar);
		
		JMenu gameMenu1 = new JMenu("Play Game");
		gameMenu.add( gameMenu1);
		JMenu gameMenu2 = new JMenu("Help");
		gameMenu.add( gameMenu2);
		JMenu gameMenu3 = new JMenu("ScoreBoard");
		gameMenu.add( gameMenu3);
		JMenu gameMenu4 = new JMenu("Exit Game");	
		gameMenu.add( gameMenu4);*/
		
	
		textArea.setEditable(false);
		textArea.setPreferredSize(new Dimension(100, 100));
		textArea.setFont(new Font("Times New Roman", Font.PLAIN, FONT_SIZE));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		textArea.setBackground(Color.white);
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		setLayout(new BorderLayout());
		add(scrollPane, BorderLayout.CENTER);
		return;
	}
	
	private void setJMenuBar(final JMenuBar menuBar, final BorderLayout borderLayout) {
		return ;
		
	}

	public void addText (String text) {
		textArea.setText(textArea.getText()+"\n"+text);
	}

}
