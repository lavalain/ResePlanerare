import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



public class NodeGraphics extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean vald = false;
	private String name;

	public NodeGraphics(int x, int y, String name) {
		this.name = name;
		setBounds(x, y, 65, 65);
		setPreferredSize(new Dimension(65, 65));
		setMaximumSize(new Dimension(65, 65));
		setMinimumSize(new Dimension(65, 65));
		System.out.println("Skapar ny nod");
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (vald == false) 
			g.setColor(Color.BLUE);
		 else 
			g.setColor(Color.RED);	
		
		g.fillOval(0, 0, 20, 20);
		g.setColor(Color.BLACK);
		g.setFont(new Font("TimesNewRoman",Font.BOLD,15));
		g.drawString(name, 2,40);	
	}

	public void setSelectedPinned(boolean vald) {
		this.vald = vald;
	}
}
