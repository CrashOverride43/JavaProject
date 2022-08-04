package break_out;

import java.awt.Color;

/**
 * A class that contains all constant values to configure the game
 * 
 * @author dmlux, modified by I. Schumacher
 * 
 */
public class Constants {

    /**
     * The screen width in pixels
     */
    public static final double SCREEN_WIDTH = 600;

    /**
     * The screen height in pixels
     */
    public static final double SCREEN_HEIGHT = 600;

    public static final Color p1Color = new Color(0, 0, 0);

    public static final Color p2Color = new Color(110, 110, 112, 255);

    public static final Color stoneColor = new Color(255,255,255);

    public static final Color gridColor = new Color(255,255,255);

    /**
     * the application name
     */
    public static final String APP_TITLE = "iBreakOut";

    /**
     * Debugging flag for special rendering hints
     */
    public static final boolean DEBUG_MODE = false;

    /**
     * The background color for the game menu
     */
    public static final Color BACKGROUND = new Color(52, 152, 219);

    /**
     * Amount of columns for blocks
     */
    public static final int SQUARES_X = 20;

    /**
     * Amount of the rows
     */
    public static final int SQUARES_Y = 24;
    
    /**
     * The paddle width in pixels
     */
    public static final double PADDLE_WIDTH = 100;
    
    /**
     * The paddle height in pixels
     */
    public static final double PADDLE_HEIGHT = 15;
    
    /**
     * The ball diameter in pixels
     */
    public static final double BALL_DIAMETER = 10;
       
    /**
     * The paddle speed
     */
    public static final double DX_MOVEMENT = 2.5;
    
    /**
     * The ball speed
     */
    public static final double BALL_SPEED = 0.40;
    
}
