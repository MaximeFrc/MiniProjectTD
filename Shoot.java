
import java.awt.Point;
import java.awt.Color;

public class Shoot {
	
	public final int TAILLE_CASE = 32;
	Point origineShoot;
	Point finishShoot;
	boolean killingShoot;
	Color colorShoot;
	
	public Shoot(Case caseTour, Minion shootedMinion) {
		this.origineShoot = new Point((int) (caseTour.getX()+TAILLE_CASE/2), (int) (caseTour.getY()+TAILLE_CASE/2));
		this.finishShoot = new Point((int) (shootedMinion.getX()+TAILLE_CASE/2), (int) (shootedMinion.getY()+TAILLE_CASE/2));
		this.colorShoot = caseTour.tour.colorShoot;
		shootedMinion.HP -= caseTour.tour.power;
		if (shootedMinion.HP<0) {
			shootedMinion.position = null;
			this.killingShoot = true;
		}
	}
}

