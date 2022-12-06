import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    
    private int[][] board;

    private int moveCount;

    private int winner;

    private boolean gameOver;

    private static final int BOARD_SIZE = 3;

    private static final int EMPTY = 0;

    private static final int PLAYER = 1;

    private static final int OPPONENT = -1;


    public TicTacToe() {
        board = new int[BOARD_SIZE][BOARD_SIZE];
        gameOver = false;
        winner = 0;
        moveCount = 0;
    }

    public void move(int row, int col, int team) {
        if (row < 0 || row > BOARD_SIZE - 1 || col < 0 || col > BOARD_SIZE - 1)
                throw new IllegalArgumentException(row + " < or > 2, " + col + " < or > 2");

        if (board[row][col] != EMPTY)
            throw new IllegalStateException();

        board[row][col] = team;
        moveCount++;
        checkGameOver(row, col, team);
    }

    public int minimax(int[][] board, int depth, boolean isMaximizing) {
        double maxEval = Double.NEGATIVE_INFINITY;
        double minEval = Double.POSITIVE_INFINITY;

        if (gameOver)
            return winner;

        if (isMaximizing) {
            for (int i = 0; i < BOARD_SIZE; i++) {
                for (int j = 0; j < BOARD_SIZE; j++) {
                    if (isValidMove(i, j)) {
                        move(i, j, PLAYER);
                        int eval = minimax(board, depth + 1, false);
                        maxEval = Math.max(maxEval, eval);
                        board[i][j] = EMPTY; // undo move 
                    }
                }
            }
            return (int) maxEval;
        } else {
            for (int i = 0; i < BOARD_SIZE; i++) {
                for (int j = 0; j < BOARD_SIZE; j++) {
                    if (isValidMove(i, j)) {
                        move(i, j, OPPONENT);
                        int eval = minimax(board, depth + 1, true);
                        minEval = Math.min(minEval, eval);
                        board[i][j] = EMPTY;
                    }
                }
            }
            return (int) minEval;
        }

    }

    public int findBestMove() {
        double maxEval = Double.POSITIVE_INFINITY;
        int bestMove = -1;

        for (int i = 0; i < Math.pow(BOARD_SIZE, 2); i++) {
                if (isValidMove(i / 3, i % 3)) {
                    board[i / 3][i % 3] = PLAYER;
                    int eval = minimax(board, 0, false);
                    board[i / 3][i % 3] = EMPTY;

                    if (eval > maxEval) {
                        bestMove = i;
                        maxEval = eval;
                    }
                }
            }
        return bestMove;
    }

    public void randomOpponentMove() {
        Random rand = new Random();
        
        while(true) {
            int pos = rand.nextInt((int) Math.pow(BOARD_SIZE, 2));
            if (isValidMove(pos / BOARD_SIZE, pos % BOARD_SIZE )) {
                move(pos / BOARD_SIZE, pos % BOARD_SIZE, OPPONENT);
                break;
            }
        }
    }

    private boolean isValidMove(int row, int col) {
        if (board[row][col] != EMPTY)
            return false;

        return true;
    }

    private void checkGameOver(int row, int col, int team) {
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
                if (board[i][BOARD_SIZE - 1 - i] != team)
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
        if (moveCount == Math.pow(BOARD_SIZE, 2))
            gameOver(0);
    }

    private void gameOver(int type) {        
        /*if (type == -1) {
            System.out.println("You Lose!");
        } else if (type == 1) {
            System.out.println("You win!");
        } else {
            System.out.println("Tie!");
        }*/

        gameOver = true;
        winner = type;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public String toString() {
        String ret = " ";
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == PLAYER) {
                    ret += "X";
                } else if (board[i][j] == OPPONENT) {
                    ret += "O";
                } else {
                    ret += " ";
                }
            
                if (j != BOARD_SIZE - 1)
                    ret += " | ";
                else
                    ret += "\n";
            }
            if (i != BOARD_SIZE - 1)
                ret += "---+---+---\n ";
        }
        
        return ret;
    }
}
