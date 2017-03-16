import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.awt.event.*;


public class ScorePanel extends JPanel implements ActionListener {
	
	/*Attributes*/
	int widthSP;
	int heightSP;
	
	
	/*Components of the Panel*/
	JLabel scoreTF;
	JLabel timeTF;
	JButton menuButton;
	Image scoreImage;
	ImageIcon menuImage;
	BufferedImage background;
	Graphics buffer;
	JLabel nbWaveT;
	JLabel nbLivesT;
	JLabel moneyT;
	
	JOptionPane d;


	public ScorePanel(int width, int height, String image) {
		
		int widthSP = width-5;
		int heightSP = 100;
		setBounds(0,0,widthSP,heightSP);
		setLayout(null);
		Font font = new java.awt.Font("MAGNETO",Font.BOLD,15);
		
		/*Score text field*/
		scoreTF = new JLabel("Score : ");
		scoreTF.setBounds(200,45,150,20);
		scoreTF.setBackground(new Color(0,0,0,128));
		scoreTF.setForeground(Color.WHITE);
		scoreTF.setFont(font);
		add(scoreTF);
		
		/*Timer text field*/
		timeTF = new JLabel("Time : ");
		timeTF.setBounds(350,45,150,20);
		timeTF.setBackground(new Color(0,0,0,128));
		timeTF.setForeground(Color.WHITE);
		timeTF.setFont(font);
		add(timeTF);
		
		/*Creation of the tool to get images*/
		Toolkit T=Toolkit.getDefaultToolkit();
		
		/*Creation of the background in which we will draw"*/
		background = new BufferedImage(getSize().width,getSize().height,BufferedImage.TYPE_INT_RGB);
		
		/*Everything is written is the background */
		buffer = background.getGraphics(); 
		
		/*Images*/
		scoreImage = T.getImage(image);
		menuImage = new ImageIcon("menu1.png");;
		
		/*Creation of the Menu button*/
		menuButton = new JButton("Menu", menuImage);
		menuButton.setBounds(40,15,120,60);
		menuButton.setBackground(new Color(0,0,0,128));
		menuButton.setForeground(Color.WHITE);
		menuButton.setFont(font);
		add(menuButton);
		menuButton.addActionListener(this);
		
		/*Creation of the money display*/
		moneyT = new JLabel("Money : ");
		moneyT.setBounds(500,45,150,20);
		moneyT.setBackground(new Color(0,0,0,128));
		moneyT.setForeground(Color.WHITE);
		moneyT.setFont(font);
		add(moneyT);
		
		/*Creation of the nb of lives display*/
		nbLivesT = new JLabel("Remaining lives : ");
		nbLivesT.setBounds(750,25,150,20);
		nbLivesT.setBackground(new Color(0,0,0,128));
		nbLivesT.setForeground(Color.WHITE);
		nbLivesT.setFont(font);
		add(nbLivesT);
		
		/*Creation of the nb of wave display*/
		nbWaveT = new JLabel("Wave number ");
		nbWaveT.setBounds(750,60,150,20);
		nbWaveT.setBackground(new Color(0,0,0,128));
		nbWaveT.setForeground(Color.WHITE);
		nbWaveT.setFont(font);
		add(nbWaveT);
	}
	
	public void paintComponent(Graphics g) {
		buffer.drawImage(scoreImage, 0,0,  this);
		g.drawImage(background, 0,0, this);
	}
	
	public void displayTime (int time) {
		timeTF.setText("Time = "+ time/20 + " s ");
	}
	
	public void displayScore (int score) {
		//scoreTF.setText("Score = "+ score);
	}
	
	public void actionPerformed(ActionEvent e ) {
		d = new JOptionPane();
		if( d.showConfirmDialog(getFrame(), "Are you sure you want to quit? All progress will be lost", "Warning !", JOptionPane.OK_CANCEL_OPTION)==0) {
			getFrame().dispose();
			FenetreInitiale fenIni = new FenetreInitiale();
		}
		
		//int retour = d.showConfirmDialog(this, "Are you sure you want to quit? All progress will be lost", "Warning !", JOptionPane.OK_CANCEL_OPTION);

	}
	
	public Frame getFrame() { // récupération de la frame de l'applet
		Component component=this;
		do{
			component=component.getParent();
		} while(!(component instanceof Frame));
		return (Frame)component;
	}
	
	
}

