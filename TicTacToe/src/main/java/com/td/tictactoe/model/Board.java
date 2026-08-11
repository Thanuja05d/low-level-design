package com.td.tictactoe.model;

public class Board {
    private final Cell[][] grid;
    private final int size;

    public Board(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Board size must be positive");
        }
        this.size = size;
        this.grid = new Cell[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                grid[row][col] = new Cell(row, col);
            }
        }
    }

    public int getSize() {
        return size;
    }

    public boolean isCellEmpty(int row, int col) {
        validateCoordinates(row, col);
        return grid[row][col].getSymbol() == null;
    }

    public void setCell(int row, int col, Symbol symbol) {
        validateCoordinates(row, col);
        if (symbol == null) {
            throw new IllegalArgumentException("Symbol must not be null");
        }
        if (!isCellEmpty(row, col)) {
            throw new IllegalStateException("Cell is already occupied");
        }
        grid[row][col].setSymbol(symbol);
    }

    public void printBoard() {
        System.out.println();
        System.out.print("   ");
        for (int col = 0; col < size; col++) {
            System.out.print((col + 1) + "   ");
        }
        System.out.println();

        for (int row = 0; row < size; row++) {
            System.out.print((row + 1) + " ");
            for (int col = 0; col < size; col++) {
                Symbol symbol = grid[row][col].getSymbol();
                char value = symbol == null ? ' ' : symbol.name().charAt(0);
                System.out.print(" " + value + " ");
                if (col < size - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (row < size - 1) {
                System.out.print("  ");
                for (int col = 0; col < size; col++) {
                    System.out.print("---");
                    if (col < size - 1) {
                        System.out.print("+");
                    }
                }
                System.out.println();
            }
        }
        System.out.println();
    }

    public boolean isFull() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (grid[row][col].getSymbol() == null) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkWin(Symbol symbol) {
        if (symbol == null) {
            return false;
        }

        for (int row = 0; row < size; row++) {
            boolean rowWin = true;
            for (int col = 0; col < size; col++) {
                if (grid[row][col].getSymbol() != symbol) {
                    rowWin = false;
                    break;
                }
            }
            if (rowWin) {
                return true;
            }
        }

        for (int col = 0; col < size; col++) {
            boolean colWin = true;
            for (int row = 0; row < size; row++) {
                if (grid[row][col].getSymbol() != symbol) {
                    colWin = false;
                    break;
                }
            }
            if (colWin) {
                return true;
            }
        }

        boolean mainDiagonalWin = true;
        for (int index = 0; index < size; index++) {
            if (grid[index][index].getSymbol() != symbol) {
                mainDiagonalWin = false;
                break;
            }
        }
        if (mainDiagonalWin) {
            return true;
        }

        boolean antiDiagonalWin = true;
        for (int index = 0; index < size; index++) {
            if (grid[index][size - 1 - index].getSymbol() != symbol) {
                antiDiagonalWin = false;
                break;
            }
        }
        return antiDiagonalWin;
    }

    private void validateCoordinates(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new IllegalArgumentException("Cell coordinates are out of bounds");
        }
    }
}
