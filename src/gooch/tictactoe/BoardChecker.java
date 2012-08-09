package gooch.tictactoe;

public class BoardChecker {
    private static final char NOBODY = 'N';

    private char winner;
    private Board gameBoard;
    private int[] winningSquares;
    private int[][] winningSets;

    public BoardChecker(Board board) {
        gameBoard = board;
        winningSquares = new int[gameBoard.dimension];
        if(board.dimension == 3) {
            winningSets = WinningSets.THREE_BY_THREE;
        } else if(board.dimension == 4) {
            winningSets = WinningSets.FOUR_BY_FOUR;
        }
    }

    public char getWinner() {
        if(!thereIsAWinner())
            winner = NOBODY;
        return winner;
    }

    public boolean thereIsAWinner() {
        if(checkWinningSets('X') || checkWinningSets('O'))
            return true;
        return false;
    }

    private boolean checkWinningSets(char mark) {
        for(int set = 0; set < winningSets.length; set++) {
            if(checkSetForWinner(set, mark)) {
                winner = mark;
                winningSquares = winningSets[set];
                return true;
            }
        }
        return false;
    }

    private boolean checkSetForWinner(int set, char mark) {
        boolean winner = true;
        for(int position = 0; position < winningSets[set].length; position++) {
            if(mark != gameBoard.getMarkInSquare(winningSets[set][position])) {
                winner = false;
                break;
            }
        }
        return winner;
    }

    public int[] getWinningSquares() {
        return winningSquares;
    }

    public boolean boardIsFull() {
        if(gameBoard.countSquaresAvailable() == 0)
            return true;
        else
            return false;
    }

}
