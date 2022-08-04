package break_out.view;

import java.awt.*;

import break_out.model.Level;

import javax.swing.JPanel;

import break_out.Constants;
import break_out.model.Position;
import break_out.model.Stone;
import net.miginfocom.swing.MigLayout;

/**
 * The field represents the board of the game. All components are on the board
 * 
 * @author dmlux modified by: 
 * 670498, Muhammed Enes Colak
 * 660479, Hakob Steven Akopjan
 * Abgabegruppe 75
 */
public class Field extends JPanel {

	/**
	 * Automatic generated serial version UID
	 */
	private static final long serialVersionUID = 2434478741721823327L;

	/**
	 * The connected view object
	 */
	private View view;

	/**
	 * The background color
	 */
	private Color background;
	
	/**
	 * The constructor needs a view
	 * 
	 * @param view The view of this board
	 */
	public Field(View view) {
		super();

		this.view = view;
		this.background = new Color(177, 92, 107);

		setFocusable(true);

		// Load settings
		initialize();
	}

	/**
	 * Initializes the settings for the board
	 */
	private void initialize() {
		// creates a layout
		setLayout(new MigLayout("", "0[grow, fill]0", "0[grow, fill]0"));
	}

	/**
	 * Change the background color
	 * @param color The new color
	 */
	public void changeBackground(Color color) {
		background = color;
		repaint();
	}
	
	/**
	 * Die Methode wird zum Zeichnen / Neuzeichnen des Spielfeldes aufgerufen, dazu werden z. B. Hintergrundfarbe, Ballfarbe usw.
	 * angegeben und die einzelnen Methoden zum Zeichnen wie drawBall(g2) aufgerufen.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		double w = Constants.SCREEN_WIDTH;
		double h = Constants.SCREEN_HEIGHT;

		// Die Abmessungen des Spielfeldes bestimmen
		setPreferredSize(new Dimension((int) w, (int) h));
		setMaximumSize(new Dimension((int) w, (int) h));
		setMinimumSize(new Dimension((int) w, (int) h));

		// Die Schaerfe der Zeichnung festlegen
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		// Die Hintergrundfarbe setzen
		g2.setColor(background);
		g2.fillRect(0, 0, getWidth(), getHeight());
		drawGrid(g2);


		drawBallp1(g2);
		drawPaddlep1(g2);

		drawBallp2(g2);
		drawPaddlep2(g2);

		// Zeichnung des Rasters

		// Zeichnung der Steine
		drawStone(g2);
		// Zeichnung des Scores
		drawScore(g2);
		// Zeichnung des lebens
		drawLeben(g2);
		debuggHitBall(g2);
	}


	/**
	 * Zeichnet den Ball, greift dabei ueber das ihm bekannte view-Objekt auf das zugehoerige Game-Objekt und 
	 * darueber auf das Level-Objekt zu, um dortige Methoden zu nutzen
	 * @param g2
	 */
	private void drawBallp1(Graphics2D g2) {
		g2.setColor(Constants.p1Color);
		g2.fillOval((int) view.getGame().getPlayer1().getBall().getPosition().getX(),
				(int) view.getGame().getPlayer1().getBall().getPosition().getY(),
				(int) (Constants.BALL_DIAMETER),
				(int) (Constants.BALL_DIAMETER));
	}

	private void drawBallp2(Graphics2D g2) {
		g2.setColor(Constants.p2Color);
		g2.fillOval((int) view.getGame().getPlayer2().getBall().getPosition().getX(),
				(int) view.getGame().getPlayer2().getBall().getPosition().getY(),
				(int) (Constants.BALL_DIAMETER),
				(int) (Constants.BALL_DIAMETER));
	}
	
	/**
	 * Zeichnet das Paddle, greift dabei ueber das ihm bekannte view-Objekt auf das zugehoerige Game-Objekt
	 * und darueber auf das Level-Objekt zu, um dortige Methoden zu nutzen
	 * @param g2
	 */
	private void drawPaddlep1(Graphics2D g2) {
		g2.setColor(Constants.p1Color);
		g2.fillRoundRect((int) view.getGame().getPlayer1().getPaddle().getpos().getX(),
				(int) view.getGame().getPlayer1().getPaddle().getpos().getY(),
				(int) (Constants.PADDLE_WIDTH),
				(int) (Constants.PADDLE_HEIGHT),
				(int) (20),
				(int) (20));
	}

	private void  drawPaddlep2(Graphics2D g2){
		g2.setColor(Constants.p2Color);
		g2.fillRoundRect((int) view.getGame().getPlayer2().getPaddle().getpos().getX(),
				(int) view.getGame().getPlayer2().getPaddle().getpos().getY(),
				(int) (Constants.PADDLE_WIDTH),
				(int) (Constants.PADDLE_HEIGHT),
				(int) (20),
				(int) (20));
	}
	/**
	 * Zeichnet das Gitter.
	 * @param g2	Graphics2D
	 */
	private void drawGrid(Graphics2D g2) {
		g2.setColor(Constants.gridColor);
		int z=(int)Constants.SCREEN_HEIGHT/Constants.SQUARES_Y;
		for(int i=0;i<=Constants.SQUARES_Y;i++)
		{
			g2.drawLine(0,z*i,(int)Constants.SCREEN_WIDTH, z*i);
		}
		
		int y=(int)Constants.SCREEN_WIDTH/Constants.SQUARES_X;
		for(int i=0;i<=Constants.SQUARES_X;i++)
		{
			g2.drawLine(y*i,0,y*i,(int)Constants.SCREEN_HEIGHT);
		}
	}

	/** Zeichnung der Steine
	 * @param g2
	 */
	private void drawStone(Graphics2D g2){
		//laden des Steinmusters
		g2.setColor(Constants.stoneColor);
		int[][] muster = view.getGame().getLevel().getMuster();
		for (int i = 0; i < muster.length; i++) {
			for (int j = 0; j < Constants.SQUARES_X; j++) {
				if (muster[i][j] == 1 ) {
						g2.fillRect(
						j*(int)(Constants.SCREEN_WIDTH/Constants.SQUARES_X) + 2,
						i*(int)(Constants.SCREEN_HEIGHT/Constants.SQUARES_Y)+ 2,
						(int)(Constants.SCREEN_WIDTH/Constants.SQUARES_X) -3,
						(int)(Constants.SCREEN_HEIGHT/Constants.SQUARES_Y) -3);
				}
			}
		}
	}
	/** Zeichnung des Scores 
	 * @param g
	 */
	private void drawScore(Graphics g){
		g.setColor(new Color(0,0,0));
		g.setFont(new Font("Impact", Font.BOLD, 12));
		g.drawString("Score: " + view.getGame().getPlayer1().getScore(), 2, 15);
		g.drawString("Score: " + view.getGame().getPlayer2().getScore(), 2, 60);
	}
	
	/** Zeichnung der Anzahl an Leben
	 * @param g
	 */
	private void drawLeben(Graphics g){
		g.setColor(new Color(0,0,0));
		g.setFont(new Font("Impact", Font.BOLD, 14));
		g.drawString("Spieler 1 Leben: " + view.getGame().getPlayer1().getLife(), 2, 30);
		g.drawString("Spieler 2 Leben: " + view.getGame().getPlayer2().getLife(), 2, 45);
		//g.drawString("maxScore: "+ view.getGame().getLevel().maxScore(),2, 450);
	}

	private void debuggHitBall(Graphics g){
		int squareBreite = (int) Constants.SCREEN_WIDTH/Constants.SQUARES_X;
		int squareHoehe = (int) Constants.SCREEN_HEIGHT/Constants.SQUARES_Y;
		int ballSpaltelinks = (int) (view.getGame().getPlayer1().getBall().getPosition().getX() / squareBreite);
		int ballZeileoben = (int) (view.getGame().getPlayer1().getBall().getPosition().getY() / squareHoehe);

		g.drawString("BPosX: "+ (int)view.getGame().getPlayer1().getBall().getPosition().getX(),2, 450);
		g.drawString("BPosY: "+ (int)view.getGame().getPlayer1().getBall().getPosition().getY(),2, 470);

		g.drawString("BPosDX: " + (int)view.getGame().getPlayer1().getBall().getDirection().getdX(), 2 , 490);
		g.drawString("BPosDY: " + (int)view.getGame().getPlayer1().getBall().getDirection().getdY(), 2 , 510);


		g.drawString("BSpalte: " + ballSpaltelinks, 2 , 530);
		g.drawString("BZeile: " + ballZeileoben, 2 , 550);
	}
	
}
