package TicTacToe;

import java.awt.*;

public class Board {
			
		//CONSTANTS FOR DRAWING THE GRID LINES 
		public static final int GRID_WIDTH = 8;// Grid line width.			
		public static final int GRID_WIDHT_HALF = GRID_WIDTH / 2;// Grid line half width.
			
		/* 
		 * OBJECT VARIABLE
		 * 2D array of [ROWS]x[COLS] Cell instances that would 
		 * represent the 3X3 board of the game.
		 */
		Cell [][] cells; 
				
		//CONSTRUCTOR
		public Board() {
				
			 	//Initialize the cells in the board.
				cells = new Cell[GameMain.ROWS][GameMain.COLS];//Allocate the 2D array.
				
				//Iterate through every cell inside the 2D array.
				for (int row = 0; row < GameMain.ROWS; ++row) {
						for (int col = 0; col < GameMain.COLS; ++col) {
							//Allocate element of the array.
							cells[row][col] = new Cell(row, col);
						}
				}
		}					
		/* 
		 * CHECK FOR A DRAW
		 * Called in the updateGame(Player thePlayer, int row, int col) method.
		 * Returns true if it is a draw. 
		 */
		public boolean isDraw() {				
				/*
				 *  Nobody's won. Check whether the game has ended in a draw (all cells are occupied) or continue playing (empty cells are present).
				 *  Iterate through every cell inside the 2D array.
				 */
				for (int row = 0; row < GameMain.ROWS; ++row) {
					 	for (int col = 0; col < GameMain.COLS; ++col) {	
					 			//It is not a draw if the 2D array has any cells with no player in it.
					 			if(cells[row][col].content == Player.Empty ) {
						 				//Continue the gameState - playing.	
						 				return false;
					 			}
					 	}	
				}
				// It is a draw as there are no empty cells.
				 return true;	
		}
		/*
		 * CHECK FOR A WIN
		 * Called in the updateGame(Player thePlayer, int row, int col) method. 
		 * Returns true if the current player has three of their symbols in a row, column or diagonal.	
		 */
		public boolean hasWon(Player thePlayer, int playerRow, int playerCol) {
				
				// check if player has 3 in a row in 4 different directions:
				if (cells[playerRow][0].content == thePlayer && cells[playerRow][1].content == thePlayer && cells[playerRow][2].content == thePlayer ) //3 in the row
						return true;
				
				if (cells[0][playerCol].content == thePlayer && cells[1][playerCol].content == thePlayer && cells[2][playerCol].content == thePlayer )//3 in the column
						return true;
				
				if (cells[0][0].content == thePlayer && cells[1][1].content == thePlayer && cells[2][2].content == thePlayer) // 3-in-the-diagonal
						return true;
				
				if (cells[0][2].content == thePlayer && cells[1][1].content == thePlayer && cells[2][0].content == thePlayer) // 3 in the opposite diagonal
						return true;
				//No winner, gameState remains as playing.
				return false;		
			
		// TESTED: test for both players in all 8 different scenarios is as expected			
		}
		/* 
		 * PAINT BOARD
		 * Board grid is drawn(rows then columns). 
		 * Cell class to paint the cells into the grid is called.
		 */	
		public void paint(Graphics g) {			
				/*
				 * Draw the grid: 
				 * 2 horizontal (rows) constant sized rounded rectangles filled with the colour gray.
				 * 2 vertical (cols) constant sized rounded rectangles filled with the colour gray.
				 */
				g.setColor(Color.gray);
				// Iterate through every row (2) drawing a grey filled rounded rectangle of the dimensions below.
				for (int row = 1; row < GameMain.ROWS; ++row) {        
						g.fillRoundRect(0, GameMain.CELL_SIZE * row - GRID_WIDHT_HALF,                
								GameMain.CANVAS_WIDTH - 1, GRID_WIDTH,                
								GRID_WIDTH, GRID_WIDTH); //Size of rounded rectangle.      
						}
				// Iterate through every col (2) drawing a grey filled rounded rectangle of the dimensions below.
				for (int col = 1; col < GameMain.COLS; ++col) {       
						g.fillRoundRect(GameMain.CELL_SIZE * col - GRID_WIDHT_HALF, 0,                
								GRID_WIDTH, GameMain.CANVAS_HEIGHT - 1,                
								GRID_WIDTH, GRID_WIDTH);
				}
				/* 
				 * Draw the cells:
				 * Iterate through every cell inside the 2D array and paint the cells.
				 */
				for (int row = 0; row < GameMain.ROWS; ++row) {          
						for (int col = 0; col < GameMain.COLS; ++col) {  
								cells[row][col].paint(g);//Calls the paint(g) method in Cell class to paint itself.
						}
				}
		}
}
