/**
 * The enumeration Tour that contains the Tour we can put in the tabCase of the GamePanel 
 * to create the window TowerDefense
 * 
 */

// Load libraries
import java.awt.Image;
import java.awt.Point;
import java.awt.Color;
import java.awt.Rectangle;
import java.io.File;
import javax.imageio.ImageIO;

public enum Tour {
	
	// object Tour 
	
	TOUR1("Tour1", 100, 200, 300, 25.0, "tower1.png", 2, Color.red),
	TOUR2("Tour2", 200, 400, 600, 50.0, "tower2.png", 3, Color.green),
	TOUR3("Tour3", 400, 800, 1200, 150.0, "tower3.png", 2, Color.orange),
	TOUR4("Tour4", 800, 1600, 2400, 300.0, "tower4.png", 3, Color.blue),
	TOUR5("Tour5", 2000, 4000, 6000, 800.0, "tower5.png", 2, Color.cyan),
	TOUR6("Tour6", 2500, 5000, 7500, 800.0, "tower6.png", 4, Color.lightGray),
	TOUR7("Tour7", 4000, 8000, 12000, 1000.0, "tower7.png", 3, Color.darkGray),
	TOUR8("Tour8", 6000, 12000, 18000, 1300.0, "tower8.png", 5, Color.black);
	
	//Attributes

	public final int TAILLE_CASE = 32;	

	public int price;
	public int priceUpgrade1;
	public int priceUpgrade2;
	public int rangeNb;
	public int numUpgrade;
	public double power;
	public String name;
	public Image img;
	public Point position;
	public Rectangle range;
	public Color colorShoot;
	
	//Methodes

	/**
	 * The construtor
	 * @param name : String that is displayed when the buttons of the tour is pressed
	 * @param price : int that is the cost of the tour
	 * @param priceUprade1 : int that is the cost to upgrade the tour the first time
	 * @param priceUprade2 : int that is the cost to upgrade the tour the seconde time
	 * @param power : double that is the HP it takes to the minions
	 * @param imageAddress : Sting to access the background image
	 * @param range : int that represent the number of case the tour can access
	 * @param colorShoot : Color for thelaser on the screen
	 */
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
	
	/**
	 * The method setPosition to put a Tour on a case
	 * @param c : Case on which we put the Tour 
	 */
	public void setPosition(Case c) {
		position = c.position;
		range.setLocation((int) (c.getX() - (range.getWidth()-TAILLE_CASE)/2) , (int) (c.getY() - (range.getHeight()-TAILLE_CASE)/2));
	}
	
	/**
	 * The method upgrade to upgade the Tour
	 */
	public void upgrade() {
		if (numUpgrade<2) {
			power *= 1.5;
			rangeNb++;
			range.setSize((2*rangeNb+1)*TAILLE_CASE,(2*rangeNb+1)*TAILLE_CASE);
			numUpgrade++;
		}
	}
	
	/**
	 * The method toString display the description of the tower
	 * @return s : String that is the text
	 */
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
