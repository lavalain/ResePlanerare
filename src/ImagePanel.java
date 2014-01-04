import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {
	private Image picture;

	public ImagePanel(Image i) {
		setLayout(null);
		setPreferredSize(new Dimension(i.getWidth(this),i.getHeight(this)));
		picture = i;

	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(picture, 0, 0,getWidth(), getHeight(), this);
	}

}
