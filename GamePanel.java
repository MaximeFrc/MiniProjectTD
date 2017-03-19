import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.io.File;


public class GamePanel extends JPanel implements MouseListener,MouseMotionListener  {
	
	Graphics buffer; 
	Image wallpaper;
	BufferedImage background;
	int widthGP;
	int heightGP;
	public Case [][] tabCase;
	public Path p;
	public final int TAILLE_CASE = 32;
	TowerDefense td;
	int xprevious;
	int yprevious;
	
	public GamePanel (int width, int height, String image, int numPath, TowerDefense td) {
		
		this.widthGP = width -300;
		this.heightGP = height-100;
		this.setBounds(0,100,widthGP,heightGP);
		this.setLayout(null);
		
		this.td = td;
		
		
		//definition tableau de jeu
		tabCase = new Case[widthGP/TAILLE_CASE][heightGP/TAILLE_CASE];
		for (int i=0; i<tabCase.length; i++) {
			for (int j=0; j<tabCase[0].length; j++) {
				tabCase[i][j] = new Case(i*TAILLE_CASE, j*TAILLE_CASE);
			}
		}
		
		p= new Path(numPath,tabCase);

		
		/*Creation of the tool to get images*/
		Toolkit T=Toolkit.getDefaultToolkit();
		
		/*Creation of the background in which we will draw"*/
		background = new BufferedImage(getSize().width,getSize().height,BufferedImage.TYPE_INT_RGB);
		
		/*Everything is written is the background */
		buffer = background.getGraphics(); 
		
		/*Loading the wallpaper*/
		wallpaper = T.getImage(image);
		
		/*the panel listens*/
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	
	public void paintComponent(Graphics g) {
		buffer.drawImage( wallpaper,0,0, widthGP,heightGP,  this);
		
		for (int i=0; i<tabCase.length; i++) {
			for (int j=0; j<tabCase[0].length; j++) {
				if(tabCase[i][j].thereIsTower && !tabCase[i][j].towerIsActive) {
					Point positionCase = tabCase[i][j].position;
					int r = tabCase[i][j].tour.rangeNb;
					buffer.fillRect((int)(positionCase.getX())-r*TAILLE_CASE,(int)(positionCase.getY())-r*TAILLE_CASE,(2*r+1)*TAILLE_CASE,(2*r+1)*TAILLE_CASE);
				}
			}
		}
		
		for (int i=0; i<tabCase.length; i++) {
			for (int j=0; j<tabCase[0].length; j++) {
				buffer.drawImage(tabCase[i][j].img,TAILLE_CASE*i,TAILLE_CASE*j, TAILLE_CASE, TAILLE_CASE, this);
				
			}
		}
		for (int i = 0; i<td.tabMinion.length ; i++) {
			if (td.tabMinion[i].position != null) {
				buffer.drawImage(td.tabMinion[i].img,(int) td.tabMinion[i].getX(),(int) td.tabMinion[i].getY(), TAILLE_CASE, TAILLE_CASE,  this);
			}
		}
		for(int i = 0; i < td.shootList.size(); i++) {
			Shoot sh = td.shootList.get(i);
			buffer.setColor(sh.colorShoot);
			buffer.drawLine((int) sh.origineShoot.getX(), (int) sh.origineShoot.getY(), (int) sh.finishShoot.getX(), (int) sh.finishShoot.getY());
		}
		
		//buffer.fillRect
		g.drawImage(background, 0,0,widthGP,heightGP, this);
	}
	
	public void mouseClicked (MouseEvent e) {
		int x = (int)(e.getPoint().getX());
		int y = (int)(e.getPoint().getY());
		for (int i=0; i<tabCase.length; i++) {
			for (int j=0; j<tabCase[0].length; j++) {
				if (x > (int)(tabCase[i][j].getX()) && x < (int)(tabCase[i][j].getX())+TAILLE_CASE && y> (int)(tabCase[i][j].getY()) && y< (int)(tabCase[i][j].getY())+TAILLE_CASE && td.interf.numTowerChosen!=-1 && !tabCase[i][j].towerIsActive ){
					if (td.money >= td.selectedTower().price){
						tabCase[i][j].addTour(td.selectedTower());
						tabCase[i][j].towerIsActive=true;
						td.money -= td.selectedTower().price;
						td.interf.towerTabButton[td.interf.numTowerChosen-1].setBackground(null);
						td.interf.numTowerChosen=-1;
					}
				}
			}
		}
	}
	
	
	
	
	public void mouseEntered (MouseEvent e) {}
	public void mouseExited (MouseEvent e) {}
	public void mousePressed (MouseEvent e) {}
	public void mouseReleased (MouseEvent e) {}
	public void mouseMoved (MouseEvent e) {
		int x = (int)(e.getPoint().getX());
		int y = (int)(e.getPoint().getY());
		for (int i=0; i<tabCase.length; i++) {
			for (int j=0; j<tabCase[0].length; j++) {
				
				if(xprevious > (int)(tabCase[i][j].getX()) && xprevious < (int)(tabCase[i][j].getX())+TAILLE_CASE && yprevious> (int)(tabCase[i][j].getY()) && yprevious < (int)(tabCase[i][j].getY())+TAILLE_CASE && tabCase[i][j].thereIsTower && !tabCase[i][j].towerIsActive && tabCase[i][j].path != true){
					tabCase[i][j].removeTour();
					
					/*try {
						tabCase[i][j].img= ImageIO.read(new File("caseBasic.png")); 
					} catch(Exception err) {
						System.out.println(" not found !");            
						System.exit(0);    
					}*/
				}
				
				if (x > (int)(tabCase[i][j].getX()) && x < (int)(tabCase[i][j].getX())+TAILLE_CASE && y> (int)(tabCase[i][j].getY()) && y< (int)(tabCase[i][j].getY())+TAILLE_CASE && td.interf.numTowerChosen!=-1 && !tabCase[i][j].towerIsActive ){
					tabCase[i][j].addTour(td.selectedTower());
					//int range = td.selectedTower().rangeNbCase;
				
				}
			}
		}
		xprevious = x;
		yprevious = y;
	}
	public void mouseDragged (MouseEvent e) {}
	
}

