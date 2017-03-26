import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.ArrayList;

public class TowerDefense extends JFrame implements ActionListener,MouseListener,MouseMotionListener{
	
	/*Parameters of the window*/
	int width = 1008;
	int height = 706;
	
	/* Components of the window*/
	JPanel mainPanel;
	Interface interf;
	GamePanel gamePanel;
	ScorePanel scorePanel;
	
	//JPanel purchasedTower;
	
	
	/*Timer */
	Timer timer;
	int temps =0;
	
	/*Parameters to play*/
	Minion tabMinion [];
	int level;
	int nbLives;
	int money;
	int score;
	boolean creatingMinions;
	int minionToCreate;
	Tour chosenTower;
	ArrayList<Shoot> shootList;
	int difficulty;
	
	int buttonPressed;
	
	int ximg;
	int yimg;

	
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
		tabMinion = new Minion[1];
		tabMinion[0] = new Minion("Minion1", 500, 4, "minion1.png");;
		
		
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
		
		/*Test de tour*/
		chosenTower=Tour.TOUR1;
		
		
		this.addMouseMotionListener(this);
		gamePanel.addMouseMotionListener(this);
		for(int i=0; i<interf.towerTabButton.length;i++) {
			interf.towerTabButton[i].addMouseMotionListener(this);
		}
		
	}
	
	public void nextLevel() {
		level++;
		
		/*tabMinion initialisation*/
		tabMinion = new Minion[9+level];
		
		double upgrdHP = level+(0.5*difficulty);
		switch (level%4) {
			case 0 :
			for (int i = 0; i<tabMinion.length ; i++) {
				tabMinion[i]= new Minion("Minion4", 1200*upgrdHP, 8, "minion4.png");
			}
			break;
			case 1 :
			for (int i = 0; i<tabMinion.length ; i++) {
				tabMinion[i]= new Minion("Minion1", 2000*upgrdHP, 2, "minion1.png");
			}
			break;
			case 2 :
			for (int i = 0; i<tabMinion.length ; i++) {
				tabMinion[i]= new Minion("Minion2", 1500*upgrdHP, 4, "minion2.png");
			}
			break;
			case 3 :
			for (int i = 0; i<tabMinion.length ; i++) {
				tabMinion[i]= new Minion("Minion3", 1000*upgrdHP, 8, "minion3.png");
			}
			break;
		}
	}
			
	
	
	public void actionPerformed(ActionEvent e) {
		scorePanel.displayLives(nbLives);
		if (nbLives<=0) {
			interf.isPause = false;
		}  
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
								money += 10 + 2*level;
							}
							
							break;
						}
						}
					}
				}
			}
		}
		
			//scorePanel.displayScore(Player.score);
			repaint();
		}
    }
    
    public void mouseEntered( MouseEvent e )  {}
	public void mouseDragged( MouseEvent e ) {
		//condition si c'est sur les boutons
		ximg = (int)(e.getPoint().getX()+this.getLocationOnScreen().getX()); //ajouter coord par rapport à l'écran
		yimg = (int)(e.getPoint().getY()+this.getLocationOnScreen().getY());
		
		System.out.println("x : "+ximg+" y : "+yimg);
	}
	public void mousePressed (MouseEvent e) {}
	public void mouseClicked (MouseEvent e) {}
	public void mouseReleased (MouseEvent e) {}
	public void mouseExited (MouseEvent e) {}
	public void mouseMoved( MouseEvent e ) { }
	
	public void paintComponent(Graphics g) {
		g.drawImage(interf.imgPurchasedTower, ximg-(int)this.getLocationOnScreen().getX(), yimg-(int)this.getLocationOnScreen().getY(), this);
	}
	
	public Tour selectedTower () {
		Tour t=Tour.TOUR1;
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
}

