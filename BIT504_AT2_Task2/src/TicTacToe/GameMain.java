package TicTacToe;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//abstract window toolkit, prior to Swing

//Swing uses Model-View-Controller(MVC) architecture
//Have made adjustments to import java.awt.*, import java.swing* to specific classes needed in these packages.
//Doing this will decrease the compile time and readability of the program and it will be clear which classes have been used

public class GameMain extends JPanel implements MouseListener{
	//Constants for game 
	// number of ROWS by COLS cell constants 
	public static final int ROWS = 3;     
	public static final int COLS = 3;  
	public static final String TITLE = "Tic Tac Toe";

	//constants for dimensions used for drawing
	//cell width and height
	public static final int CELL_SIZE = 100;
	//drawing canvas
	public static final int CANVAS_WIDTH = CELL_SIZE * COLS;
	public static final int CANVAS_HEIGHT = CELL_SIZE * ROWS;
	//Noughts and Crosses are displayed inside a cell, with padding from border
	public static final int CELL_PADDING = CELL_SIZE / 6;    
	public static final int SYMBOL_SIZE = CELL_SIZE - CELL_PADDING * 2;    
	public static final int SYMBOL_STROKE_WIDTH = 8;
	
	/*declare game object variables*/
	// the game board 
	private Board board;
	
	private GameState currentState; 
	
	// the current player
	private Player currentPlayer; 
	// for displaying game status message
	private JLabel statusBar;       
	

	/** Constructor to setup the UI and game components on the panel */
	//GameMain CONSTRUCTOR
			public GameMain() {
					//set up the UI and game components on the panel
					
					addMouseListener(this);//registers mouse listener on panel
					//implement the mouse listener.  then define an instance of the listener, then register the listener with the component.
				    //The MouseListner interface have fine abstract methods: moused pressed, mouse released, mouseentered, mouseexited and mouseclick.  
				    //But an adaptor class makes this easier. 
				    // All listeners(are interfaces) extended from EventListener
					//when a listener (which listens for events) is envoked, it activates an Event Handler. important to know what the source of the event was, or what was 
				    //the event that triggered this to happen.  ie mouse click is the original source.
				    //afer an event is generated, an object is created. this object is sent to the appropriate listener.  The object has information about
				    //the source component adn the action it took
				     //Once the listener is invoked, the handler method is executed and returns control
					
					// Setup the status bar (JLabel) to display status message       
					statusBar = new JLabel("         ");       
					statusBar.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 14));       
					statusBar.setBorder(BorderFactory.createEmptyBorder(2, 5, 4, 5));       
					statusBar.setOpaque(true);       
					statusBar.setBackground(Color.LIGHT_GRAY); 				
					setLayout(new BorderLayout()); //layout of the panel is in border layout  
					add(statusBar, BorderLayout.SOUTH);				
					setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT + 30));// account for statusBar height in overall height
									
					//Instantiates object game board and initilizes the game board
					board = new Board();				
			}	
	public static void main(String[] args) {
				// Run this program on the Event Dispatch Thread (EDT)
				javax.swing.SwingUtilities.invokeLater(new Runnable() {// Run GUI code in Event Dispatch thread for thread safety.
						
						public void run() {
						
								
								JFrame frame = new JFrame(TITLE);//set the rest of the stuff
								GameMain gameMain = new GameMain();//https://www.youtube.com/watch?v=4YhrmAGpVtI and https://www.youtube.com/javacodejunkie
								frame.add(gameMain); //it has the extended panel dont have to declare
								
								frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
								frame.pack();//call before making jframe visiable and setSize  //https://stackoverflow.com/questions/8193801/how-to-set-specific-window-frame-size-in-java-swing
								frame.setSize(315, 364);
								frame.setLocationRelativeTo(null); // place in centre of window
								frame.setResizable(false);
								frame.setVisible(true);
						}
				});
		}
			/** Custom painting codes on this JPanel */
		public void paintComponent(Graphics g) {
				/*Initialised from Super class.
				 * paintComponent from parent class.
				 * sets background colour
				 * paints board
				 * board class paints cells
				 * sets status bar message ready to paint
				 * sets symbols to be drawn in cells
				 */
				super.paintComponent(g);
				setBackground(Color.WHITE);//fill background and set colour to white
				//Fills the specified rounded corner rectangle with the current color.The left and right edges of the rectangle
				//are at x and x + width - 1,respectively. The top and bottom edges of the rectangle are at y and y + height - 1.
				//Parameters:x the x coordinate of the rectangle to be filled.y the y coordinate of the rectangle to be filled.
				//width the width of the rectangle to be filled.height the height of the rectangle to be filled.
				//arcWidth the horizontal diameterof the arc at the four corners.
				//arcHeight the vertical diameterof the arc at the four corners.
				board.paint(g); //ask the game board to paint itself
	
				//set currentState message in status bar
				if (currentState == GameState.Playing) {          
						statusBar.setForeground(Color.BLACK); 
						if (currentPlayer == Player.Cross) {   //set players-turn message in status bar
							
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
		//INITATE GAME triggered by Mouse click.
		public void initGame() {//1st player initialized as Empty (empty cell contents), 2nd gameState initialized as playing, 3rd player initialized as Cross 
				
				//initialize contents in all cells in game board are empty
				for (int row = 0; row < ROWS; ++row) {//loops through rows					
						for (int col = 0; col < COLS; ++col) {//loops through cols							
							//if none of players symbols (content) in all rows and cols(Cells) in the board, then the player = Empty (no players piece is on the board). Game can start.
							board.cells[row][col].content = Player.Empty;//all cells empty
						}
				}
				//initialize the current Status of the gameState
				currentState = GameState.Playing;//ready to play				
				//initialize the current player
				currentPlayer = Player.Cross; //Cross is initialize as the X starts first always and will be the first player
		}
	
		/**After each turn check to see if the current player hasWon by putting their symbol in that position, 
		 * If they have the GameState is set to won for that player
		 * If no winner then isDraw is called to see if deadlock, if not GameState stays as PLAYING
		 *   
		 */
		public void updateGame(Player thePlayer, int row, int col) {
			
			
			if(board.hasWon(thePlayer, row, col)){ //check for win 
						
				if (currentPlayer == Player.Cross) {
    				currentState = GameState.Cross_won;
				}
				else {
    				currentState = GameState.Nought_won;
				}		
    		
			} else if (board.isDraw()) {  // check for draw
				currentState = GameState.Draw;
			}
	// Otherwise, no change to current state (still GameState.PLAYING).
						
		}
		public void mouseClicked(MouseEvent e) {  
		// get the coordinates of where the click event happened            
				int mouseX = e.getX();             
				int mouseY = e.getY();             
				// Get the row and column clicked             
				int rowSelected = mouseY / CELL_SIZE;             
				int colSelected = mouseX / CELL_SIZE;               			
				if (currentState == GameState.Playing) {                
					if (rowSelected >= 0 && rowSelected < ROWS && colSelected >= 0 && colSelected < COLS && board.cells[rowSelected][colSelected].content == Player.Empty) {
						// move  
						board.cells[rowSelected][colSelected].content = currentPlayer; 
						// update currentState                  
						updateGame(currentPlayer, rowSelected, colSelected); 
						// Switch player
						if (currentPlayer == Player.Cross) {
							currentPlayer =  Player.Nought;
						}
						else {
							currentPlayer = Player.Cross;
						}
					}             
				} else {        
					// game over and restart              
					initGame();            
				}   
				  // Refresh the drawing canvas by posting the repaint event, which signals
			    //  the JPanel code to call its paintComponent method
				repaint();
				//set up status bar in GameMain()
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
