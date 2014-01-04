import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NodeGraphics extends JComponent {

	private boolean vald = false;

	public NodeGraphics(int x, int y) {
		setBounds(x, y, 25, 25);
		setPreferredSize(new Dimension(25, 25));
		setMaximumSize(new Dimension(25, 25));
		setMinimumSize(new Dimension(25, 25));
		System.out.println("Skapar ny nod");
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (vald == false) 
			g.setColor(Color.BLUE);
		 else 
			g.setColor(Color.RED);	
		
		g.fillOval(0, 0, getWidth(), getHeight());
		System.out.println("nymålad");
		
	}

	public void setSelectedPinned(boolean vald) {
		this.vald = vald;

	}
}
