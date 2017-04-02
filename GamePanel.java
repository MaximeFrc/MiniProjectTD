/**
 * The class GamePanel that inherits from JPanel and implements MouseListener and MouseMotionListener
 * to display the advancement of the game 
 * 
 */

// Load libraries
import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.io.File;

public class GamePanel extends JPanel implements MouseListener,MouseMotionListener  {
	
	//Attributes
	
	/* Size of the Panel */
	public int widthGP;
	public int heightGP;
	
	/* Components of the Panel */
	public Graphics buffer; 
	public Image wallpaper;
	public Image gameover;
	public BufferedImage background;
	
	/* Non graphic attributes */
	public final int TAILLE_CASE = 32;
	
	public int xprevious;
	public int yprevious;
	public TowerDefense td;
	public Path p;
	public Case [][] tabCase;

	//Methodes

	/**
	 * The construtor
	 * @param width : int that comes from the width of the TowerDefense frame
	 * @param height : int that comes from the height of the TowerDefense frame
	 * @param image : Sting to access the background image
	 * @param numPath : int that comes from the height of the TowerDefense frame
	 * @param td : TowerDefense informations of the game
	 */
	public GamePanel (int width, int height, String image, int numPath, TowerDefense td) {
		
		this.widthGP = width -300;
		this.heightGP = height-100;
		this.setBounds(0,100,widthGP,heightGP);
		this.setLayout(null);
		this.td = td;
		
		/* Definition of the game array */
		tabCase = new Case[widthGP/TAILLE_CASE][heightGP/TAILLE_CASE];
		for (int i=0; i<tabCase.length; i++) {
			for (int j=0; j<tabCase[0].length; j++) {
				tabCase[i][j] = new Case(i*TAILLE_CASE, j*TAILLE_CASE);
			}
		}
		this.p= new Path(numPath,tabCase);
		
		try {
			gameover = ImageIO.read(new File("gameover.png"));
		} catch(Exception err) {
			System.out.println("gameover.png not found !");            
			System.exit(0);    
		}

		
		/*Creation of the tool to get images*/
		Toolkit T=Toolkit.getDefaultToolkit();
		
		/*Creation of the background in which we will draw"*/
		background = new BufferedImage(getSize().width,getSize().height,BufferedImage.TYPE_INT_RGB);
		
		/*Everything is written is the background */
		buffer = background.getGraphics(); 
		
		/*Loading the wallpaper*/
		wallpaper = T.getImage(image);
		
		/* add listeners to the mouse*/
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	/**
	 * The method paintComponent to paint when TowerDefense call it
	 * @param g : Graphics where we draw
	 */ 
	public void paintComponent(Graphics g) {
		
		/* background GamePanel */
		buffer.drawImage( wallpaper,0,0, widthGP,heightGP,  this);
		
		/* Tour being placed */
		for (int i=0; i<tabCase.length; i++) {
			for (int j=0; j<tabCase[0].length; j++) {
				if(tabCase[i][j].thereIsTower && !tabCase[i][j].towerIsActive) {
					Point positionCase = tabCase[i][j].position;
					int r = tabCase[i][j].tour.rangeNb;
					buffer.fillRect((int)(positionCase.getX())-r*TAILLE_CASE,(int)(positionCase.getY())-r*TAILLE_CASE,(2*r+1)*TAILLE_CASE,(2*r+1)*TAILLE_CASE);
				}
			}
		}
		
		/* Displey the Cases */
		for (int i=0; i<tabCase.length; i++) {
			for (int j=0; j<tabCase[0].length; j++) {
				buffer.drawImage(tabCase[i][j].img,TAILLE_CASE*i,TAILLE_CASE*j, TAILLE_CASE, TAILLE_CASE, this);
				
			}
		}
		
		/* Display the Minions */
		for (int i = 0; i<td.tabMinion.length ; i++) {
			if (td.tabMinion[i].position != null) {
				buffer.drawImage(td.tabMinion[i].img,(int) td.tabMinion[i].getX(),(int) td.tabMinion[i].getY(), TAILLE_CASE, TAILLE_CASE,  this);
				buffer.setColor(Color.green);
				buffer.drawRect((int) td.tabMinion[i].getX(), (int) td.tabMinion[i].getY(), (int) ((td.tabMinion[i].HP*TAILLE_CASE)/td.tabMinion[i].initialHP) , 2);
			}
		}
		
		/* Display the list of shoot*/
		for(int i = 0; i < td.shootList.size(); i++) {
			Shoot sh = td.shootList.get(i);
			buffer.setColor(sh.colorShoot);
			buffer.drawLine((int) sh.origineShoot.getX(), (int) sh.origineShoot.getY(), (int) sh.finishShoot.getX(), (int) sh.finishShoot.getY());
			buffer.setColor(Color.white);
		}
		
		/* Game over image at the end of the game */ 
		if (td.nbLives<=0) {
			buffer.drawImage(gameover, 0, 0, widthGP, heightGP, this);
		}
		
		/* put the image of the buffer in the graphics g */
		g.drawImage(background, 0,0,widthGP,heightGP, this);
	}
	
	/**
	 * The method mouseClicked to interact with the panel
	 * @param e : MouseEvent that correspond to the mouse when we click
	 */ 
	public void mouseClicked (MouseEvent e) {
		
		/* Position of the mouse */
		int x = (int)(e.getPoint().getX());
		int y = (int)(e.getPoint().getY());
		
		/* Find the case we want to deal with */
		for (int i=0; i<tabCase.length; i++) {
			for (int j=0; j<tabCase[0].length; j++) {
				
				/*Set a tower in a case of the panel*/
				if (x > (int)(tabCase[i][j].getX()) && x < (int)(tabCase[i][j].getX())+TAILLE_CASE && y> (int)(tabCase[i][j].getY()) && y< (int)(tabCase[i][j].getY())+TAILLE_CASE && td.interf.numTowerChosen!=-1 && !tabCase[i][j].towerIsActive && !tabCase[i][j].path ){
					if (td.money >= td.selectedTower().price){
						tabCase[i][j].addTour(td.selectedTower());
						tabCase[i][j].towerIsActive=true;
						td.money -= td.selectedTower().price;
						td.interf.towerTabButton[td.interf.numTowerChosen-1].setBackground(null);
						td.interf.numTowerChosen=-1;
					}
				}
				
				/*Delete a tower */
				else if(x > (int)(tabCase[i][j].getX()) && x < (int)(tabCase[i][j].getX())+TAILLE_CASE && y> (int)(tabCase[i][j].getY()) && y< (int)(tabCase[i][j].getY())+TAILLE_CASE && tabCase[i][j].towerIsActive && td.interf.isDeleteTower) {
					td.money += (7.0/10.0)*tabCase[i][j].tour.price;
					tabCase[i][j].removeTour();
					td.interf.isDeleteTower=false;
					td.interf.deleteTower.setBackground(null);
					System.out.println("undelete");
				}
			}
		}
	}

	/**
	 * The method mouseMoved to see the tower on the map when we want to add it on the GamePanel
	 * @param e : MouseEvent that correspond to the mouse when we move it
	 */ 
	public void mouseMoved (MouseEvent e) {
		
		/* Position of the mouse */
		int x = (int)(e.getPoint().getX());
		int y = (int)(e.getPoint().getY());
		
		/* Find the case we want to deal with */
		for (int i=0; i<tabCase.length; i++) {
			for (int j=0; j<tabCase[0].length; j++) {
				
				if(xprevious > (int)(tabCase[i][j].getX()) && xprevious < (int)(tabCase[i][j].getX())+TAILLE_CASE && yprevious> (int)(tabCase[i][j].getY()) && yprevious < (int)(tabCase[i][j].getY())+TAILLE_CASE && tabCase[i][j].thereIsTower && !tabCase[i][j].towerIsActive && tabCase[i][j].path != true){
					tabCase[i][j].removeTour();
				}
				
				if (x > (int)(tabCase[i][j].getX()) && x < (int)(tabCase[i][j].getX())+TAILLE_CASE && y> (int)(tabCase[i][j].getY()) && y< (int)(tabCase[i][j].getY())+TAILLE_CASE && td.interf.numTowerChosen!=-1 && !tabCase[i][j].towerIsActive ){
					tabCase[i][j].addTour(td.selectedTower());
				
				}
			}
		}
		xprevious = x;
		yprevious = y;
	}
	
	/* Overload mouseListener and mouseMotionListener methods */
	public void mouseDragged (MouseEvent e) {}
	public void mouseEntered (MouseEvent e) {}
	public void mouseExited (MouseEvent e) {}
	public void mousePressed (MouseEvent e) {}
	public void mouseReleased (MouseEvent e) {}
	
}

