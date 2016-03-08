/* Algorithmics
 * 14708689 Orla Cullen
 * 05641349 Gavin Keaveney
 * 14343826 Jonathan Sweeney 
 */
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class OutputPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int TEXT_AREA_HEIGHT = 5;
	private static final int CHARACTER_WIDTH = 17;
	private static final int FONT_SIZE = 12;
	JTextArea textArea = new JTextArea(TEXT_AREA_HEIGHT, CHARACTER_WIDTH);
	JScrollPane scrollPane = new JScrollPane(textArea);
	DefaultCaret caret = (DefaultCaret) textArea.getCaret();

	OutputPanel() {
		textArea.setEditable(false);
		textArea.setFont(new Font("Arial Black", Font.BOLD, FONT_SIZE));
		textArea.setForeground(Color.BLACK);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		textArea.setBackground(GameData.MY_CREAM);
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		setLayout(new BorderLayout());
		add(scrollPane, BorderLayout.CENTER);
		return;
	}

	public void addText(String text) {
		textArea.setText(textArea.getText() + "\n" + text);
	}

}
