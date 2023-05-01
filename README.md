# TIC-TAC-TOE-GAME
BIT504_AT2_Task2 Java Project 

OBJECTIVE

This project aims to develop a Tic Tac Toe game using Java. It consists of developing and implementing a two-player Tic Tac Toe game program with a simplified graphical version. This project aims to continue developing the initial code and indications for this Tic Tac Toe game from another programmer.

GAME DESCRIPTION

This Tic Tak Toe game is a two-player mouse-controlled computer game. The players take turns clicking spaces in a 3 x 3 grid board with their designated symbol, either X or O. The game's goal is for one player to get three symbols in a row- horizontally, vertically or diagonally on the 3x3 grid. This program displays on the screen that this player won the game.

GAME RULES

The player who choices to go first is designated the symbol X; therefore, the other player automatically is designated the symbol O. The player clicks on one of the cells in the 3x3 grid marking the cell with an 'X' or an 'O'. Alternative play continues until the player with the first three cells with symbols matching horizontally, vertically or diagonally on the grid is declared the winner. If there is no winner when all the grid cells are filled, the game ends in a draw/tie. The game then restarts.

IMPLEMENTATION PLAN

- Start
- Display empty board 
- Loop 
  - Mouse click to display X's turn
  - Get player one ('X') cell move.
  - Game logic checks if player one ('X') has won:
    - if yes - Game over, exit loop.
    - if no  - Check if draw:
      - if yes - Game over, exit loop.
      - if no  - Display O's turn.
  - Get Player two ('O') cell move.
  - Game logic checks if player two ('O') has won:
    - if yes - Game over, exit loop.
    - if no  - Check if draw:
      - if yes - Game over, exit loop.
      - if no  - Repeat loop.                      
- End game   
 
 CODE IMPLEMENTATION
 
- Run the code.
- A 'white' Window with a grey lined 3 X 3 grid appears on the screen.
- The Title "Tic Tac Toe" appears.
- A Statis bar at the bottom of the window displays a status message, with the colour grey with a border and bold font shown.
- "X's turn" will be displayed in the Statis bar in black.
- Once the first player clicks a cell on the game board, an 'X' in red will appear. This will be defined as player one.
- If Player One wins, "'X' Won! Click to play again." will appear in the colour 'Red' on the screen.
- Once the second player clicks a cell on the game board, an 'O' in 'blue' will appear. This will be defined as player two.
- If Player Two wins, "'O' Won! Click to play again." will appear in the colour 'Red' on the screen.
- If there is a draw, "It's a Draw! Click to play again." will appear in colour 'Red' on the screen.
- The players click any cell to restart when there is a winner or a draw.
