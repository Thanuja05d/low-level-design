package com.td.tictactoe.observer;

import com.td.tictactoe.model.*;

public class ConsoleGameObserver implements GameObserver{

    @Override
    public void onGameStateChanged(Game game){
        game.printBoard();
        System.out.flush();
        GameStatus status = game.getStatus();
        if(status == GameStatus.WIN){
            System.out.println("\n -------- Congratulations "+game.getCurrentPlayer().getName()+" wins--------");
        }else if(status == GameStatus.DRAW){
            System.out.println("\n------ The game ended in a DRAW.--------");
        }
        System.out.flush();
    }
}