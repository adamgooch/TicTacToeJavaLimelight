import java.util.ArrayList;
import java.util.Iterator;

/**
 * Author: Adam Gooch
 * Date: 7/16/12
 */
public class Board {
    public static final int NUMBER_OF_SQUARES = 9;
    public static final int BOARD_DIMENSION = 3;
    private char[][] gameBoard;

    public Board() {
        gameBoard = new char[BOARD_DIMENSION][BOARD_DIMENSION];
        for(int i = 0; i < NUMBER_OF_SQUARES; i++) {
            gameBoard[getRow(i)][getColumn(i)] = Character.forDigit(i, 9);
        }
    }

    public String asString() {
        return "\n " + gameBoard[0][0] + " | " + gameBoard[0][1] + " | " + gameBoard[0][2] + "\n" +
                "-----------\n" +
                 " " + gameBoard[1][0] + " | " + gameBoard[1][1] + " | " + gameBoard[1][2] + "\n" +
                "-----------\n" +
                 " " + gameBoard[2][0] + " | " + gameBoard[2][1] + " | " + gameBoard[2][2] + "\n\n";
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
                getMarkInSquare(square) != 'X' &&
                getMarkInSquare(square) != 'O')
            return true;
        return false;
    }

    public void removeMarkInSquare(int square) {
        int row = getRow(square);
        int column = getColumn(square);
        gameBoard[row][column] = Character.forDigit(square, 9);
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
        for(int i = 0; i < 9; i++) {
            int row = getRow(i);
            int col = getColumn(i);
            if(gameBoard[row][col] != 'X' &&
                    gameBoard[row][col] != 'O')
                moves.add((Integer)i);
        }
        return moves;
    }

    public int countSquaresAvailable() {
        int count = 0;
        for(int row = 0; row < 3; row++) {
            for(int col = 0; col < 3; col++) {
                if(gameBoard[row][col] != 'X' &&
                        gameBoard[row][col] != 'O')
                    count++;
            }
        }
        return count;
    }

    public Board(Board from, int square, char mark){
        gameBoard = new char[3][3];
        for(int y = 0; y < 3; y++){
            for(int x = 0; x < 3; x++){
                gameBoard[x][y] = from.gameBoard[x][y];
            }
        }
        gameBoard[getRow(square)][getColumn(square)] = mark;
    }


}
