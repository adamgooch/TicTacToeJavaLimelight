/**
 * Author: Adam Gooch
 * Date: 7/24/12
 */
public class BoardAnalyzer {
    public static char winner;
    private static Board gameBoard;
    private int squareToWin;

    public BoardAnalyzer(Board board) {
        gameBoard = board;
        winner = 'n';
    }

    public int squareMarkCanWinIn(char mark) {
        if(markCanWin(mark))
            return squareToWin;
        return -1;
    }

    private boolean markCanWin(char mark) {
        for(int i = 0; i < 3; i++) {
            if(canWinInRow(mark, i))
                return true;
            if(canWinInCol(mark, i))
                return true;
        }
        if(canWinInDiagonalOne(mark) || canWinInDiagonalTwo(mark))
            return true;
        return false;
    }

    public int numberOfPossibleWins(char mark) {
        int possibleWins = 0;
        for(int i = 0; i < 3; i++){
            if(canWinInRow(mark, i))
                possibleWins++;
            if(canWinInCol(mark, i))
                possibleWins++;
        }
        if(canWinInDiagonalOne(mark) || canWinInDiagonalTwo(mark))
            possibleWins++;
        return possibleWins;
    }

    private boolean canWinInRow(char mark, int row) {
        int count = 0;
        for(int col = 0; col < 3; col++) {
            count += incOrDec(getSquare(row, col));
        }
        return someoneCanWin(mark, count);
    }

    private boolean canWinInCol(char mark, int col) {
        int count = 0;
        for(int row = 0; row < 3; row++) {
            count += incOrDec(getSquare(row, col));
        }
        return someoneCanWin(mark, count);
    }

    private boolean canWinInDiagonalOne(char mark) {
        int count = 0;
        for(int square = 0; square < 9; square += 4) {
            count = count + incOrDec(square);
        }
        return someoneCanWin(mark, count);

    }

    private boolean canWinInDiagonalTwo(char mark) {
        int count = 0;
        for(int square = 2; square < 8; square += 2) {
            count = count + incOrDec(square);
        }
        return someoneCanWin(mark, count);
    }

    private int incOrDec(int square) {
        if(gameBoard.getMarkInSquare(square) == 'O') {
            return 1;
        } else if(gameBoard.getMarkInSquare(square) == 'X') {
            return -1;
        } else {
            squareToWin = square;
            return 0;
        }
    }

    private boolean someoneCanWin(char mark, int count) {
        if(mark == 'X' && count == -2)
            return true;
        if(mark == 'O' && count == 2)
            return true;
        return false;
    }

    public static boolean thereIsAWinner() {
        for(int i = 0; i < Board.BOARD_DIMENSION; i++){
            if(winnerInRow(i) || winnerInColumn(i))
                return true;
        }
        if(winnerInDiagonalOne() || winnerInDiagonalTwo())
            return true;
        return false;
    }

    private static boolean winnerInRow(int row) {
        char pos1 = gameBoard.getMarkInSquare(getSquare(row, 0));
        char pos2 = gameBoard.getMarkInSquare(getSquare(row, 1));
        char pos3 = gameBoard.getMarkInSquare(getSquare(row, 2));
        return checkForWinner(pos1, pos2, pos3);
    }

    private static boolean winnerInColumn(int column) {
        char pos1 = gameBoard.getMarkInSquare(getSquare(0, column));
        char pos2 = gameBoard.getMarkInSquare(getSquare(1, column));
        char pos3 = gameBoard.getMarkInSquare(getSquare(2, column));
        return checkForWinner(pos1, pos2, pos3);
    }

    private static boolean winnerInDiagonalOne() {
        char pos1 = gameBoard.getMarkInSquare(getSquare(0, 0));
        char pos2 = gameBoard.getMarkInSquare(getSquare(1, 1));
        char pos3 = gameBoard.getMarkInSquare(getSquare(2, 2));
        return checkForWinner(pos1, pos2, pos3);
    }

    private static boolean winnerInDiagonalTwo() {
        char pos1 = gameBoard.getMarkInSquare(getSquare(0, 2));
        char pos2 = gameBoard.getMarkInSquare(getSquare(1, 1));
        char pos3 = gameBoard.getMarkInSquare(getSquare(2, 0));
        return checkForWinner(pos1, pos2, pos3);
    }

    private static boolean checkForWinner(char pos1, char pos2, char pos3) {
        if( pos1 == pos2 && pos2 == pos3) {
            winner = pos1;
            return true;
        }
        return false;
    }

    private static int getSquare(int row, int column) {
        return row * Board.BOARD_DIMENSION + column;
    }

}
