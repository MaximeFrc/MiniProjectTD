import java.awt.*;
import javax.swing.*;
import java.awt.image.*;


public class GamePanel extends JPanel  {
	
	Graphics buffer; 
	Image wallpaper;
	BufferedImage background;
	int widthGP;
	int heightGP;
	public Case [][] tabCase;
	public Path p;
	public final int TAILLE_CASE = 32;
	TowerDefense td;
	
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
		
		
	}
	
	
	public void paintComponent(Graphics g) {
		buffer.drawImage( wallpaper,0,0, widthGP,heightGP,  this);
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
		g.drawImage(background, 0,0,widthGP,heightGP, this);
	}
	
}

