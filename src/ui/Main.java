package ui;

import logic.Game; // Import Game class
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Read user input 
        Game game = new Game(); // Create an instance from the Game class
        boolean playing = true; // Variable to control menu cycle

        System.out.println("Welcome to Tic Tac Toe!");

        while (playing) {
            System.out.println("\nMenu;");
            System.out.println("1. Play Tic Tac Toe");
            System.out.println("2. Exit");
            System.out.println("Choose an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    playGame(game, scanner); // Selection 1: Call the method to play
                    break;
                case 2:
                    System.out.println("Goodbye!"); // Selection 2: Exit the cycle
                    playing = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();

    }

    // Method to execute the main logic of the game
    private static void playGame(Game game, Scanner scanner) {
        game = new Game(); // Reset the board
        boolean gameRunning = true;

        while (gameRunning) {
            System.out.println("\nCurrent Board:");
            game.printBoard();
            
            System.out.println("Player " + (game.getCurrentPlayer()) + "'s turn.");
            System.out.print("Enter row (0, 1, 2): ");
            int row = scanner.nextInt();
            System.out.print("Enter column (0, 1, 2): ");
            int col = scanner.nextInt();

            if (game.makeMove(row, col)) {
                if (game.checkWinner()) {
                    System.out.println("\nPlayer " + game.getCurrentPlayer() + " wins!");
                    game.printBoard();
                    gameRunning = false;
                } else if (game.isBoardFull()) {
                    System.out.println("\nIt's a draw!");
                    game.printBoard();
                    gameRunning = false;
                } else {
                    game.switchPlayer();
                }
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
    }
}
