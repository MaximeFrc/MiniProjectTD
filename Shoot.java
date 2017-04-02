/**
 * The class Shoot to manage the graphical interaction
 * between Tour and Minion
 * 
 */
 
// Load libraries Point and Color
import java.awt.Point;
import java.awt.Color;

public class Shoot {
	
	// Attributes
	
	public final int TAILLE_CASE = 32;
	
	public boolean killingShoot;
	public Point origineShoot;
	public Point finishShoot;
	public Color colorShoot;
	
	//Methode
	
	/**
	 * The construtor
	 * @param caseTour : Case to access the position of the tower
	 * @param shootedMinion : Minion to access the position of the minion
	 */
	public Shoot(Case caseTour, Minion shootedMinion) {
		
		/* Find the center of both the tower and the minion */
		this.origineShoot = new Point((int) (caseTour.getX()+TAILLE_CASE/2), (int) (caseTour.getY()+TAILLE_CASE/2));
		this.finishShoot = new Point((int) (shootedMinion.getX()+TAILLE_CASE/2), (int) (shootedMinion.getY()+TAILLE_CASE/2));
		
		/* Define the color */
		this.colorShoot = caseTour.tour.colorShoot;
		
		/* Remove HP from the minion */
		shootedMinion.HP -= caseTour.tour.power;
		if (shootedMinion.HP<0) {
			shootedMinion.position = null;
			this.killingShoot = true;
		}
	}
}

