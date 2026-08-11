package com.td.tictactoe;

import com.td.tictactoe.factory.GameFactory;
import com.td.tictactoe.model.Game;

public class TicTacToeSystem {
    private final Game game;

    public TicTacToeSystem() {
        this(GameFactory.createDefaultGame());
    }

    public TicTacToeSystem(Game game) {
        if (game == null) {
            throw new IllegalArgumentException("Game must not be null");
        }
        this.game = game;
    }

    public void startGame() {
        game.play();
    }

    public void makeMove(int row, int col) {
        game.makeMove(row, col);
    }

    public void printBoard() {
        game.printBoard();
    }
}



