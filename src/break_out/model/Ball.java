//2a
package break_out.model;

import break_out.Constants;

import java.awt.*;

/**
 * This object contains information about the running game
 * 
 * @author and modified by
 * 670498 Muhammed Enes Colak,
 * 660479 Hakob Steven Akopjan,
 * Abgabegruppe 75
 */
public class Ball {

	/**
	 * Deklarierung der Variable Pos um die neue Ballposition zu ermitteln
	 */
	private Position pos;

	private Color color;
	
	/**
	 * Deklarierung der Varible direction um die Richtungsvektoren zu bestimmten
	 */
	private Vector2D direction;
	
	/**
	 * Variable fuer die square Breite
	 */
	private int squareBreite = (int) Constants.SCREEN_WIDTH/Constants.SQUARES_X;
	
	/**
	 * Variable fuer die square Hoehe
	 */
	private int squareHoehe = (int) Constants.SCREEN_HEIGHT/Constants.SQUARES_Y;
	
	/**
	 * Der Konstruktor instanziiert einen neuen Ball
	 * mit einer Standart Position fuer den Ball
	 */
	
	public Ball() {
		this.pos = new Position(Constants.SCREEN_WIDTH/2-Constants.BALL_DIAMETER/2, Constants.SCREEN_HEIGHT-Constants.PADDLE_HEIGHT-Constants.BALL_DIAMETER);
		this.direction = new Vector2D(-5,5);
		this.direction.rescale();
	}
	
	/** Holt sich die Aktuelle Position
	 * @return pos gibt die aktuelle position zurueck.
	 */
	public Position getPosition() {
		return this.pos;
	}

	/** Holt sich die Richtungsvektoren
	 * @return direction gibt die Richtungsvektoren zurueck
	 */
	public Vector2D getDirection() {
		return this.direction;
	}

	/** 
	 * Setzt die neue Position auf die Alte Position mit der Erweiterung/Addition von DX und DY
	 */
	public void updatePosition() {
		pos.setX(pos.getX()+direction.getdX());
		pos.setY(pos.getY()+direction.getdY());
	}

	/**
	 * Abprallverhalten des Balles anhand der X und Y Positionen damit der Ball an der unteren, rechten, 
	 * linken und oberer Wand abprallt bzw die Richtung aendert.
	 */
	public void reactOnBorder() {
		// Wenn die Aktuelle Position(X-Position) vom Ball echt groeßer ist als die Fenster breite minus ball durchmesser
		// dann aendert der ball die richtung und er prallt sozusagen ab von der rechten Wand
    	if (pos.getX() >= Constants.SCREEN_WIDTH - Constants.BALL_DIAMETER ) {
    		direction.setdX(direction.getdX()*(-1));
    	}
    	// Wenn die Aktuelle Position(Y-Position) vom Ball echt kleiner Null ist dann aendert der ball die richtung und 
    	// Prallt von der Oberen Wand ab
    	if (pos.getY() <= 0 + Constants.BALL_DIAMETER) {
    		direction.setdY(direction.getdY()*(-1));
    	}
    	// Wenn die Aktuelle Position(X-Position) vom Ball echt kleiner Null ist dann aendert der ball die richtugn und
    	// prallt von der linken Wand ab
       	if (pos.getX() <= 0 ) {
    		direction.setdX(direction.getdX()*(-1));
    	}
	}
	/**	Ueberprueft ob das Paddle vom Ball beruehrt wird. 
	 * @param p Parameter Variable zur Verbindung der Class Paddle
	 * @return i gibt so lange false wieder, bis der Ball das paddle beruehrt und i dann true liefert
	 */
	public boolean hitsPaddle(Paddle p) {
		boolean i = false;
		if(pos.getY() + Constants.BALL_DIAMETER >= p.getpos().getY()) {
			if(pos.getX() <= p.getpos().getX() + Constants.PADDLE_WIDTH) {
				if(pos.getX() + Constants.BALL_DIAMETER >= p.getpos().getX()) {
					i = true;
				}
			}
		}
		return i;
	}
	/** Berechnung des Reaktionsverhalten vom Ball WENN es das Paddle beruehrt.
	 * @param paddle Parameter Variable zur Verbindung der Class Paddle
	 */
	public void reflectOnPaddle(Paddle paddle) {
		Position Hilfpunkt = new Position(paddle.getpos().getX()+Constants.PADDLE_WIDTH/2, paddle.getpos().getY()+ 40);
		Position ballMitte = new Position(pos.getX() + Constants.BALL_DIAMETER/2, pos.getY() + Constants.BALL_DIAMETER/2);
		this.direction = new Vector2D(Hilfpunkt,ballMitte);
		direction.rescale();
	}
	

	/** Abprallverhalten vom Ball an den Steinen
	 * @param muster Muster vom Steinenfeld
	 * @return ballSpalte ballZeile ballSpalte2 ballZeile2 ballSpalte3 ballZeile3
	 */
	public int[] hitStone(int [][] muster) {
		int ballSpaltelinks = (int) (pos.getX() / squareBreite);
		int ballZeileoben = (int) (pos.getY() / squareHoehe);
		int ballSpalterechts = (int) (pos.getX() / squareBreite) + 1;
		int ballZeileunten = (int) (pos.getY()  / squareHoehe) + 1;

		int stoneLeftSide = ballSpaltelinks * squareBreite;
		int stoneRightSide = ballSpalterechts * squareBreite;
		int stoneUpperSide = ballZeileoben * squareHoehe;
		int stoneDownSide = ballZeileunten * squareHoehe;

		//Abprallverhalten wenn der Ball den Stein von unten beruehrt

		if(muster[ballZeileoben][ballSpaltelinks] == 1 &&  pos.getY() <= stoneDownSide && direction.getdY() < 0) {
			System.out.println("@@@@@@@@@@@@@@@@VON UNTEN @@@@@@@@@@@@@@@@@@@");
			System.out.println("BallSpalte: " + ballSpaltelinks + " Ballzeile " + ballZeileoben);
			System.out.println("posY: " + pos.getY() + " posX: " + pos.getX());
			System.out.println("posDY: " + direction.getdX() + " posDX: " + direction.getdY());
			direction.setdY(direction.getdY() * (-1));
			direction.rescale();
			return new int[]{ballZeileoben, ballSpaltelinks};
		}
		
		//Abprallverhalten wenn der Ball den Stein von oben beruehrt
		if (muster[ballZeileoben][ballSpaltelinks] == 1 && pos.getY() <= stoneUpperSide && direction.getdY() > 0) {
			System.out.println("@@@@@@@@@@@@@@@@VON OBEN @@@@@@@@@@@@@@@@@@@");
			System.out.println("BallSpalte: "+ballSpaltelinks + " Ballzeile2 " + ballZeileoben);
			System.out.println("posY: " + pos.getY() + " posX: " + pos.getX());
			System.out.println("posDY: " + direction.getdX() + " posDX: " + direction.getdY());
			direction.setdY(direction.getdY()*(-1));
			direction.rescale();
			return new int[]{ballZeileoben,ballSpaltelinks};
		}
		
		//Abprallverhalten wenn der Ball den Stein von Links aus beruehrt
		if (muster[ballZeileoben][ballSpaltelinks] == 1 && pos.getX() >= stoneLeftSide && direction.getdX() > 0){
			System.out.println("@@@@@@@@@@@@@@@@ LINKE SEITE WURDE BERÜHRT @@@@@@@@@@@@@@@@@@@");
			System.out.println("BallSpalte2: "+ballSpaltelinks + " Ballzeile " + ballZeileoben);
			System.out.println("posY: " + pos.getY() + " posX: " + pos.getX());
			System.out.println("posDY: " + direction.getdX() + " posDX: " + direction.getdY());
			direction.setdX(direction.getdX()*(-1));
			direction.rescale();
			return new int[]{ballZeileoben,ballSpaltelinks};

		}
		//Abprallverhalten wenn der Ball den Stein von Rechts aus beruehrt
		if (muster[ballZeileoben][ballSpaltelinks] == 1 && pos.getX() <= stoneRightSide   && direction.getdX() < 0) {
			System.out.println("@@@@@@@@@@@@@@@@ RECHTE SEITE WURDE BERÜHRT @@@@@@@@@@@@@@@@@@@");
			System.out.println("BallSpalte: "+ballSpaltelinks + " Ballzeile " + ballZeileoben);
			System.out.println("posY: " + pos.getY() + " posX: " + pos.getX());
			System.out.println("posDY: " + direction.getdX() + " posDX: " + direction.getdY());
			direction.setdX(direction.getdX()*(-1));
			direction.rescale();
			return new int[]{ballZeileoben,ballSpaltelinks};
		}
		return null;
	}
	
	/** Wenn der ball den unteren Rand beruehrt, liefert er True, ansonsten ist der Wert False
	 * @return true wenn der untere bildschirmrand beruehrt wird vom ball, ansonsten wird false geliefert
	 */
	public boolean hitsBottom() {
    	if (pos.getY() >= Constants.SCREEN_HEIGHT - Constants.BALL_DIAMETER / 2) {
    		return true;
    	} return false;
	}
}