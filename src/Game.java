/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

// imports necessary libraries for Java swing
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

/**
 * Game Main class that specifies the frame and widgets of the GUI
 */
public class Game implements Runnable {
	private String fileName;

	public void run() {
		// NOTE : recall that the 'final' keyword notes immutability even for local
		// variables.

		final JFrame frame = new JFrame("MANCALA");
		frame.setVisible(false);

		final JFrame initial = new JFrame("Welcome!");
		
		initial.setLocation(1000, 250);

		final JPanel title_panel = new JPanel();
		initial.add(title_panel, BorderLayout.NORTH);
		final JLabel title = new JLabel("MANCALA!"); 
		title_panel.add(title);
		title.setFont(new Font("Plain", Font.PLAIN, 44));
		
		
		final JPanel button_panel = new JPanel();
		initial.add(button_panel, BorderLayout.CENTER);
		initial.setLocation(1000, 250);

		final JPanel instr_panel = new JPanel();
		initial.add(instr_panel, BorderLayout.SOUTH);
		final JTextArea rules = new JTextArea("The Rules:\nOn each turn a player will click a pit on their side,\n"
				+ "the left side for player 1, right for player 2.\nThe game ends when "
				+ "one player empties all the pits on their side.\nThe player with the " + "most stones wins!\nHave fun playing!\n\n" 
				+ "Extra info:\n'Start New' will load a new game.\n'Load old will allow you to input text file name and restart a previous game.\n"
				+ "The game can be restarted in the middle or the game can be saved and exited."
				); 
		instr_panel.add(rules);
		rules.setFont(new Font("Plain", Font.PLAIN, 32));

		// Top-level frame in which game components live
		// Be sure to change "TOP LEVEL FRAME" to the name of your game

		frame.setLocation(1000, 250); // HUH
//        frame.setSize(500, 500); //HUH
		// Status panel

		final JPanel status_panel = new JPanel();
		frame.add(status_panel, BorderLayout.SOUTH);
		final JLabel status = new JLabel("Let's Play"); // What is this
		status_panel.add(status);
		status.setFont(new Font("Plain", Font.PLAIN, 32));

		// Main playing area
		final MGameCourt court = new MGameCourt(status);
		frame.add(court, BorderLayout.CENTER);

		// Reset button
		final JPanel control_panel = new JPanel();
		frame.add(control_panel, BorderLayout.NORTH);

		final JButton newStart = new JButton("Start New");
		newStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileName = null;
				court.setStones(fileName);
				court.drawStones();
				frame.setVisible(true);
				initial.dispose();
			}
		});
		newStart.setFont(new Font("Plain", Font.PLAIN, 32));

		button_panel.add(newStart);

		final JButton oldStart = new JButton("Load Old");
		oldStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileName = JOptionPane.showInputDialog("File Name");
				court.setStones("files/" + fileName + ".txt");
				court.drawStones();
				court.isWin(); 
				initial.dispose();
				frame.setVisible(true);

			}
		});

		oldStart.setFont(new Font("Plain", Font.PLAIN, 32));
		button_panel.add(oldStart);

		initial.pack();
		initial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initial.setVisible(true);

		// Note here that when we add an action listener to the reset button, we define
		// it as an
		// anonymous inner class that is an instance of ActionListener with its
		// actionPerformed()
		// method overridden. When the button is pressed, actionPerformed() will be
		// called.
		final JButton reset = new JButton("Reset");
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				court.reset();
			}
		});
		reset.setFont(new Font("Plain", Font.PLAIN, 32));
		control_panel.add(reset);

		final JButton exitSave = new JButton("Exit and Save");
		exitSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					court.saveExit();
				} catch (IOException e1) {
				}
				frame.dispose();
			}
		});
		exitSave.setFont(new Font("Plain", Font.PLAIN, 32));
		control_panel.add(exitSave);

		// Put the frame on the screen
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	/**
	 * Main method run to start and run the game. Initializes the GUI elements
	 * specified in Game and runs it. IMPORTANT: Do NOT delete! You MUST include
	 * this in your final submission.
	 */

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Game());
	}
}