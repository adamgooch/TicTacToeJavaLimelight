/**
 * Created with IntelliJ IDEA.
 * User: Tank
 * Date: 7/16/12
 * Time: 2:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class TicTacToeBoard {
    public static final int NUMBER_OF_SQUARES = 9;
    private char[][] gameBoard;

    public TicTacToeBoard() {
        gameBoard = new char[3][3];
        for(int i = 0; i < NUMBER_OF_SQUARES; i++) {
            gameBoard[getRow(i)][getColumn(i)] = Character.forDigit(i, 9);
        }
    }

    public String printBoard() {
        return "\n " + gameBoard[0][0] + " | " + gameBoard[0][1] + " | " + gameBoard[0][2] + "\n" +
                 " " + gameBoard[1][0] + " | " + gameBoard[1][1] + " | " + gameBoard[1][2] + "\n" +
                 " " + gameBoard[2][0] + " | " + gameBoard[2][1] + " | " + gameBoard[2][2] + "\n\n";
    }

    public boolean putMarkInSquare(char mark, int square) {
        int row = getRow(square);
        int column = getColumn(square);
        if(square < 9 && gameBoard[row][column] != 'X' && gameBoard[row][column] != 'O') {
            gameBoard[row][column] = mark;
            return true;
        }
        return false;
    }

    public char getMarkInSquare(int square) {
        return gameBoard[getRow(square)][getColumn(square)];
    }

    private int getRow(int square) {
        return square / 3;
    }

    private int getColumn(int square) {
        return square % 3;
    }

    public boolean winner() {
        for(int i = 0; i < 3; i++){
            if(winnerInRow(i) || winnerInColumn(i))
                return true;
        }
        if(winnerInDiagonal())
            return true;
        return false;
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
