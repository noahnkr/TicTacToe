import java.util.Random;

public class TicTacToe {
    
    private char[][] board;

    private int moveCount;

    private boolean gameOver;

    private static final int BOARD_SIZE = 3;

    private static char EMPTY = '\u0000';

    private static char PLAYER = 'X';

    private static char OPPONENT = 'O';

    public TicTacToe() {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        gameOver = false;
        moveCount = 0;
    }

    public void move(int row, int col, char team) {
        if (row < 0 || row > 2 || col < 0 || col > 2)
                throw new IllegalArgumentException(row + " < or > 2, " + col + " < or > 2");

        if (board[row][col] != EMPTY)
            throw new IllegalStateException();

        board[row][col] = team;
        moveCount++;
        checkGameOver(row, col, team);
    }

    public void randomOpponentMove() {
        Random rand = new Random();
        
        while(true) {
            int x = rand.nextInt(2);
            int y = rand.nextInt(2);
            if (isValidMove(x, y)) {
                move(x, y, OPPONENT);
                break;
            }
        }
    }

    private boolean isValidMove(int row, int col) {
        if (board[row][col] != EMPTY)
            return false;

        return true;
    }

    private void checkGameOver(int row, int col, char team) {
        // check horiontal 
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][col] != team)
                break;
            if (i == BOARD_SIZE - 1) {
                if (team == OPPONENT)
                    gameOver(-1);
                else
                    gameOver(1);
            }
        }

        // check vertical
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[row][i] != team)
                break;
            if (i == BOARD_SIZE -1) {
                if (team == OPPONENT)
                    gameOver(-1);
                else
                    gameOver(1);   
            }
        }

        // check diagonal
        if (row == col) {
            for (int i = 0; i < BOARD_SIZE; i++) {
                if (board[i][i] != team)
                    break;
                if (i == BOARD_SIZE - 1) {
                    if (team == OPPONENT)
                        gameOver(-1);
                    else
                        gameOver(1);
                }

            }
        }

        // check anti diagonal
        if (row + col == BOARD_SIZE - 1) {
            for (int i = 0; i < BOARD_SIZE; i++) {
                if (board[i][(BOARD_SIZE - 1) - i] != team)
                    break;
                if (i == BOARD_SIZE - 1) {
                    if (team == OPPONENT)
                        gameOver(-1);
                    else
                        gameOver(1);
                }
            }
        }

        // check tie
        if (moveCount == Math.pow(BOARD_SIZE, 2) - 1) {
            System.out.println(moveCount + "=" + (Math.pow(BOARD_SIZE,2) -1));
            gameOver(0);

        }
            
    }

    private void gameOver(int type) {        
        if (type == -1) {
            System.out.println("You Lose!");
        } else if (type == 1) {
            System.out.println("You win!");
        } else {
            System.out.println("Tie!");
        }

        gameOver = true;
    }

    public boolean isGameOver() {
        return gameOver;
    }


    public String toString() {
        String ret = " ";
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == EMPTY)
                    ret += " ";
                else
                    ret += board[i][j];

                if (j != 2)
                    ret += " | ";
                else
                    ret += "\n";
            }
            if (i != 2)
                ret += "---+---+---\n ";
        }
        
        return ret;
    }
    
}
