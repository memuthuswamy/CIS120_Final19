//import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import javax.swing.JLabel;

import org.junit.Test;

/** 
 *  You can use this file (and others) to test your
 *  implementation.
 */

public class GameTest {
	JLabel status = new JLabel("Test");
	MGameCourt court = new MGameCourt(status);
	
	public boolean compareArrays(int[][] set1, int[][] set2) {
		boolean same = true; 
		for(int i = 0; i < set1.length; i++) {
			for(int j = 0; j <set1[i].length; j++) {
				same = same && (set1[i][j] == set2[i][j]); 
			}
		}
		return same; 
	}

    @Test
    public void tesWint() {
    	court.setStoneManual(new int[][] { { 0, 0 }, { 3, 0 }, { 3, 0 }, { 3, 0 }, { 3, 0 }, { 3, 0 }, { 3, 0 },
			{ 4, 4 } });
			assertTrue(court.isWin());
    }
    
        
    @Test
    public void testNotWin() {
    	court.setStoneManual(new int[][] { { 0, 0 }, { 3, 3 }, { 3, 0 }, { 3, 0 }, { 3, 0 }, { 3, 0 }, { 3, 0 },
			{ 4, 4 } });
			assertFalse(court.isWin());
    }
    
    @Test
    public void testWinFileInp() {
    	 court.setStones("files/testBigWin.txt");
			assertTrue(court.isWin()); 
    }

    @Test
    public void testNotWinFileInp() {
    	 court.setStones("files/testNotAWin.txt");
    	 assertFalse(court.isWin());
    }
    
    @Test
    public void testNotWinFileNoInp() {
    	 court.setStones("files/testNotWin1.txt");
    	 assertFalse(court.isWin());
    }
    
    @Test
    public void testWinFileInpNull() {
    	 court.setStones(null);
    	 assertFalse(court.isWin());
    }
    
    @Test
    public void testMove() {
    	court.setStones(null);
    	 court.play(3,0,0);
    	 int [][] set = new int[][] { { 1, 1 }, { 4, 3 }, { 4, 3 }, { 0, 3 }, { 3, 3 }, { 3, 3 }, { 3, 3 }, { 0, 0 } };
    	 int [][] set2 = court.getStoneCount(); 
    	 assertTrue(compareArrays(set, set2)); 
}
    @Test
    public void testMoveNotValid() {
    	court.setStones(null);
    	 court.play(3,1,0);
    	 int [][] set = new int[][] { { 1, 1 }, { 4, 3 }, { 4, 3 }, { 0, 3 }, { 3, 3 }, { 3, 3 }, { 3, 3 }, { 0, 0 } };
    	 int [][] set2 = court.getStoneCount(); 
    	 assertFalse(compareArrays(set, set2)); 
}
    @Test
    public void testMoveNotValidStatus() {
    	 court.setStones(null);
    	 court.play(3,1,0);
    	 assertEquals(court.getStatusText(), "Oops! Wrong side!"); 
}
    
    @Test
    public void testWinStatus() {
    	 court.setStones("files/testBigWin.txt");
    	 court.isWin(); 
    	 assertEquals(court.getStatusText(), "PLAYER 1 WINS!"); 

    }
    
    @Test
    public void testNotWinStatus() {
    	 court.setStones("files/testNotAWin.txt");
    	 court.isWin(); 
    	 assertEquals(court.getStatusText(), "Test"); 
    }
    
    @Test
    public void testStonesReset() {
    	 court.setStones("files/testBigWin.txt");
    	 court.reset(); 
    	 int[][] set = new int[][] { { 0, 0 }, { 3, 3 }, { 3, 3 }, { 3, 3 }, { 3, 3 }, { 3, 3 }, { 3, 3 },
 			{ 0, 0 } };
    	 assertTrue(compareArrays(court.getStoneCount(), set));

    }
}
