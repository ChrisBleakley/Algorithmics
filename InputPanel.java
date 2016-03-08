/* Algorithmics
 * 14708689 Orla Cullen
 * 05641349 Gavin Keaveney
 * 14343826 Jonathan Sweeney 
 */

import java.awt.event.ActionEvent;
import java.util.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

public class InputPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int FONT_SIZE = 14;
	private JTextField commandField = new JTextField();
	private LinkedList<String> commandBuffer = new LinkedList<String>();
	private JPanel buttonPanel = new JPanel();
	InputPanel() {
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
	        JButton drawButton= new JButton("draw");
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

		ActionListener listener = new AddActionListener();
		ActionListener drawlisten = new DrawActionListener();
		ActionListener rolllisten = new RollActionListener();
		rollButton.addActionListener(rolllisten);
		drawButton.addActionListener(drawlisten);
		commandField.addActionListener(listener);
		commandField.setFont(new Font("Times New Roman", Font.PLAIN, FONT_SIZE));
		setLayout(new BorderLayout());
		commandField.setBackground(Color.WHITE);
		add(commandField, BorderLayout.CENTER);
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
