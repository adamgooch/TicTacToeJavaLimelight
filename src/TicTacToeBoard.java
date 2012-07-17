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
        for(int i = 0; i < NUMBER_OF_SQUARES; i++){
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

    private int getRow(int square){
        return square / 3;
    }

    private int getColumn(int square){
        return square % 3;
    }
}
