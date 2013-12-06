import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ResePlanerare extends JFrame {
	JMenuItem ny, avsluta, menyHitta, menyVisa, menyNyPlats, menyNyForb;
	JMenu arkiv, op;
	JMenuBar meny;
	public ResePlanerare(){
		super("ResePlanerare");
		setLayout(new BorderLayout());
//meny 
		meny = new JMenuBar();
		arkiv = new JMenu("Arkiv");
		
		ny = new JMenuItem("Ny");
		ny.addActionListener(new NyKartaLyss());
		
		avsluta = new JMenuItem("Avsluta");
		avsluta.setMnemonic(KeyEvent.VK_X);// antar att programmet avslutas direkt om man trycker på den 
														//öppna,spara, sparasom = överkurs för högre betyg
		//avsluta.addActionListener(new AvslutaLyss());
		
		arkiv.add(ny);
		arkiv.add(avsluta);
		meny.add(arkiv);

		op = new JMenu("Operationer");
		menyHitta = new JMenuItem("Hitta väg");
		menyHitta.addActionListener(new HittaLyss());
		
		menyVisa = new JMenuItem("Visa Förbindelse");
		menyVisa.addActionListener(new VisaForLyss());
		
		menyNyPlats = new JMenuItem("Ny plats");
		menyNyPlats.addActionListener(new NyPlatsLyss());
		
		menyNyForb = new JMenuItem("Ny förbindelse");
		menyNyForb.addActionListener(new NyForbLyss());
		
		op.add(menyHitta);
		op.add(menyVisa);
		op.add(menyNyPlats);
		op.add(menyNyForb);
		meny.add(op);
		setJMenuBar(meny);
//meny	
		//Knappar gui
		JPanel north = new JPanel();
		//north.setLayout(FlowLayout());
		add(north, BorderLayout.NORTH);

		JButton hitta = new JButton("Hitta väg");
		north.add(hitta);
		hitta.addActionListener(new HittaLyss());

		JButton visaFör = new JButton("Visa förbindelse");
		north.add(visaFör);
		visaFör.addActionListener(new VisaForLyss());

		JButton nyPlats = new JButton("Ny plats");
		north.add(nyPlats);
		nyPlats.addActionListener(new NyPlatsLyss());

		JButton nyFörb = new JButton("Ny Förbindelse");
		north.add(nyFörb);
		nyFörb.addActionListener(new NyForbLyss());

		JButton andraFörb = new JButton("Ändra förbindelse");
		north.add(andraFörb);
		nyFörb.addActionListener(new AndraForbLyss());
		// knappar gui

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600,500);
		setVisible(true);



	}
	//formulär NyForb
	class NyForbindelse extends JPanel{
		private JTextField namn = new JTextField(12);
		private JTextField tid = new JTextField(6);
		public NyForbindelse(){
			//label där de städer som användaren har valt visas "Förbindelse mellan"+ punktA + " och "+ punktB, dela punkter kopplas till radiobuttons som är kodade till en lyssnare.
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			JPanel rad = new JPanel();
			//rad.add(new JLabel("Du åker från" + from + "och" + to));
			JPanel rad1 = new JPanel();
			rad1.add(new JLabel("Namn:"));
			rad1.add(namn);
			add(rad1);
			
			JPanel rad2 = new JPanel();
			rad2.add(new JLabel("Tid"));
			rad2.add(tid);
			add(rad2);
		}
		public String getNamn(){
			return namn.getText();
		}
		public int getTid(){
			return Integer.parseInt(tid.getText());
		}
	}
	//Formulär NyForb
	
	//Formulär VisaForb
	class VisaForbindelse extends JPanel{
		JTextArea display;
		public VisaForbindelse(){
			setLayout(new BorderLayout());
			//JPanel disp = new JPanel();
			display = new JTextArea();
			display.setEditable(false);
			add(new JScrollPane(display),BorderLayout.CENTER);
			
			display.setEditable(false);
		}
		public String getDisplay(){
			return display.getText();
		
			
			
			
			
			
			
		}
	}
	//knappar
	class HittaLyss implements ActionListener{
		public void actionPerformed(ActionEvent ave){
			
		}
	}
	class VisaForLyss implements ActionListener{
		public void actionPerformed(ActionEvent ave){
			
			VisaForbindelse viform = new VisaForbindelse();
			
			JOptionPane.showMessageDialog(null, viform, "Visa Förbindelse", JOptionPane.PLAIN_MESSAGE);
			
		}
	}
	class NyPlatsLyss implements ActionListener{
		public void actionPerformed(ActionEvent ave){
			
		}
	}
	class NyForbLyss implements ActionListener{
		public void actionPerformed(ActionEvent ave){
			
			NyForbindelse form = new NyForbindelse();
			
			int result = JOptionPane.showConfirmDialog(null, form, "Ny Förbindelse", JOptionPane.OK_CANCEL_OPTION);
			
		}
	}
	class AndraForbLyss implements ActionListener{
		public void actionPerformed(ActionEvent ave){
			
		}
	}
	//knappar slut
	
	// menyfunktionalitet
	class AvslutaLyss implements ActionListener{
		public void actionPerformed(ActionEvent ave){
			
		}
	}
	class NyKartaLyss implements ActionListener{
		public void actionPerformed(ActionEvent ave){
			
		}
	}
	// menyfunktionalitet





	/**
	 * @param args
	 */
	public static void main(String[] args) {new ResePlanerare(); {

	}

	}

}
