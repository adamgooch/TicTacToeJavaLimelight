package gooch.tictactoe;

import java.util.ArrayList;

public class Board {
    public static int FOUR_X_FOUR = 4;
    public static int THREE_X_THREE = 3;
    public int dimension;
    public int numberOfSquares;

    protected char[][] gameBoard;

    public Board(int dimension) {
        this.dimension = dimension;
        numberOfSquares = dimension * dimension;
        gameBoard = new char[dimension][dimension];
        initializeBoard();
    }

    private void initializeBoard() {
        for(int square = 0; square < numberOfSquares; square++) {
            gameBoard[getRow(square)][getColumn(square)] = ' ';
        }
    }

    public String asString() {
        String result = " \n";
        for(int square = 0; square < numberOfSquares; square++) {
            int row = getRow(square);
            int col = getColumn(square);
            if(col == 0) result += " "; // add space to beginning of each row
            result += (gameBoard[row][col] == ' ') ? square : Character.toString(gameBoard[row][col]);
            if(square < 10 || gameBoard[row][col] != ' ') result += " ";
            result += (col != dimension - 1) ? "| " : "\n";
            if((square + 1) % dimension == 0 && square != numberOfSquares - 1)
                result += dashes(dimension * 4 - 1);

        }
        return result;
    }

    private String dashes(int number) {
        String result = "";
        for(int i = 0; i < number; i++) {
            result += "-";
        }
        return result + "\n";
    }

    private int getRow(int square) {
        return square / dimension;
    }

    private int getColumn(int square) {
        return square % dimension;
    }

    public boolean putMarkInSquare(char mark, int square) {
        int row = getRow(square);
        int column = getColumn(square);
        if(squareIsAvailable(square)) {
            gameBoard[row][column] = mark;
            return true;
        }
        return false;
    }

    protected boolean squareIsAvailable(int square) {
        if(square < numberOfSquares && getMarkInSquare(square) == ' ')
            return true;
        return false;
    }

    public char getMarkInSquare(int square) {
        return gameBoard[getRow(square)][getColumn(square)];
    }

    public void removeMarkInSquare(int square) {
        gameBoard[getRow(square)][getColumn(square)] = ' ';
    }

    public int countSquaresAvailable() {
        int count = 0;
        for(int square = 0; square < numberOfSquares; square++) {
            if(squareIsAvailable(square))
                count++;
        }
        return count;
    }

    public ArrayList getAvailableSquares() {
        ArrayList moves = new ArrayList();
        for(int square = 0; square < numberOfSquares; square++) {
            if(squareIsAvailable(square))
                moves.add(square);
        }
        return moves;
    }
}
