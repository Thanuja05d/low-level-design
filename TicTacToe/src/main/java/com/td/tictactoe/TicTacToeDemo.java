package com.td.tictactoe;

import java.util.Scanner;
import com.td.tictactoe.factory.GameFactory;
import com.td.tictactoe.TicTacToeSystem;
public class TicTacToeDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            System.out.println("\n=== Tic Tac Toe Game ===");
            System.out.print("Enter Player 1 name (X): ");

            String player1Name = scanner.nextLine().trim();
            if (player1Name.isBlank()) {
                player1Name = "Player 1";
            }

            System.out.print("Enter Player 2 name (O): ");
            String player2Name = scanner.nextLine().trim();
            if (player2Name.isBlank()) {
                player2Name = "Player 2";
            }

            TicTacToeSystem system = new TicTacToeSystem(
                GameFactory.createGame(3, player1Name, player2Name)
            );

            system.startGame();
            
            System.out.print("\nPlay again? (Y/N): ");
            String response = scanner.nextLine().trim().toUpperCase();
            playAgain = response.equals("Y");
        }
        System.out.println("Thanks for playing! Goodbye!");
        scanner.close();
    }
}
