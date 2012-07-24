/**
 * Author: Adam Gooch
 * Date: 7/16/12
 */
public class Board {
    public static final int NUMBER_OF_SQUARES = 9;
    public static final int BOARD_DIMENSION = 3;
    public static int squaresTaken = 0;
    private char[][] gameBoard;

    public Board() {
        gameBoard = new char[BOARD_DIMENSION][BOARD_DIMENSION];
        for(int i = 0; i < NUMBER_OF_SQUARES; i++) {
            gameBoard[getRow(i)][getColumn(i)] = Character.forDigit(i, 9);
        }
    }

    public String asString() {
        return "\n " + gameBoard[0][0] + " | " + gameBoard[0][1] + " | " + gameBoard[0][2] + "\n" +
                 " " + gameBoard[1][0] + " | " + gameBoard[1][1] + " | " + gameBoard[1][2] + "\n" +
                 " " + gameBoard[2][0] + " | " + gameBoard[2][1] + " | " + gameBoard[2][2] + "\n\n";
    }

    public boolean putMarkInSquare(char mark, int square) {
        int row = getRow(square);
        int column = getColumn(square);
        if(squareIsValid(square)) {
            gameBoard[row][column] = mark;
            squaresTaken++;
            return true;
        }
        return false;
    }

    private boolean squareIsValid(int square) {
        if(square < NUMBER_OF_SQUARES &&
                getMarkInSquare(square) != 'X' &&
                getMarkInSquare(square) != 'O')
            return true;
        return false;
    }
    public void removeMarkInSquare(int square) {
        int row = getRow(square);
        int column = getColumn(square);
        gameBoard[row][column] = Character.forDigit(square, 9);
        squaresTaken--;
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
}
