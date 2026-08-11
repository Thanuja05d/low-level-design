package com.td.tictactoe.factory;

import com.td.tictactoe.model.Board;
import com.td.tictactoe.model.Game;
import com.td.tictactoe.model.Player;
import com.td.tictactoe.model.Symbol;
import com.td.tictactoe.observer.ConsoleGameObserver;

public final class GameFactory {
    private static final int DEFAULT_BOARD_SIZE = 3;

    private GameFactory() {
    }

    public static Game createDefaultGame() {
        return createGame(DEFAULT_BOARD_SIZE, "Player 1", "Player 2");
    }

    public static Game createGame(int boardSize, String playerOneName, String playerTwoName) {
        Board board = new Board(boardSize);
        Player[] players = new Player[]{
                new Player(playerOneName, Symbol.X),
                new Player(playerTwoName, Symbol.O)
        };

        Game game = new Game(board, players);
        game.addObserver(new ConsoleGameObserver());
        return game;
    }
}



