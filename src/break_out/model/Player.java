package break_out.model;

public class Player {
    private Paddle paddle;
    private Ball ball;

    private int score;

    private int life;

    public Player(int score){
        this.paddle = new Paddle();
        this.ball = new Ball();
        this.score = score;
    }

    public Paddle getPaddle(){
        return  this.paddle;
    }

    public Ball getBall(){
        return ball;
    }

    public int getScore(){return score; }

    public int getLife(){return life; }

    public Player getPlayer(){return this; }

    public void setScore(int score){this.score = score;}

    public void setLife(int life){this.life = life;}

    public void createNewPaddleBall(Paddle paddle, Ball ball){
        this.paddle = paddle;
        this.ball = ball;
    }

}
