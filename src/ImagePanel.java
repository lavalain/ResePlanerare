import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel{
	private ImageIcon picture;
	
	ImagePanel(String filename){
	picture = new ImageIcon(filename);
	int w = picture.getIconWidth();
	int h = picture.getIconHeight();
	setPreferredSize(new Dimension(w,h));
	setMaximumSize(new Dimension(w,h));
	setMinimumSize(new Dimension(w,h));
	}
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(picture.getImage(), 0, 0, this);
	}

}
