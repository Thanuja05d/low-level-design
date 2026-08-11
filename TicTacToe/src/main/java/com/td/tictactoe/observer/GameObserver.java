package com.td.tictactoe.observer;

import com.td.tictactoe.model.Game;
public interface GameObserver{
    void onGameStateChanged(Game game);
}