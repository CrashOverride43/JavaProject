package break_out.model;
import break_out.Constants;
import break_out.controller.JSONReader;


/**
 * This object contains information about the running game
 * 
 * @author dmlux and modified by: 
 * 670498, Muhammed Enes Colak
 * 660479, Hakob Steven Akopjan
 * Abgabegruppe 75
 */
public class Level extends Thread {

	/**
	 * Hier wird die Variable maxScore deklariert mit dem Wert 0
	 */
	private int maxScore=0;

	/**
	 * Hier wird das muster der Steine gespeichert
	 */
	private int[][] muster;
	
	private Stone stones[];
    /**
     * The game to which the level belongs 
     */
    private Game game;
    
    private Player player1;

	private Player player2;
    /**
     *  Der Boolische Wert fuer die While schleife in der run Methode
     */
    private boolean beendet=false;
	 
    /**
   	 * The number of the level
   	 */
    private int levelnr;

    /**
     *  Anzahl an leben vom aktuellen Level
     */

    /**
     * Flag that shows if the ball was started
     */
    private boolean ballWasStarted = false;

    /**
     * Der Konstruktor instanziiert einen neuen Level:
     * @param game Das zugehoerige Game-Objekt
     * @param levelnr Die Nummer des zu instanziierenden Levels
     */
    public Level(Game game, int levelnr, int player1Score, int player2Score) {
    	this.game = game;
    	this.levelnr = levelnr;
		this.game.getPlayer1().setScore(player1Score);
		this.game.getPlayer2().setScore(player2Score);
		this.player1 = this.game.getPlayer1();
		this.player2 = this.game.getPlayer2();
        loadLevelData(levelnr); // LEVEL 2 MÜSS ÜBERARBEITET WERDE N @@@@ 03.08.22
    }
    
    /**
     * Setzt ballWasStarted auf true, d.h. der Ball "startet", 
     * weil so die bedingten Anweisungen in der while-Schleife der run-Methode ausgefuehrt werden.
     */
    public  void startBall() {
        ballWasStarted = true;
    }

    /**
     * Setzt ballWasStarted auf false, d.h. der Ball "pausiert", weil so die bedingten Anweisungen in der while-Schleife 
     * der run-Methode nicht ausgefuehrt werden.
     */
    public void stopBall() {
        ballWasStarted = false;
    }

    /**
     * Liefert den booleschen Wert der Variablen ballWasStarted
     * @return ballWasStarted True, wenn sich der Ball bewegt, sonst false
     */
    public  boolean ballWasStarted() {
        return ballWasStarted;
    }
    
     /**
     * This method is the thread logic.
     */
    public void run() {
    		// update view, d. h. veranlasse das Neuzeichnen des Spielfeldes
    		game.notifyObservers();
    		while (!beendet) {
	            // if ballWasStarted is true (Spiel soll ablaufen, d.h. der Ball soll sich bewegen)
	            if (ballWasStarted) {
	            	
	            	// Neue Position ermitteln
					player1.getBall().updatePosition();
					player2.getBall().updatePosition();
	            //	ball.updatePosition();
	            	
	            	// Abprallverhalten an den Raendern
					player1.getBall().reactOnBorder();
					player2.getBall().reactOnBorder();
	            //	ball.reactOnBorder();
	            	
	            	// Verhlaten am unterem Rand des Spielfeldes, Leben wird reduziert und die Positionen des Balles und Paddles
	            	// Werden zurueckgesetzt auf die Standart Posi. Bei 0 Leben kommt man zurueck zum Startscreen

					playerHitsBottom();

	            	// Die Position des Paddles wird aktualisiert bzw es bewegt sich.
					player1.getPaddle().updatePosition();
					player2.getPaddle().updatePosition();
	            	//paddle.updatePosition();
	            	
	                // Abprallverhalten vom Paddle an den Raendern
	            	if (player1.getBall().hitsPaddle(player1.getPaddle()))	{
	            		player1.getBall().reflectOnPaddle(player1.getPaddle());
	            	}
					if (player2.getBall().hitsPaddle(player2.getPaddle()))	{
						player2.getBall().reflectOnPaddle(player2.getPaddle());
					}
	            	// Zum laden des naechsten Levels
	                //loadnextlvl(); NEED UPDATE @@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 03.08.22
	            	
	              	// Score Update und Beruehrte Steine werden entfernt
	            	updateStonesAndScore();
	            	// update view
	                game.notifyObservers();
	            }
	            // pause thread by a few millis
	            try {
	                Thread.sleep(4);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
    		}   
    }


	/**
    * Zugriff auf die der Levelnummer zugeordnete JSON-Datei
    * @param levelnr Die Nummer X fuer die LevelX.json Datei
    */
    private void loadLevelData(int levelnr) {
    		JSONReader obj = new JSONReader("res/Level" + levelnr + ".json");
    		player1.setLife(obj.getLifeCounterp1());
			player2.setLife(obj.getLifeCounterp2());

			int musterSize = getMusterSize(obj);
			stones = new Stone[musterSize];
    	    int[][] muster = getMuster();
    		for (int i = 0; i < muster.length; i++) {
    			for (int j = 0; j < Constants.SQUARES_X; j++) {
    				if (muster[i][j] == 1 ) {
						stones[i] = createStones(j,i);
    					maxScore=1+maxScore;
    				}
    			} 
    		}
    }

	private int getMusterSize(JSONReader obj){
		int size=0;
		muster = obj.getStones2DArray();
		for (int i = 0; i < muster.length; i++) {
			for (int j = 0; j < Constants.SQUARES_X; j++) {
				if (muster[i][j] == 1){
					size = i+j;
				}
			}
		}
		return size;
	}



	private Stone createStones(int j, int i){
		Stone stone = new Stone(j*(int)(Constants.SCREEN_WIDTH/Constants.SQUARES_X) + 2,
				i*(int)(Constants.SCREEN_HEIGHT/Constants.SQUARES_Y) + 2,
				(int)(Constants.SCREEN_WIDTH/Constants.SQUARES_X) -3,
				(int)(Constants.SCREEN_HEIGHT/Constants.SQUARES_Y) -3);
		return stone;
	}
    

    /** getter methode fuer das Steinen Muster
     * @return muster gibt das Steinen muster wieder
     */
    public int[][] getMuster(){
    	return muster;
    }
    
    /**
     * Loescht Steine die vom Ball beruehrt wurden sind und erhoet die Score nach entfernung des Steines
     */
    public void updateStonesAndScore(){
    	int[] temp = player1.getBall().hitStone(muster);
		int[] temp2 = player2.getBall().hitStone(muster);
    	
    	if (temp != null) {
    		muster[temp[0]][temp[1]]--;
    		player1.setScore(player1.getScore()+1);
    	}
		if ( temp2 != null){
			muster[temp2[0]][temp2[1]]--;
			player2.setScore(player2.getScore()+1);
		}
    }
    
    /** getter methode fuer das neustarten des spieles
     * @return beendet wird auf true gesetzt wenn die Methode Spielneustart() aufgerufen wird.
     */
    public boolean Spielneustart(){
    	return beendet=true;
    }

    
    /** getter Methode fuer das maximale Score vom Level
     * @return maxScore der maximale Score vom level
     */
    public int maxScore(){
    	int mScore=0;
    	if (levelnr== 1) {
    		return maxScore;
    	} if ( levelnr == 2) {
    		return mScore=maxScore+maxScore;
    	} return maxScore;
    }	
    
  
    
    /** Sobald die Maximale Anzahl an Punkten erreich wurden sind, liefert diese methode true, solange bleibt er auf false
     * @return liefert so lange false, bis die maximale punkte erreicht wurden sind, dann wird true geliefert
     */
    public boolean NoStoneLeft() {
    	if (levelnr == 1 &&  player1.getScore() == maxScore()) {
    		return true;
    	  } 
    		if ( levelnr == 2 && player1.getScore()  == maxScore()){
    		  return true;
    	  }
    		  return false;
    }
    
    /** Sobald die Methode NoStoneLeft() true liefert, 
     * wird das level um 1 erhoeht und das spielfeld neuerstellt mit der neuen level datei
     */
    public void loadnextlvl(){
    	if (NoStoneLeft()) {
    		levelnr++;
    		game.getLevel().Spielneustart();
    		game.createLevel(levelnr, player1.getScore(), player2.getScore());
    	}
    }

	public void playerHitsBottom(){
		if(player1.getBall().hitsBottom()) {
			player1.setLife(player1.getLife()-1);
			if (player1.getLife() >=1) {
				player1.createNewPaddleBall(new Paddle(), new Ball());

			} else if ( player1.getLife() == 0) {
				game.getLevel().Spielneustart();
				game.getController().toStartScreen();
			}
		}

		if(player2.getBall().hitsBottom()) {
			player2.setLife(player2.getLife()-1);
			if (player2.getLife() >=1) {
				player2.createNewPaddleBall(new Paddle(), new Ball());

			} else if ( player2.getLife() == 0) {
				game.getLevel().Spielneustart();
				game.getController().toStartScreen();
			}
		}
	}
    
}
    


	
