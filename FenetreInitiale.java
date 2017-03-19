/**
 * La fenêtre principale pour sélectionner les courbes à étudier
 * et pour créer les deux autres fenêtres
 * 
 */

// Chargement des bibliothèques Swing et AWT
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class FenetreInitiale extends JFrame implements ActionListener {
	
	private JButton monBoutonPath1;
	private JButton monBoutonPath2;
	private JButton monBoutonPath3;
	private JButton monBoutonPath4;
	private JComboBox boxNiveau;
	
	public FenetreInitiale(){
		this.setTitle("TD SCAN 2017");
		this.setLayout(null);
		this.setSize(600,600);
		// Pour placer la fenêtre au centre de l'écran
		//~ this.setLocationRelativeTo(null);
		this.setLocation(300,50);
		// Pour empêcher le redimensionnement de la fenêtre
		this.setResizable(false);
		// Pour rendre la fenêtre visible
		this.setVisible(true);
		// Pour permettre la fermeture de la fenêtre lors de l'appui sur la croix rouge
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/**
		 * Mon panneau 1
		 */
		JPanel panneauChoix = new JPanel();
		panneauChoix.setBounds(40,160,520,40);
		panneauChoix.setLayout(null);
		
		JLabel affTest = new JLabel();
		affTest.setText("Choose the difficulty \n");
		affTest.setForeground(Color.white);
		affTest.setBounds(155,10,150,20);
		panneauChoix.add(affTest);
		
		String[] niveau = new String[3];
		niveau[0] = "easy";
		niveau[1] = "normal";
		niveau[2] = "hard";
		
		boxNiveau = new JComboBox(niveau);
		boxNiveau.setBounds(320,10,100,20);
		panneauChoix.add(boxNiveau);
		
		/* Image de fond */
		JLabel fondChoix ;
		fondChoix = new JLabel(new ImageIcon("fondPanel.jpg"));
		fondChoix.setBounds(0,0,520,40);
		panneauChoix.add(fondChoix);
		
		/**
		 * Mon panneau 2
		 */
		JPanel panneauBouton = new JPanel();
		panneauBouton.setBounds(50,210,500,350);
		panneauBouton.setLayout(null);
		
		monBoutonPath1 = new JButton();
		monBoutonPath1.setBounds(10,10,235,160);
		monBoutonPath1.setBackground(Color.black);
		JLabel fondPath1 = new JLabel(new ImageIcon("path1.PNG"));
		fondPath1.setBounds(5,5,225,150);
		monBoutonPath1.add(fondPath1);
		panneauBouton.add(monBoutonPath1);
		
		monBoutonPath2 = new JButton();
		monBoutonPath2.setBounds(255,10,235,160);
		monBoutonPath2.setBackground(Color.black);
		JLabel fondPath2 = new JLabel(new ImageIcon("path2.PNG"));
		fondPath2.setBounds(5,5,225,150);
		monBoutonPath2.add(fondPath2);
		panneauBouton.add(monBoutonPath2);
		
		monBoutonPath3 = new JButton();
		monBoutonPath3.setBounds(10,180,235,160);
		monBoutonPath3.setBackground(Color.black);
		JLabel fondPath3 = new JLabel(new ImageIcon("path3.PNG"));
		fondPath3.setBounds(5,5,225,150);
		monBoutonPath3.add(fondPath3);
		panneauBouton.add(monBoutonPath3);
		
		monBoutonPath4 = new JButton();
		monBoutonPath4.setBounds(255,180,235,160);
		monBoutonPath4.setBackground(Color.black);
		JLabel fondPath4 = new JLabel(new ImageIcon("path4.PNG"));
		fondPath4.setBounds(5,5,225,150);
		monBoutonPath4.add(fondPath4);
		panneauBouton.add(monBoutonPath4);
		
		/* Image de fond */
		JLabel fondBouton ;
		fondBouton = new JLabel(new ImageIcon("fondPanel.jpg"));
		fondBouton.setBounds(0,0,500,350);
		panneauBouton.add(fondBouton);
		
		/**
		 * Mon panneau Global
		 */
		JPanel panneauGlobal = new JPanel();
		panneauGlobal.setBounds(0,0,600,600);
		panneauGlobal.setLayout(null);
		/* Image de fond */
		JLabel fondGlobal ;
		fondGlobal = new JLabel(new ImageIcon("fondGlobal.png"));
		fondGlobal.setBounds(0,0,600,600);
		/* Image de titre */
		JLabel titre ;
		titre = new JLabel(new ImageIcon("titre.png"));
		titre.setBounds(0,0,600,160);
		
		//panneauGlobal.setBackground(Color.yellow);
		panneauGlobal.add(panneauChoix);
		panneauGlobal.add(panneauBouton);
		panneauGlobal.add(titre);
		panneauGlobal.add(fondGlobal);
		
		this.setContentPane(panneauGlobal);

		monBoutonPath1.addActionListener(this);
		monBoutonPath2.addActionListener(this);
		monBoutonPath3.addActionListener(this);
		monBoutonPath4.addActionListener(this);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == monBoutonPath1) 
			new TowerDefense(1,boxNiveau.getSelectedIndex());
		
		if (e.getSource() == monBoutonPath2) 
			new TowerDefense(2,boxNiveau.getSelectedIndex());
						
		if (e.getSource() == monBoutonPath3) 
			new TowerDefense(3,boxNiveau.getSelectedIndex());

		if (e.getSource() == monBoutonPath4) 
			new TowerDefense(4,boxNiveau.getSelectedIndex());
		this.dispose();
	}

}
		
