
/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.*;

/**
 * GameCourt
 * 
 * This class holds the primary game logic for how different objects interact
 * with one another. Take time to understand how the timer interacts with the
 * different methods and how it repaints the GUI on every tick().
 */
@SuppressWarnings("serial")
public class MGameCourt extends JPanel {

	private int[][] stoneCount = new int[][] { { 0, 0 }, { 3, 3 }, { 3, 3 }, { 3, 3 }, { 3, 3 }, { 3, 3 }, { 3, 3 },
			{ 0, 0 } };
	// Game constants
	public static final int COURT_WIDTH = 400;
	public static final int COURT_HEIGHT = 1600;

	private Pit pit1 = new Pit(COURT_WIDTH, COURT_HEIGHT, 0, 200, Color.BLACK, Color.MAGENTA, stoneCount[1][0]);
	private Pit pit3 = new Pit(COURT_WIDTH, COURT_HEIGHT, 0, 400, Color.BLACK, Color.MAGENTA, stoneCount[2][0]);
	private Pit pit5 = new Pit(COURT_WIDTH, COURT_HEIGHT, 0, 600, Color.BLACK, Color.MAGENTA, stoneCount[3][0]);
	private Pit pit7 = new Pit(COURT_WIDTH, COURT_HEIGHT, 0, 800, Color.BLACK, Color.MAGENTA, stoneCount[4][0]);
	private Pit pit9 = new Pit(COURT_WIDTH, COURT_HEIGHT, 0, 1000, Color.BLACK, Color.MAGENTA, stoneCount[5][0]);
	private Pit pit11 = new Pit(COURT_WIDTH, COURT_HEIGHT, 0, 1200, Color.BLACK, Color.MAGENTA, stoneCount[6][0]);
	private Pit pit2 = new Pit(COURT_WIDTH, COURT_HEIGHT, 200, 200, Color.BLACK, Color.MAGENTA, stoneCount[1][1]);
	private Pit pit4 = new Pit(COURT_WIDTH, COURT_HEIGHT, 200, 400, Color.BLACK, Color.MAGENTA, stoneCount[2][1]);
	private Pit pit6 = new Pit(COURT_WIDTH, COURT_HEIGHT, 200, 600, Color.BLACK, Color.MAGENTA, stoneCount[3][1]);
	private Pit pit8 = new Pit(COURT_WIDTH, COURT_HEIGHT, 200, 800, Color.BLACK, Color.MAGENTA, stoneCount[4][1]);
	private Pit pit10 = new Pit(COURT_WIDTH, COURT_HEIGHT, 200, 1000, Color.BLACK, Color.MAGENTA, stoneCount[5][1]);
	private Pit pit12 = new Pit(COURT_WIDTH, COURT_HEIGHT, 200, 1200, Color.BLACK, Color.MAGENTA, stoneCount[6][1]);

	private EPit epit1 = new EPit(COURT_WIDTH, COURT_HEIGHT, 0, 0, Color.WHITE, Color.BLACK);
	private EPit epit2 = new EPit(COURT_WIDTH, COURT_HEIGHT, 0, 1400, Color.WHITE, Color.BLACK);

	private GameObj[][] gamePit = { { epit1, epit1 }, { pit1, pit2 }, { pit3, pit4 }, { pit5, pit6 }, { pit7, pit8 },
			{ pit9, pit10 }, { pit11, pit12 }, { epit2, epit2 } };
	private Set<Stone> stones = new TreeSet<Stone>();

	private Map<Integer, Integer> moves = new TreeMap<Integer, Integer>();

	//state of game logic
	private boolean player = false; //current player, false is player 1, true is player 2
	public boolean playing = false; // whether the game is running
	private JLabel status; // Current status text
	
	// Update interval for timer, in milliseconds
	public static final int INTERVAL = 35;
	private boolean active = true;// if no player has won the game yet, the game is active 

	
	public MGameCourt(JLabel status) {
		// creates border around the court area, JComponent method
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setLayout(new GridLayout(2, 6));
		Timer timer = new Timer(INTERVAL, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tick();
			}
		});

		timer.start(); // MAKE SURE TO START THE TIMER!

		// Enable keyboard focus on the court area.
		// When this component has the keyboard focus, key events are handled by its key
		// listener.
		setFocusable(true);
		this.status = status; 
		if (isWin()) {
			active = false;
		}
		
		//Mouse listener function 
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//if it's clicked and the game is still running 
				if (active == true) {
					//if the click is in a valid pit
					for (int i = 1; i < gamePit.length - 1; i++) {
						for (int j = 0; j < gamePit[i].length; j++) {
							if (e.getPoint().x > gamePit[i][j].getPx()
									&& e.getPoint().x < gamePit[i][j].getPx() + gamePit[i][j].getWidth()) {
								if (e.getPoint().y > gamePit[i][j].getPy()
										&& e.getPoint().y < gamePit[i][j].getPy() + gamePit[i][j].getHeight()) {
									int b = (player ? 1 : 0);
									play(i,j,b); 

								}
							}
						}
					}
				}
			}
		});

	}
	
	//
	/**
	 * Explains the rules of the game. Based on the player that is currently playing, iterate in
	 * the appropriate column. The pit that is clicked is set to zero and one is added to the pits
	 * around the board in a counter clockwise direction. 

	 */
	public void play(int i, int j, int b) {
		if (j == b) {
			moves.put((player ? 1 : 0) + 1, i);
			int icount = i;
			int count = stoneCount[i][j];
			stoneCount[i][j] = 0;
			if (count > 0) {
				stones.clear();
				//go up the column and add to the pits 
				if (player == false) {
					for (int k = i - 1; k >= 0; k--) {
						if (count > 0) {
							icount = k;
							stoneCount[k][0]++;
							if (k == 0) {
								stoneCount[0][1]++;
							}
							count--;
						} else {
							break;
						}
					}
				// go down the column and add to the pits
				} else if (player == true) {
					for (int k = i + 1; k <= 7; k++) {
						if (count > 0) {
							icount = k;
							stoneCount[k][1]++;
							if (k == 7) {
								stoneCount[7][0]++;
							}
							count--;
						} else {
							break;
						}
					}

				}
				// while there are still stones left from the pit, loop through the columns until empty 
				while (count > 0) {
					if (player == false) {
						for (int k = 1; k <= 6; k++) {
							if (count > 0) {
								icount = k;
								stoneCount[k][1]++;
								count--;
							} else {
								break;
							}
						}
						for (int k = 6; k >= 0; k--) {
							if (count > 0) {
								icount = k;
								stoneCount[k][0]++;
								if (k == 0) {
									stoneCount[0][1]++;
								}
								count--;
							} else {
								break;
							}
						}

					} else if (player == true) {
						for (int k = 6; k >= 1; k--) {
							if (count > 0) {
								icount = k;
								stoneCount[k][0]++;
								count--;
							} else {
								break;
							}
						}
						for (int k = 1; k >= 7; k++) {
							if (count > 0) {
								icount = k;
								stoneCount[k][1]++;
								if (k == 7) {
									stoneCount[7][0]++;
								}
								count--;
							} else {
								break;
							}
						}
					}
				}
				drawStones();
			// if the pit is empty change the status 
			} else {
				status.setText("Pick a pit with stones!");
			}
			// if the last pit is an Epit, the same player gets another turn and the status updates
			if ((player == true && icount == 7 && count == 0)
					|| (player == false && icount == 0 && count == 0)) {
				status.setText("Player " + ((player ? 1 : 0) + 1) + " go again");
			} else {
				// otherwise, the payer is changed andd the status is changed 
				player = !player;
				status.setText("Player " + ((player ? 1 : 0) + 1) + "'s Turn");
			}
			
			//check if it's a win
			isWin(); 
		//if click is on wrong side of the player
		} else {
			status.setText("Oops! Wrong side!");
		}
	}
	// the game is considered over when one player empties all of their pits. 
	// the player with the most stones in their pit and their side wins
	// active is set to false to stop mouseClick functionality
	public boolean isWin() {
		int sum1 = 0;
		int sum2 = 0;
		if (player == true) {
			int epit1 = stoneCount[0][0];
			for (int k = 1; k < stoneCount.length - 1; k++) {
				sum1 = sum1 + stoneCount[k][0];
			}
			if (sum1 == 0) {
				for (int k = 1; k < stoneCount.length; k++) {
					sum2 = sum2 + stoneCount[k][1];
				}
				if (epit1 < sum2) {
					status.setText("PLAYER 2 WINS!");
				} else if (epit1 == sum2) {
					status.setText("IT'S A TIE!");
				} else {
					status.setText("PLAYER 1 WINS!");
				}
				active = false; 
				return true;
			}
			return false;

		} else {
			int epit2 = stoneCount[7][0];
			for (int k = 1; k < stoneCount.length - 1; k++) {
				sum1 = sum1 + stoneCount[k][1];
			}
			if (sum1 == 0) {
				for (int k = 0; k < stoneCount.length - 1; k++) {
					sum2 = sum2 + stoneCount[k][0];
				}
				if (epit2 < sum2) {
					status.setText("PLAYER 1 WINS!");
				} else if (epit2 == sum2) {
					status.setText("IT'S A TIE!");
				} else {
					status.setText("PLAYER 2 WINS!");
				}
				active = false; 
				return true;
			}
			return false;

		}
	}

	//function to print out the 2D array
	public void printOut(int[][] a) {
		System.out.println(Arrays.deepToString(a));
	}
	
	
	// function to get a copy of the stoneCount
	public int[][] getStoneCount() {
		int[][] copy = new int[stoneCount.length][stoneCount[0].length];
		for (int i = 0; i < stoneCount.length; i++) {
			for (int j = 0; j < stoneCount[i].length; j++) {
				copy[i][j] = stoneCount[i][j];
			}
		}
		return copy;
	}
	
	//function to manually set the stoneCount from an array
	public void setStoneManual(int[][] a) {
		this.stoneCount = a; 
	}

	// function to set the stoneCount from a file input
	public void setStones(String fileName) {
		int[][] set = new int[][] { { 0, 0 }, { 3, 3 }, { 3, 3 }, { 3, 3 }, { 3, 3 }, { 3, 3 }, { 3, 3 }, { 0, 0 } };
		try {
			if (fileName != null) {
				BufferedReader r = new BufferedReader(new FileReader(fileName));
				for (int i = 0; i < set.length; i++) {
					for (int j = 0; j < set[i].length; j++) {
						int c = Integer.parseInt(r.readLine());
						set[i][j] = c;
					}
				}
				r.close();
			}
		} catch (IOException e) {

		}

		finally {
			this.stoneCount = set;
		}
	}

	//based on the count in each pit, add a stone to the stones set
	public void drawStones() {
		int rand1, rand2, x, y;
		for (int a = 0; a < gamePit.length; a++) {
			for (int d = 0; d < gamePit[a].length; d++) {
				int cc = stoneCount[a][d];
				if ((a == 0 && d == 1) || (a == 7 && d == 1)) {
					continue;
				} else {
					for (int z = 0; z < cc; z++) {
						rand1 = (int) (Math.random() * ((50 - (-50)) + 1)) + (-50);
						rand2 = (int) (Math.random() * ((50 - (-50)) + 1)) + (-50);
						x = gamePit[a][d].getPx();
						y = gamePit[a][d].getPy();
						stones.add(new Stone(x + 50 + rand1, y + 50 + rand2, COURT_WIDTH, COURT_HEIGHT, Color.GREEN));
					}
				}
			}
		}
	}

	/**
	 * (Re-)set the game to its initial state.
	 */
	public void reset() {
		stoneCount = new int[][] { { 0, 0 }, { 3, 3 }, { 3, 3 }, { 3, 3 }, { 3, 3 }, { 3, 3 }, { 3, 3 }, { 0, 0 } };
		playing = true;
		player = false;
		status.setText("Let's Play");
		stones = new TreeSet<Stone>();

		moves = new TreeMap<Integer, Integer>();
		active = true;
		drawStones();

		// Make sure that this component has the keyboard focus
		requestFocusInWindow();
	}

	// write the state of the game to a file based on the stone count in each pit
	public void saveExit() throws IOException {
		String fileName = JOptionPane.showInputDialog("File Name");
		Writer out = new BufferedWriter(new FileWriter("files/" + fileName + ".txt"));
		for (int i = 0; i < gamePit.length; i++) {
			for (int j = 0; j < gamePit[i].length; j++) {
				out.write(Integer.toString(stoneCount[i][j]) + "\n");
			}
		}

		out.flush();
		out.close();

		// Make sure that this component has the keyboard focus
		requestFocusInWindow();
	}
	
	//get the status text
	public String getStatusText() {
		return status.getText(); 
	}

	/**
	 * This method is called every time the timer defined in the constructor
	 * triggers.
	 */
	void tick() {
		// update the display
		repaint();

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// update the count of each gamePit and repaint it
		for (int i = 1; i < gamePit.length; i++) {
			for (int j = 0; j < gamePit[i].length; j++) {
				gamePit[i][j].changeNum(stoneCount[i][j]);
				gamePit[i][j].draw(g);
			}
		}
		
		// draw the two EPits
		epit1.draw(g);
		epit2.draw(g);

		// for each of the stones in the set, repaint it. 
		for (Stone s : stones) {
			s.draw(g);
		}

	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(COURT_WIDTH, COURT_HEIGHT);
	}
}