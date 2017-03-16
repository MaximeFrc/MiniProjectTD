import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;

public class TowerDefense extends JFrame implements ActionListener{
	
	/*Parameters of the window*/
	int width = 1008;
	int height = 706;
	
	/* Components of the window*/
	JPanel mainPanel;
	Interface interf;
	GamePanel gamePanel;
	ScorePanel scorePanel;
	
	/*Timer */
	Timer timer;
	int temps =0;
	
	/*Parameters to play*/
	Minion tabMinion [];
	int level;
	int nbLives;
	boolean creatingMinions;
	int minionToCreate;

	
	public TowerDefense (int path, int difficulty) {
		

		/*Window creation*/
		super("TOWER DEFENSE");
		setSize(width,height);
		setLocation (100,10);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		
		/*beginning of the party*/
		nbLives = 50;
		level=0;
		creatingMinions = false;
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
		
		
	}
	
	public void nextLevel() {
		level++;
		
		/*tabMinion initialisation*/
		tabMinion = new Minion[9+level];
		
		double upgrdHP = 1 + 0.2 * level;
		switch (level%4) {
			case 0 :
			for (int i = 0; i<tabMinion.length ; i++) {
				tabMinion[i]= new Minion("Minion4", 500*upgrdHP, 4, "minion1.png");
			}
			break;
			case 1 :
			for (int i = 0; i<tabMinion.length ; i++) {
				tabMinion[i]= new Minion("Minion1", 500*upgrdHP, 4, "minion1.png");
			}
			break;
			case 2 :
			for (int i = 0; i<tabMinion.length ; i++) {
				tabMinion[i]= new Minion("Minion2", 500*upgrdHP, 4, "minion1.png");
			}
			break;
			case 3 :
			for (int i = 0; i<tabMinion.length ; i++) {
				tabMinion[i]= new Minion("Minion3", 500*upgrdHP, 4, "minion1.png");
			}
			break;
		}
	}
			
	
	
	public void actionPerformed(ActionEvent e) {   
		if(interf.isPause) {
			temps++; 
			scorePanel.displayTime(temps);
		
		/* Create new minions */
		if (temps%10 == 0) {
			if (creatingMinions) {
				tabMinion[minionToCreate].create(gamePanel.p.path);
				System.out.println("create "+ minionToCreate);
				minionToCreate++;
				if (minionToCreate >= tabMinion.length) {
					creatingMinions = false;
				}
			}
		}
		
		/* Move the minions */	
			
		for (int i = 0; i < tabMinion.length ; i++) {
			if (tabMinion[i].position!=null) {
				System.out.println("position de : " + i +": ("+tabMinion[i].getX()+";"+tabMinion[i].getY()+")");
				tabMinion[i].update();
				if (tabMinion[i].position.equals(gamePanel.p.path[gamePanel.p.path.length-1].position)) {
					tabMinion[i].position=null;
					this.nbLives--;
				}	
			}
		}
			//scorePanel.displayScore(Player.score);
			repaint();
		}
    }
	
}

