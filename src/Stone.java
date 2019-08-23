/**
 * CIS 120 Game HW
 **/

import java.awt.*;

public class Stone extends GameObj implements Comparable<Stone>{
    public static final int SIZE = 50;
    //How to determine initial position? 
    //Try and use something using a random variable relative to the radius of the stone and the center of the hole and some random radius away from that 

    private Color color;

    /**
    * Note that, because we don't need to do anything special when constructing a Square, we simply
    * use the superclass constructor called with the correct parameters.
    */
    public Stone(int init_pos_x, int init_pos_y, int courtWidth, int courtHeight, Color color) {
        super(init_pos_x, init_pos_y, SIZE, SIZE, courtWidth, courtHeight);

        this.color = color;
    }
    

    @Override
    public void draw(Graphics g) {
        g.setColor(this.color);
        g.fillOval(this.getPx(), this.getPy(), this.getWidth(), this.getHeight());
    }
    
    
    public void changeNum(int i) {
    }
   
    public int compareTo(Stone s){
    	return Math.max(s.getPx(), this.getPx()); 
    	
    }

}
