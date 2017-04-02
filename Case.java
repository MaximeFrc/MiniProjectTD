/**
 * The class Case used into the GamePanel 
 * to create the path and contain Tour
 * 
 */

// Load libraries
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import javax.imageio.ImageIO;

public class Case {
	
	//Attributes
	
	public int length;
	public boolean path;
	public boolean thereIsTower;
	public boolean towerIsActive; //use to put a Tour on the case
	public Image img;
	public Tour tour;
	public Point position;
	
	//Methodes

	/**
	 * The construtor
	 * @param x : int to define position in x
	 * @param x : int to define position in y
	 */
	public Case(int x, int y) {
		this.position = new Point(x,y);
		this.path = false;
		try {
			img= ImageIO.read(new File("caseBasic.png"));
       } catch(Exception err) {
			System.out.println(" not found !");            
			System.exit(0);    
       }
		this.tour = null;
		thereIsTower = false;
	}
	
	/**
	 * The method addTour to add a Tour in the Case
	 * @param t : Tour we want to put in the Case
	 */ 
	public void addTour(Tour t) {
		if (!path) {
			img = t.img;
			this.tour = t;
			thereIsTower = true;
		}
	}
	
	/**
	 * The method removeTour to remove a Tour from a Case
	 */
	public void removeTour () {
		if(!path) {
			//we use the initial image of the Case
			try {
				img= ImageIO.read(new File("caseBasic.png")); 
			} catch(Exception err) {
				System.out.println("caseBasic.png not found !");            
				System.exit(0);    
			}
			this.tour = null;
			thereIsTower=false;
			towerIsActive=false;
		}
	}
	
	/**
	 * The method setPath to define a Case as a path
	 */
	public void setPath() {
		this.path = true;
		try {
			img= ImageIO.read(new File("casePath.png"));
		} catch(Exception err) {
			System.out.println(" not found !");            
			System.exit(0);    
		}
	}
		
	/**
	 * The method X to get the position in x
	 * @return position.getX() : double that is x
	 */
	public double getX() {
		return position.getX();
	}
	
	/**
	 * The method getY to get the position in y
	 * @return position.getY() : double that is y
	 */
	public double getY() {
		return position.getY();
	}
	
	/**
	 * The method setX to set the position in x
	 * @param x : int to define the position in x
	 */
	public void setX(int x) {
		position.setLocation(x, position.getY());
	}
	
	/**
	 * The method setY to set the position in y
	 * @param y : int to define the position in y
	 */
	public void setY(int y) {
		position.setLocation(position.getX(), y);
	}
}

