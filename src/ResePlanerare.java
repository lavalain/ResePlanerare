import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ResePlanerare extends JFrame {
	JMenuItem ny, avsluta, menyHitta, menyVisa, menyNyPlats, menyNyForb;
	JMenu arkiv, op;
	JMenuBar meny;
	JFileChooser fc;
	public ResePlanerare(){
		super("ResePlanerare");
		setLayout(new BorderLayout());
		//meny 
		meny = new JMenuBar();
		arkiv = new JMenu("Arkiv");

		ny = new JMenuItem("Ny");
		ny.addActionListener(new NyKartaLyss());

		avsluta = new JMenuItem("Avsluta");
		//avsluta.setMnemonic(KeyEvent.VK_X);// antar att programmet avslutas direkt om man trycker på den 
		//öppna,spara, sparasom = överkurs för högre betyg
		avsluta.addActionListener(new AvslutaLyss());

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
		andraFörb.addActionListener(new AndraForbLyss());
		// knappar gui
		
		// rita upp bilden använd detta i en inre klass istället efter lunch
		JPanel karta = new JPanel();
		karta.setLayout(null);
		add(karta,BorderLayout.CENTER);
		//JLabel lol = new JLabel();
		//karta.add(lol);
		//karta.drawImage(fc.getSelectedFile().getName());
		
		//rita upp bilden
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	//formulär NyForb
	class NyForbindelse extends JPanel{
		private JTextField nyNamn, nyTid;

		public NyForbindelse(){
			//label där de städer som användaren har valt visas "Förbindelse mellan"+ punktA + " och "+ punktB, dela punkter kopplas till radiobuttons som är kodade till en lyssnare.
			
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			nyNamn = new JTextField(12);
			nyTid = new JTextField(6);
			nyTid.setEnabled(false);//textField setEnable
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
		private JTextField viNamn, viTid;
		private JPanel rad3, rad4, rad5;
		
		public VisaForbindelse(){
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			viNamn = new JTextField(12);
			viTid = new JTextField(6);
			rad3 = new JPanel();
			// visa de förbindelser som finns mellan from och to och vilken tid det tar att resa mellan dessa
			
			rad4 = new JPanel();
			rad4.add(new JLabel("Namn:"));
			rad4.add(viNamn);
			add(rad4);
			viNamn.setEnabled(false);
			
			
			rad5 = new JPanel();
			rad5.add(new JLabel("Tid"));
			rad5.add(viTid);
			add(rad5);
			viTid.setEnabled(false);
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
		private JTextField AnNamn, AnTid;
		private JPanel rad6, rad7, rad8;
		
		public AndraForbindelse(){
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			AnNamn = new JTextField(12);
			AnTid = new JTextField(6);
			rad6 = new JPanel();
			//
			rad7 = new JPanel();
			rad7.add(new JLabel("Namn:"));
			rad7.add(AnNamn);
			add(rad7);
			AnNamn.setEnabled(false);
			
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
	//Form AndraFörbindelse
	// Ny plats form
	class NyPlatsForm extends JPanel{
		private JTextField NyPlats;
		private JPanel rad9;
		
		public NyPlatsForm (){
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			NyPlats = new JTextField(12);
			rad9 = new JPanel();
			rad9.add(new JLabel("Platsens namn:"));
			rad9.add(NyPlats);
			add(rad9);
		}
		public String getNyPlats(){
			return NyPlats.getText();
		}
	}
	//Nyplats Form 
	//Hitta väg form
	class HittaVag extends JPanel{
		private JTextArea hittaVag;
		
		public HittaVag(){
			setLayout(new FlowLayout());
			hittaVag = new JTextArea(1,10);
			hittaVag.setEditable(false);
			add(new JScrollPane(hittaVag));	
		}
	}
	//hitta väg form
	
	//bilder
	class Bilder extends JComponent{
		private Image bild;
		
		public Bilder(){
			Toolkit tk = Toolkit.getDefaultToolkit();
			bild = tk.getImage("My World.png");
		}
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(bild,  0,  0,  getWidth(),getHeight(), this);
		}
		
	}
	//Lyssnarmetoder
	class HittaLyss implements ActionListener{
		public void actionPerformed(ActionEvent ave){
			
			HittaVag hiform = new HittaVag();
			
			JOptionPane.showMessageDialog(null, hiform, "Hitta väg", JOptionPane.INFORMATION_MESSAGE);

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
			
			NyPlatsForm nypform = new NyPlatsForm();
			
			int rest = JOptionPane.showConfirmDialog(null, nypform, "Ny Plats", JOptionPane.OK_CANCEL_OPTION);
			
			//nynamn
			// add. ListGraph och pltsen dyker upp på kartan när man trycker på ok.

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
			//setWeight(); AnTid heter JTextfield som nya tiden anges i

		}
	}
	//knappar slut

	// menyfunktionalitet
	class AvslutaLyss implements ActionListener{
		public void actionPerformed(ActionEvent ave){
			System.exit(0);

		}
	}
	class NyKartaLyss implements ActionListener{
		public void actionPerformed(ActionEvent ave){
			//String str = System.getProperty("user.dir");
			final JFileChooser fc = new JFileChooser(".");
			
			
			
			int returnVal = fc.showOpenDialog((JComponent)ave.getSource());
			if( returnVal == JFileChooser.APPROVE_OPTION) {
				
				fc.getSelectedFile().getName();
				
				//anrpoa metoden i bildklassen(
				fc.getSelectedFile().getName();
				ImageIcon picture = new ImageIcon();
				
				
			
				
				
			}
			//protected void paintComponent(Graphics g){
				
			//}
		}
	}
	// menyfunktionalitet





	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new ResePlanerare(); 

	

	}

}
