/* Algorithmics
 * 14708689 Orla Cullen
 * 05641349 Gavin Keaveney
 * 14343826 Jonathan Sweeney 
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InputPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int FONT_SIZE = 14;
	private JTextField commandField = new JTextField();
	private LinkedList<String> commandBuffer = new LinkedList<String>();
	private JPanel buttonPanel = new JPanel();

	InputPanel(MouseArea mouseArea) {

		JButton rollButton= new JButton("Roll");
		rollButton.setSize(30,30);
		class RollActionListener implements ActionListener  {
			public void actionPerformed(ActionEvent event) {
				synchronized (commandBuffer) {
					String command= "roll";
					commandBuffer.add(command);
					commandField.setText("");
					commandBuffer.notify();
				}
				return;
			}
		}

		JButton drawButton= new JButton("Draw");
		drawButton.setSize(30,30);
		class DrawActionListener implements ActionListener  {
			public void actionPerformed(ActionEvent event) {
				synchronized (commandBuffer) {
					String draw="draw";
					commandBuffer.add(draw);
					commandField.setText("");
					commandBuffer.notify();
				}
				return;
			}
		}

		class AddActionListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				synchronized (commandBuffer) {
					commandBuffer.add(commandField.getText());
					commandField.setText("");
					commandBuffer.notify();
				}
				return;
			}
		}
		
		class MouseAreaListener implements MouseListener {
			public void mouseClicked(MouseEvent mouseEvent) {
				synchronized (commandBuffer) {
					for(int i=0; i<GameData.NUM_COUNTRIES; i++){
						if( Math.pow((mouseEvent.getX() - GameData.COUNTRY_COORD[i][0]), 2) + Math.pow((mouseEvent.getY() - GameData.COUNTRY_COORD[i][1]), 2) < 100 ){
							String a = GameData.COUNTRY_NAMES[i];
							commandBuffer.add(a);
							commandField.setText("");
							commandBuffer.notify();
						}
					}
				}
			}

			public void mouseEntered(MouseEvent mouseEvent) {
			}

			public void mouseExited(MouseEvent mouseEvent) {
			}

			public void mousePressed(MouseEvent mouseEvent) {
			}

			public void mouseReleased(MouseEvent mouseEvent) {
			}			
		}
		
		MouseListener maplisten = new MouseAreaListener();
		mouseArea.addMouseListener(maplisten);
		addMouseListener(maplisten);
		
		ActionListener listener = new AddActionListener();
		commandField.addActionListener(listener);
		commandField.setFont(new Font("Times New Roman", Font.PLAIN, FONT_SIZE));
		setLayout(new BorderLayout());
		commandField.setBackground(Color.WHITE);
		add(commandField, BorderLayout.CENTER);

		ActionListener drawlisten = new DrawActionListener();
		ActionListener rolllisten = new RollActionListener();
		rollButton.addActionListener(rolllisten);
		drawButton.addActionListener(drawlisten);
		add(buttonPanel, BorderLayout.WEST);
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(rollButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(20, 20)));
		buttonPanel.add(drawButton);

		return;
	}

	public String getCommand() {
		String command;
		synchronized (commandBuffer) {
			while (commandBuffer.isEmpty()) {
				try {
					commandBuffer.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			command = commandBuffer.pop();
		}
		return command;
	}

}
