package TicTacToe;


/*This Tic Tac Toe game is a Windows-based programme importing java.awt package, java.awt.event package and javax.swing packages 
 * to provide required classes and interfaces to access. Java AWT (an Abstract Window Toolkit) provides classes to show window
 * components on the screen (JavaTpoint, n.d.).
 * The game's Border, GameMain, and Cell classes require access to the AWT Component classes; Colour and Graphics. 
 * The cell class also requires access to the Graphics2D and BasicStroke AWT classes. 
 * The GameMain class also requires access to the BorderLayout, Dimension and Font classes. 
 * 
 * The game requires a mouse click event, so the java.awt.event package is imported. It provides interfaces and classes for dealing with different
 * events fired by AWT components. This game requires access to the MouseEvent class and the MouseListener interface.
 * 
 * Javax Swing (a Graphical User Interface widget toolkit) creates window-based applications. 
 * The game requires access to the JFrame class (to create a window as a base for a JPanel), 
 * JPanel class (to create a panel to add to the window ), JLabel class (to display a message on the JPanel), 
 * and BorderFactory class (to create an empty border for the Jlabel)(Fisher, 2017).
 * 
 * The source code didn't import the specific classes just mentioned. Therefore, I recommend changes to the code that
 * explicitly imports these classes to reduce compile time and the program's readability to clarify which classes are being used (JavaTpoint, n.d.). */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *	This TicTacToe Java game implements a Windows application developed through java.awt, java.awt.event (mouseClick and mouseListener events)
 * and javax.swing framework.  The game has two users controlling gameplay with the click of a mouse in the cells of a 3x3 grid.
 * It displays messages according to the game state and player.
 * The player's turn is displayed.
 * If there is a match of 3 symbols horizontally, vertically or diagonally, a message of which player has won is displayed.
 * If there are no empty cells, a message is displayed; it's a draw. 
 * A new game starts.
 * 
 * @author Vicky Taylor
 * @since 2023-05-06
 */

public class GameMain extends JPanel implements MouseListener{
		
		//CONSTANTS FOR GAME
		public static final int ROWS = 3;     
		public static final int COLS = 3;  
		public static final String TITLE = "Tic Tac Toe";
		
		//CONTSTANTS FOR THE CELL WIDTH AND HEIGHT AREA
		public static final int CELL_SIZE = 100;
			
		//CONSTANTS FOR THE AREA USED TO DRAW THE GRAPHICS INSIDE
		public static final int CANVAS_WIDTH = CELL_SIZE * COLS;
		public static final int CANVAS_HEIGHT = CELL_SIZE * ROWS;
				
		//CONSTANTS FOR DRAWING NOUGHT AND CROSSES
		public static final int CELL_PADDING = CELL_SIZE / 6;   //Padding from border. 
		public static final int SYMBOL_SIZE = CELL_SIZE - CELL_PADDING * 2;  //Symbol size determined by considering the padding inside the cell.  
		public static final int SYMBOL_STROKE_WIDTH = 8; //Thickness of symbol line drawn.
			
		//OBJECT VARIABLES
		private Board board; // The game board. 
		private JLabel statusBar; // For displaying game status message.
				
		//ENUM VARIABLES
		/* 
		 * Declare variable currentState as an instance of enum gameState.
		 * If currentState is to be assigned a value (an enum item), it would be one of the following:
		 * currentState = GameState.Playing OR GameState.Draw OR GameState.Cross_won OR GameState.Nought_won.
		 */
		private GameState currentState;
		/* 
		 * Declare variable currentPlayer as an instance of enum gamePlayer.
		 * If currentPlayer is to be assigned a value (an enum item), it would be one of the following:
		 * currentPlayer = Player.Empty OR Player.Cross OR Player.Nought. 
		 */		
		private Player currentPlayer; 
		/* 
		 * CONSTRUCTOR  
		 * The GameMain Constructor sets up:
		 *- The implementation of a mouse listener and the event of a mouse click from the User. 
		 *- This event creates a JPanel and setups the game components on the JPanel including:
		 *- JLabel status bar to JPanel,
		 *- Cells of the game board on the jPanel. 
		 */
		public GameMain() {				
				/*
				 * This implements and registers a mouse listener (an interface extended from EventListener) on the JPanel.
				 * The mouse listener listens for an event, in this case is a mouse click trigger, which activates an Event Handler
				 * and an handler method is executed.
				 */
				addMouseListener(this);					
				// Setup the status bar (JLabel) object to display status message.       
				statusBar = new JLabel("         ");  //Instantiates and initializes object JLabel.     
				statusBar.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 14));       
				statusBar.setBorder(BorderFactory.createEmptyBorder(2, 5, 4, 5)); //No borders drawn but takes up space.    
				statusBar.setOpaque(true);   //transparent 
				statusBar.setBackground(Color.LIGHT_GRAY); 				
				setLayout(new BorderLayout()); //Lays out a container to fit components of five regions- North, South, East, West, Center.   
				add(statusBar, BorderLayout.SOUTH);	// Adds JLabel to JPanel in the South component of the container.			
				setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT + 30));// Accounts for status bar height in overall height.
										
				//Instantiates object game board and initializes the game board.
				board = new Board();		
		}	
		
		//MAIN METHOD		
		public static void main(String[] args) {				
				/* 
				 * The Swing object methods used in the game are not necessarily "thread safe". Therefore the program is run
				 * on the Event Dispatch Thread (EDT) through the javax.swing.SwingUtilities.invokeLater method 
				 * as a single thread running an infinite loop, processing events, therefore reduces the risks associated
				 * with multiple threads (ie. thread interference or memory consistency errors)(The Java Tutorials, n.d.).
				 */
				javax.swing.SwingUtilities.invokeLater(new Runnable() {						
				public void run() {						
						/*
						 *  Tutorials from Java Code Junkie (2021) have attributed to my learning of Java Swing which in turn has enabled my ability to
						 *  develop this program.
						 */
			
						JFrame frame = new JFrame(TITLE);//Instance. Set up JFrame.
						GameMain gameMain = new GameMain();
						frame.add(gameMain); //GameMain class extends JPanel. JPanel is added to the JFrame.
								
						frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						frame.pack();//Call before making JFrame visible and setSize (Stackoverflow, 2021).
						frame.setSize(315, 364);// Size set that fits in the grid well.
						frame.setLocationRelativeTo(null); // Places in the center of screen.
						frame.setResizable(false);//Window size is fixed.
						frame.setVisible(true);
						}
				});
		}
		/*
		 * PAINT COMPONENTS
		 * System calls this methods when its needed to draw components on the JPanel seen on the screen (Gizdich, 2012).
		 */
		public void paintComponent(Graphics g) {
			
				//Fill the JPanel with the background color before drawing other components.
				super.paintComponent(g);//super.paintComponent(g) is used to have the superclass paint the components that aren't going to be painted.
				setBackground(Color.WHITE); //Sets background colour to be painted by the superclass.
				
				//System asks the Board class to paint itself.  The Board class, also calling the Cell class, sets and paints the cells and the symbols in the cells.
				board.paint(g); 
				
				//Sets the status bar messages ready to be painted.
				if (currentState == GameState.Playing) {          
						statusBar.setForeground(Color.BLACK); 
						if (currentPlayer == Player.Cross) {   							
								statusBar.setText("'X's Turn");					
						} else {														
								statusBar.setText("'O's Turn");
						}		
				} else if (currentState == GameState.Draw) {          
						statusBar.setForeground(Color.RED);          
						statusBar.setText("It's a Draw! Click to play again.");  
						
				} else if (currentState == GameState.Cross_won) {          
						statusBar.setForeground(Color.RED);          
						statusBar.setText("'X' Won! Click to play again.");   
						
				} else if (currentState == GameState.Nought_won) {          
						statusBar.setForeground(Color.RED);          
						statusBar.setText("'O' Won! Click to play again.");       
				}
		}	
		/*
		 * INITIATE GAME
		 * Initialization of game-board contents and current status of GameState and Player. 
		 * InitGame() is called in mouseClicked(MouseEvent e) method when GameState is not playing. 
		 */
		public void initGame() {
				
				//Initialize contents in all cells in game board to Player.Empty.
				for (int row = 0; row < ROWS; ++row) {//Loops through rows.					
						for (int col = 0; col < COLS; ++col) {//Loops through columns.							
								board.cells[row][col].content = Player.Empty;//All cells empty with clear() in Cell constructor. 
						}
				}				
				//Initialize the current Status of the gameState to playing.
				currentState = GameState.Playing;
				
				//Initialize the current player to Player Cross. Player Cross always starts first.
				currentPlayer = Player.Cross; 
		}
		/* 
		 * UPDATE GAME
		 * When gameState is Playing, after each move by a mouse click, this method checks to see if their has been
		 * a win or a draw and the gameState is changed appropriately. 
		 */
		public void updateGame(Player thePlayer, int row, int col) {
			
				//Current gameState is playing, current player is empty.		
				if(board.hasWon(thePlayer, row, col)){ //check for win 	
					
						//Winner: current GameState is Cross_won, current Player is Cross.
						if (currentPlayer == Player.Cross) {
		    					currentState = GameState.Cross_won;
						}
						//Winner: current GameState is Nought_won, current Player is Nought.
						else {
		    					currentState = GameState.Nought_won;
						}
				// No winners and board if full: current GameState is Draw.		
				} else if (board.isDraw()) {  // Check for draw.
						currentState = GameState.Draw;
				}
				// Some current player is empty and their are no winners: current GameState stays Playing.						
		}
		/* MOUSE CLICKED EVENT
		 * mouseClicked(MouseEvent e) method of the MouseListener Interface is called just 
		 * after the user clicks the listened-to Board Cells. 
		 */
		public void mouseClicked(MouseEvent e) {  
				
				// Gets the coordinates of where the click event happened in a cell.            
				int mouseX = e.getX();             
				int mouseY = e.getY();             
				
				// Gets the row and column the mouse was clicked in.             
				int rowSelected = mouseY / CELL_SIZE;             
				int colSelected = mouseX / CELL_SIZE;              
				
				// Determines if the game is Playing or if there has been a win or draw:				
				// - GameState Playing
				if (currentState == GameState.Playing) {   
						// Check if there is a empty current player. 
						if (rowSelected >= 0 && rowSelected < ROWS && colSelected >= 0 && colSelected < COLS && board.cells[rowSelected][colSelected].content == Player.Empty) {
								
								// If are empty cells, the current cells symbol is declared the corresponding current player.
								board.cells[rowSelected][colSelected].content = currentPlayer; 
								
								// Check to see if there has been any wins or draws and updates on the currentState.                  
								updateGame(currentPlayer, rowSelected, colSelected); 
								
								// Current state is still playing, switch player.
								if (currentPlayer == Player.Cross) {
										currentPlayer =  Player.Nought;
								}
								else {
										currentPlayer = Player.Cross;
								}
						}  
				// - Game over. There has been a win or a draw.
				} else {        
						// Restart game by initializing the game components again.             
						initGame();            
				}   
				/* 
				 * Inform the system to refresh the drawing canvas by posting the repaint event, which signals the JPanel 
				 * code to call the paintComponent method to redraw the components
				 * on the screen (Eck, 2022).  
				 */
				repaint();
		}
			
		@Override
		public void mousePressed(MouseEvent e) {
		//  Auto-generated, event not used
				
		}
		@Override
		public void mouseReleased(MouseEvent e) {
		//  Auto-generated, event not used
				
		}
		@Override
		public void mouseEntered(MouseEvent e) {
		// Auto-generated,event not used
				
		}
		@Override
		public void mouseExited(MouseEvent e) {
		// Auto-generated, event not used
				
		}

}
