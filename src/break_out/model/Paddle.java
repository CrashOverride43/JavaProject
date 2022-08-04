
package break_out.model;
/**
 * This object contains information about the running game
 * 
 * modified by:
 * 670498, Muhammed Enes Colak
 * 660479, Hakob Steven Akopjan
 * Abgabegruppe 75
 */
import break_out.Constants;

/**
 * This object contains information about the running game
 * 
 * @author  and modified by:
 * 670498, Muhammed Enes Colak
 * 660479, Hakob Steven Akopjan
 * Abgabegruppe 75
 */
public class Paddle {
	
	/**
	 * Deklarierung der Variable pos zur Positionsermittlung vom Paddle
	 */
	private Position pos;
	
	/**
	 * Bewegungsrichung fuer das Paddle. 0 fuer stop, 1 fuer rechts und -1 fuer links.
	 */
	private int richtung=0;

	
	/** Setzt die Position des Paddles mittig aufm Spielfeld
	 * 
	 */
	public Paddle() {
		this.pos = new Position(Constants.SCREEN_WIDTH/2-Constants.PADDLE_WIDTH/2, Constants.SCREEN_HEIGHT-Constants.PADDLE_HEIGHT);
	}
	
	/** Holt sich die Position vom Paddle
	 * @return pos gibt die Pos vom Paddle wieder
	 */
	
	public Position getpos() {
		return this.pos;
	}
	
	/** get methode fuer die richtung
	 * @return richtung gibt 0,1 oder -1 aus
	 */
	public int getRichtung(){
		return this.richtung;
	}
	
	/** set methode fuer die richtung 
	 * @param yeye verbindung der instanzvariable richtung mit dem methoden Parameter variable
	 */
	public void setRichtung(int yeye){
		this.richtung = yeye;
	}
	/**
	 * Setzt neue Position des Paddles fest, indem es die Konstante fuer die Paddlegeschwindigkeit
	 * mit der Richtungsvariable multipliziert und darauf hin zur alten Poition addiert.
	 */
	public void updatePosition()	{
		// Paddle wird daran gehindert aus dem Spielfeld zu fliegen
    	if (this.richtung == -1)	{
    		if (this.getpos().getX() - Constants.DX_MOVEMENT <= 0)	{
        		this.getpos().setX(0);
        	}
    		else	{
    			this.pos.setX(this.pos.getX() - Constants.DX_MOVEMENT);
    		}
    	}
    	if (this.richtung == 1){
    		if (this.getpos().getX() + Constants.PADDLE_WIDTH + Constants.DX_MOVEMENT >= Constants.SCREEN_WIDTH)	{
        		this.getpos().setX(Constants.SCREEN_WIDTH - Constants.PADDLE_WIDTH);
        	}
    		else	{
    			this.pos.setX(this.pos.getX() + Constants.DX_MOVEMENT);
    		}
    	}
	}
	
}
