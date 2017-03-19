import java.awt.Image;
import java.awt.Point;
import java.awt.Color;
import java.awt.Rectangle;
import java.io.File;

import javax.imageio.ImageIO;

public enum Tour {
	TOUR1("Tour1", 50, 30, 50, 10.0, "tower1.png", 5, Color.green),
	TOUR2("Tour2", 50, 30, 50, 10.0, "tower2.png", 5, Color.green),
	TOUR3("Tour3", 50, 30, 50, 10.0, "tower3.png", 5, Color.green),
	TOUR4("Tour4", 50, 30, 50, 10.0, "tower4.png", 5, Color.green),
	TOUR5("Tour5", 50, 30, 50, 10.0, "tower5.png", 5, Color.green),
	TOUR6("Tour6", 50, 30, 50, 10.0, "tower6.png", 5, Color.green),
	TOUR7("Tour7", 50, 30, 50, 10.0, "tower7.png", 5, Color.green),
	TOUR8("Tour8", 50, 30, 50, 10.0, "tower8.png", 5, Color.green);
	
	public String name;
	public double power;
	public Image img;
	public Point position;
	public int price;
	public int priceUpgrade1;
	public int priceUpgrade2;
	public int rangeNb;
	public int numUpgrade;
	public Rectangle range;
	public final int TAILLE_CASE = 32;
	public Color colorShoot;
	
	Tour(String name, int price, int priceUpgrade1, int priceUpgrade2, double power, String imageAddress, int range, Color colorShoot) {
		this.name = name ;
		this.price = price;
		this.priceUpgrade1 = priceUpgrade1;
		this.priceUpgrade2 = priceUpgrade2;
		this.power = power;
		this.rangeNb=range;
		this.numUpgrade=0;
		this.colorShoot = colorShoot;
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
		if (numUpgrade<2) {
			power *= 1.5;
			rangeNb++;
			range.setSize((2*rangeNb+1)*TAILLE_CASE,(2*rangeNb+1)*TAILLE_CASE);
			numUpgrade++;
		}
	}
	
	public String toString() {
		String s ="Tour : "+name+"<br>";
		s += "power : "+power+"<br>";
		s += "range : "+rangeNb+" cases <br>";
		s += "price : "+price+"<br>";
		s += "price upgrade 1 : "+priceUpgrade1+"<br>";
		s += "price upgrade 2 : "+priceUpgrade2+"<br>";
		return s;
	}
		
}
