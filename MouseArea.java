import javax.swing.*;
import java.awt.Dimension;
import java.awt.Color;

 
public class MouseArea extends JLabel {
	private static final long serialVersionUID = 1L;
	
	Dimension minSize = new Dimension(GameData.FRAME_WIDTH, GameData.FRAME_HEIGHT);
 
    public MouseArea(Color color) {
        setBackground(color);
        setOpaque(false);
        setBorder(BorderFactory.createLineBorder(Color.black));
    }
}