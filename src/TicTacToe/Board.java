package TicTacToe;

/**
 * Author: Adam Gooch
 * Date: 7/16/12
 */
public class Board {
    public static final int NUMBER_OF_SQUARES = 9;
    public static final int BOARD_DIMENSION = 3;
    public static boolean winner = false;
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
        if(square < NUMBER_OF_SQUARES &&
                gameBoard[row][column] != 'X' &&
                gameBoard[row][column] != 'O') {
            gameBoard[row][column] = mark;
            checkForWinner();
            return true;
        }
        return false;
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
    // break out to another class
    private void checkForWinner() {
        for(int i = 0; i < BOARD_DIMENSION; i++){
            if(winnerInRow(i) || winnerInColumn(i))
                winner = true;
        }
        if(winnerInDiagonal())
            winner = true;
    }

    private boolean winnerInRow(int row) {
        if(gameBoard[row][0] == gameBoard[row][1] &&
                gameBoard[row][0] == gameBoard[row][2]) {
            return true;
        }
        return false;
    }

    private boolean winnerInColumn(int column) {
        if(gameBoard[0][column] == gameBoard[1][column] &&
                gameBoard[0][column] == gameBoard[2][column]) {
            return true;
        }
        return false;
    }

    private boolean winnerInDiagonal() {
        if(gameBoard[0][0] == gameBoard[1][1] &&
                gameBoard[1][1] == gameBoard[2][2])
            return true;
        if(gameBoard[0][2] == gameBoard[1][1] &&
                gameBoard[2][0] == gameBoard[1][1])
            return true;
        return false;
    }

}
