/**
 * The class TowerDefense that inherits from JFrame and implements ActionListener and MouseMotionListener 
 * to create the window TowerDefense
 * 
 */

// Load libraries
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.ArrayList;

public class TowerDefense extends JFrame implements ActionListener,MouseMotionListener{
	
	//Attributes
	
	/* Parameters of the window */
	public int width = 1008;
	public int height = 706;
	
	/* Components of the window */
	public JPanel mainPanel;
	public Interface interf;
	public GamePanel gamePanel;
	public ScorePanel scorePanel;
	
	/* Timer */
	public Timer timer;
	public int temps =0;
	
	/* Parameters to play */
	public int level;
	public int nbLives;
	public int money;
	public int score;
	public int difficulty;
	public int ximg;
	public int yimg;
	public int minionToCreate;
	public int buttonPressed;
	public boolean creatingMinions;
	public Tour chosenTower;
	public Minion tabMinion [];
	public ArrayList<Shoot> shootList;
	
	//Methodes

	/**
	 * The construtor
	 * @param path : int that comes from the the buttons clicked on FenetreInitiale
	 * @param difficulty : int that comes from the comboBox on the FenetreInitiale
	 */	
	public TowerDefense (int path, int difficulty) {
		
		/*Window creation*/
		super("TOWER DEFENSE");
		setSize(width,height);
		setLocation (100,10);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		
		/*beginning of the party*/
		nbLives = 5;
		money = 300;
		score = 0;
		level = 0;
		this.difficulty = difficulty;
		creatingMinions = false;
		shootList = new ArrayList();
		tabMinion = new Minion[1]; //to initialize the game even if this minion won't appear
		tabMinion[0] = new Minion("Minion1", 500, 4, "minion1.png");
		
		/*Main Panel initialisation*/
		mainPanel = new JPanel();
		mainPanel.setBounds(10,10,width,height);
		mainPanel.setLayout(null);
		setContentPane(mainPanel);
		
		
		/*Interface initialisation*/
		interf = new Interface(width-5,height, "imginterf.png", this);
		mainPanel.add(interf);
		
		/*GamePanel initialisation*/
		gamePanel = new GamePanel(width,height,"wall1.png", path, this); //MODIFIER ENLEVER LE THIS
		mainPanel.add(gamePanel);
		
		/*Score Panel creation*/
		scorePanel = new ScorePanel(width,height,"imgSP.png");
		mainPanel.add(scorePanel);
		
		/*Timer initialisation*/
		timer = new Timer (50, this);
		timer.start();
		
		/* add listeners to the mouse*/
		gamePanel.addMouseMotionListener(this);
	}
	
	/**
	 * The method nextLevel to begin a new level
	 * called by the button sendMinions int Interface
	 */
	public void nextLevel() {
		level++;
		
		/*tabMinion initialisation*/
		tabMinion = new Minion[9+level];
		
		double upgrdHP = Math.pow(2,0.4*(level+(0.2*difficulty))); //HP depends on the level we play on
		switch (level%4) {
			case 0 :
			for (int i = 0; i<tabMinion.length ; i++) {
				tabMinion[i]= new Minion("Minion4", 800*upgrdHP, 8, "minion4.png");
			}
			break;
			case 1 :
			for (int i = 0; i<tabMinion.length ; i++) {
				tabMinion[i]= new Minion("Minion1", 1200*upgrdHP, 4, "minion1.png");
			}
			break;
			case 2 :
			for (int i = 0; i<tabMinion.length ; i++) {
				tabMinion[i]= new Minion("Minion2", 1500*upgrdHP, 4, "minion2.png");
			}
			break;
			case 3 :
			for (int i = 0; i<tabMinion.length ; i++) {
				tabMinion[i]= new Minion("Minion3", 600*upgrdHP, 8, "minion3.png");
			}
			break;
		}
	}
			
	/**
	 * The method to act when the timer is running
	 * @param e : ActionEvent corresponding to a new iteration of the timer
	 */
	public void actionPerformed(ActionEvent e) {
		scorePanel.displayLives(nbLives);
		
		/* end the game */
		if (nbLives<=0) { 
			interf.isPause = false;
		}  
		
		/* update the game */
		if(interf.isPause) { 
			temps++; 
			scorePanel.displayTime(temps);
			scorePanel.displayWave(level);
			scorePanel.displayMoney(money);
			scorePanel.displayScore(score);
		
		/* Create new minions */
		if (temps%(5*(10-tabMinion[0].speed)) == 0) {
			if (creatingMinions) {
				tabMinion[minionToCreate].create(gamePanel.p.path);
				minionToCreate++;
				if (minionToCreate >= tabMinion.length) {
					creatingMinions = false;
				}
			}
		}
		
		/* Move the minions */	
			
		for (int i = 0; i < tabMinion.length ; i++) {
			if (tabMinion[i].position!=null) {
				tabMinion[i].update();
				if (tabMinion[i].position.equals(gamePanel.p.path[gamePanel.p.path.length-1].position)) {
					tabMinion[i].position=null;
					this.nbLives--;
				}	
			}
		}
		
		/* Kill the minions */
		shootList = new ArrayList();
		for (int i = 0; i < gamePanel.tabCase.length ; i++) {
			for (int j = 0; j < gamePanel.tabCase[0].length ; j++) {
				if (gamePanel.tabCase[i][j].tour != null &&gamePanel.tabCase[i][j].towerIsActive ) {
					for (int k = 0; k < tabMinion.length ; k++) {
						if (tabMinion[k].position != null) {
							gamePanel.tabCase[i][j].tour.setPosition(gamePanel.tabCase[i][j]);
						if (gamePanel.tabCase[i][j].tour.range.intersects(tabMinion[k].rect)) {
							Shoot sh = new Shoot(gamePanel.tabCase[i][j], tabMinion[k]);
							shootList.add(sh);
							if (sh.killingShoot) {
								score += 10 * level;
								money += 10 + 8*level;
							}
							
							break;
						}
						}
					}
				}
			}
		}
			repaint();
		}
    }

	/**
	 * The method paintComponent to paint the whole panel
	 * @param g : Graphics where we draw
	 */ 
	public void paintComponent(Graphics g) {
		g.drawImage(interf.imgPurchasedTower, ximg-(int)this.getLocationOnScreen().getX(), yimg-(int)this.getLocationOnScreen().getY(), this);
	}
	
	/**
	 * The method selectedTower to select a tower when a button is clicked in the interface
	 * @return t : Tour we selected
	 */ 
	public Tour selectedTower () {
		Tour t=Tour.TOUR1; //in case we have a problem with the buttons
		switch (interf.numTowerChosen) {
			case 1 : t= Tour.TOUR1;
				break;
			case 2: t= Tour.TOUR2;
				break;
			case 3 : t= Tour.TOUR3;
				break;
			case 4: t=Tour.TOUR4;
				break;
			case 5 : t= Tour.TOUR5;
				break;
			case 6 : t= Tour.TOUR6;
				break;
			case 7 : t= Tour.TOUR7;
				break;
			case 8 : t= Tour.TOUR8;
				break;
			default : 
				break;
		}
		return t;
	}
	
	
	/* Overload mouseMotionListener methods */
	public void mouseDragged( MouseEvent e ) {}
	public void mouseMoved( MouseEvent e ) {}
	
}

