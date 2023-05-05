package TicTacToe;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class Cell {
		
		/* 
		 * ENUM VARIABLE
		 * The content of this cell (Empty, Cross, Nought).
		 */
		Player content; 
		
		//ROW AND COL VARIABLES of this cell
		int row, col;
		
		// CONSTRUCTOR to initialize this cell
		public Cell(int row, int col) {
			
				//Initialize this cells row and col coordinations.
				this.row = row;
				this.col = col;				
				// Calls the method to set this cells content to Empty.
				clear(); //(this.content = Player.Empty)
		}
		
		//PAINT CELL 
		public void paint(Graphics g) {
			
				//Graphics2D allows the setting of the pen's stroke size.
				Graphics2D graphic2D = (Graphics2D) g;
				graphic2D.setStroke(new BasicStroke(GameMain.SYMBOL_STROKE_WIDTH, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
				
				//The position coordinates for the current player to paint.
				int x1 = col * GameMain.CELL_SIZE + GameMain.CELL_PADDING;
				int y1 = row * GameMain.CELL_SIZE + GameMain.CELL_PADDING;
				
				// Paint the symbol for Player Cross.
				if (content == Player.Cross) {
						graphic2D.setColor(Color.RED);
						int x2 = (col + 1) * GameMain.CELL_SIZE - GameMain.CELL_PADDING;
						int y2 = (row + 1) * GameMain.CELL_SIZE - GameMain.CELL_PADDING;
						graphic2D.drawLine(x1, y1, x2, y2);
						graphic2D.drawLine(x2, y1, x1, y2);
						
				// Paint the symbol for Player Nought.		
				}else if (content == Player.Nought) {
						graphic2D.setColor(Color.BLUE);
						graphic2D.drawOval(x1, y1, GameMain.SYMBOL_SIZE, GameMain.SYMBOL_SIZE);
				}
		}		
		/* 
		 * CLEAR CELL
		 * Set this cell's content to EMPTY. 
		 */
		public void clear() {
				this.content = Player.Empty;			
		}		
}

