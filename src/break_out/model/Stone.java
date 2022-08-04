package break_out.model;

import java.awt.*;

public class Stone {
    private int witdh;
    private int height;

    private Position pos;

    public Stone(int posX, int posY, int witdh, int height){
        this.witdh = witdh;
        this.height = height;
        createStone(posX, posY);
    }

    public int getWitdh(){
        return witdh;
    }

    public int getHeighth(){
        return height;
    }

    public Rectangle createStone(int posX, int posY){
        Rectangle stone = new Rectangle(posX, posY, witdh, height);

        return stone;
    }

    public Position getPosition(){
        return this.pos;
    }

}
