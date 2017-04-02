/**
 * The class Minion that are the enemies in the game
 * we consider as dead when they have a null position
 * 
 */

// Load libraries
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import javax.imageio.ImageIO;

public class Minion {
	
	//Atributes
	
	public final int TAILLE_CASE = 32;
	
	public String name;
	public double HP;
	public double initialHP;
	public double speed;
	public Case [] path;
	public int avance;
	public Image img;
	public Point position;
	public Rectangle rect;
	
	//Methodes

	/**
	 * The construtor
	 * @param name : String to define a name only for the developers
	 * @param HP : double to define the health points of the minion
	 * @param speed : int to define the speed of the minion
	 * @param imageAddress : String to access the image corresponding to the minion create
	 */
	Minion(String name, double HP, int speed, String imageAddress) {
		this.name = name ;
		this.HP = HP;
		this.initialHP = HP;
		this.speed = speed;
		try {
            img= ImageIO.read(new File(imageAddress));
       } catch(Exception err) {
           System.out.println(imageAddress+" not found !");            
           System.exit(0);    
       }
	}
	
	/**
	 * The method create to put the minion on the Path
	 * @param path : Case[] to access the position of the first Case of the Path
	 */ 
	public void create(Case [] path) {
		this.path = path;
		this.position = new Point((int) path[0].position.getX(),(int) path[0].position.getY());
		this.rect = new Rectangle((int) getX(),(int) getY(),TAILLE_CASE,TAILLE_CASE);
		this.avance = 1;
	}
	
	/**
	 * The method update to move the minion at each time the timer is running
	 * The minion looks at the position of the next Case in the path and move in its direction
	 */ 
	public void update() {
		if (avance < path.length) {
			if (this.position.equals(path[avance].position)) { //To change the Case the minion is looking at
				avance++;
			}
			if (avance < path.length) {
				if (path[avance-1].getX() < path[avance].getX()) {
					int moveX = (int) (getX() + speed); //To move the minion wrt the x coordinate
					this.setX(moveX);
				} else {
					if (path[avance-1].getX() > path[avance].getX()) {
						int moveX = (int) (getX() - speed); //To move the minion wrt the x coordinate
						this.setX(moveX);
					}
				}
				if (path[avance-1].getY() < path[avance].getY()) {
					int moveY = (int) (getY() + speed); //To move the minion wrt the x coordinate
					this.setY(moveY);
				} else {
					if (path[avance-1].getY() > path[avance].getY()) {
						int moveY = (int) (getY() - speed); //To move the minion wrt the x coordinate
						this.setY(moveY);
					}
				}
			}
		}
		rect.setLocation(position);
	}
		
	/**
	 * The method getX to get the position in x
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
