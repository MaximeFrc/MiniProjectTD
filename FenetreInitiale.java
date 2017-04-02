/**
 * The class FentreInitiale that inherits from JFrame and implements ActionListener to choose the difficulty and the path
 * and create the window TowerDefense
 * 
 */

// Load libraries Swing and AWT
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class FenetreInitiale extends JFrame implements ActionListener {
	
	// Attributes
	
	private JButton monBoutonPath1;
	private JButton monBoutonPath2;
	private JButton monBoutonPath3;
	private JButton monBoutonPath4;
	private JComboBox boxNiveau;
	
	//Methodes
	
	/**
	 * The construtor
	 * Use three Jpanel to store
	 * the buttons and comboBox
	 */
	public FenetreInitiale(){
		
		super("TD SCAN 2017");
		this.setLayout(null);
		this.setSize(600,600);
		this.setLocation(300,50);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/**
		 * panel 1
		 */
		JPanel panneauChoix = new JPanel();
		panneauChoix.setBounds(40,160,520,40);
		panneauChoix.setLayout(null);
		
		/* label texte 1 */
		JLabel affTest = new JLabel();
		affTest.setText("Choose the difficulty \n");
		affTest.setForeground(Color.white);
		affTest.setBounds(155,10,150,20);
		panneauChoix.add(affTest);
		
		/* comboBox 1 */
		String[] niveau = new String[3];
		niveau[0] = "easy";
		niveau[1] = "normal";
		niveau[2] = "hard";
		
		boxNiveau = new JComboBox(niveau);
		boxNiveau.setBounds(320,10,100,20);
		panneauChoix.add(boxNiveau);
		
		/* background panel 1 */
		JLabel fondChoix ;
		fondChoix = new JLabel(new ImageIcon("fondPanel.jpg"));
		fondChoix.setBounds(0,0,520,40);
		panneauChoix.add(fondChoix);
		
		/**
		 * panel 2
		 */
		JPanel panneauBouton = new JPanel();
		panneauBouton.setBounds(50,210,500,350);
		panneauBouton.setLayout(null);
		
		/* button 1 */
		monBoutonPath1 = new JButton();
		monBoutonPath1.setBounds(10,10,235,160);
		monBoutonPath1.setBackground(Color.black);
		JLabel fondPath1 = new JLabel(new ImageIcon("path1.PNG"));
		fondPath1.setBounds(5,5,225,150);
		monBoutonPath1.add(fondPath1);
		panneauBouton.add(monBoutonPath1);
		
		/* button 2 */
		monBoutonPath2 = new JButton();
		monBoutonPath2.setBounds(255,10,235,160);
		monBoutonPath2.setBackground(Color.black);
		JLabel fondPath2 = new JLabel(new ImageIcon("path2.PNG"));
		fondPath2.setBounds(5,5,225,150);
		monBoutonPath2.add(fondPath2);
		panneauBouton.add(monBoutonPath2);
		
		/* button 3 */
		monBoutonPath3 = new JButton();
		monBoutonPath3.setBounds(10,180,235,160);
		monBoutonPath3.setBackground(Color.black);
		JLabel fondPath3 = new JLabel(new ImageIcon("path3.PNG"));
		fondPath3.setBounds(5,5,225,150);
		monBoutonPath3.add(fondPath3);
		panneauBouton.add(monBoutonPath3);
		
		/* button 4 */
		monBoutonPath4 = new JButton();
		monBoutonPath4.setBounds(255,180,235,160);
		monBoutonPath4.setBackground(Color.black);
		JLabel fondPath4 = new JLabel(new ImageIcon("path4.PNG"));
		fondPath4.setBounds(5,5,225,150);
		monBoutonPath4.add(fondPath4);
		panneauBouton.add(monBoutonPath4);
		
		/* background panel 2 */
		JLabel fondBouton ;
		fondBouton = new JLabel(new ImageIcon("fondPanel.jpg"));
		fondBouton.setBounds(0,0,500,350);
		panneauBouton.add(fondBouton);
		
		/**
		 * Global panel
		 */
		JPanel panneauGlobal = new JPanel();
		panneauGlobal.setBounds(0,0,600,600);
		panneauGlobal.setLayout(null);
		
		/* background Global panel */
		JLabel fondGlobal ;
		fondGlobal = new JLabel(new ImageIcon("fondGlobal.png"));
		fondGlobal.setBounds(0,0,600,600);
		
		/* Title image */
		JLabel titre ;
		titre = new JLabel(new ImageIcon("titre.png"));
		titre.setBounds(0,0,600,160);
		
		/**
		 * put every panels in
		 * the Glabal panel
		 */
		panneauGlobal.add(panneauChoix);
		panneauGlobal.add(panneauBouton);
		panneauGlobal.add(titre);
		panneauGlobal.add(fondGlobal);
		
		this.setContentPane(panneauGlobal);

		/* add a listener to the buttons */
		monBoutonPath1.addActionListener(this);
		monBoutonPath2.addActionListener(this);
		monBoutonPath3.addActionListener(this);
		monBoutonPath4.addActionListener(this);
		
	}
	
	/**
	 * The method to act when a button is clicked
	 * @param e : ActionEvent corresponding to the button clicked
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == monBoutonPath1) 
			new TowerDefense(1,boxNiveau.getSelectedIndex());
		
		if (e.getSource() == monBoutonPath2) 
			new TowerDefense(2,boxNiveau.getSelectedIndex());
						
		if (e.getSource() == monBoutonPath3) 
			new TowerDefense(3,boxNiveau.getSelectedIndex());

		if (e.getSource() == monBoutonPath4) 
			new TowerDefense(4,boxNiveau.getSelectedIndex());
			
		this.dispose(); // close this window
	}

}
		
