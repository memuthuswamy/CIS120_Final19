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
public class Pit extends GameObj {
    public static final int SIZE = 200;


    private Color color1;
    private Color color2; 
    
    private String num; 

    public Pit(int courtWidth, int courtHeight, int init_pos_x, int init_pos_y, Color color1, Color color2, int num) {
        super(init_pos_x, init_pos_y, SIZE, SIZE, courtWidth, courtHeight);

        this.color1 = color1;
        this.color2 = color2;
        
        this.num = Integer.toString(num);
    }
    
    public void changeNum(int sNum) {
    	num = Integer.toString(sNum); 
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(this.color1);
        g.fillRect(this.getPx(), this.getPy(), this.getWidth(), this.getHeight());
        
        g.setColor(this.color2); 
        g.fillOval(this.getPx(), this.getPy(), this.getWidth() -10, this.getHeight()-10);
        
        g.setColor(Color.WHITE);
        g.drawString(num, this.getPx() + this.getWidth()-25, this.getPy() + 20);
        
    }
}