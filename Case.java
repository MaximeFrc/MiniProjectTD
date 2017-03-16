import java.awt.Image;
import java.awt.Point;
import java.io.File;

import javax.imageio.ImageIO;

public class Case {
	
	public Point position;
	public int length;
	public boolean path;
	public Image img;
	public Tour tour;
	
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
	}
	
	public void addTour(Tour t) {
		this.tour = t;
	}
	
	public void setPath() {
		this.path = true;
		try {
			img= ImageIO.read(new File("casePath.png"));
		} catch(Exception err) {
			System.out.println(" not found !");            
			System.exit(0);    
		}
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

