package logic;
import java.util.Random; // Random number generator

public class Game {
    private char[][] board; // Board
    private char currentPlayer; // Current player, can be 'X' or 'O'

    // Constructor 
    public Game() {
        board = new char[3][3];
        currentPlayer = 'X';
        initializeBoard();
    }

    // Method to initialize the board
    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-'; // '-' represents empty space
            }
        }
    }

    // Method to print the board
    public void printBoard() {
        System.out.println("  0   1   2");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 3 - 1) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (i < 3 - 1) {
                System.out.println("  ---------");
            }
        }
    }

    // Method to check if the board is full
    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        
        // The board is full, no empty cells founded
        return true; 
    }

    // Method to check if there is a winner
    public boolean checkWinner() {
        // Validate rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;
            }
        }

        // Validate columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == currentPlayer && board[1][j] == currentPlayer && board[2][j] == currentPlayer) {
                return true;
            }
        }

        // Validate diagonals
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            return true;
        }

        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
            return true;
        }
        
        // No winner
        return false;
    }

    // Method to change player
    public void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    // Method to make a movement
    public boolean makeMove(int row, int col) {
        if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '-') {
            board[row][col] = currentPlayer;
            return true;
        }

        // Invalid movement
        return false;
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    // COM player logic
    public int[] getComputerMove() {
        Random random = new Random();
        int row, col;

        do {
            row = random.nextInt(3); // Random number between 0 and 2
            col = random.nextInt(3); // Random number between 0 and 2
        } while (board[row][col] != '-');

        return new int[] {row, col};
    }
}
