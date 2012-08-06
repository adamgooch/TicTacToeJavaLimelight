package gooch.tictactoe;

import java.util.ArrayList;

public class BoardAnalyzer {
    private static final char NOBODY = 'N';
    private char winner;
    private Board gameBoard;
    private ArrayList<Integer> winningSquares;

    public BoardAnalyzer(Board board) {
        gameBoard = board;
        winner = NOBODY;
    }

    public boolean thereIsAWinner() {
        for(int i = 0; i < Board.BOARD_DIMENSION; i++){
            if(winnerInRow(i) || winnerInColumn(i))
                return true;
        }
        if(winnerInDiagonalOne() || winnerInDiagonalTwo())
            return true;
        return false;
    }

    private boolean winnerInRow(int row) {
        int pos1 = getSquareNumber(row, 0);
        int pos2 = getSquareNumber(row, 1);
        int pos3 = getSquareNumber(row, 2);
        return helpCheckForWinner(pos1, pos2, pos3);
    }

    private boolean winnerInColumn(int column) {
        int pos1 = getSquareNumber(0, column);
        int pos2 = getSquareNumber(1, column);
        int pos3 = getSquareNumber(2, column);
        return helpCheckForWinner(pos1, pos2, pos3);
    }

    private boolean winnerInDiagonalOne() {
        int pos1 = getSquareNumber(0, 0);
        int pos2 = getSquareNumber(1, 1);
        int pos3 = getSquareNumber(2, 2);
        return helpCheckForWinner(pos1, pos2, pos3);
    }

    private boolean winnerInDiagonalTwo() {
        int pos1 = getSquareNumber(0, 2);
        int pos2 = getSquareNumber(1, 1);
        int pos3 = getSquareNumber(2, 0);
        return helpCheckForWinner(pos1, pos2, pos3);
    }

    private boolean helpCheckForWinner(int pos1, int pos2, int pos3) {
        if( gameBoard.getMarkInSquare(pos1) == gameBoard.getMarkInSquare(pos2) &&
                gameBoard.getMarkInSquare(pos2) == gameBoard.getMarkInSquare(pos3)) {
            winner = gameBoard.getMarkInSquare(pos1);
            winningSquares = new ArrayList<Integer>();
            winningSquares.add(pos1);
            winningSquares.add(pos2);
            winningSquares.add(pos3);
            return true;
        }
        return false;
    }

    private int getSquareNumber(int row, int column) {
        return row * Board.BOARD_DIMENSION + column;
    }

    public char getWinner() {
        if(!thereIsAWinner())
            winner = NOBODY;
        return winner;
    }

    public ArrayList<Integer> getWinningSquares() {
        return winningSquares;
    }

}
