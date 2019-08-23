/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

import java.awt.*;

/**
 * A basic game object starting in the upper left corner of the game court. It is displayed as a
 * circle of a specified color.
 */
public class EPit extends GameObj {
    public static final int SIZE = 200;
    public static final int INIT_POS_X = 170;
    public static final int INIT_POS_Y = 170;


    private Color color1;
    private Color color2; 
    
    

    public EPit(int courtWidth, int courtHeight, int init_pos_x, int init_pos_y, Color color1, Color color2) {
        super(init_pos_x, init_pos_y, 2*SIZE, SIZE, courtWidth, courtHeight);

        this.color1 = color1;
        this.color2 = color2; 
    }


    
    @Override
    public void draw(Graphics g) {
        g.setColor(this.color1);
        g.fillRect(this.getPx(), this.getPy(), this.getWidth(), this.getHeight());
        
        g.setColor(this.color2);
        g.fillRect(this.getPx()+10, this.getPy()+10, this.getWidth() - 20, this.getHeight()-20);
    }
    
    public void changeNum(int i) {
    }
}