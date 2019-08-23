=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: memuth
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. 2D-Arrays: A 2D array is used to store the state of the game board, and the number of stones 
  in each pit after each move. This is an appropriate use of 2D arrays because it mimics the set 
  up of a mancala board and therefore it is simple to implement with respect to the game. 

  2. Collections: A TreeMap<Integer, Integer> is used to store the moves and the player that made 
  that move for record keeping. In addition, a TreeSet<Stone> is used to store all of the stones 
  that are on the board in order for them to be repainted after every move. 

  3. File I/O: File I/O is used in a number of instances within the game. On start, the user can 
  choose to load an old game from a .txt file. In the middle of the game, it can be saved to a text
  file and exited so that it can be reloaded at a later time. 

  4. Testable Component: The testable components of the game involve JUnit testing for the various 
  functions and functionalities of the Game Court. This tests, the initialization, isWin(), and
  play() functions within MGameCourt.java. Testing these functions appropriately assesses the game's 
  model and functionality.

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
  
  Game.java: This class implements runnable and initializes the starting frame and the game frame. 
  The actual game frame's visibility is off and the starting frame's visibility is on. Within this
  class, the user can choose to initialize the game as a new game or as an old game. When a selection
  is made, the game frame is set to visible. This file also initializes the reset and save and exit 
  buttons and references the game court to implement their functionalities. 
  
  MGameCourt.java: This is where the mouseClick events are handled and the objects are repainted. 
  Based on the conditions during the click, the stoneCount changes and the gameBoard repaints 
  accordingly. When the game is won, the mouseClick functionality stops and the game is over. This
  class implements a TreeSet to store all of the Stones to be painted. The pits are organized and
  stored in a 2D array and the number of stones in each pit is also stored in a 2D array. The moves
  that are made are stored in a TreeMap. 
  
  GameObj.java: This is an abstract class for the graphics objects that help define the size and
  location of these graphics. 
  
  Stone.java: This is a class that implements GameObj for the stones to be created and repainted.
  Since this object is stored in a Set in MGameCourt, this class also implements Comaprable and a
  compareTo function. The changeNum function is left unimplemented. 
  
  Pit.java and EPit.java: This is a class that implements GameObj for the stones to be created and repainted.
  The Pit class implements changeNum  which is used to change the number that is displayed in the 
  corner after every move. The EPit class does not implement changeNum
  
  GameTest.java: This class implements JUnit testing for the various functions and functionalities 
  of the Game Court. This tests, the initialization, isWin(), and play() functions within 
  MGameCourt.java.

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
  
  There were stumbling block when it came to implementing the initial frame and hiding the gameCourt 
  until an game start option is selected. Furthermore the file i/o was a bit challenging to implement.
  Finally, making sure encapsulation wasn't being broken was a big concern while creating tests for
  the code. 

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?



========================
=: External Resources :=
========================

- Cite any external resources (libraries, images, tutorials, etc.) that you may
  have used while implementing your game.
