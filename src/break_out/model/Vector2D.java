//1a
package break_out.model;

import break_out.Constants;

/**
 * This object contains information about the running game
 * 
 * @author and modified by:
 * 670498, Muhammed Enes Colak
 * 660479, Hakob Steven Akopjan
 * Abgabegruppe 75
 */
public class Vector2D {
	
	/**
	 * Deklarierung der Variable laenge fuers Nominieren des Richtungsvektors
	 */
	private double laenge;
	
	/**
	 * Deklarierung des Richtungsvektors DX
	 */
	private double dx;
	
	/**
	 * Deklarierung des Richtungsvektors DY
	 */
	private double dy;
	

	/** 1. Konstruktor verbindung der Instanzvariable mit den Parametervariablen
	 * @param dx DX Variable wird mit dem Parametervariable verknuepft
	 * @param dy DY Variable wird mit dem Parametervariable verknuepft
	 */
	public Vector2D(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}

	/** 2. Konstruktor fuer die Berechnung der neuen Position
	 * @param p1 Die erste Position
	 * @param p2 Die zweite Position
	 */ 
	public Vector2D(Position p1, Position p2) {
		this.dx = p2.getX() - p1.getX();
		this.dy = p2.getY() - p1.getY();
		rescale();
	}
	

	/**
	 * Getter for the dx-coordinate
	 * 
	 * @return dx The dx value of this position
	 */
	public double getdX() {
		return dx;
	}

	/**
	 * Setter for the dx-coordinate
	 * @param dx The new dx-coordinate
	 */
	public void setdX(double dx) {
		this.dx = dx;
	}

	/**
	 * Getter for dy-coordinate
	 * 
	 * @return dy The dy value of the position
	 */
	public double getdY() {
		return dy;
	}

	/**
	 * Setter for the dy-coordinate
	 * @param dy The new dy-coordinate
	 */
	public void setdY(double dy) {
		this.dy = dy;
	}

	/**
	 * Nominierung des Richtungsvektor auf 1
	 * Nominierten wert mit dem Ball_Speed multiplizieren
	 */
	public void rescale() {
		laenge = Math.sqrt(dx*dx+dy*dy);
		
		dx=dx/laenge;
		dy=dy/laenge;
		dx=dx*Constants.BALL_SPEED;
		dy=dy*Constants.BALL_SPEED;
		
	}
	
}
