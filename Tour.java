import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;

import javax.imageio.ImageIO;

public enum Tour {
	TOUR1("Tour1", 50, 30, 50, 10.0, "tour1.png", 2),
	TOUR2("Tour2", 50, 30, 50, 10.0, "tour2.png", 2),
	TOUR3("Tour3", 50, 30, 50, 10.0, "tour3.png", 2),
	TOUR4("Tour4", 50, 30, 50, 10.0, "tour4.png", 2),
	TOUR5("Tour5", 50, 30, 50, 10.0, "tour5.png", 2),
	TOUR6("Tour6", 50, 30, 50, 10.0, "tour6.png", 2);
	
	public String name;
	public double power;
	public Image img;
	public Point position;
	public int price;
	public int priceUpgrade1;
	public int priceUpgrade2;
	public Rectangle range;
	public final int TAILLE_CASE = 32;
	
	Tour(String name, int price, int priceUpgrade1, int priceUpgrade2, double power, String imageAddress, int range) {
		this.name = name ;
		this.price = price;
		this.priceUpgrade1 = priceUpgrade1;
		this.priceUpgrade2 = priceUpgrade2;
		this.power = power;
		this.range = new Rectangle((2*range+1)*TAILLE_CASE,(2*range+1)*TAILLE_CASE);
		try {
            img= ImageIO.read(new File(imageAddress));
       } catch(Exception err) {
           System.out.println(imageAddress+" not found !");            
           System.exit(0);    
       }
	}
	
	public void setPosition(Case c) {
		position = c.position;
		range.setLocation((int) (c.getX() - (range.getWidth()-TAILLE_CASE)/2) , (int) (c.getY() - (range.getHeight()-TAILLE_CASE)/2));
	}
	
	public void upgrade() {
	
	}
}
