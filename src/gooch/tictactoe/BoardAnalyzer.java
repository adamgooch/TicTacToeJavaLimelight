package gooch.tictactoe;

import gooch.tictactoe.Board;

/**
 * Author: Adam Gooch
 * Date: 7/24/12
 */
public class BoardAnalyzer {
    private char winner;
    private Board gameBoard;

    public BoardAnalyzer(Board board) {
        gameBoard = board;
        winner = 'N';
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
        char pos1 = gameBoard.getMarkInSquare(getSquareNumber(row, 0));
        char pos2 = gameBoard.getMarkInSquare(getSquareNumber(row, 1));
        char pos3 = gameBoard.getMarkInSquare(getSquareNumber(row, 2));
        return helpCheckForWinner(pos1, pos2, pos3);
    }

    private boolean winnerInColumn(int column) {
        char pos1 = gameBoard.getMarkInSquare(getSquareNumber(0, column));
        char pos2 = gameBoard.getMarkInSquare(getSquareNumber(1, column));
        char pos3 = gameBoard.getMarkInSquare(getSquareNumber(2, column));
        return helpCheckForWinner(pos1, pos2, pos3);
    }

    private boolean winnerInDiagonalOne() {
        char pos1 = gameBoard.getMarkInSquare(getSquareNumber(0, 0));
        char pos2 = gameBoard.getMarkInSquare(getSquareNumber(1, 1));
        char pos3 = gameBoard.getMarkInSquare(getSquareNumber(2, 2));
        return helpCheckForWinner(pos1, pos2, pos3);
    }

    private boolean winnerInDiagonalTwo() {
        char pos1 = gameBoard.getMarkInSquare(getSquareNumber(0, 2));
        char pos2 = gameBoard.getMarkInSquare(getSquareNumber(1, 1));
        char pos3 = gameBoard.getMarkInSquare(getSquareNumber(2, 0));
        return helpCheckForWinner(pos1, pos2, pos3);
    }

    private boolean helpCheckForWinner(char pos1, char pos2, char pos3) {
        if( pos1 == pos2 && pos2 == pos3) {
            winner = pos1;
            return true;
        }
        return false;
    }

    private int getSquareNumber(int row, int column) {
        return row * Board.BOARD_DIMENSION + column;
    }

    public char getWinner() {
        thereIsAWinner();
        return winner;
    }

}
