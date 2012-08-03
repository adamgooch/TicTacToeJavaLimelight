package gooch.tictactoe;

import java.util.ArrayList;

public class Board {
    public static final int NUMBER_OF_SQUARES = 9;
    public static final int BOARD_DIMENSION = 3;
    private char[][] gameBoard;

    public Board() {
        gameBoard = new char[BOARD_DIMENSION][BOARD_DIMENSION];
        for(int i = 0; i < NUMBER_OF_SQUARES; i++) {
            gameBoard[getRow(i)][getColumn(i)] = Character.forDigit(i, NUMBER_OF_SQUARES);
        }
    }

    public String asString() {
        return "\n " + gameBoard[0][0] + " | " + gameBoard[0][1] + " | " + gameBoard[0][2] + "\n" +
                "-----------\n" +
                 " " + gameBoard[1][0] + " | " + gameBoard[1][1] + " | " + gameBoard[1][2] + "\n" +
                "-----------\n" +
                 " " + gameBoard[2][0] + " | " + gameBoard[2][1] + " | " + gameBoard[2][2] + "\n\n";
    }

    public Board clone() {
        Board newBoard = new Board();
        for(int i = 0; i < NUMBER_OF_SQUARES; i++) {
            char mark = this.getMarkInSquare(i);
            newBoard.putMarkInSquare(mark, i);
        }
        return newBoard;
    }

    public boolean putMarkInSquare(char mark, int square) {
        int row = getRow(square);
        int column = getColumn(square);
        if(squareIsAvailable(Math.abs(square))) {
            gameBoard[row][column] = mark;
            return true;
        }
        return false;
    }

    private boolean squareIsAvailable(int square) {
        if(square < NUMBER_OF_SQUARES &&
                getMarkInSquare(square) != Game.PLAYER_ONE &&
                getMarkInSquare(square) != Game.PLAYER_TWO)
            return true;
        return false;
    }

    public void removeMarkInSquare(int square) {
        gameBoard[getRow(square)][getColumn(square)] = Character.forDigit(square,
                                                                NUMBER_OF_SQUARES);
    }

    public char getMarkInSquare(int square) {
        return gameBoard[getRow(square)][getColumn(square)];
    }

    private int getRow(int square) {
        return square / BOARD_DIMENSION;
    }

    private int getColumn(int square) {
        return square % BOARD_DIMENSION;
    }

    public ArrayList getAvailableSquares() {
        ArrayList moves = new ArrayList();
        for(int square = 0; square < NUMBER_OF_SQUARES; square++) {
            if(squareIsAvailable(square))
                moves.add(square);
        }
        return moves;
    }

    public int countSquaresAvailable() {
        int count = 0;
        for(int square = 0; square < NUMBER_OF_SQUARES; square++) {
            if(squareIsAvailable(square))
                count++;

        }
        return count;
    }


}
