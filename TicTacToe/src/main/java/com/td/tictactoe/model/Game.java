package com.td.tictactoe.model;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import com.td.tictactoe.observer.GameObserver;
public class Game {
    private final Board board;
    private final Player[] players;
    private final List<GameObserver> observers;
    private int currentPlayerIndex;
    private GameStatus status;
    public Game() {
        this(new Board(3), new Player[]{
                new Player("Player 1", Symbol.X),
                new Player("Player 2", Symbol.O)
        });
    }
    public Game(Board board, Player[] players) {
        if (board == null) {
            throw new IllegalArgumentException("Board must not be null");
        }
        if (players == null || players.length != 2) {
            throw new IllegalArgumentException("Exactly two players are required");
        }
        this.board = board;
        this.players = players.clone();
        this.observers = new ArrayList<>();
        this.currentPlayerIndex = 0;
        this.status = GameStatus.IN_PROGRESS;
    }
    public void play() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Tic Tac Toe");
        System.out.println("Enter moves as row and column numbers from 1 to " + board.getSize() + ".");
        notifyObservers();
        while (status == GameStatus.IN_PROGRESS) {
            Player currentPlayer = getCurrentPlayer();
            System.out.print(currentPlayer.getName() + " (" + currentPlayer.getSymbol() + "), enter row and column: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid row number.");
                scanner.nextLine();
                continue;
            }
            int row = scanner.nextInt() - 1;
            if (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid column number.");
                scanner.nextLine();
                continue;
            }
            int col = scanner.nextInt() - 1;
            scanner.nextLine();
            try {
                makeMove(row, col);
            } catch (RuntimeException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }
    public void printBoard() {
        board.printBoard();
    }
    public void addObserver(GameObserver observer) {
        if (observer == null) {
            throw new IllegalArgumentException("Observer must not be null");
        }
        observers.add(observer);
    }
    public void removeObserver(GameObserver observer) {
        observers.remove(observer);
    }
    public void makeMove(int row, int col) {
        if (status != GameStatus.IN_PROGRESS) {
            throw new IllegalStateException("Game is already over");
        }
        Player currentPlayer = getCurrentPlayer();
        board.setCell(row, col, currentPlayer.getSymbol());
        if (checkWin()) {
            status = GameStatus.WIN;
        } else if (checkDraw()) {
            status = GameStatus.DRAW;
        } else {
            switchPlayer();
        }
        notifyObservers();
    }
    public boolean checkWin() {
        for (Player player : players) {
            if (board.checkWin(player.getSymbol())) {
                status = GameStatus.WIN;
                return true;
            }
        }
        return false;
    }
    public boolean checkDraw() {
        if (board.isFull() && status != GameStatus.WIN) {
            status = GameStatus.DRAW;
            return true;
        }
        return false;
    }
    public void switchPlayer() {
        if (status == GameStatus.IN_PROGRESS) {
            currentPlayerIndex = 1 - currentPlayerIndex;
        }
    }
    public Player getCurrentPlayer() {
        return players[currentPlayerIndex];
    }
    public Board getBoard() {
        return board;
    }
    public GameStatus getStatus() {
        return status;
    }
    private void notifyObservers() {
        for (GameObserver observer : observers) {
            observer.onGameStateChanged(this);
        }
    }
}
