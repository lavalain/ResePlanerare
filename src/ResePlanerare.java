import Graph.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.awt.image.*;
import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.awt.event.MouseAdapter;


public class ResePlanerare extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenuItem ny, avsluta, menyHitta, menyVisa, menyNyPlats, menyNyForb;
	private JMenu arkiv, op;
	private JMenuBar meny;
	//private JFileChooser fc;
	private ImagePanel picture = null;
	private BufferedImage img;
	private MouseList m;
	private NodeGraphics sel1, sel2, ng;
	private NyPlatsForm nypform;
	//private NyForbindelse form;
	private Node n;
	private String Ename;
	private int Wname;
	private String str;
	private LinkedList <Edge<Node>> eLList;
	private JTextArea hittaVag;

	private ListGraph <Node>lg = new ListGraph  <Node>();
	private HashMap<NodeGraphics, Node> nng = new HashMap<NodeGraphics, Node>();

	public ResePlanerare(){
		super("ResePlanerare");
		setLayout(new BorderLayout());
		//meny 
		meny = new JMenuBar();
		arkiv = new JMenu("Arkiv");

		ny = new JMenuItem("Ny");
		add(ny, BorderLayout.NORTH);
		ny.addActionListener(new NyKartaLyss());

		avsluta = new JMenuItem("Avsluta");
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

		//Knappar 
		JPanel north = new JPanel();
		north.setLayout(new GridLayout(1,5));
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
		// knappar 

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setLocationRelativeTo(null);
		pack();
		setVisible(true);
	}

	//formulär NyForb
	class NyForbindelse extends JPanel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private JTextField WayOfTravel, nyTid;

		public NyForbindelse(){
			
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			WayOfTravel = new JTextField(12);
			nyTid = new JTextField(6);
	
			JPanel rad = new JPanel();
			rad.add(new JLabel(" Du åker från " + nng.get(sel1) + " och " + nng.get(sel2)));
			add(rad);

			JPanel rad1 = new JPanel();
			rad1.add(new JLabel("Namn:"));
			rad1.add(WayOfTravel);
			add(rad1);

			JPanel rad2 = new JPanel();
			rad2.add(new JLabel("Tid"));
			rad2.add(nyTid);
			add(rad2);
		}
		public String getNamn(){
			return WayOfTravel.getText();
		}
		public int getTid(){
			return Integer.parseInt(nyTid.getText());
		}
	}
	//Form NyForb

	//Form VisaForb
	class VisaForbindelse extends JPanel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private JTextField viNamn, viTid;
		private JPanel rad3, rad4, rad5;

		public VisaForbindelse(){
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			viNamn = new JTextField(12);
			viTid = new JTextField(6);
			rad3 = new JPanel();
			rad3.add(new JLabel(" mellan " + nng.get(sel1) + " och "+ nng.get(sel2)));
			add(rad3);

			rad4 = new JPanel();
			rad4.add(new JLabel("Namn:"));
			rad4.add(viNamn);
			add(rad4);
			viNamn.setEnabled(false);
			viNamn.setText(Ename);

			rad5 = new JPanel();
			rad5.add(new JLabel("Tid"));
			rad5.add(viTid);
			add(rad5);
			viTid.setEnabled(false);
			viTid.setText(Wname+ "");
		}
		public String getViNamn(){
			return viNamn.getText();
		}
		public int getViTid(){
			return Integer.parseInt(viTid.getText());
		}
	}
	//Form VisaForb

	//Form ÄndraFörbindelse
	class AndraForbindelse extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private JTextField AnNamn, AnTid;
		private JPanel rad6, rad7, rad8;

		public AndraForbindelse(){
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			AnNamn = new JTextField(12);
			AnTid = new JTextField(6);
			rad6 = new JPanel();
			rad6.add(new JLabel ("Du kommer att ändra förbindelsen mellan" + nng.get(sel1) + " och " + nng.get(sel2)));
			add(rad6);

			rad7 = new JPanel();
			rad7.add(new JLabel("Namn:"));
			rad7.add(AnNamn);
			add(rad7);
			AnNamn.setEnabled(false);
			AnNamn.setText(Ename);

			rad8 = new JPanel();
			rad8.add(new JLabel("Tid"));
			rad8.add(AnTid);
			add(rad8);
			//AnTid.setText(Wname+"");
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
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
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
	//Ny plats form 

	//Hitta väg form
	class HittaVag extends JPanel{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public HittaVag(){
			setLayout(new FlowLayout());
			hittaVag = new JTextArea(1,10);
			for (Edge <Node> e : eLList){
				hittaVag.append(e.toString());
			}
			hittaVag.setEditable(false);
			add(new JScrollPane(hittaVag));	
			
		}
	}
	//hitta väg form

	//Lyssnarmetoder
	class HittaLyss implements ActionListener{ 
		public void actionPerformed(ActionEvent ave){
		
			if(!lg.pathExists(nng.get(sel1), nng.get(sel2)))
				JOptionPane.showMessageDialog(ResePlanerare.this, "Det finns ingen förbindelse mellan dessa platser", "FelMeddelande", JOptionPane.ERROR_MESSAGE);
			eLList = lg.fastPath(nng.get(sel1), nng.get(sel2));
			
			HittaVag hiform = new HittaVag();

			JOptionPane.showMessageDialog(null, hiform, "Hitta väg", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	class VisaForLyss implements ActionListener{
		public void actionPerformed(ActionEvent ave){

			if(sel1 == null || sel2 == null)
				JOptionPane.showMessageDialog(null, "Markera två noder");
			
				Edge <Node> em = lg.getEdgeBetween(nng.get(sel1), nng.get(sel2));
				if (em == null){
					JOptionPane.showMessageDialog(null, "Det finns ingen förbindelse mellan dessa noder");
					return;
				}
				Wname = em.getWeight();
				Ename = em.getName();

				VisaForbindelse viform = new VisaForbindelse();

				JOptionPane.showMessageDialog(null, viform, "Visa Förbindelse", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	class NyPlatsLyss implements ActionListener{
		public void actionPerformed(ActionEvent ave){
			Cursor c = Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
			picture.setCursor(c);
			m = new MouseList();
			picture.addMouseListener(m);
			
		}
	}
	class NyForbLyss implements ActionListener{
		public void actionPerformed(ActionEvent ave){

			if(sel1 == null || sel2 == null){
				JOptionPane.showMessageDialog(null, "Markera två noder");
				return;
			}
			NyForbindelse form = new NyForbindelse();

			JOptionPane.showConfirmDialog(null, form, "Ny Förbindelse", JOptionPane.OK_CANCEL_OPTION);
		
			lg.connect(nng.get(sel1), nng.get(sel2), form.getNamn(), form.getTid());
			



		}
	}
	class AndraForbLyss implements ActionListener{
		public void actionPerformed(ActionEvent ave){
			
			Edge <Node> eng = lg.getEdgeBetween(nng.get(sel1), nng.get(sel2));
			if( eng == null){
				JOptionPane.showMessageDialog(null, "Det finns ingen förbindelse mellan dessa noder");
				return;
			}
			Wname = eng.getWeight();
			Ename = eng.getName();
			AndraForbindelse anform = new AndraForbindelse();

			JOptionPane.showConfirmDialog(null, anform, "Ändra Förbindelse", JOptionPane.OK_CANCEL_OPTION);
			lg.setConnectionWeight(nng.get(sel1), nng.get(sel2),anform.getAnTid());



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
			
			if(!lg.isEmpty()){
				lg.clear();	
			}

			String aktuellMapp = System.getProperty("user.dir");
			JFileChooser fc = new JFileChooser(aktuellMapp);
			FileNameExtensionFilter fnef = new FileNameExtensionFilter("bilder", "jpg", "png","gif");
			fc.setFileFilter(fnef);
			System.out.println("filter!!!!");
			int returnVal = fc.showOpenDialog(ResePlanerare.this);
			if (returnVal != JFileChooser.APPROVE_OPTION)
				return;
			try {
				File f = fc.getSelectedFile();
		
				if (picture != null)
					remove(picture);
				img = ImageIO.read(f);
				picture = new ImagePanel(img);
				add(picture, BorderLayout.CENTER);
				validate();
				repaint();
				pack();
			}
			catch(IOException e1){}
		} // actionPerformed
	}
	public class MouseList extends MouseAdapter{
		public void mouseClicked(MouseEvent mev){
			nypform = new NyPlatsForm();

			str = nypform.getNyPlats();
			JOptionPane.showConfirmDialog(null, nypform, "Ny Plats", JOptionPane.OK_CANCEL_OPTION);

			n = new Node(nypform.getNyPlats());
			lg.add(n);
			ng = new NodeGraphics(mev.getX(),mev.getY(),nypform.getNyPlats());
			picture.add(ng);
			ng.addMouseListener(new MouseSelectList());
			picture.removeMouseListener(m);
			Cursor c = Cursor.getDefaultCursor();
			picture.setCursor(c);
			nng.put(ng, n);
			validate();
			repaint();
			picture.removeMouseListener(m);
			

		}
	}
	public class MouseSelectList extends MouseAdapter{
		public void mouseClicked(MouseEvent mev){
			NodeGraphics temp = (NodeGraphics)mev.getSource();
			if(sel1 == null)
				sel1 = temp;
			else if (sel2 == null)
				sel2 = temp;
			else{
				sel1.setSelectedPinned(false);
				sel1 = sel2;
				sel2 = temp;
			}
			temp.setSelectedPinned(true);

			repaint();
			
		}
	}

	// menyfunktionalitet





	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new ResePlanerare(); 

	}
	static	{
		Font f = new Font("Dialog", Font.BOLD, 18);
		String[] comps = {"Button","Label", "RadioButton","CheckBox",
				"ToggleButton", "TextArea","TextField",
				"Menu", "MenuItem"};
		for(String s : comps)
			UIManager.put(s+ "Font", f);
	}
}


