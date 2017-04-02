/**
 * The class ScorePanel that inherits from JPanel and implements ActionListener
 * to display informations about the game 
 * 
 */

// Load libraries Swing and AWT
import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.awt.event.*;


public class ScorePanel extends JPanel implements ActionListener {
	
	//Attributes
	
	/* Size of the Panel */
	public int widthSP;
	public int heightSP;
	
	/* Components of the Panel */
	public JLabel scoreTF;
	public JLabel timeTF;
	public JLabel nbWaveT;
	public JLabel nbLivesT;
	public JLabel moneyT;
	public JButton menuButton;
	public JOptionPane d; //to go back to the menu
	public Image scoreImage;
	public ImageIcon menuImage;
	public BufferedImage background;
	public Graphics buffer;
	
	//Methodes

	/**
	 * The construtor
	 * @param width : int that comes from the width of the TowerDefense frame
	 * @param height : int that comes from the height of the TowerDefense frame
	 * @param image : Sting to access the background image
	 */
	public ScorePanel(int width, int height, String image) {
		
		/* Parameters of the panel */
		widthSP = width-5;
		heightSP = 100;
		setBounds(0,0,widthSP,heightSP);
		setLayout(null);
		Font font = new java.awt.Font("MAGNETO",Font.BOLD,15);
		
		/* Score text field */
		scoreTF = new JLabel("Score : ");
		scoreTF.setBounds(200,45,150,20);
		scoreTF.setBackground(new Color(0,0,0,128));
		scoreTF.setForeground(Color.WHITE);
		scoreTF.setFont(font);
		add(scoreTF);
		
		/* Timer text field */
		timeTF = new JLabel("Time : ");
		timeTF.setBounds(350,45,150,20);
		timeTF.setBackground(new Color(0,0,0,128));
		timeTF.setForeground(Color.WHITE);
		timeTF.setFont(font);
		add(timeTF);
		
		/* Creation of the tool to get images */
		Toolkit T=Toolkit.getDefaultToolkit();
		
		/* Creation of the background in which we will draw */
		background = new BufferedImage(getSize().width,getSize().height,BufferedImage.TYPE_INT_RGB);
		
		/* Everything is written is the background */
		buffer = background.getGraphics(); 
		
		/* Images */
		scoreImage = T.getImage(image);
		menuImage = new ImageIcon("menu1.png");;
		
		/* Creation of the Menu button */
		menuButton = new JButton("Menu", menuImage);
		menuButton.setBounds(40,15,120,60);
		menuButton.setBackground(new Color(0,0,0,128));
		menuButton.setForeground(Color.WHITE);
		menuButton.setFont(font);
		add(menuButton);
		menuButton.addActionListener(this);
		
		/* Creation of the money display */
		moneyT = new JLabel("Money : ");
		moneyT.setBounds(500,45,150,20);
		moneyT.setBackground(new Color(0,0,0,128));
		moneyT.setForeground(Color.WHITE);
		moneyT.setFont(font);
		add(moneyT);
		
		/* Creation of the nb of lives display */
		nbLivesT = new JLabel("Remaining lives : ");
		nbLivesT.setBounds(750,25,180,20);
		nbLivesT.setBackground(new Color(0,0,0,128));
		nbLivesT.setForeground(Color.WHITE);
		nbLivesT.setFont(font);
		add(nbLivesT);
		
		/* Creation of the nb of wave display */
		nbWaveT = new JLabel("Wave number : ");
		nbWaveT.setBounds(750,60,150,20);
		nbWaveT.setBackground(new Color(0,0,0,128));
		nbWaveT.setForeground(Color.WHITE);
		nbWaveT.setFont(font);
		add(nbWaveT);
	}
	
	/**
	 * The method paintComponent to paint when TowerDefense call it
	 * @param g : Graphics where we draw
	 */ 
	public void paintComponent(Graphics g) {
		buffer.drawImage(scoreImage, 0,0,  this);
		g.drawImage(background, 0,0, this);
	}
	
	/**
	 * The method displayTime to change the timeTF label
	 * @param time : int coming from the timer of TowerDefense
	 */
	public void displayTime (int time) {
		timeTF.setText("Time : "+ time/20 + " s ");
	}
	
	/**
	 * The method displayScore to change the scoreTF label
	 * @param score : int modified when a minion is killed
	 */
	public void displayScore (int score) {
		scoreTF.setText("Score : "+ score);
	}
	
	/**
	 * The method displayWave to change the nbWaveT label
	 * @param wave : int corresponding to the level in TowerDefense
	 */
	public void displayWave (int wave) {
		nbWaveT.setText("Wave number : "+ wave);
	}
	
	/**
	 * The method displayLives to change the nbLivesT label
	 * @param lives : int modified when a minion arrives at the end of the path
	 */
	public void displayLives (int lives) {
		nbLivesT.setText("Remaining lives : "+ lives);
	}
	
	/**
	 * The method displayMoney to change the moneyT label
	 * @param money : int modified when a minion is killed, a tower is purchased or a tower is sold
	 */
	public void displayMoney (int money) {
		moneyT.setText("Money : "+ money);
	}
	
	
	/**
	 * The method to act when the button menu is clicked
	 * @param e : ActionEvent corresponding to the button menu clicked
	 */
	public void actionPerformed(ActionEvent e ) {
		d = new JOptionPane();
		if( d.showConfirmDialog(getFrame(), "Are you sure you want to quit? All progress will be lost", "Warning !", JOptionPane.OK_CANCEL_OPTION)==0) {
			getFrame().dispose();
			FenetreInitiale fenIni = new FenetreInitiale();
		}
	}
	
	/**
	 * The method getFrame used by the JOptionPane
	 * @return component : Frame we need to use the JOptionPane
	 */
	public Frame getFrame() {
		Component component=this;
		do{
			component=component.getParent();
		} while(!(component instanceof Frame));
		return (Frame)component;
	}
	
	
}

