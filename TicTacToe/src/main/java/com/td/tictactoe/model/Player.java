package com.td.tictactoe.model;

public class Player{
    private final String name;
    private final Symbol symbol;

    public Player(String name, Symbol symbol){
        if(name==null || name.isBlank()){
            throw new IllegalArgumentException("Player name cannot be empty.");
        }
        if(symbol==null){
            throw new IllegalArgumentException("Player Symbol must be selected");
        }
        this.name=name;
        this.symbol=symbol;
    }

    public String getName(){ return name;}
    public Symbol getSymbol(){ return symbol;}
}