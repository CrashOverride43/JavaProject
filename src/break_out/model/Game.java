package break_out.model;

import java.util.List;
import java.util.ArrayList;

import break_out.controller.Controller;
import break_out.view.View;

/**
 * This object contains information about the game (the model in MVC)
 * 
 * @author dmlux modified by:
 * 670498, Muhammed Enes Colak
 * 660479, Hakob Steven Akopjan
 * Abgabegruppe 75
 */
public class Game{

    /**
	 * A list of observer objects
	 */
	private List<View> observers = new ArrayList<View>();

	/**
     * The controller of the game
     */
    private Controller controller;
	
    /**
   	 * The current level
   	 */
    private Level level;
    
    /**
     * The first levelnumber
     */
    private int firstLevel = 1;
    
    /**
     * The last levelnumber
     */
    private int maxLevel = 2;  
       
	private Player player1;

	private Player player2;

       
    /**
     * Der Konstruktor instanziiert ein neues Game-Objekt
     * @param controller Der zugeordnete Controller (siehe MVC)
     */
    public Game(Controller controller) {
        this.controller = controller;
		createPlayers();
        createLevel(firstLevel, 0, 0);
    }

    
    /**
     * The methods of the observer pattern
     */
    public void addObserver(View observer) {
		observers.add(observer);
	}

	public void removeObserver(View observer) {
		observers.remove(observer);
	}

	public void notifyObservers() {
		for (View observer : observers)
			observer.modelChanged(this);
	}

	
	/**
	 * Getter for the Controller
	 * @return controller The controller of this game
	 */
     public Controller getController() {
    	 return controller;
     }
     
     /**
      * Getter for the current Level
      * @return level The current level of the game
      */
     public Level getLevel() {
    	 return level;
     }

    /**
     * Creates the first or next Level
     * @param levelnr The number of the new level
     */
    public void createLevel(int levelnr, int p1Score, int p2Score) {
    	player1.setScore(p1Score);
		player2.setScore(p2Score);
    	if (levelnr <= maxLevel) {
    		// Levelobjekt erzeugen
    		level = new Level(this, levelnr, player1.getScore(), player2.getScore());
    		// ruft die run-Methode des Level-Objektes auf
        	level.start();
            // Spielfeld anzeigen
            controller.toPlayground();
    	}
    	else {
    		// Umschalten auf Startscreen bei Spielende
    		controller.toStartScreen();
    	}
    }

	public void createPlayers(){
		this.player1 = new Player(0);
		this.player2 = new Player(0);
	}

	public Player getPlayer1(){return player1; }

	public Player getPlayer2(){return player2; }

	public void setPlayer(Player player){this.player1 = player;}

	public void setPlayer2(Player player){this.player2 = player;}


}
    


	
