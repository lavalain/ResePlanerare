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
		private JTextArea nyNamn, nyTid;

		public NyForbindelse(){
			//label där de städer som användaren har valt visas "Förbindelse mellan"+ punktA + " och "+ punktB, dela punkter kopplas till radiobuttons som är kodade till en lyssnare.
			
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			nyNamn = new JTextArea(1, 12);
			nyTid = new JTextArea(1, 6);
			JPanel rad = new JPanel();
			//rad.add(new JLabel("Du åker från" + from + "och" + to));
			
			JPanel rad1 = new JPanel();
			rad1.add(new JLabel("Namn:"));
			rad1.add(nyNamn);
			add(rad1);

			JPanel rad2 = new JPanel();
			rad2.add(new JLabel("Tid"));
			rad2.add(nyTid);
			add(rad2);
		}
		public String getNamn(){
			return nyNamn.getText();
		}
		public int getTid(){
			return Integer.parseInt(nyTid.getText());
		}
	}
	//Formulär NyForb

	//Formulär VisaForb
	class VisaForbindelse extends JPanel{
		private JTextArea viNamn, viTid;
		private JPanel rad3, rad4, rad5;
		
		public VisaForbindelse(){
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			viNamn = new JTextArea(1, 12);
			viTid = new JTextArea(1, 6);
			rad3 = new JPanel();
			// visa de förbindelser som finns mellan from och to och vilken tid det tar att resa mellan dessa
			
			rad4 = new JPanel();
			rad4.add(new JLabel("Namn:"));
			rad4.add(viNamn);
			add(rad4);
			viNamn.setEditable(false);
			
			
			rad5 = new JPanel();
			rad5.add(new JLabel("Tid"));
			rad5.add(viTid);
			add(rad5);
			viTid.setEditable(false);
		}
		public String getViNamn(){
			return viNamn.getText();
		}
		public int getViTid(){
			return Integer.parseInt(viTid.getText());
		}
	}
	//Formulär VisaForb
	
	//Form ÄndraFörbindelse
	class AndraForbindelse extends JPanel {
		private JTextArea AnNamn, AnTid;
		private JPanel rad6, rad7, rad8;
		
		public AndraForbindelse(){
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			AnNamn = new JTextArea(1, 12);
			AnTid = new JTextArea(1, 6);
			rad6 = new JPanel();
			//
			rad7 = new JPanel();
			rad7.add(new JLabel("Namn:"));
			rad7.add(AnNamn);
			add(rad7);
			AnNamn.setEditable(false);
			
			rad8 = new JPanel();
			rad8.add(new JLabel("Tid"));
			rad8.add(AnTid);
			add(rad8);
		}
		public String getAnNamn() {
			return AnNamn.getText();
		}
		public int getAnTid() {
			return Integer.parseInt(AnTid.getText());
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

			JOptionPane.showMessageDialog(null, viform, "Visa Förbindelse", JOptionPane.INFORMATION_MESSAGE);

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
			
			AndraForbindelse anform = new AndraForbindelse();
			
			int anRes = JOptionPane.showConfirmDialog(null, anform, "Ändra Förbindelse", JOptionPane.OK_CANCEL_OPTION);

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
