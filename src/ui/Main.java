package ui;

import logic.Game; // Import Game class
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Read user input 
        Game game = new Game(); // Create an instance from the Game class
        boolean playing = true; // Variable to control menu cycle

        System.out.println("\n==============================");
        System.out.println("\u001B[34mWelcome to Tic Tac Toe!\u001B[0m");
        System.out.println("==============================");

        while (playing) {
            System.out.println("\nMain Menu:");
            System.out.println("==============================");
            System.out.println("1. Play against another player");
            System.out.println("2. Play against the computer");
            System.out.println("3. Exit");
            System.out.println("==============================");
            System.out.println("Choose an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    playGame(game, scanner, false); // Selection 1: Call the method to play against another player
                    break;
                case 2:
                    playGame(game, scanner, true); // Selection 2: Call the method to play against computer
                    break;    
                case 3:
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
    private static void playGame(Game game, Scanner scanner, boolean vsComputer) {
        boolean keepPlaying = true; // Control for restarting the game
        
        while (keepPlaying) {
            game = new Game(); // Reset the board
            boolean gameRunning = true;

            while (gameRunning) {
                System.out.println("\nCurrent Board:");
                game.printBoard();
                
                if (!vsComputer || game.getCurrentPlayer() == 'X') {
                    // Player turn
                    System.out.println("Player " + game.getCurrentPlayer() + "'s turn.");
                    
                    int row, col; 
                    while (true) {
                        System.out.print("Enter row (0, 1, 2): ");
                        row = scanner.nextInt();
                        System.out.print("Enter column (0, 1, 2): ");
                        col = scanner.nextInt();

                        if (game.makeMove(row, col)) {
                            break; // Valid move, exit loop
                        } else {
                            System.out.println("Invalid move! Try again.");
                        }
                    }
                } else {
                    // Computer's turn (if applicable)
                    System.out.println("Computer's turn...");
                    int[] move = game.getComputerMove();
                    game.makeMove(move[0], move[1]);
                }

                // Check if there is a winner
                if (game.checkWinner()) {
                    System.out.println("\n\u001B[34mPlayer " + game.getCurrentPlayer() + " wins!\u001B[0m");
                    game.printBoard();
                    gameRunning = false;
                } else if (game.isBoardFull()) {
                    System.out.println("\nIt's a draw!");
                    game.printBoard();
                    gameRunning = false;
                } else {
                    game.switchPlayer();
                }
            }

            // Show the end-game-menu and select to restart or exit
            keepPlaying = showEndGameMenu(scanner);
        }
    }

    // Method to show end-game-menu
    private static boolean showEndGameMenu(Scanner scanner) {
        System.out.println("\n==============================");
        System.out.println("\u001B[31m        Game Over!\u001B[0m");
        System.out.println("==============================");
        System.out.println("1. Restart the game");
        System.out.println("2. Exit");
        System.out.println("==============================");
        System.out.println("Select an option: ");

        int choice;
        try {
            choice = scanner.nextInt();
        } catch (Exception e) {
            scanner.nextLine();
            System.out.println("Invalid input! Please enter 1 or 2.");
            return showEndGameMenu(scanner);
        }

        switch (choice) {
            case 1:
                return true;
            case 2:
                System.out.println("Thanks for playing! Goodbye!");
                return false;
            default:
                System.out.println("Invalid choice! Please enter 1 or 2.");
                return showEndGameMenu(scanner);
        }
    }
}
