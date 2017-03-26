import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;

import javax.imageio.ImageIO;

public class Minion {
	
	public String name;
	public double HP;
	public double initialHP;
	public Image img;
	public Point position;
	public Rectangle rect;
	public double speed;
	public Case [] path;
	public int avance;
	public final int TAILLE_CASE = 32;
	
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
	
	public void create(Case [] path) {
		this.path = path;
		this.position = new Point((int) path[0].position.getX(),(int) path[0].position.getY());
		this.rect = new Rectangle((int) getX(),(int) getY(),TAILLE_CASE,TAILLE_CASE);
		this.avance = 1;
	}
	
	public void update() {
		if (avance < path.length) {
			if (this.position.equals(path[avance].position)) {
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
	
	public double getX() {
		return position.getX();
	}
	
	public double getY() {
		return position.getY();
	}
	
	public void setX(int x) {
		position.setLocation(x, position.getY());
	}
	
	public void setY(int y) {
		position.setLocation(position.getX(), y);
	}
}
